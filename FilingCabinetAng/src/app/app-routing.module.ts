import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthComponent } from './component/auth/auth.component';
import { CatalogComponent } from './component/catalog/catalog.component';
import { Error404Component } from './component/error404/error404.component';
import { HomeComponent } from './component/home/home.component';
import { ListPatientFolderComponent } from './component/list-patient-folder/list-patient-folder.component';
import { PaginationComponent } from './component/pagination/pagination.component';
import { WelcomeComponent } from './component/welcome/welcome.component';
import { AuthGuardService } from './service/auth-guard.service';

const routes: Routes = [
  {path:'pagination',component:PaginationComponent},
  {path:'catalog',component:CatalogComponent,canActivate:[AuthGuardService]},
  {path:'home',component:HomeComponent,canActivate:[AuthGuardService]},
  {path:'listPatientFolder',component:ListPatientFolderComponent,canActivate:[AuthGuardService]},
  {path:'auth',component:AuthComponent},
  {path:'welcome',component:WelcomeComponent},
  {path:'',redirectTo: 'welcome', pathMatch: 'full'},
  {path:'**', component:Error404Component}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
