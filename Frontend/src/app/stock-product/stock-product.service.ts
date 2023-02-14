import { StockProductSave } from './stock-product-save';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { StockProduct } from './stock-product';

@Injectable({ providedIn: 'root' })
export class StockProductService {
  userdata: any;
  private apiServerUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  public getStockProducts(): Observable<StockProduct[]> {
    return this.http.get<StockProduct[]>(
      `${this.apiServerUrl}/stockproduct/all`
    );
  }

  public getPantryWithStockProducts(
    pantryId: number
  ): Observable<StockProduct[]> {
    return this.http.get<StockProduct[]>(
      `${this.apiServerUrl}/stockproduct/${pantryId}`
    );
  }

  public saveStockProductToPantryStock(
    stockProduct: StockProductSave
  ): Observable<StockProductSave> {
    return this.http.post<StockProductSave>(
      `${this.apiServerUrl}/stockproduct/add`,
      {
        expirationDate: stockProduct.expirationDate,
        name: stockProduct.name,
        pantryId: stockProduct.pantryId,
      }
    );
  }

  public deleteStockproductFromPantry(
    stockProductId: number
  ): Observable<void> {
    return this.http.delete<void>(
      `${this.apiServerUrl}/stockproduct/delete/${stockProductId}`
    );
  }
}
