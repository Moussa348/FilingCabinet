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
import {MatTreeModule} from '@angular/material/tree';
import { AuthComponent } from './component/auth/auth.component';
import { NavComponent } from './component/nav/nav.component';
import { ListPatientFolderComponent } from './component/list-patient-folder/list-patient-folder.component';
import { FooterComponent } from './component/footer/footer.component';
import { HomeComponent } from './component/home/home.component';
import {MatDividerModule} from '@angular/material/divider';
import { CatalogComponent } from './component/catalog/catalog.component';
import { UploadFileComponent } from './component/upload/upload-file/upload-file.component';
import { DownloadFileComponent } from './component/download-file/download-file.component';
import { ShowFileDetailComponent } from './component/show-file-detail/show-file-detail.component';
import { ShowPatientFolderComponent } from './component/show-patient-folder/show-patient-folder.component';
import { UploadStatusComponent } from './component/upload/upload-status/upload-status.component';
import { DatePipe } from '@angular/common';
import {MatPaginatorModule} from '@angular/material/paginator';
import { PaginationComponent } from './component/pagination/pagination.component';
import { ListLogComponent } from './component/list-log/list-log.component';

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
    CatalogComponent,
    UploadFileComponent,
    DownloadFileComponent,
    ShowFileDetailComponent,
    ShowPatientFolderComponent,
    UploadStatusComponent,
    PaginationComponent,
    ListLogComponent
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
    MatTreeModule,
    MatPaginatorModule,
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
  bootstrap: [AppComponent,DatePipe]
})
export class AppModule { }

