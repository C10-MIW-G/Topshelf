import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Pantry } from './pantry';
import { PantryService } from './pantry.service';
import { ProductDefinition } from './productdefinition';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  public pantries: Pantry[] = [];
  public productdefinitions: ProductDefinition[] = [];
  myImage: string = "assets/images/TopshelfLogoTemporary.png"

  constructor(private pantryService: PantryService){}

  ngOnInit() {
    this.getPantries();
  }

  public getPantries(): void {
    this.pantryService.getPantries().subscribe(
      (response: Pantry[]) => {
        this.pantries = response;
        console.log(this.pantries);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
