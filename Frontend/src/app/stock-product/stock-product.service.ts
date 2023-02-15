import { StockProductSave } from './stock-product-save';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { StockProduct } from './stock-product';
import { environment } from 'src/environments/environment';

@Injectable({ providedIn: 'root' })
export class StockProductService {
  userdata: any;
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {}

  public getPantryWithStockProducts(
    pantryId: number
  ): Observable<StockProduct[]> {
    return this.http.get<StockProduct[]>(
      `${this.apiServerUrl}/stockproduct/${pantryId}`
    );
  }

  public saveStockProductToPantryStock(
    stockProductSave: StockProductSave
  ): Observable<StockProductSave> {
    return this.http.post<StockProductSave>(
      `${this.apiServerUrl}/stockproduct/add`,
      {
        expirationDate: stockProductSave.expirationDate,
        name: stockProductSave.name,
        pantryId: stockProductSave.pantryId,
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
