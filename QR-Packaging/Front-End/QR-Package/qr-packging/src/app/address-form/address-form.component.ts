import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-address-form',
  templateUrl: './address-form.component.html',
  styleUrls: ['./address-form.component.css']
})
export class AddressFormComponent implements OnInit {
  packageID: string;
  packageIDText: string;
  textSenderAddress: string;
  textReceiverAddress: string;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    public httpClient: HttpClient
    ) { }

  ngOnInit(): void {
    this.route.queryParams
      .subscribe(params => {
        this.packageID = params.packageID;
        this.packageIDText = 'Package ID: ' + params.packageID;
      }
    );
  }

  AcceptForm(): void {
    this.sendPutRequest();
    this.router.navigate(['/guest'], {queryParams: {packageID: this.packageID},
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
