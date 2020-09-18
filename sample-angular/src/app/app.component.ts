import { Component } from '@angular/core';
import { HttpClient, JsonpInterceptor, HttpHeaders } from '@angular/common/http';
import { from, Observable, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import * as JsEncryptModule from 'jsencrypt';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'sampleapp';

  constructor(private httpClient: HttpClient) {

  }

  encryptObj(_jsonObj){
    var objEncObjJson = _jsonObj;
    let publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDqCV8ziSSWwzqYrfJtY12WISkutrGwLFho6B/yWlNTtrdEWuKxrlTypZ/xwUPuONqkG9RmMOtvzaVHWmRfg7ZWkc+uYaRDRwzXXNkF4WnLK4ySxmpZurBrU+2r1UVni8yD/oDoRINJFpdd+0AHJKuRuk2eR/vYuBf96XwLlMOwlQIDAQAB";
    var RSAEncrypt = new JsEncryptModule.JSEncrypt();
    RSAEncrypt.setPublicKey(publicKey);
    let encryptedPass = RSAEncrypt.encrypt(_jsonObj.password);
    objEncObjJson.password = encryptedPass;
    return objEncObjJson;
  }

  createUser() {
    try {
      var obj = {};
      obj["username"] = "aaaaa";
      obj["password"] = "asdasdasd";
      const headers = { 'Content-Type': 'application/json' }
      obj = this.encryptObj(obj);
      this.httpClient.post<any>(`http://localhost:8080/SpringBootApi/sample/response`, obj,{ headers }).subscribe(
        (res) => console.log(res),
        (err) => console.log(err)
      );

      // this.httpClient.post(`http://localhost:8080/SpringBootApi/sample/postmethod1`,JSON.stringify(obj),).
      //     pipe(
      //       map((data: any) => {
      //         console.log("asdasd ",data);
      //         return data;
      //       }), catchError( error => {
      //         return throwError( 'Something went wrong!' );
      //       })
      //     )
    } catch (error) {
      console.log(error);
    }
  }
}
