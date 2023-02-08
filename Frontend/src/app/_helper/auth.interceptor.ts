import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpInterceptor
} from '@angular/common/http';
import { TokenStorageService } from '../_services/token-storage.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor( private token: TokenStorageService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const token = this.token.getToken();
    if(token){
    req = req.clone({
      url:  req.url,
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    })};
    return next.handle(req);
  }
}
