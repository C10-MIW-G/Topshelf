import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StockProductComponent } from './stock-product/stock-product.component';
import { PantryComponent } from './pantry/pantry.component';
import { RegisterComponent } from './register/register.component';
import { BasicStockProductComponent } from './basic-stock-product/basic-stock-product.component';
import { ChangePasswordComponent } from './change-password/change-password.component';

const appRoute: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'pantry', component: PantryComponent },
  { path: 'changepassword', component: ChangePasswordComponent},
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

  { path: '', redirectTo: 'login', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(appRoute)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
