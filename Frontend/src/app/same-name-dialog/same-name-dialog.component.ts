import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-same-name-dialog',
  templateUrl: './same-name-dialog.component.html',
  styleUrls: ['./same-name-dialog.component.css'],
})
export class SameNameDialogComponent implements OnInit {
  name?: string;

  constructor(
    private dialogRef: MatDialogRef<SameNameDialogComponent>,

    @Inject(MAT_DIALOG_DATA) data: any
  ) {
    this.name = data.name;
  }

  ngOnInit(): void {
    this.dialogRef.updateSize('450px', '350px');
  }
}
