import { Injectable } from '@angular/core';
import { Http, Response, Headers} from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { Milepost } from '../classes/mile-post';
import { environment } from '../../environments/environment';

@Injectable()
export class MilepostService{
  
	private dbUrl: string = environment.dbUrl;

	constructor(private http : Http){		
	}

	getAll(): Observable<Milepost[]>{
		let milepost$ = this.http
		.get(this.dbUrl + '/mileposts', {headers: this.getHeaders()})
		.map((res:Response) => res.json())
		.catch(handleError);
		return milepost$;
    }

    getPath(start: number, end: number, direction: string): Observable<Milepost[]>{
		let milepost$ = this.http
		.get(this.dbUrl + `/getMilepostRange/${direction}/${start}/${end}`, {headers: this.getHeaders()})		
		.map((res:Response) => res.json())
		.catch(handleError);
		return milepost$;
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