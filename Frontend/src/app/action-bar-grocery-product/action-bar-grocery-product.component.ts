import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { GroceryProductService } from '../grocery-product/grocery-product.service';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ModalAddGroceryProductComponent } from './../modal-add-grocery-product/modal-add-grocery-product.component';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-action-bar-grocery-product',
  templateUrl: './action-bar-grocery-product.component.html',
  styleUrls: ['./action-bar-grocery-product.component.css'],
})
export class ActionBarGroceryProductComponent {
  pantryId!: number;
  openNewModal?: boolean;

  constructor(
    private groceryProductService: GroceryProductService,
    private route: ActivatedRoute,
    private matDialog: MatDialog,
    private toastr: ToastrService
  ) {}

  onOpenDialog() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.data = {
      name: null,
      isSubmitted: true,
    };

    const dialogRef = this.matDialog.open(
      ModalAddGroceryProductComponent,
      dialogConfig
    );

    dialogRef.afterClosed().subscribe((data) => {
      this.saveProduct(data);
    });
  }

  private saveProduct(data: any) {
    if (data.isSubmitted) {
      this.groceryProductService
        .saveGroceryProductToPantryStock({
          name: data.groceryProductName,
          amount: data.amount,
          pantryId: this.getPantryId(),
          groceryProductId: data.groceryProductId,
        })
        .subscribe({
          complete: () => {
            if (data.openNewModal == true) {
              this.onOpenDialog();
              this.toastr.success('Product added!', 'Success!', {
                positionClass: 'toast-top-center',
              });
              this.openNewModal = true;
            } else {
              window.location.reload();
            }
          },
          error: () => {
            alert('Failed adding product');
          },
        });
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
}
