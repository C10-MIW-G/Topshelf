import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PantryStockComponent } from './pantry-stock/pantry-stock.component';
import { PantryComponent } from './pantry/pantry.component';

const appRoute: Routes = [
  {path: '', redirectTo: 'pantry', pathMatch: 'full'},
  {path: 'pantry', component: PantryComponent},
  {path: 'pantry/:pantryId', component: PantryStockComponent}
]

@NgModule({
  imports: [RouterModule.forRoot(appRoute)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
