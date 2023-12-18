import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Acessos } from '../model/Acessos';

@Injectable({
  providedIn: 'root'
})
export class PainelService { 

  constructor(
    private http: HttpClient
  ) { }

  token = {
    headers: new HttpHeaders().set('Authorization', environment.token)
  }

  buscarAcessos(): Observable<Acessos[]>{
    return this.http.get<Acessos[]>('http://localhost:8080/api/acesso', this.token)
  }
  
}
