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
        groceryProductId: groceryProductEdit.groceryProductId,
        pantryId: groceryProductEdit.pantryId,
        name: groceryProductEdit.name,
        amount: groceryProductEdit.amount
      }
    );
  }

  public deleteGroceryProductFromPantry(
    groceryProductId: number
  ): Observable<void> {
    return this.http.delete<void>(
      `${this.apiServerUrl}/groceryproduct/delete/${groceryProductId}`
    );
  }
}
