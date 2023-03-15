import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Component, OnInit, Inject, HostListener } from '@angular/core';
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
  isSubmitted: boolean;
  openNewModal?: boolean;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<ModalAddGroceryProductComponent>,
    @Inject(MAT_DIALOG_DATA) data: any
  ) {
    this.groceryProductName = data.name;
    this.amount = data.amount;
    this.isSubmitted = data.isSubmitted;
    this.openNewModal = data.openNewModal;
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
      openNewModal: new FormControl(this.openNewModal),
    });
  }

  @HostListener('document:keypress', ['$event'])
  keyEvent(event: KeyboardEvent) {
    if (event.key === "Enter") {
      this.save();
    }
  }

  ngOnInit(): void {
    this.dialogRef.updateSize('450px', '350px');
  }

  close() {
    this.isSubmitted = false;
    this.dialogRef.close(this.isSubmitted);
  }

  save() {
    this.dialogRef.close(this.form.value);
  }
}
