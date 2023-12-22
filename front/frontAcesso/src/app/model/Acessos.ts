import { Time } from "@angular/common"
import { Usuarios } from "./Usuarios"

export class Acessos{
    public id: number
    public tipoAcesso: String
    public horaAcesso: Time
    public dataAcesso: Date    
    public usuarios: Usuarios
}