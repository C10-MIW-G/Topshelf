import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-pantrydetail',
  templateUrl: './pantrydetail.component.html',
  styleUrls: ['./pantrydetail.component.css'],
})
export class PantrydetailComponent implements OnInit {
  pantryId!: number;

  constructor(private route: ActivatedRoute) {}

  ngOnInit() {
    this.getPantryId;
  }

  public getPantryId() {
    const id = Number(this.route.snapshot.paramMap.get('pantryId'));
    this.pantryId = id;
  }
}
