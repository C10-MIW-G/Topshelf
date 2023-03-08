import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { StockProductService } from '../stock-product/stock-product.service';

@Component({
  selector: 'app-action-bar-stock-product',
  templateUrl: './action-bar-stock-product.component.html',
  styleUrls: ['./action-bar-stock-product.component.css'],
})
export class ActionBarStockProductComponent {
  addStockProductForm = new FormGroup({
    name: new FormControl('', Validators.required),
    expirationdate: new FormControl('', Validators.required),
  });
  isSubmitted: boolean | undefined;

  pantryId!: number;

  constructor(
    private stockProductService: StockProductService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

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

  public getPantryId(): number {
    this.route.parent?.params.subscribe((params) => {
      const response = params['pantryId'];
      this.pantryId = parseInt(response.split(';')[0], 10);
    });
    return this.pantryId;
  }
}
