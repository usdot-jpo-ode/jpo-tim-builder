package com.trihydro.timCreator.controller;

import org.springframework.web.bind.annotation.RestController;
import com.trihydro.timCreator.dao.MilepostService;
import com.trihydro.timCreator.model.Milepost;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin
@RestController
public class MilepostController {

	MilepostService milepostService = new MilepostService();

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
