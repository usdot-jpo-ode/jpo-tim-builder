package com.trihydro.timCreator.controller;

import org.springframework.web.bind.annotation.RestController;
import com.trihydro.timCreator.dao.TIMService;
import com.trihydro.timCreator.dao.DataFrameService;
import com.trihydro.timCreator.dao.TIMRSUService;
import com.trihydro.timCreator.dao.RegionService;
import com.trihydro.timCreator.dao.PathService;
import com.trihydro.timCreator.dao.NodeXYService;
import com.trihydro.timCreator.dao.DataFrameItisCodeService;
import com.trihydro.timCreator.dao.PathNodeXYService;
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

    // services
    TIMService timService = new TIMService();
    DataFrameService dataFrameService = new DataFrameService();
    TIMRSUService timRsuService = new TIMRSUService();
    RegionService regionService = new RegionService();  
    DataFrameItisCodeService dataFrameItisCodeService = new DataFrameItisCodeService();
    PathService pathService = new PathService();
    NodeXYService nodeXYService = new NodeXYService();
    PathNodeXYService pathNodeXYService = new PathNodeXYService();

    @RequestMapping(value="/sendTim", method = RequestMethod.POST, headers="Accept=application/json")
    public ResponseEntity<?> sendTim(@RequestBody SubmittedTIM submittedTim) { 

        // insert tim	
        Long timId = timService.insertTIM(submittedTim.getTIM());
        System.out.println("tim id: " + timId);

        Long dataFrameId;
        Long pathId = null;
        Long nodeXYId = null;

        // for each data frames 
        for(int i = 0; i < submittedTim.getTIM().getdataframes().length; i++) {
            // insert data frame  
            dataFrameId = dataFrameService.insertDataFrame(submittedTim.getTIM().getdataframes()[i], timId); 
            // for each region region
            for(int j = 0; j < submittedTim.getTIM().getdataframes()[i].getRegions().length; j++){
                // if region has path
                if(submittedTim.getTIM().getdataframes()[i].getRegions()[j].getPath() != null){         
                    // insert path
                    pathId = pathService.insertPath(submittedTim.getTIM().getdataframes()[i].getRegions()[j].getPath());
                    // for each node in path
                    for(int k = 0; k < submittedTim.getTIM().getdataframes()[i].getRegions()[j].getPath().getNodes().length; k++){
                        nodeXYId = nodeXYService.insertNodeXY(submittedTim.getTIM().getdataframes()[i].getRegions()[j].getPath().getNodes()[k]);
                        pathNodeXYService.insertPathNodeXY(pathId, nodeXYId);
                    }   
                }
                // insert region
                regionService.insertRegion(submittedTim.getTIM().getdataframes()[i].getRegions()[j], dataFrameId, new Long(0), pathId);    
            }
            dataFrameItisCodeService.insertDataFrameItisCode(dataFrameId, submittedTim.getTIM().getdataframes()[i]); 
        }

        // insert tim rsus
        timRsuService.insertTIMRSUs(submittedTim, timId);    

        URI location = ServletUriComponentsBuilder
        .fromCurrentRequest().path("/{id}")
        .buildAndExpand(submittedTim.getTIM().getTimId())
        .toUri();

        return ResponseEntity.created(location).build();   		
    }
}
