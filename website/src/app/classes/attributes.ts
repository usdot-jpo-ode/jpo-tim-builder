import { LocalNode } from './local-node';
import { DisabledList } from './disabled-list';
import { EnabledList } from './enabled-list';
import { DataList } from './data-list';

export class Attributes{	
	localNodes: LocalNode[];
    disabledLists: DisabledList[];
    enabledLists: EnabledList[];
    dataLists: DataList[];  
}