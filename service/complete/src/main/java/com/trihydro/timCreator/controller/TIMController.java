package com.trihydro.timCreator.controller;

import org.springframework.web.bind.annotation.RestController;
import com.trihydro.timCreator.dao.TIMService;
import com.trihydro.timCreator.model.NodeXY;
import com.trihydro.timCreator.model.Region;
import com.trihydro.timCreator.model.SubmittedTIM;
import com.trihydro.timCreator.model.LocalNode;
import com.trihydro.timCreator.model.DisabledList;
import com.trihydro.timCreator.model.EnabledList;
import com.trihydro.timCreator.model.DataList;
import com.trihydro.timCreator.model.SpeedLimits;
import com.trihydro.timCreator.model.RegionList;
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
		                        	addNodeAttributes(node, nodeXYId);	                        	                                            
		                        }
		                    }   
	                    }
	                }
	                // if region has old region
	                if(region.getOldRegion() != null){
	                	if(region.getOldRegion().getShapePoint() != null){
	                		if(region.getOldRegion().getShapePoint().getComputedLane() != null){
	                			computedLaneId = computedLaneService.insertComputedLane(region.getOldRegion().getShapePoint().getComputedLane());
	                		}
	                		shapePointId = shapePointService.insertShapePoint(region.getOldRegion().getShapePoint(), computedLaneId);
	                		if(region.getOldRegion().getShapePoint().getNodexy() != null){
	                			for(NodeXY node: region.getOldRegion().getShapePoint().getNodexy()){
	                				nodeXYId = nodeXYService.insertNodeXY(node);
	                				shapePointNodeXYService.insertShapePointNodeXY(shapePointId, nodeXYId);
	                				// attributes
			                        if(node.getAttributes() != null){
			                        	addNodeAttributes(node, nodeXYId);	                        	                                            
			                        }
	                			}
	                		}	                		
	                	}
	                	oldRegionId = oldRegionService.insertOldRegion(region.getOldRegion(), shapePointId);
	                	if(region.getOldRegion().getRegionPoint().getRegionList() != null){
	                		for(RegionList regionList: region.getOldRegion().getRegionPoint().getRegionList()){
	                			regionListService.insertRegionList(regionList, oldRegionId);
	                		}
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
    
    protected void addNodeAttributes(NodeXY node, Long nodeXYId){    
    	Long dataListId = null;
    	
    	// local nodes;
    	if(node.getAttributes().getLocalNodes() != null){
        	for(LocalNode localNode : node.getAttributes().getLocalNodes()){
        		localNodeService.insertLocalNode(localNode, nodeXYId);
        	}
    	}
    	// disabled lists
    	if(node.getAttributes().getDisabledLists() != null){
        	for(DisabledList disabledList : node.getAttributes().getDisabledLists()){
        		disabledListService.insertDisabledList(disabledList, nodeXYId);
        	}
    	}		                        
    	// enabled lists
    	if(node.getAttributes().getEnabledLists() != null){
        	for(EnabledList enabledList : node.getAttributes().getEnabledLists()){
        		enabledListService.insertEnabledList(enabledList, nodeXYId);
        	}
    	}	
    	// data lists
    	if(node.getAttributes().getDataLists() != null){
        	for(DataList dataList : node.getAttributes().getDataLists()){
        		dataListId = dataListService.insertDataList(dataList, nodeXYId);
        		// speed limits
        		if(dataList.getSpeedLimits() != null){
        			for(SpeedLimits speedLimit : dataList.getSpeedLimits()){
        				speedLimitService.insertSpeedLimit(speedLimit, dataListId);
        			}			                        	
        		}
        	}
    	}
    }
}
