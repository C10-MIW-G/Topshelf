import { Component } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { BasicStockProductService } from '../basic-stock-product/basic-stock-product.service';
import { ModaladdbasicstockComponent } from '../modaladdbasicstock/modaladdbasicstock.component';

@Component({
  selector: 'app-action-bar-basic-stock-product',
  templateUrl: './action-bar-basic-stock-product.component.html',
  styleUrls: ['./action-bar-basic-stock-product.component.css'],
})
export class ActionBarBasicStockProductComponent {
  pantryId!: number;

  constructor(
    private basicStockProductService: BasicStockProductService,
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
      ModaladdbasicstockComponent,
      dialogConfig
    );

    dialogRef.afterClosed().subscribe((data) => {
      console.log(data);
      if (data.basicStockProductName !== null && data.isSubmitted) {
        this.basicStockProductService
          .saveBasicStockProductToPantryStock({
            name: data.basicStockProductName,
            amount: data.amount,
            pantryId: this.getPantryId(),
            basicStockProductId: data.basicStockProductId,
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
    });
  }

  public getPantryId(): number {
    this.route.parent?.params.subscribe((params) => {
      const response = params['pantryId'];
      this.pantryId = parseInt(response.split(';')[0], 10);
    });
    return this.pantryId;
  }
}
