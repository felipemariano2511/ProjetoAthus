package br.com.unicuritiba.projetoathus.interfaces.controllers;

import br.com.unicuritiba.projetoathus.application.services.CategoriasService;
import br.com.unicuritiba.projetoathus.domain.models.Categorias;
import br.com.unicuritiba.projetoathus.domain.dto.CategoriasDTO;
import br.com.unicuritiba.projetoathus.mappers.CategoriaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriasController {

    @Autowired
    private CategoriasService service;

    @Autowired
    private CategoriaMapper mapper;

    @GetMapping
    public ResponseEntity<List<CategoriasDTO>> getCategorias() {
        return ResponseEntity.ok(service.getAllCategorias().getBody());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEntity<?>> getCategoria(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCategoria(id));
    }

    @PreAuthorize("principal.nivel >= 2") // Rota acessivel apenas para usuarios com nivel ADMIN ou superior
    @PostMapping
    public ResponseEntity<CategoriasDTO> postCategoria(@RequestBody Categorias categoria) {
        return ResponseEntity.ok(mapper.toDTO(service.saveCategoria(categoria)));
    }

    @PreAuthorize("principal.nivel >= 2") // Rota acessivel apenas para usuarios com nivel ADMIN ou superior
    @PutMapping("/{id}")
    public ResponseEntity<CategoriasDTO> putCategoria(@PathVariable Long id, @RequestBody Categorias categoria) {
        return ResponseEntity.ok(mapper.toDTO(service.updateCategoria(id, categoria)));
    }

    @PreAuthorize("principal.nivel >= 2") // Rota acessivel apenas para usuarios com nivel ADMIN ou superior
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategoria(@PathVariable long id) {
        service.deleteCategoria(id);
        return ResponseEntity.ok().build();
    }
}
