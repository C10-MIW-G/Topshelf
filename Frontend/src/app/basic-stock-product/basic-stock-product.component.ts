import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { BasicStockProduct } from './basic-stock-product';
import { BasicStockProductService } from './basic-stock-product.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-basic-stock-product',
  templateUrl: './basic-stock-product.component.html',
  styleUrls: ['./basic-stock-product.component.css'],
})
export class BasicStockProductComponent implements OnInit {
  public basicStockProducts?: BasicStockProduct[] = [];
  public basicStockProductId?: number;
  public pantryWithBasicStockProducts?: BasicStockProduct[] = [];
  public namePantry!: string;
  public pantryId!: number;

  addBasicStockProductForm = new FormGroup({
    name: new FormControl('', Validators.required),
    amount: new FormControl(0, Validators.required),
  });

  constructor(
    private basicStockProductService: BasicStockProductService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.getPantryName();
    this.getPantryIdWithBasicStockProducts();
  }

  public getPantryIdWithBasicStockProducts(): void {
    const id = Number(this.route.snapshot.paramMap.get('pantryId'));
    this.pantryId = id;
    this.basicStockProductService.getPantryWithBasicStockProducts(id).subscribe(
      (response: BasicStockProduct[]) => {
        this.pantryWithBasicStockProducts = response;
        console.log(response);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public save() {
    const nameValue = this.addBasicStockProductForm.value.name;
    const amountValue = this.addBasicStockProductForm.value.amount;
    const id = Number(this.route.snapshot.paramMap.get('pantryId'));

    if (nameValue && amountValue) {
      this.basicStockProductService
        .saveBasicStockProductToPantryStock({
          name: nameValue,
          amount: amountValue,
          pantryId: id,
        })
        .subscribe({
          complete: () => {
            console.log('Product has been added to pantry basic stock');
            this.router.navigate(['/basicstock', id]);
            window.location.reload();
          },
        });
    }
  }

  public getPantryName() {
    const id = this.route.snapshot.queryParamMap.get('name')!;
    this.namePantry = id;
  }
}
