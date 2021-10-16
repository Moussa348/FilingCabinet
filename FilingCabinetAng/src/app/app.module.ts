import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ALLOWED_URLS,STORAGE_KEY } from './util/constant';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { JwtModule } from '@auth0/angular-jwt';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { WelcomeComponent } from './component/welcome/welcome.component';
import { Error404Component } from './component/error404/error404.component';
import {MatGridListModule} from '@angular/material/grid-list';
import { AuthComponent } from './component/auth/auth.component';
import { NavComponent } from './component/nav/nav.component';
import { ListPatientFolderComponent } from './component/list-patient-folder/list-patient-folder.component';
import { FooterComponent } from './component/footer/footer.component';
import { HomeComponent } from './component/home/home.component';
import {MatDividerModule} from '@angular/material/divider';
import { CatalogComponent } from './component/catalog/catalog.component';

@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    Error404Component,
    AuthComponent,
    NavComponent,
    ListPatientFolderComponent,
    FooterComponent,
    HomeComponent,
    CatalogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatGridListModule,
    MatDividerModule,
    JwtModule.forRoot({
      config:{
        tokenGetter: () => {
          return sessionStorage.getItem(STORAGE_KEY);
        },
        allowedDomains:ALLOWED_URLS
      },
    }),
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

