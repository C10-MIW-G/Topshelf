import { Component } from '@angular/core';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent {

  constructor(
  private tokenStorageService: TokenStorageService
){}

  public isLoggedIn(): boolean{
    return this.tokenStorageService.isLoggedIn();
  }

  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload;
  }
}
