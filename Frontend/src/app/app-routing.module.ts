import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StockProductComponent } from './stock-product/stock-product.component';
import { PantryComponent } from './pantry/pantry.component';

const appRoute: Routes = [
  {path: '', redirectTo: 'pantry', pathMatch: 'full'},
  {path: 'pantry', component: PantryComponent},
  {path: 'pantry/:pantryId', component: StockProductComponent}
]

@NgModule({
  imports: [RouterModule.forRoot(appRoute)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
