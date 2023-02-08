import { AppRoutingModule } from './app-routing.module';
import { RouterModule } from '@angular/router';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { PantryComponent } from './pantry/pantry.component';
import { AuthInterceptor } from './_helper/auth.interceptor';
import { StockProductComponent } from './stock-product/stock-product.component';
import { RegisterComponent } from './register/register.component';
import { CommonModule } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { NavComponent } from './nav/nav.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    PantryComponent,
    StockProductComponent,
    NavComponent,
    StockProductComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule,
    AppRoutingModule,
    NgbModule,
    CommonModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    ReactiveFormsModule
  ],
  providers: [{provide: HTTP_INTERCEPTORS,
    useClass : AuthInterceptor,
    multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
