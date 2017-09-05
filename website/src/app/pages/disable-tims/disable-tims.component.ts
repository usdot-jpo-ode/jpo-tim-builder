import { Component, OnInit } from '@angular/core';
import { RSU } from '../../classes/rsu';
import { TimQuery } from '../../classes/tim-query';
import { RSUService } from '../../services/rsu.service';
import { Response } from '@angular/http';
import { TimBuilderService } from '../../services/tim-builder.service';
import { Index } from '../../classes/index';
import { Observable } from 'rxjs/Rx';
import { FormsModule } from '@angular/forms';

@Component({
	selector: 'tc-disable-tims',
	templateUrl: './disable-tims.component.html',
	providers: [RSUService, TimBuilderService]
})
export class DisableTimsComponent implements OnInit{

	rsuData: RSU[];	
	errorMessage: string = '';
	isLoading: boolean = true;
	rsus: RSU[];

   	constructor(private timBuilderService : TimBuilderService, private rsuService: RSUService){ }

	ngOnInit(){				
		this.rsus = [];
		this.rsuData = [];
		this.rsuService.getAll().subscribe(
		 /* happy path */ r => this.rsuData = r,
         /* error path */ e => this.errorMessage = e,
         /* onComplete */ () => { this.getTIMIndices(); } 
		);
	}

	getTIMIndices(){
		for(let r of this.rsuData){ 
			r.rsuRetries = "1";
			r.rsuTimeout = "2000"; 			
			r.indicies_set = [];
			let timQuery = new TimQuery; 
			this.timBuilderService.queryTim(r).subscribe(
				i => timQuery = i, 
				e => this.errorMessage = e,
				() => { 
					this.isLoading = false; 
					r.indicies = timQuery.indicies_set;
					for (var i = 0; i < r.indicies.length; i++) {
				 		r.indicies_set.push(new Index(r.indicies[i], false));
					} 
					if(r.indicies_set.length > 0){
						console.log(r.indicies_set);
						this.rsus.push(r); 
					}
				} 
			);	
		}
	}

	deleteTIMs(){

		let observableBatch = [];

		// for each RSU
		for (var i = 0; i < this.rsus.length; i++) {
			// for each index
			for (var j = 0; j < this.rsus[i].indicies_set.length; j++) {
				// if index selected
				if(this.rsus[i].indicies_set[j].isSelected){
					// delete
					observableBatch.push( 
						this.timBuilderService.deleteTim(this.rsus[i], this.rsus[i].indicies_set[j].index) 
					);
				}
			}
		}

		Observable.forkJoin(observableBatch).subscribe( data => { console.log("success"); this.ngOnInit(); } );		
	}
}