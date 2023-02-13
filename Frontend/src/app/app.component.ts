import { Router } from '@angular/router';
import { TokenStorageService } from './_services/token-storage.service';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  title = 'Topshelf';

  roles: string[] = [];
  username: string | undefined;
  router: Router = new Router();

  constructor(
    private tokenStorageService: TokenStorageService,
    private httpclient: HttpClient
  ) {}

  ngOnInit() {
    this.httpclient.get(environment.authUrl);
  }

  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload;
  }

  public isLoggedIn(): boolean {
    return this.tokenStorageService.isLoggedIn();
  }
}
