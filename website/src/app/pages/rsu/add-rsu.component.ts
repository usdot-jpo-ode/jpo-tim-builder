import { Component, OnInit } from '@angular/core';
import { RSU } from '../../classes/rsu';
import { RSUService } from '../../services/rsu.service';
import { Response } from '@angular/http';

@Component({
	selector: 'tc-add-rsu',
	templateUrl: './add-rsu.component.html',
	providers: [RSUService]
})
export class RSUComponent implements OnInit{

	newRSU: RSU;

   	constructor(private rsuService: RSUService){ }

	ngOnInit(){			
		this.newRSU = new RSU();		
	}

	submitForm(){
		this.rsuService
	      	.add(this.newRSU)
	      	.subscribe((r: Response) => {console.log(r); })      
	}
}