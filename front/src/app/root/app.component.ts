import {Component} from '@angular/core';
import {AppService} from './app.service';
import {HttpClient} from '@angular/common/http';
import {Location} from "@angular/common";
import {Router} from '@angular/router';
import {environment} from "../../environments/environment";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private app: AppService, private http: HttpClient, private router: Router, location:Location) {
    this.app.authenticate(undefined, () => {
      this.router.navigateByUrl('/home');
    }, () => {
      if(window.location.pathname == '/confirm'){
        this.router.navigateByUrl(location.path());
      }
      else {
        this.router.navigateByUrl('/login');
      }
    });
  }

  logout() {
    return this.http.post(environment.apiUrl + '/logout', {}, {withCredentials: true}).subscribe(() => {
      this.router.navigateByUrl('/login');
    });
  }

  authenticated() {
    return this.app.authenticated;
  }
}
