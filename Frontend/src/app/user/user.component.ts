import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from './user';
import { UserService } from './user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
})
export class UserComponent implements OnInit {
  public errorMessage: string = '';
  public namePantry!: string;
  public users: User[] = [];
  public admins: User[] = [];
  public pantryId!: number;

  constructor(
    private userService: UserService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.getPantryName();
    this.getPantryId();
    this.getUsers();
    this.getAdmins();
  }

  public getUsers(): any {
    this.userService.getUsersByPantry(this.pantryId).subscribe(
      (response: User[]) => {
        this.users = response;
      },
      (_error: HttpErrorResponse) => {
        this.errorMessage = 'Unable to retrieve the users';
      }
    );
  }

  public getAdmins(): any {
    this.userService.getAdminsByPantry(this.pantryId).subscribe(
      (response: User[]) => {
        this.admins = response;
      },
      (_error: HttpErrorResponse) => {
        this.errorMessage = 'Unable to retrieve the admins';
      }
    );
  }

  public getPantryName() {
    this.route.queryParams.subscribe((params) => {
      this.namePantry = params['name'];
    });
  }

  public getPantryId() {
    this.route.parent?.params.subscribe((params) => {
      const response = params['pantryId'];
      this.pantryId = parseInt(response.split(';')[0], 10);
    });
    return this.pantryId;
  }
}
