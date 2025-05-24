package br.com.unicuritiba.projetoathus.controllers;

import br.com.unicuritiba.projetoathus.application.services.CategoriasService;
import br.com.unicuritiba.projetoathus.domain.models.Categorias;
import br.com.unicuritiba.projetoathus.dto.CategoriaDTO;
import br.com.unicuritiba.projetoathus.mappers.CategoriaMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<CategoriaDTO>> getCategorias() {
        return ResponseEntity.ok(service.getAllCategorias().getBody());
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> postCategoria(@RequestBody Categorias categoria) {
        return ResponseEntity.ok(mapper.toDTO(service.saveCategoria(categoria)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> putCategoria(@PathVariable Long id, @RequestBody Categorias categoria) {
        return ResponseEntity.ok(mapper.toDTO(service.updateCategoria(id, categoria)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategoria(@PathVariable long id) {
        service.deleteCategoria(id);
        return ResponseEntity.ok().build();
    }
}
