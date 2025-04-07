package br.com.unicuritiba.projetoathus.controllers;

import br.com.unicuritiba.projetoathus.domain.models.Usuario;
import br.com.unicuritiba.projetoathus.domain.repositories.UsuarioRepository;
import br.com.unicuritiba.projetoathus.dto.UsuarioDTO;
import br.com.unicuritiba.projetoathus.application.services.UsuarioService;
import br.com.unicuritiba.projetoathus.mappers.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioService service;

    @Autowired
    private UsuarioMapper mapper;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
        List<UsuarioDTO> usuarios = service.getAllUsuarios()
                .stream()
                .map(mapper::toDTO)
                .toList();

        return usuarios.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuario(@PathVariable Long id) {
        return service.getUsuario(id)
                .map(mapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> putUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario atualizado = service.putUsuario(usuario);
            return ResponseEntity.ok(mapper.toDTO(atualizado));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        return repository.findById(id)
                .map(usuario -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
