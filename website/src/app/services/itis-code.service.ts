import { Injectable } from '@angular/core';
import { Http, Response, Headers} from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { ItisCode } from '../classes/itis-code';

@Injectable()
export class ItisCodeService{
  
	private baseUrl: string = 'http://localhost:9000';

	constructor(private http : Http){		
	}

	getAll(): Observable<ItisCode[]>{
		let itisCode$ = this.http
		.get(this.baseUrl + '/itiscodes', {headers: this.getHeaders()})
		.map((res:Response) => res.json())
		.catch(handleError);
		return itisCode$;
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