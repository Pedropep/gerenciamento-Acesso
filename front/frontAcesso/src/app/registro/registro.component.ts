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
  listaAcessoUser: Acessos[]

  usuario: Usuarios = new Usuarios()
  idUsuario: number

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

  buscarAcessoUser(id:number){    
    this.service.buscarAcessoPorUser(id).subscribe((resp: Acessos[])=> {
      this.listaAcessoUser = resp      
    })
  }

  buscaCpf(cpf:string = environment.cpfUser){
    this.service.buscarPorCpf(cpf).subscribe((resp: Usuarios) =>{
      this.usuario = resp
      
      this.idUsuario = this.usuario.id
      console.log(this.idUsuario)
      this.buscarAcessoUser(this.idUsuario)         
    })       
  }

  btnEntrada(){
    this.acesso.usuarios = this.usuario    
    this.acesso.tipoAcesso = "ENTRADA"
       
    this.service.registrarAcesso(this.acesso).subscribe((resp: Acessos) =>{
      this.acesso = resp

      environment.cpfUser = ''
      alert("Entrada Registrada com sucesso!!")      
      this.router.navigate(['/painel'])     
    })
  }

  btnSaida(){
    this.acesso.usuarios = this.usuario    
    this.acesso.tipoAcesso = "SAIDA"

    this.service.registrarAcesso(this.acesso).subscribe((resp: Acessos) =>{      
      this.acesso = resp

      environment.cpfUser = ''
      alert("Saida Registrada com Sucesso!!")      
      this.router.navigate(['/painel'])
    })
  }

  btnCancelar(){    
    environment.cpfUser = ''    
    this.router.navigate(['/painel'])
  }
}
