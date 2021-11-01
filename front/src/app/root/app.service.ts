import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable()
export class AppService {
  authenticated = false;
  userName = "";

  constructor(private http: HttpClient) {
  }
  authenticate(credentials: any, successCallback: any, failureCallback: any) {
    const headers = new HttpHeaders(credentials ? {
      authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    } : {});
    this.http.post(environment.apiUrl + '/authentication', {}, {headers: headers, withCredentials: true})
      .toPromise()
      .then((response: any) => {
        this.authenticated = true;
        // this.userName = credentials.username;
        return successCallback && successCallback();
      }).catch(() => {
        this.authenticated = false;
        return failureCallback && failureCallback();
      }
    );
  }

  getEvents(successCallback: any, failureCallback: any) {
    this.http.get(environment.apiUrl + '/event', {})
      .toPromise()
      .then((data: any) => {
        return successCallback && successCallback(data)
      }).catch((e) =>{
        return failureCallback && failureCallback(e)
    });
  }
}

