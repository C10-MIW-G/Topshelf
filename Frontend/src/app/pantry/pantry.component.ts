import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Pantry } from './pantry';
import { PantryService } from './pantry.service';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ModaladdpantryComponent } from '../modaladdpantry/modaladdpantry.component';

@Component({
  selector: 'app-pantry',
  templateUrl: './pantry.component.html',
  styleUrls: ['./pantry.component.css'],
})
export class PantryComponent implements OnInit {
  public pantries?: Pantry[] = [];
  errorMessage: string = '';
  unAuthorizedUser = true;

  constructor(
    private pantryService: PantryService,
    private matDialog: MatDialog
  ) {}

  ngOnInit() {
    this.getPantriesByUser();
  }

  public getPantriesByUser(): void {
    this.pantryService.getPantriesForUser().subscribe(
      (response: Pantry[]) => {
        this.pantries = response;
      },
      (error: HttpErrorResponse) => {
        this.errorMessage = 'Unable to retrieve the pantries';
      }
    );
  }

  public getPantries(): void {
    this.pantryService.getPantries().subscribe(
      (response: Pantry[]) => {
        this.pantries = response;

        this.unAuthorizedUser = true;
      },
      (error: HttpErrorResponse) => {
        this.errorMessage = 'You are not logged in';

        this.unAuthorizedUser = false;
      }
    );
  }
  onOpenDialog() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.data = {
      name: null,
      isSubmitted: true,
    };
    dialogConfig.height = '40%';
    dialogConfig.width = '60%';
    const dialogRef = this.matDialog.open(
      ModaladdpantryComponent,
      dialogConfig
    );

    dialogRef.afterClosed().subscribe((data) => {
      console.log(data);
      if (data.pantryName !== null && data.isSubmitted) {
        this.pantryService.addPantry({ name: data.pantryName }).subscribe({
          complete: () => {
            window.location.reload();
          },
          error: () => {
            alert('pantry niet toegevoegd');
          },
        });
      }
    });
  }
}
