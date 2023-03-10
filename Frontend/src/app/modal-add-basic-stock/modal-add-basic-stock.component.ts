import { Component, Inject, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  FormControl,
} from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { BasicStockProductComponent } from '../basic-stock-product/basic-stock-product.component';
import { BasicStockProduct } from '../basic-stock-product/basic-stock-product';
import { BasicStockProductService } from '../basic-stock-product/basic-stock-product.service';

@Component({
  selector: 'app-modal-add-basic-stock',
  templateUrl: './modal-add-basic-stock.component.html',
  styleUrls: ['./modal-add-basic-stock.component.css'],
})
export class ModalAddBasicStockComponent implements OnInit {
  form!: FormGroup;
  basicStockProductName: string;
  amount: number;
  hasFailed: boolean = false;
  errormessage?: string;
  isSubmitted: boolean;
  openNewModal!: boolean;
  basicStockProductsToBeAdded: BasicStockProduct[] = [];
  basicStockProcuctService!: BasicStockProductService;
  public basicStockProductComponent!: BasicStockProductComponent;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<ModalAddBasicStockComponent>,
    @Inject(MAT_DIALOG_DATA) data: any
  ) {
    this.basicStockProductName = data.name;
    this.amount = data.amount;
    this.isSubmitted = data.isSubmitted;
    this.form = this.fb.group({
      basicStockProductName: new FormControl(this.basicStockProductName, [
        Validators.required,
      ]),
      amount: new FormControl(
        this.amount,
        Validators.compose([
          Validators.required,
          Validators.pattern('^[0-9]*$'),
          Validators.min(1),
          Validators.max(2147483647),
        ])
      ),
      isSubmitted: this.isSubmitted,
      openNewModal: new FormControl(true),
    });
  }

  ngOnInit(): void {
    this.dialogRef.updateSize('25%', '50%');
  }

  close() {
    this.isSubmitted = false;
    this.dialogRef.close(this.isSubmitted);
  }

  save() {
    const groceryProductName = this.form.value.groceryProductName;
    this.dialogRef.close(this.form.value);
  }

  public myError = (controlName: string, errorName: string) => {
    return this.form.controls[controlName].hasError(errorName);
  };
}
