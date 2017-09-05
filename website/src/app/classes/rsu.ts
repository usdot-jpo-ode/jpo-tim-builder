import { Index } from './index';

export class RSU {
	rsuTarget: string;
	rsuUsername: string;
	rsuPassword: string;
	rsuRetries: string;
	rsuTimeout: string;
	isSelected: boolean;
	index: number;
	indicies_set: Index[];
	indicies: number[];
	latitude: number;
	longitude: number;
}