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
import com.trihydro.timCreator.dao.ComputedLaneService;
import com.trihydro.timCreator.dao.LocalNodeService;
import com.trihydro.timCreator.dao.DisabledListService;
import com.trihydro.timCreator.dao.EnabledListService;
import com.trihydro.timCreator.dao.DataListService;
import com.trihydro.timCreator.dao.SpeedLimitService;
import com.trihydro.timCreator.dao.OldRegionService;
import com.trihydro.timCreator.dao.ShapePointService;
import com.trihydro.timCreator.dao.RegionListService;
import com.trihydro.timCreator.dao.ShapePointNodeXYService;
import com.trihydro.timCreator.model.SubmittedTIM;
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
    ComputedLaneService computedLaneService = new ComputedLaneService();
    LocalNodeService localNodeService = new LocalNodeService();
    DisabledListService disabledListService = new DisabledListService();
    EnabledListService enabledListService = new EnabledListService();
    DataListService dataListService = new DataListService();
    SpeedLimitService speedLimitService = new SpeedLimitService();
    OldRegionService oldRegionService = new OldRegionService();
    ShapePointService shapePointService = new ShapePointService();
    RegionListService regionListService = new RegionListService();
    ShapePointNodeXYService shapePointNodeXYService = new ShapePointNodeXYService();
    
    @RequestMapping(value="/sendTim", method = RequestMethod.POST, headers="Accept=application/json")
    public ResponseEntity<?> sendTim(@RequestBody SubmittedTIM submittedTim) { 

        // insert tim	
        Long timId = timService.insertTIM(submittedTim.getTIM());
        System.out.println("tim id: " + timId);

        Long dataFrameId;
        Long pathId = null;
        Long nodeXYId = null;
        Long computedLaneId = null;
        Long dataListId = null;
        Long oldRegionId = null;
        Long shapePointId = null;
        
        // for each data frames 
        for(int i = 0; i < submittedTim.getTIM().getdataframes().length; i++) {
            // insert data frame  
            dataFrameId = dataFrameService.insertDataFrame(submittedTim.getTIM().getdataframes()[i], timId); 
            // for each region region
            for(int j = 0; j < submittedTim.getTIM().getdataframes()[i].getRegions().length; j++){
                // if region has path
                if(submittedTim.getTIM().getdataframes()[i].getRegions()[j].getPath() != null){ 
                	if(submittedTim.getTIM().getdataframes()[i].getRegions()[j].getPath().getComputedLane() != null){
                		computedLaneId = computedLaneService.insertComputedLane(submittedTim.getTIM().getdataframes()[i].getRegions()[j].getPath().getComputedLane());
                	}
                    // insert path
                    pathId = pathService.insertPath(submittedTim.getTIM().getdataframes()[i].getRegions()[j].getPath(), computedLaneId);               
                    // for each node in path
                    for(int k = 0; k < submittedTim.getTIM().getdataframes()[i].getRegions()[j].getPath().getNodes().length; k++){
                        nodeXYId = nodeXYService.insertNodeXY(submittedTim.getTIM().getdataframes()[i].getRegions()[j].getPath().getNodes()[k]);
                        pathNodeXYService.insertPathNodeXY(pathId, nodeXYId);
                        // attributes
                        if(submittedTim.getTIM().getdataframes()[i].getRegions()[j].getPath().getNodes()[k].getAttributes() != null){
                        	// local nodes;
                        	for( int l = 0; l < submittedTim.getTIM().getdataframes()[i].getRegions()[j].getPath().getNodes()[k].getAttributes().getLocalNodes().length; l++){
                        		localNodeService.insertLocalNode(submittedTim.getTIM().getdataframes()[i].getRegions()[j].getPath().getNodes()[k].getAttributes().getLocalNodes()[l], nodeXYId);
                        	}
                        	// disabled lists
                        	for( int l = 0; l < submittedTim.getTIM().getdataframes()[i].getRegions()[j].getPath().getNodes()[k].getAttributes().getDisabledLists().length; l++){
                        		disabledListService.insertDisabledList(submittedTim.getTIM().getdataframes()[i].getRegions()[j].getPath().getNodes()[k].getAttributes().getDisabledLists()[l], nodeXYId);
                        	}
                        	// enabled lists
                        	for( int l = 0; l < submittedTim.getTIM().getdataframes()[i].getRegions()[j].getPath().getNodes()[k].getAttributes().getEnabledLists().length; l++){
                        		enabledListService.insertEnabledList(submittedTim.getTIM().getdataframes()[i].getRegions()[j].getPath().getNodes()[k].getAttributes().getEnabledLists()[l], nodeXYId);
                        	}
                        	// data lists
                        	for( int l = 0; l < submittedTim.getTIM().getdataframes()[i].getRegions()[j].getPath().getNodes()[k].getAttributes().getDataLists().length; l++){
                        		dataFrameId = dataListService.insertDataList(submittedTim.getTIM().getdataframes()[i].getRegions()[j].getPath().getNodes()[k].getAttributes().getDataLists()[l], nodeXYId);
                        		// speed limits
                        		for(int m = 0; m < submittedTim.getTIM().getdataframes()[i].getRegions()[j].getPath().getNodes()[k].getAttributes().getDataLists()[l].getSpeedLimits().length; m++){
                        			speedLimitService.insertSpeedLimit(submittedTim.getTIM().getdataframes()[i].getRegions()[j].getPath().getNodes()[k].getAttributes().getDataLists()[l].getSpeedLimits()[m], dataListId);
                        		}                        		
                        	}                                                 
                        }
                    }   
                }
                // if region has old region
                if(submittedTim.getTIM().getdataframes()[i].getRegions()[j].getOldRegion() != null){
                	if(submittedTim.getTIM().getdataframes()[i].getRegions()[j].getOldRegion().getShapepoint() != null){
                		shapePointId = shapePointService.insertShapePoint(submittedTim.getTIM().getdataframes()[i].getRegions()[j].getOldRegion().getShapepoint(), computedLaneId);
                		shapePointNodeXYService.insertShapePointNodeXY(shapePointId, nodeXYId);
                	}
                	oldRegionId = oldRegionService.insertOldRegion(submittedTim.getTIM().getdataframes()[i].getRegions()[j].getOldRegion(), shapePointId);
                	for(int n = 0; n < submittedTim.getTIM().getdataframes()[i].getRegions()[j].getOldRegion().getRegionPoint().getRegionList().length; n++){
                		regionListService.insertRegionList(submittedTim.getTIM().getdataframes()[i].getRegions()[j].getOldRegion().getRegionPoint().getRegionList()[n], oldRegionId);
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
