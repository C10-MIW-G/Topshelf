import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BasicStockProduct } from './basic-stock-product';
import { BasicStockProductService } from './basic-stock-product.service';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ModalAddBasicStockComponent } from '../modal-add-basic-stock/modal-add-basic-stock.component';
import { SameNameDialogComponent } from '../same-name-dialog/same-name-dialog.component';

@Component({
  selector: 'app-basic-stock-product',
  templateUrl: './basic-stock-product.component.html',
  styleUrls: ['./basic-stock-product.component.css'],
})
export class BasicStockProductComponent implements OnInit {
  public pantryWithBasicStockProducts: BasicStockProduct[] = [];
  public namePantry!: string;
  public pantryId!: number;

  constructor(
    private basicStockProductService: BasicStockProductService,
    private route: ActivatedRoute,
    private matDialog: MatDialog
  ) {}

  ngOnInit() {
    this.getPantryName();
    this.getPantryId();
    this.getBasicStockProductsByPantryId();
  }

  public getPantryId(): number {
    this.route.parent?.params.subscribe((params) => {
      const response = params['pantryId'];
      this.pantryId = parseInt(response.split(';')[0], 10);
    });
    return this.pantryId;
  }

  public getPantryName() {
    this.route.queryParams.subscribe((params) => {
      this.namePantry = params['name'];
    });
  }

  public getBasicStockProductsByPantryId(): any {
    this.basicStockProductService
      .getBasicStockProductsByPantry(this.pantryId)
      .subscribe(
        (response: BasicStockProduct[]) => {
          this.pantryWithBasicStockProducts = response;
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
  }

  public remove(basicStockProductId: number) {
    this.basicStockProductService
      .deleteBasicStockProductFromPantry(basicStockProductId)
      .subscribe(() => {
        this.getBasicStockProductsByPantryId;
        window.location.reload();
      }),
      (error: HttpErrorResponse) => {
        alert(error.message);
      };
  }

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

  editDialog(basicStockProduct: BasicStockProduct) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.data = {
      name: basicStockProduct?.name,
      amount: basicStockProduct?.amount,
      isSubmitted: true,
    };

    const dialogRef = this.matDialog.open(
      ModalAddBasicStockComponent,
      dialogConfig
    );

    dialogRef.afterClosed().subscribe((data) => {
      this.editBasicStockProduct(data, basicStockProduct);
    });
  }

  private editBasicStockProduct(
    data: any,
    basicStockProduct: BasicStockProduct
  ) {
    if (data.isSubmitted) {
      this.basicStockProductService
        .editBasicStockProduct({
          name: data.basicStockProductName,
          amount: data.amount,
          pantryId: this.getPantryId(),
          basicStockProductId: basicStockProduct.basicStockProductId,
        })
        .subscribe({
          complete: () => {
            window.location.reload();
          },
          error: () => {
            this.openSameNameDialog(data.basicStockProductName);
          },
        });
    } else {
      window.location.reload();
    }
  }
}
