import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { BasicStockProduct } from './basic-stock-product';
import { BasicStockProductService } from './basic-stock-product.service';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ModalAddBasicStockComponent } from '../modal-add-basic-stock/modal-add-basic-stock.component';

@Component({
  selector: 'app-basic-stock-product',
  templateUrl: './basic-stock-product.component.html',
  styleUrls: ['./basic-stock-product.component.css'],
})
export class BasicStockProductComponent implements OnInit {
  public basicStockProducts?: BasicStockProduct[] = [];
  public pantryWithBasicStockProducts: BasicStockProduct[] = [];
  public namePantry!: string;
  public pantryId!: number;
  public basicStockProductId?: number;
  public modalAddBasicStock!: ModalAddBasicStockComponent;
  public isSubmitted?: boolean;

  constructor(
    private basicStockProductService: BasicStockProductService,
    private router: Router,
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

  public getBasicStockProductsByPantryId(): void {
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
      this.saveBasicStockProduct(data, basicStockProduct);
    });
  }

  private saveBasicStockProduct(
    data: any,
    basicStockProduct: BasicStockProduct
  ) {
    if (data.basicStockProductName !== null && data.isSubmitted) {
      this.basicStockProductService
        .saveBasicStockProductToPantryStock({
          name: data.basicStockProductName,
          amount: data.amount,
          pantryId: this.getPantryId(),
          basicStockProductId: basicStockProduct.basicStockProductId,
        })
        .subscribe({
          complete: () => {
            window.location.reload();
          },
        });
    } else {
      window.location.reload();
    }
  }

  public isEmptyOrSpaces(str: string | null | undefined) {
    return str === null || str?.match(/[\S]/g) !== null;
  }

  editButtonClick(name: string) {
    this.router.navigate(['/edit', name]);
  }
}
