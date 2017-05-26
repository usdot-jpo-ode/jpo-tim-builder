import { Component, OnInit } from '@angular/core';
import { Tim } from '../../classes/tim';
import { RSU } from '../../classes/rsu';
import { TimMessage } from '../../classes/tim-message';
import { TimSample } from '../../classes/tim-sample';
import { DataFrame } from '../../classes/data-frame';
import { ItisCode } from '../../classes/itis-code';
import { Region } from '../../classes/region';
import { OldRegion } from '../../classes/old-region';
import { J2735Position3D } from '../../classes/J2735-Position-3D';
import { Geometry } from '../../classes/geometry';
import { Path } from '../../classes/path';
import { ComputedLane } from '../../classes/computed-lane';
import { Circle } from '../../classes/circle';
import { SNMP } from '../../classes/snmp';
import { TimCreatorService } from '../../services/tim-creator.service';
import { RSUService } from '../../services/rsu.service';
import { ItisCodeService } from '../../services/itis-code.service';
import { Response } from '@angular/http';
import { NodeXY } from '../../classes/node-xy';
import { Attributes } from '../../classes/attributes';
import { LocalNode } from '../../classes/local-node';
import { DisabledList } from '../../classes/disabled-list';
import { EnabledList } from '../../classes/enabled-list';
import { DataList } from '../../classes/data-list';
import { SpeedLimits } from '../../classes/speed-limits';
import { RegionPoint } from '../../classes/region-point';
import { ShapePoint } from '../../classes/shape-point';
import { RegionList } from '../../classes/region-list';
 
@Component({
	selector: 'tc-home',   
	templateUrl: './home.component.html',
	providers: [TimCreatorService, RSUService, ItisCodeService]
})
export class HomeComponent implements OnInit{

	tim: TimMessage;
	itisCodes: ItisCode[];
	testJSON: string;
	df: DataFrame;
	rsuData: RSU[];	
	errorMessage: string = '';
	isLoading: boolean = true;
	selectedItisCodeId: number;

   	constructor(private timCreatorService : TimCreatorService, private rsuService: RSUService, private itisCodeService: ItisCodeService){ }

	ngOnInit(){			
		this.tim = new TimMessage();
		this.df = new DataFrame();

		this.rsuService.getAll().subscribe(
		 /* happy path */ r => this.rsuData = r,
         /* error path */ e => this.errorMessage = e,
         /* onComplete */ () => { this.isLoading = false; console.log(this.rsuData); } 
		);

		this.itisCodeService.getAll().subscribe(
		 /* happy path */ i => this.itisCodes = i,
         /* error path */ e => this.errorMessage = e,
         /* onComplete */ () => { this.isLoading = false; console.log(this.itisCodes); } 
		);
	}

	checkChanged(e){
		for(let r of this.rsuData){  			
			if(r.rsuTarget == e.target.name){ 
				if(e.target.checked)
					r.isSelected = true;			
				else
					r.isSelected = false;
			}
		}
	}

