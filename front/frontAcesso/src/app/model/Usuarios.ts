import { Acessos } from "./Acessos"

export class Usuarios{
    public id: number
    public nome: string
    public cpf: string
    public tipo: Number
    public foto: string
    public email: String
    public senha: string
    public acesso: Acessos[]

}