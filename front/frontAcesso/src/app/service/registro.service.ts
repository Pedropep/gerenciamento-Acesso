import { Injectable } from '@angular/core';
import { Acessos } from '../model/Acessos';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment.prod';

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
    return this.http.post<Acessos>('http://localhost:8080/api/acesso', acesso)
  }
}
