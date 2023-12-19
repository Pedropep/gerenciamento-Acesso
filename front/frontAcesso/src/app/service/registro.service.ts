import { Injectable } from '@angular/core';
import { Acessos } from '../model/Acessos';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment.prod';
import { Usuarios } from '../model/Usuarios';

@Injectable({
  providedIn: 'root'
})
export class RegistroService {

  constructor(
    private http: HttpClient
  ) { }
  
  token = {
    headers: new HttpHeaders().set('Authorization', environment.token)
  }

  registrarAcesso(acesso: Acessos): Observable<Acessos>{
    return this.http.post<Acessos>('http://localhost:8080/api/acesso', acesso, this.token)
  }

  buscarPorCpf(cpf: String): Observable<Usuarios>{
    return this.http.get<Usuarios>(`http://localhost:8080/api/user/cpf/${cpf}`, this.token)
  }
}
