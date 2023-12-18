import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PainelService } from '../service/painel.service';
import { RegistroService } from '../service/registro.service';
import { Acessos } from '../model/Acessos';
import { Usuarios } from '../model/Usuarios';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent implements OnInit{    
  acesso: Acessos = new Acessos()
  usuario: Usuarios = new Usuarios()

  constructor(
    private router : Router,
    private service: RegistroService
  ){}

  ngOnInit() {
    if(environment.token == ''){
      alert('Sua seção expirou, faça o login novamente.')
      this.router.navigate(['/login'])
    }
  }

  registrar(){
    this.service.registrarAcesso(this.acesso).subscribe((resp: Acessos) =>{
      this.acesso = resp
      alert("Acesso registrado")
      this.router.navigate(['/painel'])
    })
  }
}
