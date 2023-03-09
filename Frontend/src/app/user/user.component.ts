import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Pantry } from '../pantry/pantry';
import { User } from './user';
import { UserService } from './user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
})
export class UserComponent implements OnInit {
  public form: any = [];
  public isSuccessful = false;
  public isExistingUser = false;
  public isLoading = false;
  public isAdmin = false;
  public errorMessage: string = '';

  public pantries?: Pantry[] = [];
  public users: User[] = [];
  public admins: User[] = [];
  public namePantry!: string;
  public user!: User;
  public pantryId!: number;

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private toastr: ToastrService
  ) {}

  inviteUserToPantryForm = new FormGroup({
    email: new FormControl('', [
      Validators.required,
      Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$'),
    ]),
  });

  ngOnInit(): void {
    this.getUsers();
    this.getAdmins();
    this.getCurrentPantryAdmin();
  }

  public getUsers(): any {
    const id = Number(this.route.snapshot.paramMap.get('pantryId'));
    this.pantryId = id;
    this.userService.getUsersByPantry(id).subscribe(
      (response: User[]) => {
        this.users = response;
        console.log(response);
      },
      (_error: HttpErrorResponse) => {
        this.errorMessage = 'Unable to retrieve the users';
      }
    );
  }

  public getAdmins(): any {
    const id = Number(this.route.snapshot.paramMap.get('pantryId'));
    this.pantryId = id;
    this.userService.getAdminsByPantry(id).subscribe(
      (response: User[]) => {
        this.admins = response;
        console.log(response);
      },
      (_error: HttpErrorResponse) => {
        this.errorMessage = 'Unable to retrieve the admins';
      }
    );
  }

  public getCurrentPantryAdmin(): any {
    const id = Number(this.route.snapshot.paramMap.get('pantryId'));
    this.pantryId = id;
    this.userService.checkIfUserIsPantryAdmin(id).subscribe(
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

  public saveUserToPantry(): void {
    const id = Number(this.route.snapshot.paramMap.get('pantryId'));
    this.pantryId = id;
    const emailUser = String(this.inviteUserToPantryForm.value.email);
    this.isLoading = true;
    this.userService.inviteUserToPantry(emailUser, id).subscribe(
      () => {
        console.log(emailUser);
        this.isSuccessful = true;
        this.toastr.success('User has been invited', 'Invitation success!', {
          positionClass: 'toast-top-center',
        });
        this.inviteUserToPantryForm.reset();
        window.location.reload;
        this.isLoading = false;
      },
      (_error: HttpErrorResponse) => {
        this.isLoading = false;
        if (_error.status == 403) {
          this.errorMessage = 'User is already part of the pantry';
        }
        if (_error.status == 400) {
          this.errorMessage = "User doesn't have an account";
        }
      }
    );
  }

  public getPantryName() {
    const id = this.route.snapshot.queryParamMap.get('name')!;
    this.namePantry = id;
  }

  public get email() {
    return this.inviteUserToPantryForm.get('email');
  }
}
