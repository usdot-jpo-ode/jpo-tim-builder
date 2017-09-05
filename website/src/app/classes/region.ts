import { J2735Position3D } from './J2735-Position-3D';
import { Geometry } from './geometry';
import { Path } from './path';
import { OldRegion } from './old-region';

export class Region {
	name: string;
	regulatorID: string;
	segmentID: string;
	anchorPosition: J2735Position3D;
	laneWidth: string;
	directionality: string;
	closedPath: string;
	direction: string;
	regionType: string;
	description: string;
	path: Path;
	geometry: Geometry;
	oldRegion: OldRegion;
}