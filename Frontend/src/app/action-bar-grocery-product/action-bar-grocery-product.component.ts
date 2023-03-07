import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { GroceryProductService } from '../grocery-product/grocery-product.service';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ModalAddGroceryProductComponent } from './../modal-add-grocery-product/modal-add-grocery-product.component';

@Component({
  selector: 'app-action-bar-grocery-product',
  templateUrl: './action-bar-grocery-product.component.html',
  styleUrls: ['./action-bar-grocery-product.component.css'],
})
export class ActionBarGroceryProductComponent {
  pantryId!: number;
  basicStockProductService: any;

  constructor(
    private groceryProductService: GroceryProductService,
    private route: ActivatedRoute,
    private matDialog: MatDialog
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

    dialogRef
      .afterClosed()
      .subscribe(
        (data: {
          groceryProductName: null;
          isSubmitted: any;
          amount: any;
          groceryProductId: any;
        }) => {
          if (data.groceryProductName !== null && data.isSubmitted) {
            this.groceryProductService
              .saveGroceryProductToPantryStock({
                name: data.groceryProductName,
                amount: data.amount,
                pantryId: this.getPantryId(),
                groceryProductId: data.groceryProductId,
              })
              .subscribe({
                complete: () => {
                  window.location.reload();
                },
                error: () => {
                  alert('Failed adding product');
                },
              });
          }
        }
      );
  }

  public isEmptyOrSpaces(str: string | null | undefined) {
    return str === null || str?.match(/[\S]/g) !== null;
  }

  public getPantryId(): number {
    this.route.parent?.params.subscribe((params) => {
      const response = params['pantryId'];
      this.pantryId = parseInt(response.split(';')[0], 10);
    });
    return this.pantryId;
  }
}
