import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { StockProduct } from './stock-product';
import { StockProductService } from './stock-product.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ModalStockProductComponent } from '../modal-stock-product/modal-stock-product.component';

@Component({
  selector: 'app-stock-product',
  templateUrl: './stock-product.component.html',
  styleUrls: ['./stock-product.component.css'],
})
export class StockProductComponent implements OnInit {
  public pantryWithStockProducts: StockProduct[] = [];
  public namePantry!: string;
  public pantryId!: number;

  constructor(
    private stockProductService: StockProductService,
    private route: ActivatedRoute,
    private matDialog: MatDialog
  ) {}

  ngOnInit() {
    this.getPantryId();
    this.getPantryIdWithStockProducts();
    this.getPantryName();
  }

  public getPantryIdWithStockProducts(): void {
    this.stockProductService
      .getPantryWithStockProducts(this.getPantryId())
      .subscribe(
        (response: StockProduct[]) => {
          this.pantryWithStockProducts = response;
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
  }

  public remove(stockProduct: StockProduct) {
    this.stockProductService
      .deleteStockproductFromPantry(stockProduct.stockProductId)
      .subscribe((response: void) => {
        this.getPantryIdWithStockProducts;
        window.location.reload();
      }),
      (error: HttpErrorResponse) => {
        alert(error.message);
      };
  }

  public getPantryName() {
    this.route.queryParams.subscribe((params) => {
      this.namePantry = params['name'];
    });
  }

  public getPantryId(): number {
    this.route.parent?.params.subscribe((params) => {
      const response = params['pantryId'];
      this.pantryId = parseInt(response.split(';')[0], 10);
    });
    return this.pantryId;
  }

  editDialog(stockProduct: StockProduct) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.data = {
      name: stockProduct.name,
      expirationDate: stockProduct.expirationDate,
      isSubmitted: true,
    };

    const dialogRef = this.matDialog.open(
      ModalStockProductComponent,
      dialogConfig
    );

    dialogRef.afterClosed().subscribe((data) => {
      this.saveStockProduct(data, stockProduct);
    });
  }

  private saveStockProduct(data: any, stockProduct: StockProduct) {
    if (data.isSubmitted) {
      this.stockProductService
        .saveStockProductToPantryStock({
          expirationDate: data.expirationDate,
          name: data.stockProductName,
          pantryId: this.getPantryId(),
          stockProductId: stockProduct.stockProductId,
        })
        .subscribe({
          complete: () => {
            window.location.reload();
          },
          error: () => {
            alert('Edit failed');
          },
        });
    } else {
      window.location.reload();
    }
  }
}
