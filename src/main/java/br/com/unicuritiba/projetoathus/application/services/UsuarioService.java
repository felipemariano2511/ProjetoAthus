package br.com.unicuritiba.projetoathus.application.services;

import br.com.unicuritiba.projetoathus.domain.models.Usuario;
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
        Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = usuarioLogado.getId();

        Optional<Usuario> buscarUsuario = repository.findById(id);

        if (buscarUsuario.isEmpty()) {
            throw new Exception("Usuário não encontrado com id: " + id);
        }

        Usuario usuarioAtualizado = buscarUsuario.get();

        usuarioAtualizado.setNome(usuario.getNome());
        usuarioAtualizado.setTelefone(usuario.getTelefone());
        usuarioAtualizado.setSenha(passwordEncoder.encode(usuario.getSenha()));
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
}
