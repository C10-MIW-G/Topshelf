import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { GroceryProduct } from './grocery-product';

@Injectable({ providedIn: 'root' })
export class GroceryProductService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {}

  public getGroceryProductsByPantry(
    pantryId: number
  ): Observable<GroceryProduct[]> {
    return this.http.get<GroceryProduct[]>(
      `${this.apiServerUrl}/groceryproduct/${pantryId}`
    );
  }

  getGroceryProduct(groceryProductId: number): Observable<GroceryProduct> {
    return this.http.get<GroceryProduct>(`${this.apiServerUrl}/${groceryProductId}`)
  }

  public saveGroceryProductToPantryStock(
    groceryProductEdit: GroceryProduct
  ): Observable<any> {
    return this.http.post(
      `${this.apiServerUrl}/groceryproduct/add`,
      {
        name: groceryProductEdit.name,
        amount: groceryProductEdit.amount,
        pantryId: groceryProductEdit.pantryId,
        groceryProductId: groceryProductEdit.groceryProductId
      }
    );
  }
}
