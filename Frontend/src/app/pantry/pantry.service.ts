import { PantryDTO } from './pantrydto';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Pantry } from './pantry';
import { environment } from 'src/environments/environment';

@Injectable({ providedIn: 'root' })
export class PantryService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {}

  public getPantries(): Observable<Pantry[]> {
    return this.http.get<Pantry[]>(`${this.apiServerUrl}/pantry/all`);
  }

  public getPantriesForUser(): Observable<Pantry[]> {
    return this.http.get<Pantry[]>(`${this.apiServerUrl}/pantry/all/user`);
  }

  public addPantry(pantryDTO: PantryDTO): Observable<PantryDTO> {
    return this.http.post<PantryDTO>(
      `${this.apiServerUrl}/pantry/add`,
      pantryDTO
    );
  }

  public editPantry(pantry: Pantry): Observable<Pantry> {
    return this.http.post<Pantry>(
      `${this.apiServerUrl}/pantry/add`, {
        pantryId: pantry.pantryId,
        name: pantry.name,
      }
    )
  }

  public deletePantry(pantryId: number): Observable<void> {
    return this.http.delete<void>(
      `${this.apiServerUrl}/pantry/delete/${pantryId}`
    );
}
}
