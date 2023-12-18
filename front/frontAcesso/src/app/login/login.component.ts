import { Component, OnInit } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';
import { UsuarioLogin } from '../model/UsuarioLogin';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
  userLogin: UsuarioLogin = new UsuarioLogin()

  constructor(
    private auth: AuthService,
    private router: Router
  ) {}

  ngOnInit(){
      window.scroll(0,0)
  }

  entrar(){
    this.auth.entrar(this.userLogin).subscribe((resp: UsuarioLogin) => {     
      this.userLogin = resp

      environment.token = this.userLogin.token
      environment.nome = this.userLogin.nome
      environment.id = this.userLogin.id
      console.log(environment.token)
      console.log(environment.nome)
      console.log(environment.id)

      this.router.navigate(['/painel'])
    }, erro => {
      if(erro.status == 500){
        alert("Usuário ou senha estão incorretos!")
      }
    })
  }

}
