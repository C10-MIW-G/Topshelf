import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ModalAddPantryComponent } from '../modal-add-pantry/modal-add-pantry.component';
import { ModalDeletePantryComponent } from '../modal-delete-pantry/modal-delete-pantry.component';
import { Pantry } from '../pantry/pantry';
import { PantryService } from '../pantry/pantry.service';

@Component({
  selector: 'app-action-bar-pantry-detail',
  templateUrl: './action-bar-pantry-detail.component.html',
  styleUrls: ['./action-bar-pantry-detail.component.css']
})
export class ActionBarPantryDetailComponent {
  public pantryId!: number;
  public pantry!: Pantry;
  public errorMessage?: string;
  public namePantry!: string;

  constructor(
    private pantryService: PantryService,
    private route: ActivatedRoute,
    private router: Router,
    private matDialog: MatDialog,
    private toastr: ToastrService
  ) {}

  ngOnInit() {
    this.getPantryId();
    this.getPantryName();
  }

  deleteDialog() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.data = {
      name: this.namePantry,
      isSubmitted: true,
    };

    const dialogRef = this.matDialog.open(
      ModalDeletePantryComponent,
      dialogConfig
    );

    dialogRef.afterClosed().subscribe((data) => {
      this.remove(data);
    });
  }

  public remove(data: any) {
    if(data){
    this.pantryService
      .deletePantry(this.pantryId)
      .subscribe(() => {
        this.router.navigate(['/pantry']);
        this.toastr.success('Pantry has been deleted!', 'Success!', {
          positionClass: 'toast-top-center',
        })
      }),
      (_error: HttpErrorResponse) => {
        this.errorMessage = "Pantry was not deleted, please try again"
      };
    } else {
      window.location.reload();
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
    const name = this.route.snapshot.queryParamMap.get('name')!;
    this.namePantry = name;
  }

  editOpenDialog() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.data = {
      pantryId: this.pantryId,
      name: this.namePantry,
      isSubmitted: true,
    };
    dialogConfig.height = '40%';
    dialogConfig.width = '60%';
    const dialogRef = this.matDialog.open(
      ModalAddPantryComponent,
      dialogConfig
    );

    dialogRef.afterClosed().subscribe((data) => {
      console.log(this.pantryId)
      console.log(data)
      if (data.isSubmitted) {
        this.pantryService.editPantry({pantryId: this.pantryId, name: data.pantryName }).subscribe({
          complete: () => {
            this.router.navigate(["/pantry"]);
            this.toastr.success('Pantry has been edited!', 'Success!', {
          positionClass: 'toast-top-center',
        })
          },
          error: () => {
            alert('pantry niet toegevoegd');
          },
        });
      }
    });
  }
}
