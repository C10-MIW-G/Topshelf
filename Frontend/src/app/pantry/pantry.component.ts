import { Router } from '@angular/router';
import { PantryDTO } from './pantrydto';
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

  constructor(
    private pantryService: PantryService,
    private matDialog: MatDialog,
    private router: Router
  ) {}

  ngOnInit() {
    this.getPantries();
  }

  public getPantries(): void {
    this.pantryService.getPantries().subscribe(
      (response: Pantry[]) => {
        this.pantries = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  onOpenDialog() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.data = {
      name: null,
    };

    const dialogRef = this.matDialog.open(
      ModaladdpantryComponent,
      dialogConfig
    );

    dialogRef.afterClosed().subscribe((data) => {
      if (data.pantryName !== null) {
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
