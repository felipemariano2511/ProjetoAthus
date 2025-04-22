package br.com.unicuritiba.projetoathus.controllers;

import br.com.unicuritiba.projetoathus.application.services.CategoriasService;
import br.com.unicuritiba.projetoathus.domain.models.Categorias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriasController {

    @Autowired
    private CategoriasService service;

    @GetMapping
    public ResponseEntity<List<Categorias>> getCategorias() {
        List<Categorias> categorias = service.getAllCategorias();
        return ResponseEntity.ok(categorias);
    }

    @PostMapping
    public ResponseEntity<Categorias> postCategoria(@RequestBody Categorias categoria) {
        Categorias categoriaSalva = service.saveCategoria(categoria);
        return ResponseEntity.ok(categoriaSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categorias> putCategoria(@PathVariable Long id, @RequestBody Categorias categoria) {
        Categorias categoriaAtualizada = service.updateCategoria(id, categoria);
        return ResponseEntity.ok(categoriaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategoria(@PathVariable long id) {
        service.deleteCategoria(id);
        return ResponseEntity.ok().build();
    }
}
