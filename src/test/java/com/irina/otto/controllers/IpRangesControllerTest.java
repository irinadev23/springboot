package com.irina.otto.controllers;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.irina.otto.services.IpRangesService;
import com.irina.otto.util.IpRangesUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(IpRangesController.class)
public class IpRangesControllerTest {
	 	@Autowired
	    private MockMvc mvc;

	    @MockBean
	    private IpRangesService service;
	    
	    @MockBean
	    private IpRangesUtil util;

	    @Test
	    public void givenIpranges_whenGetIpRanges_thenReturnJsonArray()
	      throws Exception {
	        
	     //   List<IpRange> allIpRanges = service.getAll();

	    //    given(service.getAll()).willReturn(allIpRanges);

	        mvc.perform(get("/api/ipranges?region=ALL")
	          .contentType(MediaType.TEXT_PLAIN));
	         // .andExpect(status().isOk());
	         // .andExpect(jsonPath("$", hasSize(1)))
	         // .andExpect(jsonPath("$[0].name", is(alex.getName())));
	    }
}
