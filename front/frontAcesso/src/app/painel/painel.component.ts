import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute  } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { Acessos } from '../model/Acessos';
import { PainelService } from '../service/painel.service';
import { RegistroService } from '../service/registro.service';
import { Usuarios } from '../model/Usuarios';
import { isEmpty } from 'rxjs';

@Component({
  selector: 'app-painel',
  templateUrl: './painel.component.html',
  styleUrls: ['./painel.component.css']
})

export class PainelComponent implements OnInit{
  acesso: Acessos = new Acessos()
  listaAcessos: Acessos[]
  user: Usuarios = new Usuarios()
  userCpf: string

  constructor(
    private router : Router,
    private service: PainelService,
    private serviceRegistro: RegistroService,
    private route: ActivatedRoute
  ){}
  
  ngOnInit() {
    this.buscarTodosAcessos()

    if(environment.token == ''){
      alert('Sua seção expirou, faça o login novamente.')
      this.router.navigate(['/login'])
    }
    environment.cpfUser = ''    
  }

  cpfUser(event:any){
    this.userCpf = event.target.value    
  }

  //BUSCA TODOS OS ACESSOS
  buscarTodosAcessos(){
    this.service.buscarAcessos().subscribe((resp: Acessos[]) =>{
      this.listaAcessos = resp
    })
  }

  //VERIFICA SE O CPF EXISTE
  checaCpf(cpf:string){
    this.serviceRegistro.buscarPorCpf(cpf).subscribe((resp: Usuarios) =>{
      this.user = resp      
    })

    if(this.user.cpf == null){
      alert("Usuario não existe")
      this.userCpf = ''
      environment.cpfUser = ''        

    }else{
      environment.cpfUser = this.userCpf
      this.router.navigate(['/registro'])
    }
  }
  
}
