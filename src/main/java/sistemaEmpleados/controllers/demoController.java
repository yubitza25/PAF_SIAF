package sistemaEmpleados.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sistemaEmpleados.Auth.AuthResponse;
import sistemaEmpleados.Auth.AuthService;
import sistemaEmpleados.Auth.LoginRequest;
import sistemaEmpleados.Auth.RegisterRequest;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/v1")
public class demoController {

    @PostMapping(value = "demo")
    public String welcome()
    {
        return "Welcome from secure endpoint";
    }
}