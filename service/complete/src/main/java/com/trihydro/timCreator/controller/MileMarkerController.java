package com.trihydro.timCreator.controller;

import org.springframework.web.bind.annotation.RestController;
import com.trihydro.timCreator.dao.MileMarkerService;
import com.trihydro.timCreator.model.MileMarker;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin
@RestController
public class MileMarkerController {

	MileMarkerService mileMarkerService = new MileMarkerService();

	@RequestMapping(value="/milemarkers",method = RequestMethod.GET,headers="Accept=application/json")
  	public List<MileMarker> getMileMarkers() { 
   		List<MileMarker> mileMarkers = mileMarkerService.selectAll();
   		return mileMarkers;
  	}

  	@RequestMapping(method = RequestMethod.GET, value = "/getMileMarkerRange/{direction}/{startingMilepost}/{endingMilepost}")
  	public List<MileMarker> getMileMarkerRange(@PathVariable String direction, @PathVariable Integer startingMilepost, @PathVariable Integer endingMilepost) { 
   		List<MileMarker> mileMarkers = mileMarkerService.selectMileMarkerRange(direction, startingMilepost, endingMilepost);
   		return mileMarkers;
  	}
}
