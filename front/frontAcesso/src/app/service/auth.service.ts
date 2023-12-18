import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UsuarioLogin } from '../model/UsuarioLogin';
import { Usuarios } from '../model/Usuarios';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient
  ) { }

  entrar(usuarioLogin: UsuarioLogin): Observable<UsuarioLogin>{
    // let httpHeadersOptions = {
    //   headers : new HttpHeaders({
    //     'Access-Control-Allow-Origin':'*',
    //     'Access-Control-Allow-Method': 'POST, GET, PUT, DELETE',
    //     'Access-Control-Allow-Headers':'*'
    //   })
    // }
    return this.http.post<UsuarioLogin>('http://localhost:8080/api/user/logar', usuarioLogin)
  }

  cadastrar(usuarios: Usuarios): Observable<Usuarios>{
    return this.http.post<Usuarios>('http://localhost:8080/api/user/cadastrar', usuarios)
  }
}
