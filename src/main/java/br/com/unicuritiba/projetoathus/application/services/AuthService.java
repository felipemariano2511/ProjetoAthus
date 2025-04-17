package br.com.unicuritiba.projetoathus.application.services;

import br.com.unicuritiba.projetoathus.application.mails.VerificarCadastro;
import br.com.unicuritiba.projetoathus.domain.models.Usuario;
import br.com.unicuritiba.projetoathus.domain.repositories.UsuarioRepository;
import br.com.unicuritiba.projetoathus.dto.RegisterRequestDTO;
import br.com.unicuritiba.projetoathus.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final TokenService tokenService;

    private final Map<String, VerificarCadastro> verificacoes = new ConcurrentHashMap<>();
    private final Map<String, RegisterRequestDTO> cadastrosPendentes = new ConcurrentHashMap<>();

    private final String IMAGEM_PADRAO = "../images/usuario.png";
    private final Short NIVEL = 0;
    private final Boolean ATIVO = true;
    private final Boolean NAO_BANIDO = true;

    public String iniciarCadastro(RegisterRequestDTO body) {
        if (repository.findByEmail(body.email()).isPresent()) {
            throw new IllegalArgumentException("E-mail já está em uso.");
        }

        VerificarCadastro verificador = verificacoes.getOrDefault(body.email(), new VerificarCadastro());
        int codigo = verificador.gerarNovoCodigo();

        verificacoes.put(body.email(), verificador);
        cadastrosPendentes.put(body.email(), body);

        emailService.enviarEmail(
                body.email(),
                "Verificação de e-mail",
                String.format("Olá, %s! Seu código de verificação é: %d", body.nome(), codigo)
        );

        return "Código de verificação enviado para o e-mail.";
    }

    public ResponseDTO validarCodigo(String email, int codigo) {
        VerificarCadastro verificador = verificacoes.get(email);
        if (verificador == null) {
            throw new IllegalStateException("Nenhum código foi gerado para esse e-mail.");
        }

        if (!verificador.verificarCodigo(codigo)) {
            if (verificador.getTentativasRestantes() <= 0) {
                throw new IllegalStateException("Tentativas excedidas. Aguarde 60 minutos.");
            }
            throw new IllegalArgumentException("Código inválido. Tentativas restantes: " + verificador.getTentativasRestantes());
        }

        RegisterRequestDTO dados = cadastrosPendentes.get(email);
        if (dados == null) {
            throw new IllegalStateException("Nenhum cadastro pendente foi encontrado.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dados.nome());
        usuario.setEmail(dados.email());
        usuario.setSenha(passwordEncoder.encode(dados.senha()));
        usuario.setNumero(0);
        usuario.setApartamento(0);
        usuario.setImagemPerfil(IMAGEM_PADRAO);
        usuario.setNivel(NIVEL);
        usuario.setAtivo(ATIVO);
        usuario.setBanido(NAO_BANIDO);

        repository.save(usuario);
        verificacoes.remove(email);
        cadastrosPendentes.remove(email);

        emailService.enviarEmail(email, "Cadastro concluído", "Parabéns, sua conta foi criada com sucesso!");
        String token = tokenService.gerarToken(usuario);

        return new ResponseDTO(usuario.getNome(), token);
    }
}
