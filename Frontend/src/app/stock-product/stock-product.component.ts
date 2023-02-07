import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Pantry } from '../pantry/pantry';
import { StockProduct } from './stock-product';
import { StockProductService } from './stock-product.service';

@Component({
  selector: 'app-stock-product',
  templateUrl: './stock-product.component.html',
  styleUrls: ['./stock-product.component.css']
})
export class StockProductComponent implements OnInit{
  public stockProducts?: StockProduct[] = [];
  public stockProductId?: number;
  public pantryWithStockProducts?: StockProduct[] = [];

  constructor(
    private stockProductService : StockProductService,
    private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.getStockProducts();
    this.getPantryWithStockProducts();
  }

  public getStockProducts (): void {
    this.stockProductService.getStockProducts().subscribe(
      (response: StockProduct[]) => {
        this.stockProducts = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
      );
  }

  public getPantryWithStockProducts (): void {
    const id = Number(this.route.snapshot.paramMap.get('pantryId'))
    this.stockProductService.getPantryWithStockProducts(id).subscribe(
      (response: StockProduct[]) => {
        this.pantryWithStockProducts = response;
        console.log(response);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
      );
  }
}
