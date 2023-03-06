import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

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
      this.pantryName = params['name'];
    });
  }
}
