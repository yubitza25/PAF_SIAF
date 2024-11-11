import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http'; 
import { Observable } from 'rxjs'; 
@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private baseUrl = 'http://localhost:8083/auth/login';
  private tokenkey = 'authToken';
  constructor(private http: HttpClient, private router: Router) { }

    login(usuario: string, password: string): Observable<any>{
      const loginData = { usuario, password };
      return this.http.post(`${this.baseUrl}`, loginData);
    }

    setToken(token: string) {
      if (typeof window !== 'undefined') {
        localStorage.setItem(this.tokenkey, token);
        console.log(token);
      }
    }
  
    // Método para obtener el token almacenado
    getToken(): string | null {
      if (typeof window !== 'undefined') {
       
        return localStorage.getItem(this.tokenkey);
      }
      return null;
    }
  
    // Método para eliminar el token y realizar logout
    logout() {
      localStorage.removeItem(this.tokenkey);
      this.router.navigate(['/login']);
    }
}
