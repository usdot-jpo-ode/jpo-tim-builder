import { J2735Position3D } from './J2735-Position-3D';
import { Region } from './region';
import { ItisCode } from './itis-code';

export class DataFrame{
	sspTimRights: string;
	frameType: string;
	msgID: string;
	furtherInfoID: string;
	position: J2735Position3D;
	viewAngle: string;
	mutcd: string;
	crc: string;
	startDateTime: string;
	durationTime: string;
	priority: string;
	sspLocationRights: string;
	regions: Region[];
	sspMsgTypes: string;
	sspMsgContent: string;
	content: string;
	items: string[];
	itisCodes: ItisCode[];
	url: string;
}