export class Usuario {
  id: number;
  usuario: string;
  password: string;
  salt: string;
  tipo_usuario: string;

  constructor(
    id: number,
    usuario: string,
    password: string,
    salt: string,
    tipo_usuario: string
  ) {
    this.id = id;
    this.usuario = usuario;
    this.password = password;
    this.salt = salt;
    this.tipo_usuario = tipo_usuario;
  }
}
