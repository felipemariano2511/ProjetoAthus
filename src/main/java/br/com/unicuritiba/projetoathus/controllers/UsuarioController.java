package br.com.unicuritiba.projetoathus.controllers;

import br.com.unicuritiba.projetoathus.models.Usuario;
import br.com.unicuritiba.projetoathus.repositories.UsuarioRepository;
import br.com.unicuritiba.projetoathus.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioService service;

    @GetMapping()
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> buscarUsuarios = service.getAllUsuarios();

        if (buscarUsuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(buscarUsuarios);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> getUsuario(@PathVariable Long id) {

        Optional<Usuario> buscarUsuario = service.getUsuario(id);

        if (buscarUsuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(buscarUsuario);
        }
    }

    @PostMapping()
    public ResponseEntity<Usuario> postUsuario(@RequestBody Usuario usuario) {
        Usuario salvarUsuario = service.postUsuario(usuario);
        return ResponseEntity.ok(salvarUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> putUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {

        try {
            Usuario usuarioAtualizado = service.putUsuario(id, usuario);
            //Caso encontre o usuário irá retorna 200 Ok!
            return ResponseEntity.ok(usuarioAtualizado);
        } catch (Exception ex) {
            // Caso o usuário não seja encontrado, retorna 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deleteUsuario(@PathVariable Long id) {
        Optional<Usuario> buscarUsuario = repository.findById(id);
        if (buscarUsuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }else {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }
}
