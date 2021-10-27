import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../environments/environment";

@Injectable()
export class AppService {
  authenticated = false;

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
        return successCallback && successCallback();
      }).catch(() => {
        this.authenticated = false;
        return failureCallback && failureCallback();
      }
    );
  }
}

