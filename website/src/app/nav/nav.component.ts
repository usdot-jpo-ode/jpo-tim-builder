import { Component} from '@angular/core';

@Component({
	selector: 'tc-nav',
	templateUrl: './nav.component.html'
})
export class NavComponent{

	homeActive: string;
	disableActive: string;

  	constructor() { 
  		this.homeActive = "active";
  	}

  	onHomeSelected(): void {    	
		this.homeActive = "active";
		this.disableActive = "";    		
	}	

	onDisableSelected(): void {    	
		this.homeActive = "";
		this.disableActive = "active";    	
	}	

}