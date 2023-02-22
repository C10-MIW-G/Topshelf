import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { BasicStockProduct } from './basic-stock-product';
import { BasicStockProductService } from './basic-stock-product.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ModaladdbasicstockComponent } from '../modaladdbasicstock/modaladdbasicstock.component';

@Component({
  selector: 'app-basic-stock-product',
  templateUrl: './basic-stock-product.component.html',
  styleUrls: ['./basic-stock-product.component.css'],
})
export class BasicStockProductComponent implements OnInit {
  public basicStockProducts?: BasicStockProduct[] = [];
  public basicStockProductId?: number;
  public pantryWithBasicStockProducts: BasicStockProduct[] = [];
  public namePantry!: string;
  public pantryId!: number;
  errorMessage: string = '';

  addBasicStockProductForm = new FormGroup({
    name: new FormControl(
      '',
      Validators.compose([Validators.required, Validators.pattern(/[\S]/g)])
    ),
    amount: new FormControl(
      0,
      Validators.compose([
        Validators.required,
        Validators.min(0),
        Validators.max(2147483647),
      ])
    ),
  });

  get name() {
    return this.addBasicStockProductForm.get('name');
  }
  get amount() {
    return this.addBasicStockProductForm.get('amount');
  }

  constructor(
    private basicStockProductService: BasicStockProductService,
    private router: Router,
    private route: ActivatedRoute,
    private matDialog: MatDialog
  ) {}

  ngOnInit() {
    this.getPantryName();
    this.getBasicStockProductsByPantryId();
  }

  public getPantryName() {
    const id = this.route.snapshot.queryParamMap.get('name')!;
    this.namePantry = id;
  }

  public getBasicStockProductsByPantryId(): void {
    const id = Number(this.route.snapshot.paramMap.get('pantryId'));
    this.pantryId = id;
    this.basicStockProductService.getBasicStockProductsByPantry(id).subscribe(
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
    const id = Number(this.route.snapshot.paramMap.get('pantryId'));

    if (this.isEmptyOrSpaces(nameValue) && amountValue! > 0) {
      this.basicStockProductService
        .saveBasicStockProductToPantryStock({
          name: nameValue,
          amount: amountValue,
          pantryId: id,
        })
        .subscribe({
          complete: () => {
            console.log('Product has been added to pantry basic stock');
            this.router.navigate(['/basicstock', id]);
            window.location.reload();
          },
        });
    }
  }

  onOpenDialog() {
    const dialogConfig = new MatDialogConfig();
    const id = Number(this.route.snapshot.paramMap.get('pantryId'));
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
        this.basicStockProductService.saveBasicStockProductToPantryStock({ name: data.basicStockProductName, pantryId: id, amount: data.amount  }).subscribe({
          complete: () => {
            window.location.reload();
          },
          error: () => {
            alert('Product niet toegevoegd');
          },
        });
      }
    });
  }

  public isEmptyOrSpaces(str: string | null | undefined) {
    return str === null || str?.match(/[\S]/g) !== null;
  }
}
