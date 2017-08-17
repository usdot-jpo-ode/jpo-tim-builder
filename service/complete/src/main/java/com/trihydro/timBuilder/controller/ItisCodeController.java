package com.trihydro.timBuilder.controller;

import org.springframework.web.bind.annotation.RestController;
import com.trihydro.timBuilder.dao.ItisCodeService;
import com.trihydro.timBuilder.model.ItisCode;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;

@CrossOrigin
@RestController
public class ItisCodeController {

	private final ItisCodeService itisCodeService;
	
	@Autowired
	ItisCodeController(ItisCodeService itisCodeService) {
		this.itisCodeService = itisCodeService;
	}

	// select all ITIS codes
	@RequestMapping(value="/itiscodes",method = RequestMethod.GET,headers="Accept=application/json")
  	public List<ItisCode> selectAllItisCodes() { 
   		List<ItisCode> itisCodes = itisCodeService.selectAll();
   		return itisCodes;
  	}
}
