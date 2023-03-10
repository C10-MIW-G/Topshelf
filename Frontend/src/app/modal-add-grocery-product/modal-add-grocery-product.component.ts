import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Component, OnInit, Inject } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  FormControl,
} from '@angular/forms';

@Component({
  selector: 'app-modal-add-grocery-product',
  templateUrl: './modal-add-grocery-product.component.html',
  styleUrls: ['./modal-add-grocery-product.component.css'],
})
export class ModalAddGroceryProductComponent implements OnInit {
  form!: FormGroup;
  groceryProductName: string;
  amount: number;
  hasFailed: boolean = false;
  errormessage?: string;
  isSubmitted: boolean;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<ModalAddGroceryProductComponent>,
    @Inject(MAT_DIALOG_DATA) data: any
  ) {
    this.groceryProductName = data.name;
    this.amount = data.amount;
    this.isSubmitted = data.isSubmitted;
    this.form = this.fb.group({
      groceryProductName: new FormControl(
        '',
        Validators.compose([Validators.required, Validators.pattern(/[\S]/g)])
      ),
      amount: new FormControl(
        '',
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
    console.log(groceryProductName + ' grocery-component');
    if (!this.isEmptyOrSpaces(groceryProductName)) {
      this.hasFailed = true;
      this.errormessage = 'Enter product name';
    }
    if (this.form.value.amount === null) {
      this.hasFailed = true;
      this.errormessage = 'Enter a postive amount';
    }
    if (
      this.form.value.amount < 0 ||
      !this.isEmptyOrSpaces(groceryProductName)
    ) {
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

  public isEmptyOrSpaces(str: string | null | undefined) {
    console.log(str);
    return str === null || str?.match(/[\S]/g) !== null;
  }
}
