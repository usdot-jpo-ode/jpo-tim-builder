import { Injectable } from '@angular/core';
import { RequestOptions, Http, Response, Headers} from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { ItisCode } from '../classes/itis-code';
import { TimSample } from '../classes/tim-sample';
import { TimQuery } from '../classes/tim-query';
import { RSU } from '../classes/rsu';
import { environment } from '../../environments/environment';

@Injectable()
export class TimBuilderService{
  
	private odeUrl: string = environment.odeUrl;
	private dbUrl: string = environment.dbUrl;

	constructor(private http : Http){		
	}

	sendTimToRSU(timSample: TimSample) : Observable<Response>{ 
		return this.http
		.post(`${this.odeUrl}/tim`, JSON.stringify(timSample), {headers: this.getHeaders()});
	}	

	queryTim(rsu: RSU) : Observable<TimQuery>{   
		let q = this.http
		.post(`${this.odeUrl}/tim/query`, JSON.stringify(rsu), {headers: this.getHeaders()})
	    .map((res:Response) => res.json())
	    .catch(handleError);
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