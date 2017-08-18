import { ShapePoint } from './shape-point';
import { Circle } from './circle';
import { RegionPoint } from './region-point';

export class OldRegion {
	direction: string;
	extent: string;
	area: string;
	shapePoint: ShapePoint;
	regionPoint: RegionPoint;
	circle: Circle;
}