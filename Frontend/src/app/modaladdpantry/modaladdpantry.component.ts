import {
  FormBuilder,
  FormGroup,
  Validators,
  FormControl,
} from '@angular/forms';
import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-modaladdpantry',
  templateUrl: './modaladdpantry.component.html',
  styleUrls: ['./modaladdpantry.component.css'],
})
export class ModaladdpantryComponent implements OnInit {
  form!: FormGroup;
  pantryName: string;
  hasFailed: boolean = false;
  errormessage?: string;
  isSubmitted: boolean;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<ModaladdpantryComponent>,
    @Inject(MAT_DIALOG_DATA) data: any
  ) {
    this.pantryName = data.name;
    this.isSubmitted = data.isSubmitted;
    this.form = this.fb.group({
      pantryName: new FormControl('', [Validators.required]),
      isSubmitted: this.isSubmitted,
    });
  }

  ngOnInit() {
    this.dialogRef.updateSize('25%', '50%');
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
