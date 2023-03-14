import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Component, OnInit, Inject } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  FormControl,
} from '@angular/forms';

@Component({
  selector: 'app-modal-stock-product',
  templateUrl: './modal-stock-product.component.html',
  styleUrls: ['./modal-stock-product.component.css']
})
export class ModalStockProductComponent implements OnInit{
  form!: FormGroup;
  stockProductName: string;
  expirationDate: Date;
  hasFailed: boolean = false;
  errormessage?: string;
  isSubmitted: boolean;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<ModalStockProductComponent>,
    @Inject(MAT_DIALOG_DATA) data: any
  ) {
    this.stockProductName = data.name;
    this.expirationDate = data.expirationDate;
    this.isSubmitted = data.isSubmitted;
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      stockProductName: new FormControl('', [Validators.required]),
      expirationDate: new FormControl(
        '',
        Validators.compose([
          Validators.required,
        ])
      ),
      isSubmitted: this.isSubmitted,
    });
  }

  close() {
    this.isSubmitted = false;
    this.dialogRef.close(this.isSubmitted);
  }

  save() {
    const stockProductName = this.form.value.stockProductName;
    console.log(this.form.value.expirationDate)
    if (!this.isEmptyOrSpaces(stockProductName)) {
      this.hasFailed = true;
      this.errormessage = 'Enter product name';
    }
    if (this.form.value.expirationDate === null) {
      this.hasFailed = true;
      this.errormessage = 'Enter an expirationDate';
    }
    else {
      this.isSubmitted = true;
      this.dialogRef.close(this.form.value);
    }
  }

  public myError = (controlName: string, errorName: string) => {
    return this.form.controls[controlName].hasError(errorName);
  };

  public isEmptyOrSpaces(str: string | null | undefined) {
    console.log(str);
    return str === null || str?.match(/[\S]/g) !== null;
  }

}
