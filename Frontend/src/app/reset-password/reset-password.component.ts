import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../_services/auth.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent {
  public form: any = [];
  public captchaResponse: string | undefined;
  public isSuccessful = false;
  public isResetFailed = false;
  public isLoading = false;
  errorMessage = '';

  constructor(
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService
  ) {
    this.captchaResponse = undefined;
  }

  ngOnInit(): void {}

  onSubmit(): void {
    //this.form contains the data of the user. this.captcharesponse contains the SiteKey.
    this.isLoading = true;
    this.authService.resetPassword(this.form, this.captchaResponse).subscribe(
      (response) => {
        // when registration is succesfull:
        console.log(response);
        this.isSuccessful = true;
        this.isResetFailed = false;
        this.isLoading = false;
        this.router.navigate(['/login']);
        this.toastr.success('Check your mail', 'Password reset', {
          positionClass: 'toast-top-center',
        });
      },
      // when password reset isnt succesfull:
      (error) => {
        this.isResetFailed = true;
        this.isLoading = false;
        // password reset failed for not using the recaptcha checkbox
        if (this.captchaResponse == undefined) {
          this.errorMessage = 'Please check reCaptcha';
        } else {
          // password reset failed choosing a username which already exists. This error resets recaptcha checkbox
          this.errorMessage =
            "Your email wasn't found or was invalid. \nTry again or create an account.";
          this.captchaResponse = undefined;
        }
      }
    );
  }
}
