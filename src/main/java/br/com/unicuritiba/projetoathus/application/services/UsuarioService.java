package br.com.unicuritiba.projetoathus.application.services;

import br.com.unicuritiba.projetoathus.domain.models.Usuario;
import br.com.unicuritiba.projetoathus.domain.models.enums.UsuarioEnum;
import br.com.unicuritiba.projetoathus.domain.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> getAllUsuarios() {
        return repository.findAll();
    }

    public Optional<Usuario> getUsuario(Long id){ return repository.findById(id); }

    public Usuario putUsuario(Usuario usuario) throws Exception {
        // Recupera o usuário logado a partir do contexto de segurança
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!(principal instanceof Usuario)) {
            throw new Exception("Usuário não autenticado");
        }

        Usuario usuarioLogado = (Usuario) principal;
        Long id = usuarioLogado.getId();

        Optional<Usuario> buscarUsuario = repository.findById(id);
        if (buscarUsuario.isEmpty()) {
            throw new Exception("Usuário não encontrado com id: " + id);
        }

        Usuario usuarioAtualizado = buscarUsuario.get();

        if (usuario.getSenha() != null && !usuario.getSenha().isBlank()) {
            usuarioAtualizado.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }

        usuarioAtualizado.setCpf(usuario.getCpf());
        usuarioAtualizado.setDataNascimento(usuario.getDataNascimento());
        usuarioAtualizado.setPais(usuario.getPais());
        usuarioAtualizado.setEstado(usuario.getEstado());
        usuarioAtualizado.setCidade(usuario.getCidade());
        usuarioAtualizado.setCep(usuario.getCep());
        usuarioAtualizado.setRua(usuario.getRua());
        usuarioAtualizado.setNumero(usuario.getNumero());
        usuarioAtualizado.setApartamento(usuario.getApartamento());
        usuarioAtualizado.setLogradouro(usuario.getLogradouro());
        usuarioAtualizado.setImagemPerfil(usuario.getImagemPerfil());

        return repository.saveAndFlush(usuarioAtualizado);
    }

    public String obterDescricaoCargo(Integer codigo) {
        UsuarioEnum usuarioEnum = UsuarioEnum.toEnum(codigo);
        return usuarioEnum != null ? usuarioEnum.getDescricao() : "Cargo não encontrado!";
    }

}
