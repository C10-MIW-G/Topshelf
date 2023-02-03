import { AppRoutingModule } from './app-routing.module';
import { RouterModule } from '@angular/router';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { PantryComponent } from './pantry/pantry.component';
<<<<<<< HEAD
import { PantryStockComponent } from './pantry-stock/pantry-stock.component';
import { AuthInterceptor } from './_helper/auth.interceptor';
=======
import { StockProductComponent } from './stock-product/stock-product.component';
import { AppRoutingModule } from './app-routing.module';
>>>>>>> de6f61bd4ba1074cb66ac32d5b6d306fb7978f6b

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    PantryComponent,
    StockProductComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule,
    AppRoutingModule,
    NgbModule
  ],
  providers: [{provide: HTTP_INTERCEPTORS,
    useClass : AuthInterceptor,
    multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
