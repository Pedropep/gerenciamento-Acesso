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

    this.buscaCpf()
  }

  buscaCpf(cpf:string = environment.cpfUser){
    this.service.buscarPorCpf(cpf).subscribe((resp: Usuarios) =>{
      this.usuario = resp
    })
    console.log(this.usuario)    
  }

  btnEntrada(){
    this.acesso.tipoAcesso = 'ENTRADA'
    this.service.registrarAcesso(this.acesso).subscribe((resp: Acessos) =>{
      this.acesso = resp
      alert("Acesso registrado")
      environment.cpfUser = ''
      this.router.navigate(['/painel'])
    })
  }

  btnSaida(){
    this.acesso.tipoAcesso = 'SAIDA'
    this.service.registrarAcesso(this.acesso).subscribe((resp: Acessos) =>{      
      this.acesso = resp
      alert("Acesso registrado")
      environment.cpfUser = ''
      this.router.navigate(['/painel'])
    })
  }

  btnCancelar(){
    environment.cpfUser = ''
    this.router.navigate(['/painel'])
  }
}
