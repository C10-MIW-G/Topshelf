import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StockProductComponent } from './stock-product/stock-product.component';
import { PantryComponent } from './pantry/pantry.component';
import { RegisterComponent } from './register/register.component';

const appRoute: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'pantry/:pantryId', component: StockProductComponent},
  {path: 'pantry', component: PantryComponent}
]

@NgModule({
  imports: [RouterModule.forRoot(appRoute)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
