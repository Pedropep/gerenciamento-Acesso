import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { Acessos } from '../model/Acessos';
import { PainelService } from '../service/painel.service';

@Component({
  selector: 'app-painel',
  templateUrl: './painel.component.html',
  styleUrls: ['./painel.component.css']
})

export class PainelComponent implements OnInit{
  acesso: Acessos = new Acessos()
  listaAcessos: Acessos[]

  constructor(
    private router : Router,
    private service: PainelService
  ){}
  
  ngOnInit() {
    this.buscarTodosAcessos()

    if(environment.token == ''){
      alert('Sua seção expirou, faça o login novamente.')
      this.router.navigate(['/login'])
    }
    
  }
  
  buscarTodosAcessos(){
    this.service.buscarAcessos().subscribe((resp: Acessos[]) =>{
      this.listaAcessos = resp
    })
  }
  
}
