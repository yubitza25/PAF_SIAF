import { CanActivateFn } from '@angular/router';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../services/login.service';

export const authGuard: CanActivateFn = (route, state) => {
  const loginService = inject(LoginService);
  const router = inject(Router);

  // Verificar si hay un token de autenticación
  if (loginService.getToken()) {
    return true;  // Permitir acceso si hay un token
  } else {
    // Si no hay token, redirigir a login y evitar navegar hacia atrás
    router.navigateByUrl('/login', { replaceUrl: true }); 
    return false;  // Bloquear el acceso
  }
};

export const noAuthGuard: CanActivateFn = (route, state) => {
  const loginService = inject(LoginService);
  const router = inject(Router);

  // Verificar si el usuario está autenticado
  if (loginService.getToken()) {
    // Si ya está autenticado, redirigir al home o dashboard
    router.navigateByUrl('/home', { replaceUrl: true });
    return false;
  }
  return true;  // Permitir acceso al login solo si no está autenticado
};
