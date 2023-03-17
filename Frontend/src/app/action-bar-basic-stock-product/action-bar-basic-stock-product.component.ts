import { Component } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { BasicStockProductService } from '../basic-stock-product/basic-stock-product.service';
import { ModalAddBasicStockComponent } from '../modal-add-basic-stock/modal-add-basic-stock.component';
import { SameNameDialogComponent } from '../same-name-dialog/same-name-dialog.component';

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
    private matDialog: MatDialog,
    private toastr: ToastrService
  ) {}

  openSameNameDialog(name?: string) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.data = {
      name: name,
    };

    const dialogRef = this.matDialog.open(
      SameNameDialogComponent,
      dialogConfig
    );
  }

  onOpenDialog(openNewModal?: boolean) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.data = {
      name: null,
      isSubmitted: true,
      openNewModal,
    };

    const dialogRef = this.matDialog.open(
      ModalAddBasicStockComponent,
      dialogConfig
    );

    dialogRef.afterClosed().subscribe((data) => {
      this.saveBasicStockProduct(data);
    });
  }

  private saveBasicStockProduct(data: any) {
    if (data.isSubmitted) {
      this.basicStockProductService
        .saveBasicStockProductToPantryStock({
          name: data.basicStockProductName,
          amount: data.amount,
          pantryId: this.getPantryId(),
          basicStockProductId: data.basicStockProductId,
        })
        .subscribe({
          complete: () => {
            if (data.openNewModal) {
              this.onOpenDialog(true);
              this.toastr.success(
                data.basicStockProductName + ' added!',
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
            this.openSameNameDialog(data.basicStockProductName);
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
