import { J2735Position3D } from './J2735-Position-3D';
import { ComputedLane } from './computed-lane';
import { NodeXY } from './node-xy';

export class ShapePoint{ 	
 	position: J2735Position3D;
 	laneWidth: string;
 	directionality: string;
 	nodeType: string;
 	computedLane: ComputedLane;
 	nodexy: NodeXY[];
}