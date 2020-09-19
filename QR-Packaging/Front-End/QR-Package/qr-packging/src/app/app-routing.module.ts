import { LoginPageComponent } from './login-page/login-page.component';
import { UserMainPageComponent } from './user-main-page/user-main-page.component';
import { AddressFormComponent } from './address-form/address-form.component';
import { HomepageEntrepreneurComponent } from './homepage-entrepreneur/homepage-entrepreneur.component';
import { HomepageComponent } from './homepage/homepage.component';
import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {
    path: 'guest',
    pathMatch: 'full',
    component: HomepageComponent
  },
  {
    path: 'entrepreneur',
    component: HomepageEntrepreneurComponent
  },
  {
    path: 'form',
    component: AddressFormComponent
  },
  {
    path: 'user',
    component: UserMainPageComponent
  },
  {
    path: 'welcome',
    component: LoginPageComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
