import { Component, OnInit } from '@angular/core';
import { Tim } from '../../classes/tim';
import { RSU } from '../../classes/rsu';
import { TimMessage } from '../../classes/tim-message';
import { TimSample } from '../../classes/tim-sample';
import { DataFrame } from '../../classes/data-frame';
import { ITISCODES } from '../../data/itis-codes';
import { ItisCode } from '../../classes/itis-code';
import { Region } from '../../classes/region';
import { J2735Position3D } from '../../classes/J2735-Position-3D';
import { Geometry } from '../../classes/geometry';
import { Circle } from '../../classes/circle';
import { SNMP } from '../../classes/snmp';
import { TimCreatorService } from '../../services/tim-creator.service';
import { Response } from '@angular/http';

@Component({
	selector: 'tc-home',
	templateUrl: './home.component.html',
	providers: [TimCreatorService]
})
export class HomeComponent implements OnInit{

	tim: TimMessage;
	itisCodes: ItisCode[];
	testJSON: string;

   	constructor(private timCreatorService : TimCreatorService){ }

	ngOnInit(){			
		this.tim = new TimMessage();
		this.itisCodes = ITISCODES;
	}

	submitForm(){
		let timSample = new TimSample();
		let tim = new Tim();
		tim.msgCnt = "13";
		tim.timeStamp = "2017-03-13T01:07:11-05:00";
		tim.packetID = "1";
		tim.urlB = "null";

		let df = new DataFrame();
		df.sspTimRights = "0";
		df.frameType = "0";
		df.msgID = "RoadSignID";
		df.position = new J2735Position3D();
		df.position.latitude = "41.678473";
		df.position.longitude = "-108.782775";
		df.position.elevation = "917.1432";
		df.viewAngle = "1010101010101010";
		df.mutcd = "5";
		df.crc = "0000000000000000";
		df.startDateTime = "2017-12-01T17:47:11-05:00";
		df.durationTime = "22";
		df.priority = "0";
		df.sspLocationRights = "3";
		df.regions = [];

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

		df.sspMsgTypes = "2";
		df.sspMsgContent = "3";
		df.content = "Advisory";
		df.items = [];
		df.items.push("250");
		df.url = "null";

		df.regions.push(region);
		let dfa: DataFrame[] = [];
		dfa.push(df);
		tim.dataframes = dfa;
		timSample.tim = tim;

		timSample.rsus = [];
		let rsu = new RSU();
		rsu.rsuTarget = "10.145.1.15";
		rsu.rsuUsername = "v3user";
		rsu.rsuPassword = "password";
		rsu.rsuRetries = "1";
		rsu.rsuTimeout = "2000";

		timSample.rsus.push(rsu);

		timSample.snmp = new SNMP();
		timSample.snmp.rsuid = "8300";
		timSample.snmp.msgid = "31";
		timSample.snmp.mode = "1";
		timSample.snmp.channel = "178";
		timSample.snmp.interval = "1";
		timSample.snmp.deliverystart = "2017-01-01T17:47:11-05:00";
		timSample.snmp.deliverystop = "2019-01-01T17:47:11-05:15";
		timSample.snmp.enable = "1";
		timSample.snmp.status = "4";

		this.testJSON = JSON.stringify(timSample);	

		this.timCreatorService
      	.sendTim(timSample)
      	.subscribe(
      		(r: Response) => { console.log(''); }
  		);
        
	}
}