import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { BasicStockProduct } from './basic-stock-product';
import { BasicStockProductService } from './basic-stock-product.service';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ModaladdbasicstockComponent } from '../modaladdbasicstock/modaladdbasicstock.component';
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

  addBasicStockProductForm = new FormGroup({
    name: new FormControl(
      '',
      Validators.compose([Validators.required, Validators.pattern(/[\S]/g)])
    ),
    basicStockProductId: new FormControl(0),
    amount: new FormControl(
      0,
      Validators.compose([
        Validators.required,
        Validators.min(1),
        Validators.max(2147483647),
      ])
    ),
  });
  constructor(
    private basicStockProductService: BasicStockProductService,
    private router: Router,
    private route: ActivatedRoute,
    private matDialog: MatDialog
  ) {}

  ngOnInit() {
    this.getPantryName();
    this.getPantryId();
    console.log(this.pantryId + ' basicstockproduct');
    this.getBasicStockProductsByPantryId();
  }

  get name() {
    return this.addBasicStockProductForm.get('name');
  }
  get amount() {
    return this.addBasicStockProductForm.get('amount');
  }

  getBasicStockProduct(basicStockProductId: number) {
    this.basicStockProductService
      .getBasicStockProduct(basicStockProductId)
      .subscribe((basicStockProduct: BasicStockProduct) =>
        this.showBasicStockProductInForm(basicStockProduct)
      );
  }

  public getPantryName() {
    const id = this.route.snapshot.queryParamMap.get('name')!;
    this.namePantry = id;
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
  public save() {
    const nameValue = this.addBasicStockProductForm.value.name;
    const amountValue = this.addBasicStockProductForm.value.amount;
    const basicStockProductId =
      this.addBasicStockProductForm.value.basicStockProductId;

    if (this.isEmptyOrSpaces(nameValue) && amountValue! > 0) {
      this.basicStockProductService
        .saveBasicStockProductToPantryStock({
          name: nameValue,
          basicStockProductId: basicStockProductId,
          pantryId: this.pantryId,
          amount: amountValue,
        })
        .subscribe({
          complete: () => {
            console.log('Product has been added to pantry basic stock');
            this.router.navigate(['/basicstock', this.pantryId]);
            window.location.reload();
          },
        });
    }
  }

  public showBasicStockProductInForm(basicStockProduct: BasicStockProduct) {
    this.addBasicStockProductForm.patchValue({
      basicStockProductId: basicStockProduct.basicStockProductId,
      name: basicStockProduct.name,
      amount: basicStockProduct.amount,
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
            pantryId: Number(this.route.snapshot.paramMap.get('pantryId')),
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

  openEditModal(BasicStockProductedit: BasicStockProduct): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.data = {
      name: BasicStockProductedit.name,
      basicStockProductId: BasicStockProductedit.basicStockProductId,
      amount: BasicStockProductedit.amount,
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
            basicStockProductId: BasicStockProductedit.basicStockProductId,
            pantryId: Number(this.route.snapshot.paramMap.get('pantryId')),
            amount: data.amount,
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

  public getPantryId() {
    this.route.parent?.params.subscribe((params) => {
      const response = params['pantryId'];
      this.pantryId = response.split(';')[0];
    });
  }
}

