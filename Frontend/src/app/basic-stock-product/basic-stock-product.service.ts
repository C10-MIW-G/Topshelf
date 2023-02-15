import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { BasicStockProduct } from './basic-stock-product';
import { environment } from 'src/environments/environment';

@Injectable({ providedIn: 'root' })
export class BasicStockProductService {
  userdata: any;
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {}

  public getPantryWithBasicStockProducts(
    pantryId: number
  ): Observable<BasicStockProduct[]> {
    return this.http.get<BasicStockProduct[]>(
      `${this.apiServerUrl}/basicstockproduct/${pantryId}`
    );
  }

  public saveBasicStockProductToPantryStock(
    basicStockProduct: BasicStockProduct
  ): Observable<BasicStockProduct> {
    return this.http.post<BasicStockProduct>(
      `${this.apiServerUrl}/basicstockproduct/add`,
      {
        name: basicStockProduct.name,
        amount: basicStockProduct.amount,
        pantryId: basicStockProduct.pantryId,
      }
    );
  }
}
