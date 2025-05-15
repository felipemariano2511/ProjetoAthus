package br.com.unicuritiba.projetoathus.controllers;

import br.com.unicuritiba.projetoathus.domain.models.Usuario;
import br.com.unicuritiba.projetoathus.dto.UsuarioDTO;
import br.com.unicuritiba.projetoathus.application.services.UsuarioService;
import br.com.unicuritiba.projetoathus.mappers.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private UsuarioMapper mapper;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
        return ResponseEntity.ok(service.getAllUsuarios().getBody());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEntity<?>> getUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(service.getUsuario(id));
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> putUsuario(@RequestBody Usuario usuario) throws Exception {
        return ResponseEntity.ok(mapper.toDTO(service.putUsuario(usuario).getBody()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseEntity<?>> deleteUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteUsuario(id));
    }
}
