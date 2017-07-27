import { Injectable } from '@angular/core';
import { Http, Response, Headers} from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { MilePost } from '../classes/mile-post';
import { environment } from '../../environments/environment';

@Injectable()
export class MilePostService{
  
	private dbUrl: string = environment.dbUrl;

	constructor(private http : Http){		
	}

	getAll(): Observable<MilePost[]>{
		let milePost$ = this.http
		.get(this.dbUrl + '/milemarkers', {headers: this.getHeaders()})
		.map((res:Response) => res.json())
		.catch(handleError);
		return milePost$;
    }

    getPath(start: number, end: number, direction: string): Observable<MilePost[]>{
		let milePost$ = this.http
		.get(this.dbUrl + `/getMileMarkerRange/${direction}/${start}/${end}`, {headers: this.getHeaders()})		
		.map((res:Response) => res.json())
		.catch(handleError);
		return milePost$;
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