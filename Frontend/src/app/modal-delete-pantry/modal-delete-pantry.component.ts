import { FormGroup } from '@angular/forms';
import { Component, HostListener, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-modal-delete-pantry',
  templateUrl: './modal-delete-pantry.component.html',
  styleUrls: ['./modal-delete-pantry.component.css'],
})
export class ModalDeletePantryComponent implements OnInit {
  form!: FormGroup;
  pantryName: string;
  hasFailed: boolean = false;
  errormessage?: string;
  isSubmitted: boolean;

  constructor(
    private dialogRef: MatDialogRef<ModalDeletePantryComponent>,
    @Inject(MAT_DIALOG_DATA) data: any
  ) {
    this.pantryName = data.name;
    this.isSubmitted = data.isSubmitted;
  }

  @HostListener('document:keypress', ['$event'])
  keyEvent(event: KeyboardEvent) {
    if (event.key === 'Enter') {
      this.delete();
    }
  }

  ngOnInit(): void {
    this.dialogRef.updateSize('450px', '350px');
  }

  close() {
    this.isSubmitted = false;
    this.dialogRef.close(this.isSubmitted);
  }

  delete() {
    this.isSubmitted = true;
    this.dialogRef.close(this.isSubmitted);
  }
}
