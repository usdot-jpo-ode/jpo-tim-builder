package com.trihydro.timCreator.controller;

import org.springframework.web.bind.annotation.RestController;
import com.trihydro.timCreator.dao.TIMService;
import com.trihydro.timCreator.model.NodeXY;
import com.trihydro.timCreator.model.Region;
import com.trihydro.timCreator.model.SubmittedTIM;
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
            // if there is at least one region
            if(submittedTim.getTIM().getdataframes()[i].getRegions() != null){
            	// for each region region
	            for(Region region : submittedTim.getTIM().getdataframes()[i].getRegions()){
	                // if region has path
	                if(region.getPath() != null){ 
	                	if(region.getPath().getComputedLane() != null){
	                		computedLaneId = computedLaneService.insertComputedLane(region.getPath().getComputedLane());
	                	}
	                    // insert path
	                    pathId = pathService.insertPath(region.getPath(), computedLaneId);               
	                    // for each node in path
	                    if(region.getPath().getNodes() != null){
		                    for(NodeXY node : region.getPath().getNodes()){
		                        nodeXYId = nodeXYService.insertNodeXY(node);
		                        pathNodeXYService.insertPathNodeXY(pathId, nodeXYId);
		                        // attributes
		                        if(node.getAttributes() != null){
		                        	// local nodes;
		                        	for( int l = 0; l < node.getAttributes().getLocalNodes().length; l++){
		                        		localNodeService.insertLocalNode(node.getAttributes().getLocalNodes()[l], nodeXYId);
		                        	}
		                        	// disabled lists
		                        	for( int l = 0; l < node.getAttributes().getDisabledLists().length; l++){
		                        		disabledListService.insertDisabledList(node.getAttributes().getDisabledLists()[l], nodeXYId);
		                        	}
		                        	// enabled lists
		                        	for( int l = 0; l < node.getAttributes().getEnabledLists().length; l++){
		                        		enabledListService.insertEnabledList(node.getAttributes().getEnabledLists()[l], nodeXYId);
		                        	}
		                        	// data lists
		                        	for( int l = 0; l < node.getAttributes().getDataLists().length; l++){
		                        		dataFrameId = dataListService.insertDataList(node.getAttributes().getDataLists()[l], nodeXYId);
		                        		// speed limits
		                        		for(int m = 0; m < node.getAttributes().getDataLists()[l].getSpeedLimits().length; m++){
		                        			speedLimitService.insertSpeedLimit(node.getAttributes().getDataLists()[l].getSpeedLimits()[m], dataListId);
		                        		}                        		
		                        	}                                                 
		                        }
		                    }   
	                    }
	                }
	                // if region has old region
	                if(region.getOldRegion() != null){
	                	if(region.getOldRegion().getShapepoint() != null){
	                		shapePointId = shapePointService.insertShapePoint(region.getOldRegion().getShapepoint(), computedLaneId);
	                		shapePointNodeXYService.insertShapePointNodeXY(shapePointId, nodeXYId);
	                	}
	                	oldRegionId = oldRegionService.insertOldRegion(region.getOldRegion(), shapePointId);
	                	for(int n = 0; n < region.getOldRegion().getRegionPoint().getRegionList().length; n++){
	                		regionListService.insertRegionList(region.getOldRegion().getRegionPoint().getRegionList()[n], oldRegionId);
	                	}
	                }
	                // insert region
	                regionService.insertRegion(region, dataFrameId, oldRegionId, pathId);    
	            }
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
