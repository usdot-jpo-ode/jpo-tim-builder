package com.trihydro.timBuilder.controller;

import org.springframework.web.bind.annotation.RestController;
import com.trihydro.timBuilder.dao.RSUService;
import com.trihydro.timBuilder.model.RSU;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.net.URI;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;

@CrossOrigin
@RestController
public class RSUController {

	private final RSUService rsuService;
	
	@Autowired
	RSUController(RSUService rsuService) 
	{
		this.rsuService = rsuService;
	}

	@RequestMapping(value="/rsus", method = RequestMethod.GET, headers="Accept=application/json")
	public List<RSU> selectAllRsus() { 
 		List<RSU> rsus = rsuService.selectAll();
 		return rsus;      
	}
}
