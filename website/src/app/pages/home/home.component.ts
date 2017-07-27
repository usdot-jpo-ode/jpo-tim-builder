import { Component, OnInit, Input } from '@angular/core';
import { Tim } from '../../classes/tim';
import { RSU } from '../../classes/rsu';
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
import { MilePostService } from '../../services/mile-post.service';
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
import { Index } from '../../classes/index';
import { MilePost } from '../../classes/mile-post';

@Component({
	selector: 'tc-home',   
	templateUrl: './home.component.html',
	providers: [TimCreatorService, RSUService, ItisCodeService, MilePostService]
})
export class HomeComponent implements OnInit{

	milePosts: MilePost[];
	itisCodes: ItisCode[];
	testJSON: string;
	tim: Tim;
	df: DataFrame;
	rsuData: RSU[];	
	errorMessage: string = '';
	isLoading: boolean = true;
	selectedItisCodeId: number;
	snmpIndex: number;
	autoGenerateIndex: boolean;
	messages: string[];
	mapPoint: any;
	direction: string;
	startingMilePost: number;
	endingMilePost: number;
	milePostDD: MilePost[];
	pathPosts: MilePost[];	

   	constructor(private timCreatorService : TimCreatorService, private rsuService: RSUService, private itisCodeService: ItisCodeService, private milePostService: MilePostService){ }

	ngOnInit(){	
		
		this.df = new DataFrame();
		this.tim = new Tim();
		this.messages = [];

		this.rsuService.getActiveRSUs().subscribe(
		 /* happy path */ r => this.rsuData = r,
         /* error path */ e => this.errorMessage = e,
         /* onComplete */ () => { this.isLoading = false; } 
		);

		this.milePostService.getAll().subscribe(
		 /* happy path */ i => this.milePosts = i,
         /* error path */ e => this.errorMessage = e,
         /* onComplete */ () => { this.isLoading = false; console.log(this.milePosts); } 
		);

		this.itisCodeService.getAll().subscribe(
		 /* happy path */ i => this.itisCodes = i,
         /* error path */ e => this.errorMessage = e,
         /* onComplete */ () => { this.isLoading = false; } 
		);
	}

	directionChanged(){
		if(this.direction == "Eastbound")
			this.milePostDD = this.milePosts.filter(function(i) { return i.direction == "eastbound" }); 
		else
			this.milePostDD = this.milePosts.filter(function(i) { return i.direction == "westbound" }); 
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

	filterWestBoundMilePosts(p){
		return p.milepost >= this.startingMilePost && p.milepost <= this.endingMilePost && p.direction == "westbound";
	}

	filterEastBoundMilePosts(p){
		return p.milepost >= this.startingMilePost && p.milepost <= this.endingMilePost && p.direction == "eastbound";
	}

	milePostChanged(){
		if(this.startingMilePost != null && this.endingMilePost != null){
			if(this.direction == "Westbound"){
				this.milePostService.getPath(this.startingMilePost, this.endingMilePost, "westbound").subscribe(
					i => this.pathPosts = i,
			        e => this.errorMessage = e,
			        () => { this.isLoading = false; console.log(this.pathPosts); } 
				);
				//this.pathPosts = this.milePosts.filter(this.filterWestBoundMilePosts, this);
			}	
			else
				this.pathPosts = this.milePosts.filter(this.filterEastBoundMilePosts, this);
			console.log(this.pathPosts);
		}
	}

	public doSomething(mapPoint: any):void {
    	this.mapPoint = mapPoint;
	}

	submitFormGeometry(){    

		let builtTim: TimSample;
		// for each selected RSU
		for(let r of this.rsuData){ 
			r.rsuRetries = "1";
			r.rsuTimeout = "2000"; 	 			
			if(r.isSelected){ 
				console.log(r);
		     	this.timCreatorService.queryTim(r).subscribe(
					i => r.indicies = i,
					e => this.errorMessage = e,
					() => { 
						this.isLoading = false;	
						// build JSON 
						builtTim = this.buildJSON(r);       
						// send TIM to RSU
						this.sendTimToRSU(builtTim); 
						// send TIM to DB
						this.sendTimToDB(builtTim);
					} 
				);				
			}
		}				
	}

	buildJSON(rsu: RSU):TimSample{

		let timSample = new TimSample();
		let tim = new Tim();		
		tim.msgCnt = "13";
		tim.index = this.findFirstAvailableIndex(rsu.indicies).toString();

		console.log("index: " + tim.index);
		console.log("start date time : " + this.df.startDateTime);
      
		var today = new Date();
		tim.timeStamp = today.toISOString();

		tim.packetID = "1";
		tim.urlB = "null";

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
		this.df.furtherInfoID = "test";

		let region = new Region();
		region.name = "bob";
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
		region.geometry.circle.position.latitude = this.mapPoint.latitude;
		region.geometry.circle.position.longitude = this.mapPoint.longitude;
		region.geometry.circle.radius = "5";
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

		timSample.snmp = new SNMP();
		timSample.snmp.rsuid = "0083";
		timSample.snmp.msgid = "31";
		timSample.snmp.mode = "1";
		timSample.snmp.channel = "178";
		timSample.snmp.interval = "2";
		timSample.snmp.deliverystart = "2017-06-01T17:47:11-05:00";
		timSample.snmp.deliverystop = "2018-01-01T17:47:11-05:15";
		timSample.snmp.enable = "1";
		timSample.snmp.status = "4";

		timSample.rsus.push(rsu);
	
		this.testJSON = JSON.stringify(timSample);	

		return timSample;
  	
	}

	sendTimToRSU(tim: TimSample){
		// set date sent
		tim.dateSent = new Date().toISOString();
		// send to RSU
		console.log(tim);
		this.timCreatorService
     	.sendTimToRSU(tim)
     	.subscribe((r: Response) => {
     		// set date received 
     		tim.dateReceived = new Date().toISOString();
     		this.verifyDeposit(parseInt(tim.tim.index), tim.rsus[0]);
     		//this.sendTimToDB(tim);
     	})   
	}

	sendTimToDB(tim: TimSample){
		this.timCreatorService
		    	.sendTimToDB(tim)
		    	.subscribe(
		    		(r: Response) => { console.log("db response: " + r); }
				);
	}

	findFirstAvailableIndex(indicies: number[]): number{
		for (var i = 1; i < 100; i++) {
			if(!indicies.includes(i)){
				return i;
			}
		}
	}

	verifyDeposit(index: number, rsu: RSU){
		let indicies: number[];
		this.timCreatorService.queryTim(rsu).subscribe(
			i => indicies = i,
			e => this.errorMessage = e,
			() => { 
				if(indicies.includes(index))
					this.messages.push("TIM successfully deposited to RSU " + rsu.rsuTarget + " at index " + index);				
				else
					this.messages.push("TIM deposit failed on RSU " + rsu.rsuTarget);				
			} 
		);
	}
}
