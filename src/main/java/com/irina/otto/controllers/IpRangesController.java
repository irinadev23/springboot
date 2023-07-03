package com.irina.otto.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.irina.otto.exceptions.InvalidRegionException;
import com.irina.otto.model.IpRange;
import com.irina.otto.services.IpRangesService;
import com.irina.otto.util.IpRangesUtil;

@RestController
@RequestMapping("/otto")
public class IpRangesController {
	private final IpRangesService service;
	private final IpRangesUtil util;

	@Autowired
	public IpRangesController(IpRangesService service, IpRangesUtil util) {
		this.service = service;
		this.util = util;
	}

	@GetMapping(value = "/ipranges")
	String plaintext (@RequestParam("region") String region, HttpServletResponse response) {
	//	log.info("region = {}", region);
		 response.setContentType("text/plain");
		 response.setCharacterEncoding("UTF-8");
			 
		if (util.checkValidRegion(region))
			return service.getByRegion(region).stream().map(e -> e.toString()).reduce("", String::concat);
		else if (region.equalsIgnoreCase("ALL"))
			return service.getAll().stream().map(e -> e.toString()).reduce("\t", String::concat);
		else throw new InvalidRegionException("Region is invalid!");
	}


}
