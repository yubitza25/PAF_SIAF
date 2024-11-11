import { Component } from '@angular/core';
import { LoginService } from '../../services/login.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  usuario: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private loginService: LoginService, private router: Router) {}

  onSubmit() {
    // Llamar al servicio de login para autenticar al usuario
    this.loginService.login(this.usuario, this.password).subscribe({
      next: (response) => {
        // Guardar el token cuando el login sea exitoso
        this.loginService.setToken(response.token);
        console.log(response.token);
        // Redirigir al dashboard después del login
        this.router.navigate(['/home']);
      },
      error: (error) => {
        // Si ocurre un error, mostrar un mensaje
        this.errorMessage = 'Credenciales inválidas';
      },
    });
  }
}
