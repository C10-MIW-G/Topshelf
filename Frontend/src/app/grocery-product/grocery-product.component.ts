import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { GroceryProduct } from './grocery-product';
import { GroceryProductService } from './grocery-product.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-grocery-product',
  templateUrl: './grocery-product.component.html',
  styleUrls: ['./grocery-product.component.css'],
})
export class GroceryProductComponent implements OnInit {
  public groceryProducts?: GroceryProduct[] = [];
  public groceryProductId?: number;
  public pantryWithGroceryProducts: GroceryProduct[] = [];
  public namePantry!: string;
  public pantryId!: number;
  public GroceryProductId: number | undefined;

  addGroceryProductForm = new FormGroup({
    name: new FormControl(
      '',
      Validators.compose([Validators.required, Validators.pattern(/[\S]/g)])
    ),
    groceryProductId: new FormControl(0),
    amount: new FormControl(
      0,
      Validators.compose([
        Validators.required,
        Validators.min(1),
        Validators.max(2147483647),
      ])
    ),
  });

  constructor(
    private GroceryProductService: GroceryProductService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.getPantryName();
    this.getGroceryProductsByPantryId();
    this.getPantryId();
    console.log('pantryId: ' + this.pantryId);
  }

  get name() {
    return this.addGroceryProductForm.get('name');
  }
  get amount() {
    return this.addGroceryProductForm.get('amount');
  }

  public getPantryId(): number {
    this.route.parent?.params.subscribe((params) => {
      const response = params['pantryId'];
      this.pantryId = parseInt(response.split(';')[0], 10);
    });
    return this.pantryId;
  }

  getGroceryProduct(groceryProductId: number) {
    this.GroceryProductService.getGroceryProduct(groceryProductId).subscribe(
      (groceryProduct: GroceryProduct) =>
        this.showGroceryProductInForm(groceryProduct)
    );
  }

  public getPantryName() {
    const name = this.route.snapshot.queryParamMap.get('name')!;
    this.namePantry = name;
  }

  public getGroceryProductsByPantryId(): void {
    const id = this.getPantryId();
    this.GroceryProductService.getGroceryProductsByPantry(id).subscribe(
      (response: GroceryProduct[]) => {
        this.pantryWithGroceryProducts = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public save() {
    const nameValue = this.addGroceryProductForm.value.name;
    const amountValue = this.addGroceryProductForm.value.amount;
    const id = this.getPantryId();
    const groceryProductId = this.addGroceryProductForm.value.groceryProductId;

    if (this.isEmptyOrSpaces(nameValue) && amountValue! > 0) {
      this.GroceryProductService.saveGroceryProductToPantryStock({
        name: nameValue,
        groceryProductId: groceryProductId,
        pantryId: id,
        amount: amountValue,
      }).subscribe({
        complete: () => {
          console.log('Product has been added to stock grocery list');
          this.router.navigate(['/groceries', id]);
          window.location.reload();
        },
      });
    }
  }

  public showGroceryProductInForm(groceryProduct: GroceryProduct) {
    this.addGroceryProductForm.patchValue({
      groceryProductId: groceryProduct.groceryProductId,
      name: groceryProduct.name,
      amount: groceryProduct.amount,
    });
  }

  public isEmptyOrSpaces(str: string | null | undefined) {
    return str === null || str?.match(/[\S]/g) !== null;
  }

  editButtonClick(name: string) {
    this.router.navigate(['/edit', name]);
  }
}
