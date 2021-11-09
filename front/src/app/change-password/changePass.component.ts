import {Component} from "@angular/core";
import {AppService} from "../root/app.service";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Subscription} from "rxjs";

@Component({
  templateUrl : 'changePass.component.html'
})
export class ChangePassComponent{
  changing : boolean = true;
  code : any;
  processText : string = '';
  email : any;
  password : any;
  correctCode : boolean = false;
  changed : any;
  userName : any;
  private querySubscription: Subscription;
  constructor(private app: AppService, private route: ActivatedRoute, private router: Router, private http: HttpClient) {
    this.querySubscription = route.queryParams.subscribe(
      (queryParam: any) => {
        this.code = queryParam['code'];
      }
    );
    if(this.code != null){
      this.changing = false;
      this.http.post(environment.apiUrl + '/register/' + this.code, {})
        .toPromise()
        .then((data : any) => {
          this.correctCode = data.result == true;
          this.userName = data.userLogin;
        })
    }
  }

  authenticated() {
    return this.app.authenticated;
  }
  sendCodeToUser(){
    this.http.put(environment.apiUrl + '/user', {
      email : this.email
    })
      .toPromise()
      .then((data : any) =>{
        if(data.result != true){
          this.processText = 'User with this email isn' + '\'' + 't exists';
        }
        else {
          this.processText = 'Check you are mail service for mail with instruction';
        }
      })
  }
  changePassword(){
    this.http.put(environment.apiUrl + '/user', {
      password : this.password,
      email : this.userName
    })
      .toPromise()
      .then((data : any) =>{
        this.changed = data.result == true;
      })
  }
}
