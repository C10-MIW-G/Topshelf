import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HttpClient } from '@angular/common/http';
import { StockProduct } from "./stock-product";

@Injectable({providedIn: 'root'})

export class StockProductService {
  private apiServerUrl = 'http://localhost:8080';

  constructor(private http: HttpClient){}

  public getStockProducts(): Observable<StockProduct[]> {
    return this.http.get<StockProduct[]>(`${this.apiServerUrl}/stockproduct/all`);
  }

  public getPantryWithStockProducts(pantryId: number): Observable<StockProduct[]> {
    return this.http.get<StockProduct[]>(`${this.apiServerUrl}/stockproduct/pantry/${pantryId}`)
  }
}