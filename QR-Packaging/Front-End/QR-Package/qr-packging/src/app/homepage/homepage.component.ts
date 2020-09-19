import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  packageID: string;
  packageIDtext: string;
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    public httpClient: HttpClient) {
    }

  ngOnInit(): void {
      this.route.queryParams
      .subscribe(params => {
        this.packageID = params.packageID;
        this.packageIDtext = 'Package ID: ' + params.packageID;
        }
      );
  }

  btnGotoForm_onClicked(): void {
    this.router.navigate(['/form'], {queryParams: {packageID: this.packageID},
    queryParamsHandling: 'preserve'});
  }

  btnAddrCheck_onClicked(): void {
    const  params = new  HttpParams()
      .set('packageID', this.packageID);

    this.httpClient.get('http://localhost:8080/getAddress', {params})
    .subscribe(
      val => {
        alert('ผู้ส่ง: ' + val[0].Sender + '\nผู้รับ: ' + val[1].Receiver);
    },
      (response: any) => {
        alert('Test');
    });
  }
}
