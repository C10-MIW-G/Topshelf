import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ModalStockProductComponent } from '../modal-stock-product/modal-stock-product.component';
import { StockProductService } from '../stock-product/stock-product.service';

@Component({
  selector: 'app-action-bar-stock-product',
  templateUrl: './action-bar-stock-product.component.html',
  styleUrls: ['./action-bar-stock-product.component.css'],
})
export class ActionBarStockProductComponent {
  isSubmitted: boolean | undefined;

  pantryId!: number;

  constructor(
    private stockProductService: StockProductService,
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
      ModalStockProductComponent,
      dialogConfig
    );

    dialogRef
      .afterClosed()
      .subscribe(
        (data: {
          stockProductName: null;
          expirationDate: any;
          isSubmitted: any;
          stockProductId: any;
        }) => {
          this.saveProduct(data);
        }
      );
  }

  private saveProduct(data: {
    expirationDate: Date;
    stockProductName: null;
    isSubmitted: any;
    stockProductId: any;
  }) {
    if (data.stockProductName !== null && data.isSubmitted) {
      this.stockProductService
        .saveStockProductToPantryStock({
          name: data.stockProductName,
          expirationDate: data.expirationDate,
          pantryId: this.getPantryId(),
          stockProductId: data.stockProductId,
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

  public getPantryId(): number {
    this.route.parent?.params.subscribe((params) => {
      const response = params['pantryId'];
      this.pantryId = parseInt(response.split(';')[0], 10);
    });
    return this.pantryId;
  }
}
