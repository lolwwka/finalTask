import {Component} from '@angular/core';
import {AppService} from './app.service';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'login.component',
  templateUrl: 'login.component.html',
})
export class LoginComponent {

  error = false;

  credentials = {username: '', password: ''};


  constructor(private app: AppService, private http: HttpClient, private router: Router) {
  }
  login() {
    this.app.authenticate(this.credentials, () => {
      this.router.navigateByUrl('/');
    }, () => {
      this.error = true;
    });
    return false;
  }
  register(){
    this.router.navigateByUrl('/register');
  }
}
