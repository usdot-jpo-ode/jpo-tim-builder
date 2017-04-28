import { Injectable } from '@angular/core';
import { Http, Response, Headers} from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { ItisCode } from '../classes/itis-code';
import { TimSample } from '../classes/tim-sample';

@Injectable()
export class TimCreatorService{
  
	private baseUrl: string = 'http://localhost:8080';

	constructor(private http : Http){
		
	}

	sendTim(timSample: TimSample) : Observable<Response>{   
		console.log(timSample);
		return this.http
		.post(`${this.baseUrl}/sendTim`, JSON.stringify(timSample), {headers: this.getHeaders()});
	}

	private getHeaders(){
	    let headers = new Headers();
	    headers.append('Content-Type', 'application/json');
	    return headers;
	}

}