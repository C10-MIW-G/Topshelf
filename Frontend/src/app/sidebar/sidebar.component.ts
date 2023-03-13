import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule, Routes } from '@angular/router';
import { Pantry } from '../pantry/pantry';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css'],
})
export class SidebarComponent implements OnInit {
  pantryId?: number;
  initialPantryId?: number;
  pantryName?: string;
  pantry!: Pantry;

  constructor(private route: ActivatedRoute) {}

  ngOnInit() {
    this.route.parent?.params.subscribe((params) => {
      this.pantryId = params['pantryId'];
    });
    this.getPantryName();
    this.getPantryId();
  }

  public getPantryName() {
    this.route.queryParams.subscribe((params) => {
      this.pantryName = params['name'];
    });
  }

  public getPantryId() {
    this.route.queryParams.subscribe((params) => {
      this.initialPantryId = params['id'];
    });
  }
}
