import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ChangePasswordRequest } from '../change-password/change-password';

const AUTH_API = environment.authUrl;
const API_URL = environment.apiBaseUrl;
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  login(credentials: { username: string; password: string }): Observable<any> {
    console.log(credentials);
    return this.http.post(
      AUTH_API + '/auth',
      {
        username: credentials.username,
        password: credentials.password,
      },
      httpOptions
    );
  }

  register(
    user: { username: string; email: string; password: string },
    captchaResponse: string | undefined
  ): Observable<any> {
    return this.http.post(
      AUTH_API + '/register',
      {
        username: user.username,
        email: user.email,
        password: user.password,
        captchaResponse,
      },
      httpOptions
    );
  }

  changePassword(request: {
    password: string;
    newPassword: string;
  }): Observable<any> {
    return this.http.post(
      API_URL + '/user/updatepassword',
      {
        password: request.password,
        newPassword: request.newPassword,
      },
      httpOptions
    );
  }

  GetAll() {
    return this.http.get(AUTH_API);
  }

  GetbyCode(code: any) {
    return this.http.get(AUTH_API + '/' + code);
  }

  ProceedRegister(inputdata: any) {
    return this.http.post(AUTH_API, inputdata);
  }

  UpdateUser(code: any, inputdata: any) {
    return this.http.post(AUTH_API + '/' + code, inputdata);
  }
}
