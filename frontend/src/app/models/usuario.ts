export class Usuario {
  id: number;
  usuario: string;
  password: string;
  activo: number;
  rol: string;

  constructor(
    id: number,
    usuario: string,
    password: string,
    activo: number,
    rol: string
  ) {
    this.id = id;
    this.usuario = usuario;
    this.password = password;
    this.activo = activo;
    this.rol = rol;
  }
}
