import { Tim } from './tim';
import { RSU } from './rsu';
import { SNMP } from './snmp';
import { SDW } from './sdw';

export class TimSample {
	tim: Tim;
	rsus: RSU[];
	snmp: SNMP;
	sdw: SDW;
	dateSent: string;
	dateReceived: string;
}