import { Injectable } from '@angular/core';
import { Http, Response, Headers} from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { RSU } from '../classes/rsu';

@Injectable()
export class RSUService{
  
	private baseUrl: string = 'http://localhost:9000';

	constructor(private http : Http){		
	}

	getAll(): Observable<RSU[]>{
		let rsu$ = this.http
		.get(this.baseUrl + '/rsus', {headers: this.getHeaders()})
		.map((res:Response) => res.json())
		.catch(handleError);
		return rsu$;
    }

    add(rsu: RSU) : Observable<Response>{  
    	return this.http
    	.post(`${this.baseUrl}/rsus`, JSON.stringify(rsu), {headers: this.getHeaders()});
	}

	private getHeaders(){
	    let headers = new Headers();
	    headers.append('Content-Type', 'application/json');
	    return headers;
	}

}

function handleError (error: any) {
	let errorMsg = error.message || `An error has occured.`
	console.error(errorMsg);
	return Observable.throw(errorMsg);
}