import { NodeXY } from './node-xy';
import { ComputedLane } from './computed-lane';

export class Path {
	scale: string;
	type: string;
	nodes: NodeXY[];
    computedLane: ComputedLane;
}