import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../_services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  form: any = [];
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = 'Username already taken';

  constructor(private authService: AuthService, private router: Router) {}
  ngOnInit(): void {}

  onSubmit(): void {
    this.authService.register(this.form).subscribe(
      (response) => {
        console.log(response);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
        this.registrationComplete();
      },
      (error) => {
        this.errorMessage;
        this.isSignUpFailed = true;
      }
    );
  }

  registrationComplete(): void {
    this.router.navigate(['/login']);
  }
}
