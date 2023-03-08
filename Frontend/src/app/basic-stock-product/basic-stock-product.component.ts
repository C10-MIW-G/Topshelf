import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { BasicStockProduct } from './basic-stock-product';
import { BasicStockProductService } from './basic-stock-product.service';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ModaladdbasicstockComponent } from '../modaladdbasicstock/modaladdbasicstock.component';
import { ToastrService } from 'ngx-toastr';

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
  public modaladdbasicstock!: ModaladdbasicstockComponent;
  public openNewModal?: boolean;

  constructor(
    private basicStockProductService: BasicStockProductService,
    private router: Router,
    private route: ActivatedRoute,
    private matDialog: MatDialog,
    private toastr: ToastrService
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

  public isEmptyOrSpaces(str: string | null | undefined) {
    return str === null || str?.match(/[\S]/g) !== null;
  }

  editButtonClick(name: string) {
    this.router.navigate(['/edit', name]);
  }
}
