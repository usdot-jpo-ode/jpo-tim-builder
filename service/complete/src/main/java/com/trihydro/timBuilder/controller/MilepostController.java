package com.trihydro.timBuilder.controller;

import org.springframework.web.bind.annotation.RestController;
import com.trihydro.timBuilder.dao.MilepostService;
import com.trihydro.timBuilder.model.Milepost;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

@CrossOrigin
@RestController
public class MilepostController {

	private final MilepostService milepostService;
	
	@Autowired
	MilepostController(MilepostService milepostService) 
	{
		this.milepostService = milepostService;
	}

	@RequestMapping(value="/mileposts",method = RequestMethod.GET,headers="Accept=application/json")
	public List<Milepost> getMileposts() { 
 		List<Milepost> mileposts = milepostService.selectAll();
 		return mileposts;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getMilepostRange/{direction}/{startingMilepost}/{endingMilepost}")
	public List<Milepost> getMilepostRange(@PathVariable String direction, @PathVariable Integer startingMilepost, @PathVariable Integer endingMilepost) { 
 		List<Milepost> mileposts = milepostService.selectMilepostRange(direction, startingMilepost, endingMilepost);
 		return mileposts;
	}
}
