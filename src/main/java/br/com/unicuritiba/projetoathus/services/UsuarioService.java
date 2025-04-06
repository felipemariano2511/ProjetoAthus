package br.com.unicuritiba.projetoathus.services;

import br.com.unicuritiba.projetoathus.models.Usuario;
import br.com.unicuritiba.projetoathus.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    private PasswordEncoder passwordEncoder;

    public List<Usuario> getAllUsuarios() {
        return repository.findAll();
    }

    public Optional<Usuario> getUsuario(Long id){
        return repository.findById(id);
    }

    public Usuario postUsuario(Usuario usuario) {
        this.passwordEncoder = new BCryptPasswordEncoder();
        usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
        return repository.saveAndFlush(usuario);
    }

    public Usuario putUsuario(Long id, Usuario usuario) throws Exception {
        this.passwordEncoder = new BCryptPasswordEncoder();
        Optional<Usuario> buscarUsuario = repository.findById(id);
        if (buscarUsuario.isEmpty()) {
            throw new Exception("Usuário não encontrado com id: " + id);
        }else {
            Usuario usuarioAtualizado = buscarUsuario.get();

            usuarioAtualizado.setNome(usuario.getNome());
            usuarioAtualizado.setEmail(usuario.getEmail());
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
}
