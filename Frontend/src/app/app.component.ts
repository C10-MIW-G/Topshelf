import { HttpErrorResponse, HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {

  title = 'Topshelf'

  roles: string[] = [];
  tokenStorageService: any;
  isLoggedIn: boolean = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  username: string | undefined;

  constructor(
    private httpclient: HttpClient){
    }

  ngOnInit() {
    this.httpclient.get(`http://localhost:8080/topshelf`);

  }

  if (_isLoggedIn: boolean) {
    const user = this.tokenStorageService.getUser();
    this.roles = user.roles;

    this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
    this.showModeratorBoard = this.roles.includes('ROLE_MODERATOR');

    this.username = user.username;
  }
  logout(): void {
  this.tokenStorageService.signOut();
  window.location.reload();

}

}

