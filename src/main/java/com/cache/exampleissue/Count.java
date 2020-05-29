package com.cache.exampleissue;

public class Count
{
	private long id;

	private long count;

	public Count(long id, long count)
	{
		this.id = id;
		this.count = count;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getCount()
	{
		return count;
	}

	public void setCount(long count)
	{
		this.count = count;
	}
}
