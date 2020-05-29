package com.cache.exampleissue;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IntermediateCountService
{
	private final CountJDBCTemplate countJDBCTemplate;

	public IntermediateCountService(CountJDBCTemplate countJDBCTemplate)
	{
		this.countJDBCTemplate = countJDBCTemplate;
	}

	public Count newCount()
	{
		return countJDBCTemplate.newCount();
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	public Count increment(long id)
	{
		final Count count = countJDBCTemplate.getCount(id);
		System.out.println("Count before increment: " + count.getCount());
		countJDBCTemplate.increment(id);
		final Count shouldBeIncremented = countJDBCTemplate.getCount(id);
		System.out.println("Count after increment:" + shouldBeIncremented.getCount());
		return shouldBeIncremented;
	}

	public Count get(long id)
	{
		return countJDBCTemplate.getCount(id);
	}
}
