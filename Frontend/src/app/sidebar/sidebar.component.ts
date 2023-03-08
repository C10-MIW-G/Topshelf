import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule, Routes } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css'],
})
export class SidebarComponent implements OnInit {
  pantryId?: number;
  pantryName?: string;

  constructor(private route: ActivatedRoute) {}

  ngOnInit() {
    this.route.parent?.params.subscribe((params) => {
      this.pantryId = params['pantryId'];
    });

    this.getPantryName();
  }

  public getPantryName() {
    this.route.queryParams.subscribe((params) => {
      this.pantryName = params['name'];
    });
  }
}
