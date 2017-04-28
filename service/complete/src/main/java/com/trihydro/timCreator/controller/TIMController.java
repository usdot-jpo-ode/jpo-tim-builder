package com.trihydro.timCreator.controller;

import org.springframework.web.bind.annotation.RestController;
import com.trihydro.timCreator.dao.TIMService;
import com.trihydro.timCreator.dao.DataFrameService;
import com.trihydro.timCreator.dao.TIMRSUService;
import com.trihydro.timCreator.model.SubmittedTIM;
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
public class TIMController {

	TIMService timService = new TIMService();
  DataFrameService dataFrameService = new DataFrameService();
  TIMRSUService timRsuService = new TIMRSUService();

  @RequestMapping(value="/sendTim", method = RequestMethod.POST, headers="Accept=application/json")
  public ResponseEntity<?> sendTim(@RequestBody SubmittedTIM submittedTim) { 
  		
   	Long timId = timService.insertTIM(submittedTim.getTIM());

    System.out.println("tim id: " + timId);
    // insert data frames 
    dataFrameService.insertDataFrames(submittedTim.getTIM().getdataframes(), timId);    
    // insert tim rsus
    timRsuService.insertTIMRSUs(submittedTim, timId);    

  	URI location = ServletUriComponentsBuilder
  						.fromCurrentRequest().path("/{id}")
  						.buildAndExpand(submittedTim.getTIM().getTimId())
  						.toUri();
       
  	return ResponseEntity.created(location).build();   		
  }

}
