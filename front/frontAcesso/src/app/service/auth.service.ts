import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UsuarioLogin } from '../model/UsuarioLogin';
import { Usuarios } from '../model/Usuarios';
import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient
  ) { }

  entrar(usuarioLogin: UsuarioLogin): Observable<UsuarioLogin>{   
    return this.http.post<UsuarioLogin>('http://localhost:8080/api/user/logar', usuarioLogin)
  }

  cadastrar(usuarios: Usuarios): Observable<Usuarios>{
    return this.http.post<Usuarios>('http://localhost:8080/api/user/cadastrar', usuarios)
  }

  logado(){
    let ok: boolean = false
    
    if(environment.token != ''){
      ok = true
    }
    return ok
  }  
}
