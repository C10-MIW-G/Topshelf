import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { BasicStockProductEdit } from './basic-stock-product-edit';
import { environment } from 'src/environments/environment';
import { BasicStockProduct } from './basic-stock-product';
import { ObserversModule } from '@angular/cdk/observers';

@Injectable({ providedIn: 'root' })
export class BasicStockProductService {
  userdata: any;
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
    basicStockProductEdit: BasicStockProductEdit
  ): Observable<any> {
    return this.http.post(
      `${this.apiServerUrl}/basicstockproduct/add`,
      {
        basicStockProductId: basicStockProductEdit.basicStockProductId,
        pantryId: basicStockProductEdit.pantryId,
        name: basicStockProductEdit.name,
        amount: basicStockProductEdit.amount
      }
    );
  }

  public editBasicStockProduct(
    basicStockProductEdit: BasicStockProductEdit
  ): Observable<any> {
    return this.http.put(
      `${this.apiServerUrl}/basicstockproduct/edit`,
      {
        basicStockProductId: basicStockProductEdit.basicStockProductId,
        pantryId: basicStockProductEdit.pantryId,
        name: basicStockProductEdit.name,
        amount: basicStockProductEdit.amount
      }
    );
  }
}
