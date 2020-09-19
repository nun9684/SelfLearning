import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { analyzeAndValidateNgModules } from '@angular/compiler';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  username: string;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    public httpClient: HttpClient
  ) { }

  ngOnInit(): void {

  }

  loginRequest(username: string, password: string): void {
    const body = [username, password];
    this.username = username;
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    let responseValue: any;

    this.httpClient.put('http://localhost:8080/login', JSON.stringify(body), {headers})
    .subscribe(
      val => {
        responseValue = val;
        if (this.responseCheck(responseValue)){
          this.router.navigateByUrl('/user?userID=' + this.username);
        }
    },
    response => {
        alert(JSON.stringify(response));
    });
  }

  responseCheck(stringResult: any): boolean {

    if (stringResult.Response === 'Success') {
      return true;
    }
    else {
      return false;
    }
  }
}
