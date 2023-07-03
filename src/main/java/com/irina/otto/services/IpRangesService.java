package com.irina.otto.services;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.irina.otto.util.IpRangesUtil;
import com.irina.otto.exceptions.ProblemReadingJsonException;
import com.irina.otto.model.IpRange;
import com.irina.otto.repository.IpRangesRepository;


@Component
public class IpRangesService {
	private final IpRangesRepository repository;
	private final IpRangesUtil util;
	
	Logger log = LoggerFactory.getLogger(IpRangesService.class);
	
	@Autowired
	public IpRangesService(IpRangesRepository repository, IpRangesUtil util) {
		this.repository = repository;
		this.util = util;
	}
	
	@EventListener
    public void appReady(ApplicationReadyEvent event) {

        try {
        	log.info("!!! ApplicationReadyEvent, reading json");
			util.readJson();
		} catch (JSONException | IOException e) {
			throw new ProblemReadingJsonException("There was a pb reading the AWS json!");
		}
    }
	
	
	public List<IpRange> getByRegion(String region) {
	
		return  repository.getByRegion(region.toLowerCase());	
		
	}
	
    public List<IpRange> getAll() {
		
    	return repository.findAll();
    }
	

}
