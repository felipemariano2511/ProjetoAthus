package br.com.unicuritiba.projetoathus.controller;

import br.com.unicuritiba.projetoathus.model.Usuario;
import br.com.unicuritiba.projetoathus.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getAllUsuarios() {

        List<Usuario> buscarUsuarios = repository.findAll();

        if (buscarUsuarios.isEmpty()) {
            return ResponseEntity.ofNullable(null);
        }else {
            return ResponseEntity.ok(buscarUsuarios);
        }
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Optional<Usuario>> getUsuario(@PathVariable @Valid Long id) {

        Optional<Usuario> buscarUsuario = repository.findById(id);

        if (buscarUsuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(buscarUsuario);
        }
    }

    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> postUsuario(@RequestBody Usuario usuario) {
        Usuario salvarUsuario = repository.save(usuario);
        return ResponseEntity.ok(salvarUsuario);
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> putUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {

        Optional<Usuario> buscarUsuario = repository.findById(id);

        if (buscarUsuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }else {
            Usuario usuarioExistente = buscarUsuario.get();

            // Atualizando os campos do usuário existente com os dados recebidos
            usuarioExistente.setNome(usuario.getNome());
            usuarioExistente.setCpf(usuario.getCpf());
            usuarioExistente.setTelefone(usuario.getTelefone());
            usuarioExistente.setEndereco(usuario.getEndereco());

            // Salvando as alterações no banco
            repository.saveAndFlush(usuarioExistente);

            // Retorna o usuário atualizado
            return ResponseEntity.ok(usuarioExistente);
        }
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> deleteUsuario(@PathVariable Long id) {
        Optional<Usuario> buscarUsuario = repository.findById(id);
        if (buscarUsuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }else {
            repository.deleteById(id);
            return ResponseEntity.ok(buscarUsuario.get());
        }
    }
}
