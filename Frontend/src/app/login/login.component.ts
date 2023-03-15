import { HttpClient } from '@angular/common/http';
import { TokenStorageService } from './../_services/token-storage.service';
import { AuthService } from './../_services/auth.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  private apiServerUrl = environment.apiBaseUrl;
  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];

  constructor(
    private authService: AuthService,
    private tokenStorageService: TokenStorageService,
    private http: HttpClient,
    private router: Router
  ) {}

  ngOnInit(): void {
    if (this.tokenStorageService.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorageService.getUser().roles;
    }
    this.http.get<Headers>(`${this.apiServerUrl}/auth`);
  }

  onSubmit(): void {
    this.authService.login(this.form).subscribe(
      (data) => {
        this.tokenStorageService.saveToken(data.token);
        this.tokenStorageService.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorageService.getUser().roles;
        this.reloadPage();
      },
      (err) => {
        console.log(err);
        this.errorMessage =
          "User doesn't exist or password was incorrect, please try again.";
        this.isLoginFailed = true;
      }
    );
  }

  reloadPage(): void {
    this.router.navigate(['/pantry']);
  }

  public getAuthenticated(): Observable<Headers> {
    return this.http.get<Headers>(`${this.apiServerUrl}/auth`);
  }
}
