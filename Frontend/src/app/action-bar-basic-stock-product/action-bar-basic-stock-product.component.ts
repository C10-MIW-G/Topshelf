import { Component } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { BasicStockProduct } from '../basic-stock-product/basic-stock-product';
import { BasicStockProductService } from '../basic-stock-product/basic-stock-product.service';
import { ModaladdbasicstockComponent } from '../modaladdbasicstock/modaladdbasicstock.component';

@Component({
  selector: 'app-action-bar-basic-stock-product',
  templateUrl: './action-bar-basic-stock-product.component.html',
  styleUrls: ['./action-bar-basic-stock-product.component.css'],
})
export class ActionBarBasicStockProductComponent {
  pantryId!: number;
  basicStockProductId?: number;
  openNewModal?: boolean;

  constructor(
    private basicStockProductService: BasicStockProductService,
    private route: ActivatedRoute,
    private matDialog: MatDialog,
    private toastr: ToastrService
  ) {}

  onOpenDialog(basicStockProduct?: BasicStockProduct) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.data = {
      name: basicStockProduct?.name,
      amount: basicStockProduct?.amount,
      isSubmitted: true
    };

    const dialogRef = this.matDialog.open(
      ModaladdbasicstockComponent,
      dialogConfig
    );

    dialogRef.afterClosed().subscribe((data) => {
      this.saveBasicStockProduct(data, basicStockProduct);
    });
  }

  private saveBasicStockProduct(
    data: any,
    basicStockProduct: BasicStockProduct | undefined
  ) {
    if (data.basicStockProductName !== null && data.isSubmitted) {
      if (basicStockProduct?.basicStockProductId !== null) {
        this.basicStockProductId = basicStockProduct?.basicStockProductId;
      } else {
        this.basicStockProductId = data.basicStockProductId;
      }
      this.basicStockProductService
        .saveBasicStockProductToPantryStock({
          name: data.basicStockProductName,
          amount: data.amount,
          pantryId: this.getPantryId(),
          basicStockProductId: this.basicStockProductId,
        })
        .subscribe({
          complete: () => {
            if (data.openNewModal == true) {
              this.onOpenDialog();
              this.toastr.success('Success!', 'Product added!', {
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
