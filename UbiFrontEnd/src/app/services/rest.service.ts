import { Injectable } from '@angular/core';
import { Http, Response, Headers, URLSearchParams, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Rx';
import { Configuration } from '../app.constants';

@Injectable()
export class RestService {
    private headers: Headers;
    data;
    // cache data
    public lastGetAll: Array<any>;
    public lastGet: any;

    constructor(private http: Http, private config: Configuration) {
        this.headers = new Headers();
        this.headers.append('Content-Type', 'application/json');
        this.headers.append('Accept', 'application/json');
    }



    private getActionUrl() {
        return this.config.getServerWithApiUrl(); //+ this.modelName + '/';
    }


    // REST functions
    public getAll(endPoint: string): Observable<any[]> {
        return this.http.get(this.getActionUrl() + endPoint, { headers: this.headers })
            .map((response: Response) => {
                // getting an array having the same name as the model
                let data = response.json()//[this.modelName];
                return data;
            })
            .catch(this.handleError);
    }

    public get(endPoint: string, id: number): Observable<any> {
        return this.http.get(this.getActionUrl() + endPoint + id)
            .map((response: Response) => {
                let data = response.json();
                this.lastGet = data;
                return data;
            })
            .catch(this.handleError);
    }

    public post(item: any, endPoint: string): Observable<any> {
        let toAdd = JSON.stringify(item);        

        return this.http.post(this.getActionUrl() + endPoint, toAdd, { headers: this.headers })
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }

    public put(item: any, endPoint: string): Observable<number> {
        return this.http.put(this.getActionUrl() + endPoint, JSON.stringify(item), { headers: this.headers })
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }

    public delete(endPoint: string): Observable<Response> {
        return this.http.delete(this.getActionUrl() + endPoint)
            .catch(this.handleError);
    }

   

    private handleError(error: Response) {
        console.error(error);
        return Observable.throw(error.status || 'Server error');
    }
}