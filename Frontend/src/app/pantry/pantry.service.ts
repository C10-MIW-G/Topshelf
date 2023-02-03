import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HttpClient } from '@angular/common/http';
import { Pantry } from "./pantry";

@Injectable({providedIn: 'root'})

export class PantryService {
  private apiServerUrl = 'http://localhost:8080';

  constructor(private http: HttpClient){}

  public getPantries(): Observable<Pantry[]> {
    return this.http.get<Pantry[]>(`${this.apiServerUrl}/pantry/all`);
  }
}