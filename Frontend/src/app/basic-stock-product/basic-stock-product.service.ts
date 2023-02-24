import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { BasicStockProduct } from './basic-stock-product';

@Injectable({ providedIn: 'root' })
export class BasicStockProductService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {}

  public getBasicStockProductsByPantry(
    pantryId: number
  ): Observable<BasicStockProduct[]> {
    return this.http.get<BasicStockProduct[]>(
      `${this.apiServerUrl}/basicstockproduct/${pantryId}`
    );
  }

  getBasicStockProduct(basicStockProductId: number): Observable<BasicStockProduct> {
    return this.http.get<BasicStockProduct>(`${this.apiServerUrl}/${basicStockProductId}`)
  }

  public saveBasicStockProductToPantryStock(
    basicStockProductEdit: BasicStockProduct
  ): Observable<any> {
    return this.http.post(
      `${this.apiServerUrl}/basicstockproduct/add`,
      {
        name: basicStockProductEdit.name,
        amount: basicStockProductEdit.amount,
        pantryId: basicStockProductEdit.pantryId,
        basicStockProductId: basicStockProductEdit.basicStockProductId
      }
    );
  }
}
