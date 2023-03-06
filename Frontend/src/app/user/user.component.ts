import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Pantry } from '../pantry/pantry';
import { User } from './user';
import { UserService } from './user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
})
export class UserComponent implements OnInit {
  public pantries?: Pantry[] = [];
  public users: User[] = [];
  errorMessage: string = '';
  public namePantry!: string;
  public pantryId!: number;

  constructor(
    private userService: UserService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.getUsersByPantry();
  }

  public getPantriesByUser(): void {
    const id = Number(this.route.snapshot.paramMap.get('pantryId'));
    this.pantryId = id;
    this.userService.getPantriesForUser().subscribe(
      (response: Pantry[]) => {
        this.pantries = response;
      },
      (error: HttpErrorResponse) => {
        this.errorMessage = 'Unable to retrieve the pantries';
      }
    );
  }

  public getUsersByPantry(): void {
    const id = Number(this.route.snapshot.paramMap.get('pantryId'));
    this.pantryId = id;
    this.userService.getUsersByPantry(id).subscribe(
      (response: User[]) => {
        this.users = response;
      },
      (error: HttpErrorResponse) => {
        this.errorMessage = 'Unable to retrieve the users';
      }
    );
  }

  public getPantryName() {
    const id = this.route.snapshot.queryParamMap.get('name')!;
    this.namePantry = id;
  }
}