	submitSuperTIM(){

		console.log(this.df.startDateTime);


		let timSample = new TimSample();
		let tim = new Tim();
		tim.msgCnt = "13";
		
		var today = new Date();
		tim.timeStamp = today.getFullYear() + "-" + (today.getMonth()+1) + "-" + today.getDate() + " " + today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
		tim.packetID = "1";
		tim.urlB = "urlb";

		// dataframe 
		this.df.sspTimRights = "1";
		this.df.frameType = "1";
		this.df.msgID = "RoadSignID";
		this.df.position = new J2735Position3D();
		this.df.position.latitude = "41.678473";
		this.df.position.longitude = "-108.782775";
		this.df.position.elevation = "917.1432";
		this.df.viewAngle = "1010101010101010";
		this.df.mutcd = "5";
		this.df.crc = "0000000000000000";
		this.df.priority = "2";
		this.df.sspLocationRights = "0";
		this.df.regions = [];
		this.df.furtherInfoID = "test";
		this.df.sspMsgTypes = "2"; 
		this.df.sspMsgContent = "3";
		this.df.content = "Advisory";
		this.df.url = "null";
	
		this.df.regions = [];

		// first region - geometry
		let regionG = new Region();
		regionG.name = "TRIHYDRO TEST";
		regionG.regulatorID = "23";
		regionG.segmentID = "33";
		regionG.anchorPosition = new J2735Position3D();
		regionG.anchorPosition.latitude = "41.678473";
		regionG.anchorPosition.longitude = "-108.782775";
		regionG.anchorPosition.elevation = "917.1432";
		regionG.laneWidth = "7";
		regionG.directionality = "3";
		regionG.closedPath = "false";		
		regionG.direction = "1010101010101010";
		regionG.regionType = "region type";
		regionG.description = "geometry";
		regionG.geometry = new Geometry();
		regionG.geometry.direction = "1010101010101010";
		regionG.geometry.extent = "1";
		regionG.geometry.laneWidth = "33";
		regionG.geometry.circle = new Circle();
		regionG.geometry.circle.position = new J2735Position3D();
		regionG.geometry.circle.position.latitude = "41.678473";
		regionG.geometry.circle.position.longitude = "-108.782775";
		regionG.geometry.circle.position.elevation = "917.1432";
		regionG.geometry.circle.radius = "15";
		regionG.geometry.circle.units = "7";
		this.df.regions.push(regionG);

		// first region - path
		let regionP = new Region();
		regionP.name = "TRIHYDRO TEST";
		regionP.regulatorID = "23";
		regionP.segmentID = "33";
		regionP.anchorPosition = new J2735Position3D();
		regionP.anchorPosition.latitude = "41.678473";
		regionP.anchorPosition.longitude = "-108.782775";
		regionP.anchorPosition.elevation = "917.1432";
		regionP.laneWidth = "7";
		regionP.directionality = "3";
		regionP.closedPath = "false";		
		regionP.direction = "1010101010101010";
		regionP.regionType = "region type";
		regionP.description = "path";
		regionP.path = new Path();
		regionP.path.scale = "25";
		regionP.path.type = "path type";

		// path computed lane
		regionP.path.computedLane = new ComputedLane();
		regionP.path.computedLane.laneID = "44";
		regionP.path.computedLane.offsetSmallX = "10";
		regionP.path.computedLane.offsetLargeX = "11";
		regionP.path.computedLane.offsetSmallY = "12";
		regionP.path.computedLane.offsetLargeY = "13";
		regionP.path.computedLane.angle = "14";
		regionP.path.computedLane.xScale = "15";
		regionP.path.computedLane.yScale = "16";

		// path nodes
		regionP.path.nodes = [];

		let node1 = new NodeXY();
		node1.delta = "node-XY2";
		node1.x = "22";
		node1.y = "234";
		node1.nodeLat = "41";
		node1.nodeLong = "-108";
	    node1.attributes = new Attributes();
	    node1.attributes.dWidth = "50";
	    node1.attributes.dElevation = "60";

	    // local nodes
	    node1.attributes.localNodes = [];

		let localNode1 = new LocalNode();
		localNode1.type = "1";
		node1.attributes.localNodes.push(localNode1);

		let localNode2 = new LocalNode();
		localNode2.type = "2";
		node1.attributes.localNodes.push(localNode2);

		// disabled list
		node1.attributes.disabledLists = [];

		let disabledList1 = new DisabledList();
		disabledList1.type = "3";
		node1.attributes.disabledLists.push(disabledList1);

		let disabledList2 = new DisabledList();
		disabledList2.type = "4";
		node1.attributes.disabledLists.push(disabledList2);

		// enabled list
		node1.attributes.enabledLists = [];

		let enabledList1 = new EnabledList();
		enabledList1.type = "5";
		node1.attributes.enabledLists.push(enabledList1);

		let enabledList2 = new EnabledList();
		enabledList2.type = "6";
		node1.attributes.enabledLists.push(enabledList2);

		// datalists
		node1.attributes.dataLists = [];

		let dataLists1 = new DataList();
		dataLists1.pathEndpointAngle = "7";
		dataLists1.laneCrownCenter = "8";
		dataLists1.laneCrownRight = "9";
		dataLists1.laneCrownLeft = "10";
		dataLists1.laneAngle = "11";
		dataLists1.speedLimits = [];

		let speedLimit1 = new SpeedLimits();
		speedLimit1.type = "12";	
		speedLimit1.velocity = "50";	

		dataLists1.speedLimits.push(speedLimit1);

		let speedLimit2 = new SpeedLimits();
		speedLimit2.type = "13";
		speedLimit2.velocity = "51";

		dataLists1.speedLimits.push(speedLimit2);

		node1.attributes.dataLists.push(dataLists1);

	    regionP.path.nodes.push(node1);

		// old region
		regionP.oldRegion = new OldRegion();
		regionP.oldRegion.direction = "old region direction";
		regionP.oldRegion.extent = "88";
		regionP.oldRegion.area = "old region area";

		// old region circle
		regionP.oldRegion.circle = new Circle();
	    regionP.oldRegion.circle.position = new J2735Position3D();
		regionP.oldRegion.circle.position.latitude = "41.678473";
		regionP.oldRegion.circle.position.longitude = "-108.782775";
		regionP.oldRegion.circle.position.elevation = "917.1432";
		regionP.oldRegion.circle.radius = "15";
		regionP.oldRegion.circle.units = "7";

		// old region region point
		regionP.oldRegion.regionPoint = new RegionPoint();
		regionP.oldRegion.regionPoint.position = new J2735Position3D(); 
	    regionP.oldRegion.regionPoint.position.latitude = "41.678473";
		regionP.oldRegion.regionPoint.position.longitude = "-108.782775";
		regionP.oldRegion.regionPoint.position.elevation = "917.1432";
		regionP.oldRegion.regionPoint.scale = "99";

		// region poit region list
		regionP.oldRegion.regionPoint.regionList = [];
		let regionList1 = new RegionList();
		regionList1.xOffset = "90";
		regionList1.yOffset = "91";
		regionList1.zOffset = "92";
		regionP.oldRegion.regionPoint.regionList.push(regionList1);

		let regionList2 = new RegionList();
		regionList2.xOffset = "93";
		regionList2.yOffset = "94";
		regionList2.zOffset = "95";
		regionP.oldRegion.regionPoint.regionList.push(regionList2);

		// shape point
		regionP.oldRegion.shapePoint = new ShapePoint();
		regionP.oldRegion.shapePoint.position = new J2735Position3D(); 
	    regionP.oldRegion.shapePoint.position.latitude = "41.678473";
		regionP.oldRegion.shapePoint.position.longitude = "-108.782775";
		regionP.oldRegion.shapePoint.position.elevation = "917.1432";
		regionP.oldRegion.shapePoint.laneWidth = "66";
		regionP.oldRegion.shapePoint.directionality = "77";
		regionP.oldRegion.shapePoint.nodeType = "shape point node type";

		// old region shape point computed lane
		regionP.oldRegion.shapePoint.computedLane = new ComputedLane();
		regionP.oldRegion.shapePoint.computedLane.laneID = "444";
		regionP.oldRegion.shapePoint.computedLane.offsetSmallX = "100";
		regionP.oldRegion.shapePoint.computedLane.offsetLargeX = "110";
		regionP.oldRegion.shapePoint.computedLane.offsetSmallY = "120";
		regionP.oldRegion.shapePoint.computedLane.offsetLargeY = "130";
		regionP.oldRegion.shapePoint.computedLane.angle = "140";
		regionP.oldRegion.shapePoint.computedLane.xScale = "150";
		regionP.oldRegion.shapePoint.computedLane.yScale = "160";

		// old region shape point nodes list
		regionP.oldRegion.shapePoint.nodexy = [];

		let node3 = new NodeXY();
		node3.delta = "node-XY2";
		node3.x = "22";
		node3.y = "234";
		node3.nodeLat = "41";
		node3.nodeLong = "-108";
	    node3.attributes = new Attributes();
	    node3.attributes.dWidth = "50";
	    node3.attributes.dElevation = "60";

	    // local nodes
	    node3.attributes.localNodes = [];

		let localNode3 = new LocalNode();
		localNode3.type = "1";
		node3.attributes.localNodes.push(localNode3);

		regionP.oldRegion.shapePoint.nodexy.push(node3);


		this.df.regions.push(regionP);

		this.df.items = [];		
		this.df.itisCodes = [];
		for(let i of this.itisCodes){  			
			if(i.itisCodeId == this.selectedItisCodeId){ 
				this.df.items.push(i.itisCode.toString());
				this.df.itisCodes.push(i);
			}
		}

		let dfa: DataFrame[] = [];
		dfa.push(this.df);
		tim.dataframes = dfa;
		timSample.tim = tim;

		timSample.rsus = [];

		// selected RSUs
		for(let r of this.rsuData){  			
			if(r.isSelected){ 
				timSample.rsus.push(r);
			}
		}

		var dateSent = new Date();
		timSample.dateSent = dateSent.getFullYear() + "-" + (dateSent.getMonth()+1) + "-" + dateSent.getDate() + " " + dateSent.getHours() + ":" + dateSent.getMinutes() + ":" + dateSent.getSeconds();

		timSample.snmp = new SNMP();
		timSample.snmp.rsuid = "8300";
		timSample.snmp.msgid = "31";
		timSample.snmp.mode = "1";
		timSample.snmp.channel = "178";
		timSample.snmp.interval = "2";		
		timSample.snmp.deliverystart = "2017-01-01T17:47:11-05:00";
		timSample.snmp.deliverystop = "2019-01-01T17:47:11-05:15";
		timSample.snmp.enable = "3";
		timSample.snmp.status = "4";

		var dateSent = new Date();
		timSample.dateSent = "2017-01-01T17:47:11-05:00";
        //tim.timeStamp = "2017-01-01T17:47:11-05:00";
		//tim.timeStamp = today.getFullYear() + "-" + (today.getMonth()+1) + "-" + today.getDate() + " " + today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();

		// today = new Date();
		// date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
		// time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
		// timSample.dateReceived = date + "T" + time;

		var dateReceived = new Date();
		timSample.dateReceived = "2017-01-01T17:47:11-05:00";
		//timSample.dateReceived = dateReceived.getFullYear() + "-" + (dateReceived.getMonth()+1) + "-" + dateReceived.getDate() + " " + dateReceived.getHours() + ":" + dateReceived.getMinutes() + ":" + dateReceived.getSeconds();


		this.testJSON = JSON.stringify(timSample);	



  		this.timCreatorService
	     	.sendTim(timSample)
	     	.subscribe((r: Response) => {console.log(r); })    
	}

