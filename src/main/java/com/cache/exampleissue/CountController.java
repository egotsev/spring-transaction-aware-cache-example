package com.cache.exampleissue;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/counts")
public class CountController
{
	private final IntermediateCountService countService;

	public CountController(IntermediateCountService countService)
	{
		this.countService = countService;
	}

	@PostMapping
	public Count create()
	{
		return countService.newCount();
	}

	@GetMapping("/{id}")
	public Count get(@PathVariable long id)
	{
		return countService.get(id);
	}


	@PostMapping("/{id}/increment")
	public Count increment(@PathVariable long id)
	{
		return countService.increment(id);
	}
}
