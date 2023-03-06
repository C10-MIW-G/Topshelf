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
import { GroceryProductComponent } from './grocery-product/grocery-product.component';

const appRoute: Routes = [
  { path: 'home', component: HomePageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'changepassword', component: ChangePasswordComponent },
  { path: 'resetpassword', component: ResetPasswordComponent },
  {
    path: 'pantry',
    component: PantryComponent,
  },
  { path: 'edit/:id', component: BasicStockProductComponent },
  {
    path: 'pantry/:pantryId/groceries',
    component: GroceryProductComponent,
    pathMatch: 'full',
  },
  {
    path: 'pantry/:pantryId',
    component: PantrydetailComponent,
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
        path: 'basicstock',
        component: BasicStockProductComponent,
        outlet: 'stockproduct',
      },
      {
        path: 'stock',
        component: StockProductComponent,
        outlet: 'stockproduct',
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
