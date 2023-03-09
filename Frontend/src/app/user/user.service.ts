import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Pantry } from '../pantry/pantry';
import { User } from './user';

@Injectable({ providedIn: 'root' })
export class UserService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {}

  public getPantriesForUser(): Observable<Pantry[]> {
    return this.http.get<Pantry[]>(`${this.apiServerUrl}/pantry/all/user`);
  }

  public getUsersByPantry(pantryId: number): Observable<User[]> {
    return this.http.get<User[]>(
      `${this.apiServerUrl}/pantry/findusers/${pantryId}`
    );
  }

  public getAdminsByPantry(pantryId: number): Observable<User[]> {
    return this.http.get<User[]>(
      `${this.apiServerUrl}/pantry/findadmins/${pantryId}`
    );
  }

  public inviteUserToPantry(email: string, pantryId: number): Observable<any> {
    return this.http.post(`${this.apiServerUrl}/user/inviteuser/${pantryId}`, {
      email,
      pantryId,
    });
  }

  public checkIfUserIsPantryAdmin(pantryId: number): Observable<User> {
    return this.http.get<User>(
      `${this.apiServerUrl}/user/checkuser/${pantryId}`
    );
  }
}
