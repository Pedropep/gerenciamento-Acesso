import { Time } from "@angular/common"
import { Usuarios } from "./Usuarios"

export class Acessos{
    public id: number
    public tipoAcesso: String
    public horaAcesso: Time
    public usuarios: Usuarios
    public dataAcesso: Date    
}