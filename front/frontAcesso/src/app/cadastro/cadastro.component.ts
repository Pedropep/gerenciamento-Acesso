import { Component, OnInit } from '@angular/core';
import { UsuarioLogin } from '../model/UsuarioLogin';
import { Usuarios } from '../model/Usuarios';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent implements OnInit {
  user: Usuarios = new Usuarios
  senhaConfirmar:String
  usertipo:Number

  constructor(
    private auth: AuthService,
    private router: Router
  ) {}

  ngOnInit(){
      window.scroll(0,0)
  }

  confirmeSenha(event: any){
    this.senhaConfirmar = event.target.value
  }

  tipoUser(event: any){
    this.tipoUser = event.target.value
  }

  cadastrar(){
    this.user.tipo = this.usertipo

    if(this.user.senha != this.senhaConfirmar){
      alert('A senhas são incorretas.')
    }else{
      this.auth.cadastrar(this.user).subscribe((resp: Usuarios) => {
        this.user = resp
        this.router.navigate(['/entrar'])
        alert('Usuario cadastrado com sucesso!')
      })
    }

  }
}
