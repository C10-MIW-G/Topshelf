import { Component, HostListener, Inject, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  FormControl,
} from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-modal-user',
  templateUrl: './modal-user.component.html',
  styleUrls: ['./modal-user.component.css'],
})
export class ModalUserComponent implements OnInit {
  form!: FormGroup;
  emailUser!: string;
  isSubmitted: boolean;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<ModalUserComponent>,
    @Inject(MAT_DIALOG_DATA) data: any
  ) {
    this.emailUser = data.email;
    this.isSubmitted = data.isSubmitted;
    this.form = this.fb.group({
      inviteUserToPantryForm: new FormControl(this.emailUser, [
        Validators.required,
        Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$'),
      ]),
      isSubmitted: this.isSubmitted,
      openNewModal: new FormControl(true),
    });
  }

  @HostListener('document:keypress', ['$event'])
  keyEvent(event: KeyboardEvent) {
    if (event.key === 'Enter') {
      this.form.markAllAsTouched();
      if (this.form.valid) {
        this.save();
      }
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
