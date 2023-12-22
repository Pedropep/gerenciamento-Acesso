import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute  } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { Acessos } from '../model/Acessos';
import { PainelService } from '../service/painel.service';
import { RegistroService } from '../service/registro.service';
import { Usuarios } from '../model/Usuarios';

@Component({
  selector: 'app-painel',
  templateUrl: './painel.component.html',
  styleUrls: ['./painel.component.css']
})

export class PainelComponent implements OnInit{
  key = 'dataAcesso'
  reverse = true

  acesso: Acessos = new Acessos()
  listaAcessos: Acessos[]

  user: Usuarios = new Usuarios()
  usuario: Usuarios = new Usuarios()
  userCpf: string
  
  constructor(
    private router : Router,
    private service: PainelService,
    private serviceRegistro: RegistroService       
  ){}
  
  ngOnInit() {
    this.buscarTodosAcessos()  

    if(environment.token == ''){
      alert('Sua seção expirou, faça o login novamente.')
      this.router.navigate(['/login'])
    }    
  }
  //BUSCA TODOS OS ACESSOS
  buscarTodosAcessos(){
    this.service.buscarAcessos().subscribe((resp: Acessos[]) =>{
      this.listaAcessos = resp
    })
  } 

  //Busca um Usuario por cpf
  buscaCpf(){       
    this.serviceRegistro.buscarPorCpf(this.usuario.cpf).subscribe((resp: Usuarios) =>{
      this.user = resp
      
      //Verifica se existe um usuario e segue para pagina de registrar o Acesso.
      if(this.user === null){
        alert("User não existe")
        this.usuario = new Usuarios()
        
      }else{
        environment.cpfUser = this.user.cpf
        this.router.navigate(['/registro'])
      }
    })
    
  }  
}
