import { JwtHelperService, JwtModule } from '@auth0/angular-jwt';
import { AppRoutingModule } from './app-routing.module';
import { RouterModule } from '@angular/router';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {
  RECAPTCHA_SETTINGS,
  RecaptchaFormsModule,
  RecaptchaModule,
  RecaptchaSettings,
} from 'ng-recaptcha';
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
import { MatDialogModule } from '@angular/material/dialog';
import { ModalAddPantryComponent } from './modal-add-pantry/modal-add-pantry.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { environment } from 'src/environments/environment';
import { BasicStockProductComponent } from './basic-stock-product/basic-stock-product.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { PantrydetailComponent } from './pantrydetail/pantrydetail.component';
import { HomePageComponent } from './home-page/home-page.component';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatToolbarModule } from '@angular/material/toolbar';
import { GroceryProductComponent } from './grocery-product/grocery-product.component';
import { ActionBarStockProductComponent } from './action-bar-stock-product/action-bar-stock-product.component';
import { ActionBarBasicStockProductComponent } from './action-bar-basic-stock-product/action-bar-basic-stock-product.component';
import { ActionBarGroceryProductComponent } from './action-bar-grocery-product/action-bar-grocery-product.component';
import { ModalAddGroceryProductComponent } from './modal-add-grocery-product/modal-add-grocery-product.component';
import { ModalAddBasicStockComponent } from './modal-add-basic-stock/modal-add-basic-stock.component';
import { UserComponent } from './user/user.component';
import { ActionBarUserComponent } from './action-bar-user/action-bar-user.component';
import { ModalUserComponent } from './modal-user/modal-user.component';
import { ModalStockProductComponent } from './modal-stock-product/modal-stock-product.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import {
  MatMomentDateModule,
  MAT_MOMENT_DATE_ADAPTER_OPTIONS,
} from '@angular/material-moment-adapter';
import { MAT_DATE_LOCALE } from '@angular/material/core';
import { PantryDetailComponent } from './pantry-detail/pantry-detail.component';
import { ActionBarPantryDetailComponent } from './action-bar-pantry-detail/action-bar-pantry-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    PantryComponent,
    StockProductComponent,
    NavComponent,
    RegisterComponent,
    ModalAddPantryComponent,
    BasicStockProductComponent,
    ChangePasswordComponent,
    SidebarComponent,
    PantrydetailComponent,
    ResetPasswordComponent,
    HomePageComponent,
    GroceryProductComponent,
    ActionBarStockProductComponent,
    ActionBarBasicStockProductComponent,
    ActionBarGroceryProductComponent,
    ModalAddGroceryProductComponent,
    ModalAddBasicStockComponent,
    HomePageComponent,
    UserComponent,
    ActionBarUserComponent,
    ModalUserComponent,
    ActionBarUserComponent,
    ModalStockProductComponent,
    PantryDetailComponent,
    ActionBarPantryDetailComponent,
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
    ReactiveFormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    RecaptchaModule,
    RecaptchaFormsModule,
    MatCheckboxModule,
    MatToolbarModule,
    MatDatepickerModule,
    MatMomentDateModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: () => localStorage.getItem('auth-token'),
        allowedDomains: ['example.com'],
        disallowedRoutes: ['example.com/login'],
      },
    }),
  ],
  exports: [MatFormFieldModule],
  entryComponents: [ModalAddPantryComponent],
  providers: [
    JwtHelperService,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    {
      provide: RECAPTCHA_SETTINGS,
      useValue: {
        siteKey: environment.recaptcha.siteKey,
      } as RecaptchaSettings,
    },
    { provide: MAT_MOMENT_DATE_ADAPTER_OPTIONS, useValue: { useUtc: true } },
    { provide: MAT_DATE_LOCALE, useValue: 'nl' },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
