import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { BasicStockProduct } from './basic-stock-product';
import { BasicStockProductService } from './basic-stock-product.service';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ModaleditbasicstockComponent } from '../modaleditbasicstock/modaleditbasicstock.component';

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
  public BasicStockProductId: number | undefined;

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

  openEditModal(basicStockProductedit: BasicStockProduct): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.data = {
      name: basicStockProductedit.name,
      basicStockProductId: basicStockProductedit.basicStockProductId,
      amount: basicStockProductedit.amount,
      isSubmitted: true,
    };

    const dialogRef = this.matDialog.open(
      ModaleditbasicstockComponent,
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
            basicStockProductId: basicStockProductedit.basicStockProductId,
          })
          .subscribe({
            complete: () => {
              window.location.reload();
            },
            error: () => {
              alert('Update failed');
            },
          });
      }
    });
  }

  public isEmptyOrSpaces(str: string | null | undefined) {
    return str === null || str?.match(/[\S]/g) !== null;
  }

  editButtonClick(name: string) {
    this.router.navigate(['/edit', name]);
  }
}
