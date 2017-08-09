package com.trihydro.timCreator.controller;

import org.springframework.web.bind.annotation.RestController;
import com.trihydro.timCreator.dao.RSUService;
import com.trihydro.timCreator.model.RSU;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.net.URI;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
public class RSUController {

	RSUService rsuService = new RSUService();

	@RequestMapping(value="/rsus", method = RequestMethod.GET, headers="Accept=application/json")
	public List<RSU> selectAllRsus() { 
 		List<RSU> rsus = rsuService.selectAll();
 		return rsus;      
	}
}
