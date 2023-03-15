import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { StockProduct } from './stock-product';
import { StockProductService } from './stock-product.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-stock-product',
  templateUrl: './stock-product.component.html',
  styleUrls: ['./stock-product.component.css'],
})
export class StockProductComponent implements OnInit {
  public stockProductId?: number;
  public pantryWithStockProducts: StockProduct[] = [];
  public stockProductDelete?: StockProduct;
  public namePantry!: string;
  public pantryId!: number;
  public isSubmitted?: boolean = false;

  constructor(
    private stockProductService: StockProductService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.getPantryId();
    this.getPantryIdWithStockProducts();
    this.getPantryName();
  }

  addStockProductForm = new FormGroup({
    name: new FormControl('', Validators.required),
    expirationdate: new FormControl('', Validators.required),
  });

  public getPantryIdWithStockProducts(): void {
    this.stockProductService
      .getPantryWithStockProducts(this.getPantryId())
      .subscribe(
        (response: StockProduct[]) => {
          this.pantryWithStockProducts = response;
          console.log(response);
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
  }

  public remove(stockProduct: StockProduct) {
    this.stockProductService
      .deleteStockproductFromPantry(stockProduct.stockProductId)
      .subscribe((response: void) => {
        this.getPantryIdWithStockProducts;
        window.location.reload();
      }),
      (error: HttpErrorResponse) => {
        alert(error.message);
      };
  }

  public getPantryName() {
    this.route.queryParams.subscribe((params) => {
      this.namePantry = params['name'];
    });
  }

  public getPantryId(): number {
    this.route.parent?.params.subscribe((params) => {
      const response = params['pantryId'];
      this.pantryId = parseInt(response.split(';')[0], 10);
    });
    return this.pantryId;
  }
}
