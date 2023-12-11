import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistroComponent } from './registro/registro.component';
import { CadastroComponent } from './cadastro/cadastro.component';
import { PainelComponent } from './painel/painel.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  {path:'', redirectTo: 'painel', pathMatch: 'full'},

  {path:'registro', component:RegistroComponent},
  {path:'painel', component:PainelComponent},
  {path:'cadastro', component:CadastroComponent},
  {path:'login', component:LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
