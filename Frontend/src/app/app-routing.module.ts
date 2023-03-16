import { ActionBarGroceryProductComponent } from './action-bar-grocery-product/action-bar-grocery-product.component';
import { ActionBarBasicStockProductComponent } from './action-bar-basic-stock-product/action-bar-basic-stock-product.component';
import { ActionBarStockProductComponent } from './action-bar-stock-product/action-bar-stock-product.component';
import { PantrydetailComponent } from './pantrydetail/pantrydetail.component';
import { SidebarComponent } from './sidebar/sidebar.component';
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
import { GroceryProductComponent } from './grocery-product/grocery-product.component';
import { ActionBarUserComponent } from './action-bar-user/action-bar-user.component';
import { PantryDetailComponent } from './pantry-detail/pantry-detail.component';
import { ActionBarPantryDetailComponent } from './action-bar-pantry-detail/action-bar-pantry-detail.component';

import { AuthGuardService as AuthGuard } from './_services/auth-guard.service';

const appRoute: Routes = [
  { path: 'home', component: HomePageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {
    path: 'changepassword',
    component: ChangePasswordComponent,
    canActivate: [AuthGuard],
  },
  { path: 'resetpassword', component: ResetPasswordComponent },
  { path: 'pantry', component: PantryComponent, canActivate: [AuthGuard] },
  {
    path: 'pantry/:pantryId',
    component: PantrydetailComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: '',
        component: SidebarComponent,
        outlet: 'sidebar',
      },
      {
        path: '',
        component: StockProductComponent,
        outlet: 'stockproduct',
      },
      {
        path: '',
        component: ActionBarStockProductComponent,
        outlet: 'action',
      },
      {
        path: 'basicstock',
        component: BasicStockProductComponent,
        outlet: 'stockproduct',
      },
      {
        path: 'basicstock',
        component: ActionBarBasicStockProductComponent,
        outlet: 'action',
      },
      {
        path: 'stock',
        component: StockProductComponent,
        outlet: 'stockproduct',
      },
      {
        path: 'stock',
        component: ActionBarStockProductComponent,
        outlet: 'action',
      },
      {
        path: 'groceries',
        component: GroceryProductComponent,
        outlet: 'stockproduct',
      },
      {
        path: 'groceries',
        component: ActionBarGroceryProductComponent,
        outlet: 'action',
      },
      {
        path: 'users',
        component: UserComponent,
        outlet: 'stockproduct',
      },
      {
        path: 'users',
        component: ActionBarUserComponent,
        outlet: 'action',
      },
      {
        path: 'pantrydetail',
        component: PantryDetailComponent,
        outlet: 'stockproduct',
      },
      {
        path: 'pantrydetail',
        component: ActionBarPantryDetailComponent,
        outlet: 'action',
      },
    ],
  },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(appRoute)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
