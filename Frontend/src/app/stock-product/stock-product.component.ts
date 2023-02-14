import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { StockProduct } from './stock-product';
import { StockProductService } from './stock-product.service';
import { FormControl, FormGroup, Validators} from '@angular/forms'

@Component({
  selector: 'app-stock-product',
  templateUrl: './stock-product.component.html',
  styleUrls: ['./stock-product.component.css']
})
export class StockProductComponent implements OnInit{
  public stockProducts?: StockProduct[] = [];
  public stockProductId?: number;
  public pantryWithStockProducts?: StockProduct[] = [];

  addStockProductForm= new FormGroup({
    name: new FormControl('', Validators.required),
    expirationdate: new FormControl('', Validators.required)
  })

  collectData() {
  }

  constructor(
    private stockProductService : StockProductService,
    private router: Router,
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

  public save() {
    const nameValue = this.addStockProductForm.value.name;
    const expDateValue = this.addStockProductForm.value.expirationdate;
    const id = Number(this.route.snapshot.paramMap.get('pantryId'))

    if (nameValue && expDateValue) {
      this.stockProductService.saveStockProductToPantryStock({
        name: nameValue,
        expirationDate: new Date(expDateValue),
        pantryId: id
      }).subscribe({
        complete: ()=> {
          console.log("Product has been added to pantry stock");
          this.router.navigate(['/pantry', id]);
          window.location.reload();
        }
      })
    }
  }
}
