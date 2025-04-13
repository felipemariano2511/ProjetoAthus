package br.com.unicuritiba.projetoathus.controllers;

import br.com.unicuritiba.projetoathus.application.services.AuthService;
import br.com.unicuritiba.projetoathus.application.services.TokenService;
import br.com.unicuritiba.projetoathus.domain.models.Usuario;
import br.com.unicuritiba.projetoathus.domain.repositories.UsuarioRepository;
import br.com.unicuritiba.projetoathus.dto.LoginRequestDTO;
import br.com.unicuritiba.projetoathus.dto.RegisterRequestDTO;
import br.com.unicuritiba.projetoathus.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO body) {
        Usuario usuario = repository.findByEmail(body.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        if (!passwordEncoder.matches(body.senha(), usuario.getSenha())) {
            return ResponseEntity.badRequest().body("Senha inválida.");
        }

        String token = tokenService.gerarToken(usuario);
        return ResponseEntity.ok(new ResponseDTO(usuario.getNome(), token));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody RegisterRequestDTO body) {
        try {
            String msg = authService.iniciarCadastro(body);
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/validarcodigo")
    public ResponseEntity<?> validarCodigo(@RequestBody Map<String, String> body) {
        try {
            String email = body.get("email");
            int codigo = Integer.parseInt(body.get("codigo"));
            ResponseDTO response = authService.validarCodigo(email, codigo);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
