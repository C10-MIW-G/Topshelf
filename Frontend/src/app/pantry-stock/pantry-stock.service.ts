import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HttpClient } from '@angular/common/http';
import { PantryStock } from "./pantry-stock";

@Injectable({providedIn: 'root'})

export class PantryStockService {
  private apiServerUrl = 'http://localhost:8080';
  getPantryStock: any;

  constructor(private http: HttpClient){}

  public getPantries(pantryId: number): Observable<PantryStock> {
    return this.http.get<PantryStock>(`${this.apiServerUrl}/pantry/${pantryId}`);
  }
}