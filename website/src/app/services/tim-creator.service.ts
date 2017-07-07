import { Injectable } from '@angular/core';
import { RequestOptions, Http, Response, Headers} from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { ItisCode } from '../classes/itis-code';
import { TimSample } from '../classes/tim-sample';
import { RSU } from '../classes/rsu';

@Injectable()
export class TimCreatorService{
  
	private odeUrl: string = 'http://localhost:8080';
	private dbUrl: string = 'http://localhost:9000';

	constructor(private http : Http){
		
	}

	sendTimToRSU(timSample: TimSample) : Observable<Response>{ 
		return this.http
		.post(`${this.odeUrl}/tim`, JSON.stringify(timSample), {headers: this.getHeaders()});
	}

	sendTimToDB(timSample: TimSample) : Observable<Response>{   
		return this.http
		.post(`${this.dbUrl}/sendTim`, JSON.stringify(timSample), {headers: this.getHeaders()});
	}

	queryTim(rsu: RSU) : Observable<number[]>{   
		let q = this.http
		.post(`${this.odeUrl}/tim/query`, JSON.stringify(rsu), {headers: this.getHeaders()})
		.map(mapTimQueryToArray);	
		return q;
	}

  	deleteTim(rsu: RSU, index: number) : Observable<Response>{ 
		return this.http
		.delete(`${this.odeUrl}/tim?index=${index}`, new RequestOptions({ headers: this.getHeaders(), body: rsu}));
	}

	private getHeaders(){
	    let headers = new Headers();
	    headers.append('Content-Type', 'application/json');
	    return headers;
	}
}

function mapTimQueryToArray(response: Response): number[]{
	return JSON.parse(response.text().split('",')[1].split('}')[0]);;
}

function handleError (error: any) {
	// log error
	let errorMsg = error.message || `An error has occured.`
	console.error(errorMsg);

	// throw an application level error
	return Observable.throw(errorMsg);
}