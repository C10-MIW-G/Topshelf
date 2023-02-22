import { Component, OnInit, Inject } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  FormControl,
} from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-modaladdbasicstock',
  templateUrl: './modaladdbasicstock.component.html',
  styleUrls: ['./modaladdbasicstock.component.css'],
})
export class ModaladdbasicstockComponent implements OnInit {
  form!: FormGroup;
  basicStockProductName: string;
  amount: number;
  hasFailed: boolean = false;
  errormessage?: string;
  isSubmitted: boolean;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<ModaladdbasicstockComponent>,
    @Inject(MAT_DIALOG_DATA) data: any
  ) {
    this.basicStockProductName = data.basicStockProductName;
    this.amount = data.amount;
    this.isSubmitted = data.isSubmitted;
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      basicStockProductName: new FormControl('', [Validators.required]),
      amount: new FormControl(
        '',
        Validators.compose([
          Validators.required,
          Validators.pattern("^[0-9]*$"),
          Validators.min(1),
          Validators.max(2147483647),
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
    if (this.form.value.basicStockProductName === null) {
      this.hasFailed = true;
      this.errormessage = 'Enter product name';
    } 
    if (this.form.value.amount === null) {
      this.hasFailed = true;
      this.errormessage = 'Enter a postive amount';
    }
    if (this.form.value.amount < 0) {
      this.hasFailed = true;
      this.errormessage = 'Amount has to be positive';
    } else {
      this.isSubmitted = true;
      this.dialogRef.close(this.form.value);
    }
  }

  public myError = (controlName: string, errorName: string) => {
    return this.form.controls[controlName].hasError(errorName);
  };
}
