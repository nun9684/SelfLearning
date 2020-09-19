import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

export class CRUD {
    constructor(
        private httpClient: HttpClient
    ){}

    GET(url, key, data): any{
        const  params = new  HttpParams().set(key, data);
        this.httpClient.get(url, {params}).subscribe(
            val => {
                return val;
            },
            (response: any) => {
                return response;
            }
        );
    }
}

