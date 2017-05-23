package com.trihydro.timCreator.controller;

import org.springframework.web.bind.annotation.RestController;
import com.trihydro.timCreator.dao.ItisCodeService;
import com.trihydro.timCreator.model.ItisCode;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
public class ItisCodeController {

	ItisCodeService itisCodeService = new ItisCodeService();

	@RequestMapping(value="/itiscodes",method = RequestMethod.GET,headers="Accept=application/json")
  	public List<ItisCode> selectAllRsus() { 
   		List<ItisCode> itisCodes = itisCodeService.selectAll();
   		return itisCodes;
  	}

}
