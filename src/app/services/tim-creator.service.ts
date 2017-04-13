import { Injectable } from '@angular/core';
import { Http, Response, Headers} from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { ItisCode } from '../classes/itis-code';

@Injectable()
export class TimCreatorService{
  
  private baseUrl: string = 'http://swapi.co/api';

  constructor(private http : Http){
  	
  }

}