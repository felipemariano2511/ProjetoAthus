package br.com.unicuritiba.projetoathus.controllers;

import br.com.unicuritiba.projetoathus.application.services.AuthService;
import br.com.unicuritiba.projetoathus.application.services.ResetSenhaService;
import br.com.unicuritiba.projetoathus.dto.LoginRequestDTO;
import br.com.unicuritiba.projetoathus.dto.RegisterRequestDTO;
import br.com.unicuritiba.projetoathus.dto.ResetSenhaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private ResetSenhaService resetSenhaService;

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

    @PostMapping("/validarcodigo")
    public ResponseEntity<?> validarCodigo(@RequestBody Map<String, String> body) {
        return ResponseEntity.ok(authService.validarCodigo(body.get("email"), Integer.parseInt(body.get("codigo"))));
    }

    @PostMapping("/resetsenha")
    public ResponseEntity<?> enviarTokenReset(@Valid @RequestBody ResetSenhaDTO.ResetSenhaRequestDto request) {
        return resetSenhaService.enviarTokenReset(request.email());
    }
}
