import { LoginComponent } from './login/login.component';
import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PantryStockComponent } from './pantry-stock/pantry-stock.component';
import { PantryComponent } from './pantry/pantry.component';


const appRoute: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {path: 'pantry', component: PantryComponent},
  {path: 'pantry/:pantryId', component: PantryStockComponent},
  {path: 'login', component: LoginComponent}
]

@NgModule({
  imports: [RouterModule.forRoot(appRoute)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
