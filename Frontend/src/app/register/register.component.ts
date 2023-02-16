import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../_services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  form: any = [];
  captchaResponse: string | undefined;
  isSuccessful = false;
  isSignUpFailed = false;
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
    this.authService.register(this.form, this.captchaResponse).subscribe(
      (response) => {
        // when registration is succesfull:
        console.log(response);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
        this.router.navigate(['/login']);
        this.toastr.success('You can now log in', 'Registration success!', {
          positionClass: 'toast-top-center',
        });
      },
      // when registration isnt succesfull:
      (error) => {
        this.isSignUpFailed = true;
        // registration failed for not using the recaptcha checkbox
        if (this.captchaResponse == undefined) {
          this.errorMessage = 'Please check reCaptcha';
        } else {
          // registration failed choosing a username which already exist. This error resets recaptcha checkbox
          this.errorMessage =
            'Username already taken, please choose a different username';
          this.captchaResponse = undefined;
        }
      }
    );
  }
}
