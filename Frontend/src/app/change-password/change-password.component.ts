import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { sequenceEqual } from 'rxjs';
import { AuthService } from '../_services/auth.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css'],
})
export class ChangePasswordComponent implements OnInit {
  changePasswordRequest: any = [];


  constructor(
    private authService: AuthService,
    private toastr: ToastrService,
    private router: Router
  ) {}

  changePasswordForm = new FormGroup({
    password: new FormControl('', Validators.required),
    newPassword: new FormControl('', [
      Validators.required,
      Validators.minLength(6),
    ]),
    confirmPassword: new FormControl(''),
  });

  get password() {
    return this.changePasswordForm.get('password');
  }
  get newPassword() {
    return this.changePasswordForm.get('newPassword');
  }

  ngOnInit(): void {}

  save(): void {
    const passw = this.changePasswordForm.value.password;
    const newPw = this.changePasswordForm.value.newPassword;
    const confirmPw = this.changePasswordForm.value.confirmPassword;
    // new password and confirmation password don't match
    if (newPw != confirmPw) {
      this.confirmDoesNotMatch();
      return;
    }
    // new password is the same as the old password
    if (passw == newPw) {
      this.samePassword();
      return;
    }
    // old password matches, and the new password is valid
    if (passw && newPw) {
      this.setNewPassword(passw, newPw);
    }
  }

  confirmDoesNotMatch(): void {
    this.toastr.error('Passwords did not match', 'Password change failed!', {
      positionClass: 'toast-top-center',
    });
  }

  samePassword(): void {
    this.toastr.error(
      'New password can not be the same',
      'Password change failed!',
      {
        positionClass: 'toast-top-center',
      }
    );
  }

  setNewPassword(passw: string, newPw: string): void {
    this.authService
      // change password in the backend
      .changePassword({ password: passw, newPassword: newPw })
      .subscribe({
        complete: () => {
          console.log('success');
          this.router.navigate(['/pantry']);
          this.toastr.success('Password has been changed!', 'Success!', {
            positionClass: 'toast-top-center',
          });
        },
        error: () => {
          console.log('error');
          this.toastr.error(
            'Password change failed!',
            'Your password did not match your old password!',
            {
              positionClass: 'toast-top-center',
            }
          );
        },
      });
  }

}
