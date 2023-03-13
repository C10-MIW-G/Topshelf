import {
  FormBuilder,
  FormGroup,
  Validators,
  FormControl,
} from '@angular/forms';
import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-modal-add-pantry',
  templateUrl: './modal-add-pantry.component.html',
  styleUrls: ['./modal-add-pantry.component.css'],
})
export class ModalAddPantryComponent implements OnInit {
  form!: FormGroup;
  pantryName: string;
  hasFailed: boolean = false;
  errormessage?: string;
  isSubmitted: boolean;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<ModalAddPantryComponent>,
    @Inject(MAT_DIALOG_DATA) data: any
  ) {
    this.pantryName = data.name;
    this.isSubmitted = data.isSubmitted;
    this.form = this.fb.group({
      pantryName: new FormControl(
        '',
        Validators.compose([Validators.required, Validators.pattern(/[\S]/g)])
      ),
      isSubmitted: this.isSubmitted,
    });
  }

  ngOnInit(): void {
    this.dialogRef.updateSize('450px', '350px');
  }

  close() {
    this.isSubmitted = false;
    this.dialogRef.close(this.isSubmitted);
  }

  save() {
    if (this.form.value.pantryName === null) {
      this.hasFailed = true;
      this.errormessage = 'Fill in a pantryname';
    } else {
      this.isSubmitted = true;
      this.dialogRef.close(this.form.value);
    }
  }

  public myError = (controlName: string, errorName: string) => {
    return this.form.controls[controlName].hasError(errorName);
  };
}
