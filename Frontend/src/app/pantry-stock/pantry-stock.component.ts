import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { PantryStock } from './pantry-stock';
import { PantryStockService } from './pantry-stock.service';

@Component({
  selector: 'app-pantry-stock',
  templateUrl: './pantry-stock.component.html',
  styleUrls: ['./pantry-stock.component.css']
})
export class PantryStockComponent implements OnInit{
  
  public pantryStock?: PantryStock;
  public pantryId!: number;

  constructor(private pantryStockService : PantryStockService) {
  }

  ngOnInit(): void {
    this.getPantryStock(this.pantryId)
  }

  public getPantryStock (pantryId: number): void {
    this.pantryStockService.getPantryStock(pantryId).subscribe(
      (response: PantryStock) => {
        this.pantryStock = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
      );
  }
}
