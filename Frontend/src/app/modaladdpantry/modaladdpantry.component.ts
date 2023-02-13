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
  isSuccesfull: boolean = false;
  hasFailed: boolean = false;
  errormessage?: string;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<ModaladdpantryComponent>,
    @Inject(MAT_DIALOG_DATA) data: any
  ) {
    this.pantryName = data.name;
  }

  ngOnInit() {
    this.form = this.fb.group({
      pantryName: new FormControl('', [Validators.required]),
    });
  }

  close() {
    this.dialogRef.close(this.form.value);
  }

  save() {
    if (this.form.value.pantryName === null) {
      this.hasFailed = true;
      this.errormessage = 'Fill in a pantryname';
    }

    if (this.form.value.pantryName !== null) {
      this.dialogRef.close(this.form.value);
    }
  }

  public myError = (controlName: string, errorName: string) => {
    return this.form.controls[controlName].hasError(errorName);
  };
}
