import { Component, OnInit } from '@angular/core';
import { TimMessage } from '../../classes/tim-message';
import { ITISCODES } from '../../data/itis-codes';
import { ItisCode } from '../../classes/itis-code';
import { TimCreatorService } from '../../services/tim-creator.service';

@Component({
	selector: 'tc-home',
	templateUrl: './home.component.html',
	providers: [TimCreatorService]
})
export class HomeComponent implements OnInit{

	tim: TimMessage;
	itisCodes: ItisCode[];
   	constructor(){ }

	ngOnInit(){			
		this.tim = new TimMessage();
		this.itisCodes = ITISCODES;
	}
}