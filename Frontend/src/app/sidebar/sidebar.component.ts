import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule, Routes } from '@angular/router';
import { Pantry } from '../pantry/pantry';
import { User } from '../user/user';
import { UserService } from '../user/user.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css'],
})
export class SidebarComponent implements OnInit {
  pantryId!: number;
  initialPantryId!: number;
  pantryName?: string;
  pantry!: Pantry;
  isAdmin = false;
  user!: User;
  errorMessage?: string;

  constructor(
    private userService: UserService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.getPantryId();
    this.getPantryName();
    this.getInitialPantryId();
    if (this.initialPantryId !== undefined) {
      this.getInitialPantryAdmin(this.initialPantryId);
    }
    if (this.initialPantryId == undefined) {
      this.getCurrentPantryAdmin(this.pantryId);
    }
  }
  public getPantryId(): number {
    this.route.parent?.params.subscribe((params) => {
      const response = params['pantryId'];
      this.pantryId = parseInt(response.split(';')[0], 10);
    });
    return this.pantryId;
  }

  public getPantryName() {
    this.route.queryParams.subscribe((params) => {
      this.pantryName = params['name'];
    });
  }

  public getInitialPantryId() {
    this.route.queryParams.subscribe((params) => {
      this.initialPantryId = params['id'];
    });
  }

  public getInitialPantryAdmin(pantryId: number): any {
    this.userService.checkIfUserIsPantryAdmin(pantryId).subscribe(
      (response: User) => {
        this.user = response;
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

  public getCurrentPantryAdmin(pantryId: number): any {
    this.userService.checkIfUserIsPantryAdmin(pantryId).subscribe(
      (response: User) => {
        this.user = response;
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
}
