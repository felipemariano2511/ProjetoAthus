package br.com.unicuritiba.projetoathus.application.services;

import br.com.unicuritiba.projetoathus.domain.models.Usuario;
import br.com.unicuritiba.projetoathus.domain.repositories.UsuarioRepository;
import br.com.unicuritiba.projetoathus.dto.UsuarioDTO;
import br.com.unicuritiba.projetoathus.infrastructure.exceptions.NoContentException;
import br.com.unicuritiba.projetoathus.infrastructure.exceptions.NotFoundException;
import br.com.unicuritiba.projetoathus.infrastructure.exceptions.UnauthorizedException;
import br.com.unicuritiba.projetoathus.mappers.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioMapper mapper;


    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() throws NoContentException {
        return ResponseEntity.ok(repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList());
    }

    public ResponseEntity<?> getUsuario(Long id) throws NotFoundException{
        return ResponseEntity.ok(repository.findById(id)
                .stream()
                .map(mapper::toDTO));
    }

    public ResponseEntity<Usuario> putUsuario(Usuario usuario) throws NotFoundException {
        String email = getEmailUsuarioLogado();

        Usuario usuarioLogado = repository.findByEmail(email).orElseThrow(() ->
                new NotFoundException("Usuário não encontrado no banco de dados"));

        if (usuario.getSenha() != null && !usuario.getSenha().isBlank()) {
            usuarioLogado.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }

        usuarioLogado.setCpf(usuario.getCpf());
        usuarioLogado.setDataNascimento(usuario.getDataNascimento());
        usuarioLogado.setPais(usuario.getPais());
        usuarioLogado.setEstado(usuario.getEstado());
        usuarioLogado.setCidade(usuario.getCidade());
        usuarioLogado.setCep(usuario.getCep());
        usuarioLogado.setRua(usuario.getRua());
        usuarioLogado.setNumero(usuario.getNumero());
        usuarioLogado.setApartamento(usuario.getApartamento());
        usuarioLogado.setLogradouro(usuario.getLogradouro());
        usuarioLogado.setImagemPerfil(usuario.getImagemPerfil());

        repository.saveAndFlush(usuarioLogado);

        return ResponseEntity.ok(usuarioLogado);
    }

    public ResponseEntity<?> deleteUsuario(Long id) {
        return repository.findById(id)
                .map(usuario -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado como id: " + id));
    }

    private String getEmailUsuarioLogado() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof String email) {
            return email;
        } else if (principal instanceof Usuario usuario) {
            return usuario.getEmail();
        }

        throw new UnauthorizedException("Usuário não autenticado");
    }

}
