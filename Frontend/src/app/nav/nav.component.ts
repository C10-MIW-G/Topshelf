import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css'],
})
export class NavComponent implements OnInit {
  currentUrl: string;
  atHome: boolean = false;

  constructor(
    private tokenStorageService: TokenStorageService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.currentUrl = window.location.href;
  }
  ngOnInit(): void {
    this.isAtHome();
    console.log(this.currentUrl);
    console.log(this.atHome);
  }

  public isAtHome(): void {
    if (this.currentUrl === 'http://localhost:4200/home') {
      this.atHome = true;
    }
  }

  public isLoggedIn(): boolean {
    return this.tokenStorageService.isLoggedIn();
  }

  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload;
  }

  public getUsername(): string {
    return this.tokenStorageService.getUsername();
  }
}
