import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable()
export class AppService {
  authenticated = false;
  userName = "";
  userId = 0;
  roles : Array<any> = [];
  balance : any;

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
        this.userName = response.email;
        this.userId = response.id;
        this.roles = <Array<any>>response.roles;
        return successCallback && successCallback();
      }).catch(() => {
        this.authenticated = false;
        return failureCallback && failureCallback();
      }
    );
  }

  getEvents(successCallback: any, failureCallback: any) {
    this.http.get(environment.apiUrl + '/event', {
      withCredentials: true
    })
      .toPromise()
      .then((data: any) => {
        return successCallback && successCallback(data)
      }).catch((e) => {
      return failureCallback && failureCallback(e)
    });
  }

  getEvent(id: any, successCallback: any, failureCallback: any) {
    this.http.get(environment.apiUrl + '/event/' + id, {
      withCredentials: true
    })
      .toPromise()
      .then((data: any) => {
        return successCallback && successCallback(data)
      }).catch((e) => {
      return failureCallback && failureCallback(e)
    });
  }
}

