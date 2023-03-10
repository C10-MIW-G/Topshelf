import { ModalAddGroceryProductComponent } from './../modal-add-grocery-product/modal-add-grocery-product.component';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { GroceryProduct } from './grocery-product';
import { GroceryProductService } from './grocery-product.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';

@Component({
  selector: 'app-grocery-product',
  templateUrl: './grocery-product.component.html',
  styleUrls: ['./grocery-product.component.css'],
})
export class GroceryProductComponent implements OnInit {
  public groceryProductId?: number;
  public pantryWithGroceryProducts: GroceryProduct[] = [];
  public namePantry!: string;
  public pantryId!: number;

  constructor(
    private groceryProductService: GroceryProductService,
    private router: Router,
    private route: ActivatedRoute,
    private matDialog: MatDialog
  ) {}

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

  ngOnInit() {
    this.getPantryName();
    this.getGroceryProductsByPantryId();
    this.getPantryId();
  }

  public getPantryId(): number {
    this.route.parent?.params.subscribe((params) => {
      const response = params['pantryId'];
      this.pantryId = parseInt(response.split(';')[0], 10);
    });
    return this.pantryId;
  }

  public getPantryName() {
    const name = this.route.snapshot.queryParamMap.get('name')!;
    this.namePantry = name;
  }

  public remove(groceryProduct: GroceryProduct) {
    this.groceryProductService
      .deleteGroceryProductFromPantry(groceryProduct.groceryProductId)
      .subscribe(() => {
        this.getGroceryProductsByPantryId;
        window.location.reload();
      }),
      (error: HttpErrorResponse) => {
        alert(error.message);
      };
  }

  public getGroceryProductsByPantryId(): void {
    const id = this.getPantryId();
    this.groceryProductService.getGroceryProductsByPantry(id).subscribe(
      (response: GroceryProduct[]) => {
        this.pantryWithGroceryProducts = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  openEditModal(groceryProductEdit: GroceryProduct): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.data = {
      name: groceryProductEdit.name,
      groceryProductId: groceryProductEdit.groceryProductId,
      amount: groceryProductEdit.amount,
      isSubmitted: true,
    };

    const dialogRef = this.matDialog.open(
      ModalAddGroceryProductComponent,
      dialogConfig
    );

    dialogRef.afterClosed().subscribe((data) => {
      if (data.name !== null && data.isSubmitted) {
        this.groceryProductService
          .saveGroceryProductToPantryStock({
            name: data.groceryProductName,
            amount: data.amount,
            pantryId: this.getPantryId(),
            groceryProductId: groceryProductEdit.groceryProductId,
          })
          .subscribe({
            complete: () => {
              window.location.reload();
            },
            error: () => {
              console.log(data.name);
              alert('Update failed');
            },
          });
      }
    });
  }
}
