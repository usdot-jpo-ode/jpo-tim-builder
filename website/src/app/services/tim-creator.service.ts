import { Injectable } from '@angular/core';
import { Http, Response, Headers} from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { ItisCode } from '../classes/itis-code';
import { TimSample } from '../classes/tim-sample';

@Injectable()
export class TimCreatorService{
  
	private databaseUrl: string = 'http://localhost:8080';
	private odeUrl: string = 'http://localhost:8080';

	constructor(private http : Http){
		
	}

	sendTimToRSU(timSample: TimSample) : Observable<Response>{   
		console.log(timSample);
		return this.http
		.post(`${this.odeUrl}/tim`, JSON.stringify(timSample), {headers: this.getHeaders()});
	}

	sendTim(timSample: TimSample) : Observable<Response>{   
		console.log(timSample);
		return this.http
		.post(`${this.databaseUrl}/sendTim`, JSON.stringify(timSample), {headers: this.getHeaders()});
	}

	// TODO: Will need to change
	getTIMs(): Observable<TimSample[]>{
    	let tims$ = this.http
         .get(this.odeUrl + '/getTims', {headers: this.getHeaders()})
         .map((res:Response) => res.json())
         .catch(handleError);
    	return tims$;
  	}

  	// TODO: Will need to change
  	disableTim(timSample: TimSample) : Observable<Response>{   
		console.log(timSample);
		return this.http
		.post(`${this.odeUrl}/disableTim`, JSON.stringify(timSample), {headers: this.getHeaders()});
	}

	private getHeaders(){
	    let headers = new Headers();
	    headers.append('Content-Type', 'application/json');
	    return headers;
	}
}

function handleError (error: any) {
	// log error
	let errorMsg = error.message || `An error has occured.`
	console.error(errorMsg);

	// throw an application level error
	return Observable.throw(errorMsg);
}