	submitFormPathXY(){
		let timSample = new TimSample();
		let tim = new Tim();
		tim.msgCnt = "13";
		tim.timeStamp = "2017-03-13T01:07:11-05:00";
		tim.packetID = "1";
		tim.urlB = "null";

		//this.df.sspTimRights = "1";
		this.df.frameType = "1";
		this.df.msgID = "RoadSignID";
		this.df.position = new J2735Position3D();
		this.df.position.latitude = "41.678473";
		this.df.position.longitude = "-108.782775";
		this.df.position.elevation = "917.1432";
		this.df.viewAngle = "1010101010101010";
		this.df.mutcd = "5";
		this.df.crc = "0000000000000000";
		this.df.priority = "2";
		this.df.sspLocationRights = "0";
		this.df.regions = [];
		this.df.furtherInfoID = "test";

		let region = new Region();
		region.name = "TRIHYDRO TEST";
		region.regulatorID = "23";
		region.segmentID = "33";
		region.anchorPosition = new J2735Position3D();
		region.anchorPosition.latitude = "41.678473";
		region.anchorPosition.longitude = "-108.782775";
		region.anchorPosition.elevation = "917.1432";
		region.laneWidth = "7";
		region.directionality = "3";
		region.closedPath = "false";
		region.direction = "1010101010101010";
		region.description = "path";

		region.path = new Path();
		region.path.scale = "1";
		region.path.type = "xy";
		region.path.nodes = [];

		let node = new NodeXY();
		node.delta = "node-XY2";
		node.x = "2";
		node.y = "2";
		node.attributes = new Attributes();
	    node.attributes.localNodes = [];
		let localNode = new LocalNode();
		localNode.type = "1";
		node.attributes.localNodes.push(localNode);
		node.attributes.disabledLists = [];
		node.attributes.enabledLists = [];
		node.attributes.dataLists = [];

		region.path.nodes.push(node);

		let node2 = new NodeXY();
		node2.delta = "node-XY1";
		node2.x = "1";
		node2.y = "1";
		node2.attributes = new Attributes();
	    node2.attributes.localNodes = [];
		let localNode2 = new LocalNode();
		localNode2.type = "1";
		node2.attributes.localNodes.push(localNode2);
		node2.attributes.disabledLists = [];
		node2.attributes.enabledLists = [];
		node2.attributes.dataLists = [];

		region.path.nodes.push(node2);


		this.df.sspMsgTypes = "2"; 
		this.df.sspMsgContent = "3";
		this.df.content = "Advisory";

		this.df.items = [];		

		this.df.itisCodes = [];
		for(let i of this.itisCodes){  			
			if(i.itisCodeId == this.selectedItisCodeId){ 
				this.df.items.push(i.itisCode.toString());
				this.df.itisCodes.push(i);
			}
		}

		this.df.url = "null";


		this.df.regions.push(region);
		let dfa: DataFrame[] = [];
		dfa.push(this.df);
		tim.dataframes = dfa;
		timSample.tim = tim;

		timSample.rsus = [];

		// selected RSUs
		for(let r of this.rsuData){  			
			if(r.isSelected){ 
				timSample.rsus.push(r);
			}
		}

		timSample.snmp = new SNMP();
		timSample.snmp.rsuid = "8300";
		timSample.snmp.msgid = "31";
		timSample.snmp.mode = "1";
		timSample.snmp.channel = "178";
		timSample.snmp.interval = "2";
		timSample.snmp.deliverystart = "2017-01-01T17:47:11-05:00";
		timSample.snmp.deliverystop = "2019-01-01T17:47:11-05:15";
		timSample.snmp.enable = "3";
		timSample.snmp.status = "4";

		var today = new Date();
		var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
		var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();


		timSample.dateSent = date + "T" + time;


		// this.timCreatorService
  //     	.sendTim(timSample)
  //     	.subscribe(
  //     		(r: Response) => { console.log(''); }
  // 		);

  		today = new Date();
		date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
		time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
		timSample.dateReceived = date + "T" + time;

		this.testJSON = JSON.stringify(timSample);	

		

  		this.timCreatorService
	      	.sendTim(timSample)
	      	.subscribe((r: Response) => {console.log(r); })      
        
	}
	submitFormGeometry(){
		let timSample = new TimSample();
		let tim = new Tim();
		tim.msgCnt = "13";
		tim.timeStamp = "2017-03-13T01:07:11-05:00";
		tim.packetID = "1";
		tim.urlB = "null";

		this.df.startDateTime = "2017-01-01T17:47:11-05:00";
		this.df.sspTimRights = "0";
		this.df.frameType = "0";
		this.df.msgID = "RoadSignID";
		this.df.position = new J2735Position3D();
		this.df.position.latitude = "41.678473";
		this.df.position.longitude = "-108.782775";
		this.df.position.elevation = "917.1432";
		this.df.viewAngle = "1010101010101010";
		this.df.mutcd = "5";
		this.df.crc = "0000000000000000";
		this.df.priority = "0";
		this.df.sspLocationRights = "3";
		this.df.regions = [];
		//this.df.furtherInfoID = "test";

		let region = new Region();
		region.name = "TRIHYDRO TEST";
		region.regulatorID = "23";
		region.segmentID = "33";
		region.anchorPosition = new J2735Position3D();
		region.anchorPosition.latitude = "41.678473";
		region.anchorPosition.longitude = "-108.782775";
		region.anchorPosition.elevation = "917.1432";
		region.laneWidth = "7";
		region.directionality = "3";
		region.closedPath = "false";
		region.direction = "1010101010101010";
		region.description = "geometry";
		region.geometry = new Geometry();
		region.geometry.direction = "1010101010101010";
		region.geometry.extent = "1";
		region.geometry.laneWidth = "33";
		region.geometry.circle = new Circle();
		region.geometry.circle.position = new J2735Position3D();
		region.geometry.circle.position.latitude = "41.678473";
		region.geometry.circle.position.longitude = "-108.782775";
		region.geometry.circle.position.elevation = "917.1432";
		region.geometry.circle.radius = "15";
		region.geometry.circle.units = "7";

		this.df.sspMsgTypes = "2"; 
		this.df.sspMsgContent = "3";
		this.df.content = "Advisory";

		this.df.items = [];
		

		this.df.itisCodes = [];
		for(let i of this.itisCodes){  			
			if(i.itisCodeId == this.selectedItisCodeId){ 
				this.df.items.push(i.itisCode.toString());
				this.df.itisCodes.push(i);
			}
		}

		this.df.url = "null";


		this.df.regions.push(region);
		let dfa: DataFrame[] = [];
		dfa.push(this.df);
		tim.dataframes = dfa;
		timSample.tim = tim;

		timSample.rsus = [];

		// selected RSUs
		for(let r of this.rsuData){  			
			if(r.isSelected){ 
				r.rsuRetries = "1";
				r.rsuTimeout = "2000";
				timSample.rsus.push(r);
			}
		}

		timSample.snmp = new SNMP();
		timSample.snmp.rsuid = "8300";
		timSample.snmp.msgid = "31";
		timSample.snmp.mode = "1";
		timSample.snmp.channel = "178";
		timSample.snmp.interval = "2";
		timSample.snmp.deliverystart = "2017-01-01T17:47:11-05:00";
		timSample.snmp.deliverystop = "2019-01-01T17:47:11-05:15";
		timSample.snmp.enable = "3";
		timSample.snmp.status = "4";

		var today = new Date();
		var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
		var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();


		timSample.dateSent = date + "T" + time;


		// this.timCreatorService
  //     	.sendTimToRSU(timSample)
  //     	.subscribe(
  //     		(r: Response) => { console.log('rsu response: ' + r); }
  // 		);

  		today = new Date();
		date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
		time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
		timSample.dateReceived = date + "T" + time;

		this.testJSON = JSON.stringify(timSample);	

  		this.timCreatorService
	     	.sendTim(timSample)
	     	.subscribe((r: Response) => {console.log(r); })      
        
	}
}
