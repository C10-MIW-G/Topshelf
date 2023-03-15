import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Component, OnInit, Inject, HostListener } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  FormControl,
} from '@angular/forms';

@Component({
  selector: 'app-modal-stock-product',
  templateUrl: './modal-stock-product.component.html',
  styleUrls: ['./modal-stock-product.component.css'],
})
export class ModalStockProductComponent implements OnInit {
  form!: FormGroup;
  stockProductName: string;
  expirationDate: Date;
  isSubmitted: boolean;
  openNewModal?: boolean;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<ModalStockProductComponent>,
    @Inject(MAT_DIALOG_DATA) data: any
  ) {
    this.stockProductName = data.name;
    this.expirationDate = data.expirationDate;
    this.isSubmitted = data.isSubmitted;
    this.openNewModal = data.openNewModal;
    this.form = this.fb.group({
      stockProductName: new FormControl(
        this.stockProductName,
        Validators.compose([Validators.required, Validators.pattern(/[\S]/g)])
      ),
      expirationDate: new FormControl(
        this.expirationDate,
        Validators.compose([Validators.required])
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
