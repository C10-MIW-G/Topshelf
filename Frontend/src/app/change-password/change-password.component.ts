import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { environment } from 'src/environments/environment';
import { AuthService } from '../_services/auth.service';
import { ChangePasswordRequest } from './change-password';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit{
  private apiServerUrl = environment.apiBaseUrl;
  changePasswordRequest:any = [];

  constructor(
    private authService: AuthService,
    private toastr: ToastrService,
    private router: Router
  ){}

  changePasswordForm= new FormGroup({
    password: new FormControl('', Validators.required),
    newPassword: new FormControl('', Validators.required),
    confirmPassword: new FormControl('', Validators.required)
  })

  get password() {return this.changePasswordForm.get('password')};
  get newPassword() {return this.changePasswordForm.get('newPassword')};
  get confirmPassword() {return this.changePasswordForm.get('confirmPassword')}
  
  ngOnInit(): void {}

  save(): void {
    const passw = this.changePasswordForm.value.password;
    const newPassw = this.changePasswordForm.value.newPassword;
    
    if (passw && newPassw) {
      this.authService.changePassword({
        password: passw,
        newPassword: newPassw
      })
      .subscribe({
        complete: () => {
          console.log('Password has been changed!');
          this.router.navigate(['/pantry']);
          window.location.reload();
        },
      });
  
    }
  }
}
