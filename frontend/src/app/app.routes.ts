import { Routes } from '@angular/router';
import { LoginComponent } from './authentication/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { authGuard } from './guards/auth.guard';
export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'home', component: DashboardComponent, canActivate: [authGuard] },  // Ruta protegida
    { path: '', redirectTo: '/login', pathMatch: 'full' }
];
