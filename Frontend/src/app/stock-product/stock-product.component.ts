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
    this.getPantryId();
    this.getPantryIdWithStockProducts(this.pantryId);
  }

  addStockProductForm = new FormGroup({
    name: new FormControl('', Validators.required),
    expirationdate: new FormControl('', Validators.required),
  });

  public getPantryIdWithStockProducts(pantryId: number): void {
    const id = Number(this.route.snapshot.paramMap.get('pantryId'));
    this.pantryId = id;
    this.stockProductService.getPantryWithStockProducts(pantryId).subscribe(
      (response: StockProduct[]) => {
        this.pantryWithStockProducts = response;
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

    if (nameValue && expDateValue) {
      this.stockProductService
        .saveStockProductToPantryStock({
          name: nameValue,
          expirationDate: new Date(expDateValue),
          pantryId: this.getPantryId(),
        })
        .subscribe({
          complete: () => {
            console.log('Product has been added to pantry stock');
            this.router.navigate(['/pantry', this.pantryId]);
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
    const name = this.route.snapshot.queryParamMap.get('name')!;
    this.namePantry = name;
  }

  public getPantryId(): number {
    this.route.parent?.params.subscribe((params) => {
      const response = params['pantryId'];
      this.pantryId = parseInt(response.split(';')[0], 10);
    });
    return this.pantryId;
  }
}
