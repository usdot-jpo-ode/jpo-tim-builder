import { Tim } from './tim';
import { RSU } from './rsu';
import { SNMP } from './snmp';

export class TimSample {
	tim: Tim;
	rsus: RSU[];
	snmp: SNMP;
	dateSent: string;
	dateReceived: string;
}