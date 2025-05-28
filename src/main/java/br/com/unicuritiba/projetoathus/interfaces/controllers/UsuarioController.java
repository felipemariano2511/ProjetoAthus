package br.com.unicuritiba.projetoathus.interfaces.controllers;

import br.com.unicuritiba.projetoathus.application.forms.UsuarioForm;
import br.com.unicuritiba.projetoathus.domain.models.Usuario;
import br.com.unicuritiba.projetoathus.dto.SetAtivoDTO;
import br.com.unicuritiba.projetoathus.dto.SetNivelDTO;
import br.com.unicuritiba.projetoathus.dto.UsuarioDTO;
import br.com.unicuritiba.projetoathus.application.services.UsuarioService;
import br.com.unicuritiba.projetoathus.mappers.UsuarioFormMapper;
import br.com.unicuritiba.projetoathus.mappers.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    public ResponseEntity<?> getUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(service.getUsuario(id));
    }

    @GetMapping("/logado")
    public ResponseEntity<?> getInfoUsuarioLogado() {
        return ResponseEntity.ok(service.getInfoUsuarioLogado());
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UsuarioDTO> putUsuario(
            @RequestParam("imagemPerfil") MultipartFile imagem,
            @ModelAttribute UsuarioForm form) {

        Usuario usuario = UsuarioFormMapper.toUsuario(form);
        return ResponseEntity.ok(mapper.toDTO(service.putUsuario(imagem, usuario).getBody()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseEntity<?>> deleteUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteUsuario(id));
    }

    @PreAuthorize("principal.nivel >= 1") // Rota acessivel apenas para usuarios não-comuns
    @PatchMapping("/{id}")
    public ResponseEntity<?> patchAtivoUsuario(@PathVariable Long id, @RequestBody SetAtivoDTO body){
        return ResponseEntity.ok(service.setAtivoUsuario(id, body.ativo()));
    }

    @PreAuthorize("principal.nivel >= 2") // Rota acessivel apenas para usuarios com nivel ADMIN ou superior
    @PatchMapping("/{id}/nivel")
    public ResponseEntity<?> patchNivelUsuario(@PathVariable Long id, @RequestBody SetNivelDTO body){
        return ResponseEntity.ok(service.setNivelUsuario(id, body.nivel()));
    }

}
