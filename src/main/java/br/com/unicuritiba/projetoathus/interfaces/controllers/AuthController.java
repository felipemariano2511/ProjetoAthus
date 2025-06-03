package br.com.unicuritiba.projetoathus.interfaces.controllers;

import br.com.unicuritiba.projetoathus.application.services.AuthService;
import br.com.unicuritiba.projetoathus.domain.dto.auth.LoginRequestDTO;
import br.com.unicuritiba.projetoathus.domain.dto.auth.RegisterRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody RegisterRequestDTO body) {
        return ResponseEntity.ok(authService.iniciarCadastro(body));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO body) {
        return ResponseEntity.ok(authService.login(body));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> body) {
        return ResponseEntity.ok(authService.refresh(body.get("refreshToken")));
    }

    @PostMapping("/novocodigo")
    public ResponseEntity<?> solicitarNovoCodigo(@RequestBody RegisterRequestDTO body) {
        return ResponseEntity.ok(authService.solicitarNovoCodigo(body));
    }


    @PostMapping("/validarcodigo")
    public ResponseEntity<?> validarCodigo(@RequestBody Map<String, String> body) {
        return ResponseEntity.ok(authService.validarCodigo(body.get("email"), Integer.parseInt(body.get("codigo"))));
    }
}
