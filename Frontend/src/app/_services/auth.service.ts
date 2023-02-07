import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const AUTH_API = 'http://localhost:8080/topshelf/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(credentials: { username: any; password: any; }): Observable<any> {
    console.log(credentials)
    return this.http.post(AUTH_API + 'auth', {
      username: credentials.username,
      password: credentials.password


    }, httpOptions);
  }

  register(user: { username: any; email: any; password: any; }): Observable<any> {
    return this.http.post(AUTH_API + 'register', {
      username: user.username,
      email: user.email,
      password: user.password
    }, httpOptions);
  }

  GetAll(){
    return this.http.get(AUTH_API);
  }
  
  GetbyCode(code: any){
    return this.http.get(AUTH_API + '/' + code);
  }

  ProceedRegister(inputdata: any) {
    return this.http.post(AUTH_API, inputdata);
  }

  UpdateUser(code: any, inputdata: any) {
    return this.http.post(AUTH_API + '/' + code, inputdata);
  }
}
