package br.com.unicuritiba.projetoathus.controllers;

import br.com.unicuritiba.projetoathus.domain.models.enums.UsuarioEnum;
import br.com.unicuritiba.projetoathus.dto.LoginRequestDTO;
import br.com.unicuritiba.projetoathus.dto.RegisterRequestDTO;
import br.com.unicuritiba.projetoathus.dto.ResponseDTO;
import br.com.unicuritiba.projetoathus.application.services.TokenService;
import br.com.unicuritiba.projetoathus.domain.models.Usuario;
import br.com.unicuritiba.projetoathus.domain.repositories.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    private final String IMAGEM_PADRAO = "../images/usuario.png";
    private final Boolean ATIVO = true;
    private final Boolean NAO_BANIDO = true;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body) {
        Usuario usuario = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        if (passwordEncoder.matches(body.senha(), usuario.getSenha())) {
            String token = this.tokenService.gerarToken(usuario);

            return ResponseEntity.ok(new ResponseDTO(usuario.getNome(), token));
        }

        //Ajustar futuramente para enviar um erro de senha inválida
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrar(@RequestBody RegisterRequestDTO body) {
        Optional <Usuario> usuario = this.repository.findByEmail(body.email());

        if(usuario.isEmpty()) {
            Usuario novoUsuario = new Usuario();

            novoUsuario.setNome(body.nome());
            novoUsuario.setEmail(body.email());
            novoUsuario.setSenha(passwordEncoder.encode(body.senha()));
            novoUsuario.setImagemPerfil(IMAGEM_PADRAO);
            novoUsuario.setNivel(UsuarioEnum.USER);
            novoUsuario.setAtivo(ATIVO);
            novoUsuario.setBanido(NAO_BANIDO);
            this.repository.save(novoUsuario);

            String token = this.tokenService.gerarToken(novoUsuario);

            return ResponseEntity.ok(new ResponseDTO(novoUsuario.getNome(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}
