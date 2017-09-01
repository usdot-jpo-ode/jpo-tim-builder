import { Injectable } from '@angular/core';
import { Http, Response, Headers} from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { RSU } from '../classes/rsu';
import { environment } from '../../environments/environment';

@Injectable()
export class RSUService{
  
	private dbUrl: string = environment.dbUrl;

	constructor(private http : Http){		
	}

	getAll(): Observable<RSU[]>{
		let rsu$ = this.http
		.get(this.dbUrl + '/rsus', {headers: this.getHeaders()})
		.map((res:Response) => res.json())
		.catch(handleError);
		return rsu$;
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