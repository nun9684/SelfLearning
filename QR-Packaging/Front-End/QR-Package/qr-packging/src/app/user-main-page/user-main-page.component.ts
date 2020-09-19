import { PackageList } from './Model/packageList';

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { ThrowStmt } from '@angular/compiler';

// import { CRUD } from '../CRUD/restAPI';

@Component({
  selector: 'app-user-main-page',
  templateUrl: './user-main-page.component.html',
  styleUrls: ['./user-main-page.component.css']
})

export class UserMainPageComponent implements OnInit {
  userIDtext: string;
  userID: string;
  userInformation: string;
  val: any;
  packageList: any;
  textSenderAddress: string;
  textReceiverAddress: string;
  resultInformation: PackageList;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    public httpClient: HttpClient
    ) { }

  ngOnInit(): void {
    this.route.queryParams
      .subscribe(params => {
        this.userID = params.userID;
        this.userIDtext = 'สวัสดี ' + params.userID;
        }
      );
    this.resultInformation = {
      packageID: '',
      credential: '',
      senderAddress: '',
      receiverAddress: ''
    };
    this.getUserInformation();
  }

  getUserInformation(): void {
    const params = new  HttpParams().set('userID', this.userID);
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    const body = [this.userID];
    let tempValue;

    this.httpClient.post('http://localhost:8080/getAddress', JSON.stringify(body), {headers})
    .subscribe(
      val => {
        tempValue = val;
        this.packageList = tempValue.PackageList;
    },
      (response: any) => {
        alert('Test');
    });
  }

  btnGotoForm_onClicked(): void {
    this.router.navigate(['/form'], {queryParams: {packageID: this.userID},
    queryParamsHandling: 'preserve'});
  }

  sendPutRequest(): void{
    let tmp;
    tmp = [this.textReceiverAddress, this.textSenderAddress];
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    this.httpClient.put('http://localhost:8080/submitForm', JSON.stringify(tmp), {headers})
    .subscribe(
      val => {
        alert('PUT call successful value returned in body' + val);
    },
    response => {
        alert(this.handleResponse(response));
    },
    () => {
        alert('The PUT observable is now completed.');
    });
  }

  handleResponse(response): string{
    let returnValue = '';

    if (response.status === 200){
      returnValue = 'The PUT observable is now completed.';
    }
    else {
      returnValue = 'The PUT observable is not completed.';
    }
    return returnValue;
  }
}
