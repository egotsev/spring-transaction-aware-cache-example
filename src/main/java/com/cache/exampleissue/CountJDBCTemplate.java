package com.cache.exampleissue;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Map;

@Repository
public class CountJDBCTemplate extends NamedParameterJdbcDaoSupport
{
	public CountJDBCTemplate(DataSource dataSource)
	{
		setDataSource(dataSource);
	}

	@CachePut(cacheNames = "counts", key = "#result.id")
	public Count newCount()
	{
		GeneratedKeyHolder holder = new GeneratedKeyHolder();
		getNamedParameterJdbcTemplate().update("insert into counts(count) values (0)",
											   new MapSqlParameterSource(),
											   holder);
		return new Count(holder.getKey().longValue(), 0L);
	}

	@CacheEvict("counts")
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Count increment(long id)
	{
		getNamedParameterJdbcTemplate().update("update counts set count = count + 1 where id = :id",
											   Map.of("id", id));
		return getCount(id);
	}

	@Cacheable("counts")
	public Count getCount(long id)
	{
		final long count = getNamedParameterJdbcTemplate().queryForObject("select count from counts where id = :id",
																		  Map.of("id", id),
																		  Long.class);
		return new Count(id, count);
	}
}
