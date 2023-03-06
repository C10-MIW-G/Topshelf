import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StockProductComponent } from './stock-product/stock-product.component';
import { PantryComponent } from './pantry/pantry.component';
import { RegisterComponent } from './register/register.component';
import { BasicStockProductComponent } from './basic-stock-product/basic-stock-product.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { HomePageComponent } from './home-page/home-page.component';
import { UserComponent } from './user/user.component';

const appRoute: Routes = [
  { path: 'home', component: HomePageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'changepassword', component: ChangePasswordComponent},
  { path: 'resetpassword', component: ResetPasswordComponent},
  { path: 'pantry', component: PantryComponent },
  { path: 'pantry/:pantryId/users', component: UserComponent, pathMatch: 'full' },
  { path: 'edit/:id', component: BasicStockProductComponent},
  {
    path: 'pantry/:pantryId/basicstock',
    component: BasicStockProductComponent,
    pathMatch: 'full',
  },
  {
    path: 'pantry/:pantryId',
    component: StockProductComponent,
  },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(appRoute)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
