import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ModalStockProductComponent } from '../modal-stock-product/modal-stock-product.component';
import { StockProductService } from '../stock-product/stock-product.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-action-bar-stock-product',
  templateUrl: './action-bar-stock-product.component.html',
  styleUrls: ['./action-bar-stock-product.component.css'],
})
export class ActionBarStockProductComponent {
  pantryId!: number;

  constructor(
    private stockProductService: StockProductService,
    private route: ActivatedRoute,
    private matDialog: MatDialog,
    private toastr: ToastrService
  ) {}

  onOpenDialog(openNewModal?: boolean) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.data = {
      name: null,
      isSubmitted: true,
      openNewModal,
    };

    const dialogRef = this.matDialog.open(
      ModalStockProductComponent,
      dialogConfig
    );

    dialogRef.afterClosed().subscribe((data) => {
      this.saveProduct(data);
    });
  }

  private saveProduct(data: any) {
    if (
      data.isSubmitted &&
      data.basicStockProductName !== null &&
      data.amount !== null
    ) {
      this.stockProductService
        .saveStockProductToPantryStock({
          name: data.stockProductName,
          expirationDate: data.expirationDate,
          pantryId: this.getPantryId(),
          stockProductId: data.stockProductId,
        })
        .subscribe({
          complete: () => {
            if (data.openNewModal) {
              this.onOpenDialog(true);
              this.toastr.success(
                data.stockProductName + ' has been added!',
                'Success!',
                {
                  positionClass: 'toast-top-center',
                }
              );
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
