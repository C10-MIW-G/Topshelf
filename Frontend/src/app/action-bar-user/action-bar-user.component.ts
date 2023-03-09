import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { User } from '../user/user';
import { UserService } from '../user/user.service';

@Component({
  selector: 'app-action-bar-user',
  templateUrl: './action-bar-user.component.html',
  styleUrls: ['./action-bar-user.component.css'],
})
export class ActionBarUserComponent {
  public isSuccessful = false;
  public isLoading = false;
  public errorMessage: string = '';
  public isAdmin = false;
  public user!: User;
  public namePantry!: string;
  public pantryId!: number;

  inviteUserToPantryForm = new FormGroup({
    email: new FormControl('', [
      Validators.required,
      Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$'),
    ]),
  });

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private toastr: ToastrService
  ) {}

  ngOnInit() {
    this.getPantryName();
    this.getPantryId();
    this.getCurrentPantryAdmin(this.pantryId);
  }

  public saveUserToPantry(pantryId: number): any {
    const emailUser = String(this.inviteUserToPantryForm.value.email);
    this.isLoading = true;
    
    this.userService.inviteUserToPantry(emailUser, pantryId).subscribe(
      () => {
        console.log(emailUser);
        this.isSuccessful = true;
        this.inviteUserToPantryForm.reset();
        this.isLoading = false;
        window.location.reload;
        this.toastr.success('User has been invited', 'Invitation success!', {
          positionClass: 'toast-top-center',
        });
      },
      (_error: HttpErrorResponse) => {
        this.isLoading = false;
        this.isSuccessful = false;
        if (_error.status == 403) {
          this.errorMessage = 'User is already part of the pantry';
        }
        if (_error.status == 400) {
          this.errorMessage = "User doesn't have an account";
        }
      }
    );
  }

  public getCurrentPantryAdmin(pantryId: number): any {
    this.userService.checkIfUserIsPantryAdmin(pantryId).subscribe(
      (response: User) => {
        this.user = response;
        console.log(response);
        this.isAdmin = true;
        if (response == null) {
          this.isAdmin = false;
        }
      },
      (_error: HttpErrorResponse) => {
        if (_error.status == 400) {
          this.isAdmin = false;
          this.errorMessage = 'Current user is not an admin of this pantry';
        }
      }
    );
  }

  public getPantryName() {
    this.route.queryParams.subscribe((params) => {
      this.namePantry = params['name'];
    });
  }

  public getPantryId(): number {
    this.route.parent?.params.subscribe((params) => {
      const response = params['pantryId'];
      this.pantryId = parseInt(response.split(';')[0], 10);
    });
    return this.pantryId;
  }

  public get email() {
    return this.inviteUserToPantryForm.get('email');
  }
}
