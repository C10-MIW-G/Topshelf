import { Component, HostListener, Inject, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  FormControl,
} from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-modal-add-basic-stock',
  templateUrl: './modal-add-basic-stock.component.html',
  styleUrls: ['./modal-add-basic-stock.component.css'],
})
export class ModalAddBasicStockComponent implements OnInit {
  form!: FormGroup;
  basicStockProductName: string;
  amount: number;
  isSubmitted: boolean;
  openNewModal?: boolean;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<ModalAddBasicStockComponent>,
    @Inject(MAT_DIALOG_DATA) data: any
  ) {
    this.basicStockProductName = data.name;
    this.amount = data.amount;
    this.isSubmitted = data.isSubmitted;
    this.openNewModal = data.openNewModal;
    this.form = this.fb.group({
      basicStockProductName: new FormControl(
        this.basicStockProductName,
        Validators.compose([Validators.required, Validators.pattern(/[\S]/g)])
      ),
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
