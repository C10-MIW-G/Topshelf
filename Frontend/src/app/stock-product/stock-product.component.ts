import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { StockProduct } from './stock-product';
import { StockProductService } from './stock-product.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-stock-product',
  templateUrl: './stock-product.component.html',
  styleUrls: ['./stock-product.component.css'],
})
export class StockProductComponent implements OnInit {
  public stockProducts?: StockProduct[] = [];
  public stockProductId?: number;
  public pantryWithStockProducts: StockProduct[] = [];
  public stockProductDelete?: StockProduct;
  public namePantry!: string;
  public pantryId!: number;
  public isSubmitted?: boolean = false;

  constructor(
    private stockProductService: StockProductService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.getPantryName();
    this.getPantryIdWithStockProducts();
  }

  addStockProductForm = new FormGroup({
    name: new FormControl('', Validators.required),
    expirationdate: new FormControl('', Validators.required),
  });

  public getPantryIdWithStockProducts(): void {
    const id = Number(this.route.snapshot.paramMap.get('pantryId'));
    this.pantryId = id;
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

  public save() {
    this.isSubmitted = true;
    const nameValue = this.addStockProductForm.value.name;
    const expDateValue = this.addStockProductForm.value.expirationdate;
    const id = Number(this.route.snapshot.paramMap.get('pantryId'));

    if (nameValue && expDateValue) {
      this.stockProductService
        .saveStockProductToPantryStock({
          name: nameValue,
          expirationDate: new Date(expDateValue),
          pantryId: id,
        })
        .subscribe({
          complete: () => {
            console.log('Product has been added to pantry stock');
            this.router.navigate(['/pantry', id]);
            window.location.reload();
          },
        });
    }
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
    const id = this.route.snapshot.queryParamMap.get('name')!;
    this.namePantry = id;
  }
}
