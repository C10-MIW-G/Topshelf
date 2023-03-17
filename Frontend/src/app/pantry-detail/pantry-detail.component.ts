import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BasicStockProduct } from '../basic-stock-product/basic-stock-product';
import { BasicStockProductService } from '../basic-stock-product/basic-stock-product.service';
import { GroceryProduct } from '../grocery-product/grocery-product';
import { GroceryProductService } from '../grocery-product/grocery-product.service';
import { StockProduct } from '../stock-product/stock-product';
import { StockProductService } from '../stock-product/stock-product.service';
import { User } from '../user/user';
import { UserService } from '../user/user.service';

@Component({
  selector: 'app-pantry-detail',
  templateUrl: './pantry-detail.component.html',
  styleUrls: ['./pantry-detail.component.css'],
})
export class PantryDetailComponent implements OnInit {
  public stockProducts: StockProduct[] = [];
  public basicStockProducts: BasicStockProduct[] = [];
  public groceryProducts: GroceryProduct[] = [];
  public users: User[] = [];
  public admins: User[] = [];
  public namePantry!: string;
  public pantryId!: number;
  public errorMessage?: string;

  constructor(
    private route: ActivatedRoute,
    private stockProductService: StockProductService,
    private basicStockProductService: BasicStockProductService,
    private groceryProductService: GroceryProductService,
    private userService: UserService
  ) {}

  ngOnInit() {
    this.getPantryId();
    this.getPantryName();
    this.getStockProducts();
    this.getBasicStockProducts();
    this.getGroceryProducts();
    this.getUsers();
    this.getAdmins();
  }

  public getSizeOfLists() {}

  public getStockProducts(): any {
    this.stockProductService
      .getPantryWithStockProducts(this.pantryId)
      .subscribe(
        (response: StockProduct[]) => {
          this.stockProducts = response;
        },
        (_error: HttpErrorResponse) => {
          this.errorMessage = 'Unable to retrieve the stock products';
        }
      );
  }

  public getBasicStockProducts(): any {
    this.basicStockProductService
      .getBasicStockProductsByPantry(this.pantryId)
      .subscribe(
        (response: BasicStockProduct[]) => {
          this.basicStockProducts = response;
        },
        (_error: HttpErrorResponse) => {
          this.errorMessage = 'Unable to retrieve the basic stock products';
        }
      );
  }

  public getGroceryProducts(): any {
    this.groceryProductService
      .getGroceryProductsByPantry(this.pantryId)
      .subscribe(
        (response: GroceryProduct[]) => {
          this.groceryProducts = response;
        },
        (_error: HttpErrorResponse) => {
          this.errorMessage = 'Unable to retrieve the grocery products';
        }
      );
  }

  public getUsers(): any {
    this.userService.getUsersByPantry(this.pantryId).subscribe(
      (response: User[]) => {
        this.users = response;
      },
      (_error: HttpErrorResponse) => {
        this.errorMessage = 'Unable to retrieve the users';
      }
    );
  }

  public getAdmins(): any {
    this.userService.getAdminsByPantry(this.pantryId).subscribe(
      (response: User[]) => {
        this.admins = response;
      },
      (_error: HttpErrorResponse) => {
        this.errorMessage = 'Unable to retrieve the admins';
      }
    );
  }

  public getPantryName() {
    this.route.queryParams.subscribe((params) => {
      this.namePantry = params['name'];
    });
  }

  public getPantryId() {
    this.route.parent?.params.subscribe((params) => {
      const response = params['pantryId'];
      this.pantryId = parseInt(response.split(';')[0], 10);
    });
    return this.pantryId;
  }
}
