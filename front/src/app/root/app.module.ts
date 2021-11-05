import {Injectable, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {RouterModule, Routes} from "@angular/router";

import {LoginComponent} from '../login/login.component';
import {AppComponent} from './app.component';
import {AppService} from './app.service';
import {RegisterComponent} from "../register/register.component";
import {ConfirmRegisterComponent} from "../register/confirmRegister.component"
import {MainComponent} from "../main/main.component";
import {ProfileComponent} from "../profile/profile.component";
import {BalanceComponent} from "../balance/balance.component";
import {AdminPageComponent} from "../admin/admin-page.component";

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule} from "@angular/material/button";
import {MatGridListModule} from "@angular/material/grid-list";
import {MatInputModule} from '@angular/material/input';
import {MatIconModule} from "@angular/material/icon";
import {MatListModule} from "@angular/material/list";
import {EventComponent} from "../event/event.component";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatOptionModule} from "@angular/material/core";


@Injectable()
export class XhrInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const xhr = req.clone({
      headers: req.headers.set('X-Requested-With', 'XMLHttpRequest')
    });
    return next.handle(xhr);
  }
}

const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'home'},
  {path: 'home', component: MainComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'confirm', component: ConfirmRegisterComponent},
  {path: 'event', component: EventComponent},
  {path: 'event/:id', component: EventComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'balance', component: BalanceComponent},
  {path: 'admin', component: AdminPageComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    ConfirmRegisterComponent,
    MainComponent,
    EventComponent,
    ProfileComponent,
    BalanceComponent,
    AdminPageComponent
  ],
    imports: [
        RouterModule.forRoot(routes),
        BrowserModule,
        HttpClientModule,
        FormsModule,
        BrowserAnimationsModule,
        MatButtonModule,
        MatGridListModule,
        MatInputModule,
        MatIconModule,
        MatListModule,
        MatPaginatorModule,
        MatOptionModule
    ],
  providers: [AppService, {provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
