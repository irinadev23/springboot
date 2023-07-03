package com.irina.otto.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.irina.otto.repository.IpRangesRepository;
import com.irina.otto.services.IpRangesService;
import com.irina.otto.exceptions.InvalidJsonPathException;
import com.irina.otto.exceptions.IprangeNotSavedException;
import com.irina.otto.model.IpRange;

@Component
public class IpRangesUtil {
	
	Logger log = LoggerFactory.getLogger(IpRangesUtil.class);

	private final IpRangesRepository repository;
	
	@Autowired
	public IpRangesUtil(IpRangesRepository repository) {
		this.repository = repository;
	}
	
	static String[] validRegionsArray = {"eu", "us", "ap", "cn", "sa", "af", "ca"};

	static List<String> validRegions = Arrays.asList(validRegionsArray);
	
	static String jsonPath = "https://ip-ranges.amazonaws.com/ip-ranges.json";
	

	public boolean checkValidRegion(String region) {
		return validRegions.contains(region.toLowerCase());
	}
	
	
	public void readJson() throws IOException, JSONException {
		JSONObject jsonRead;
		
		if (jsonPath != null && !jsonPath.isEmpty()) {
		    jsonRead = readJsonFromUrl(jsonPath);
		    log.info("!!! jsonRead = {}"+ jsonRead);
			JSONArray arr = jsonRead.getJSONArray("prefixes");
			for (int i = 0; i < arr.length(); i++)
			{ 
			    String ipPrefix = arr.getJSONObject(i).getString("ip_prefix");
			    String region = arr.getJSONObject(i).getString("region");
			    String service = arr.getJSONObject(i).getString("service");
			    String networkBorderGroup = arr.getJSONObject(i).getString("network_border_group");
			    
			    IpRange range = new IpRange(ipPrefix,region,service, networkBorderGroup);
			    
			    try {
			  		repository.save(range);
				} catch (Exception e) {
					throw new IprangeNotSavedException("Ip ranges could not be saved!"+range);					
									}
			}
		}
		else throw new InvalidJsonPathException("Ip ranges json path is invalid!");
	}
	

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      return json;
	    } finally {
	      is.close();
	    }
	}


	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }
}
