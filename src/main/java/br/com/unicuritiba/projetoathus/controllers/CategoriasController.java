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
        return ResponseEntity.ok(service.getAllCategorias());
    }

    @PostMapping
    public ResponseEntity<Categorias> postCategoria(@RequestBody Categorias categoria) {
        return ResponseEntity.ok(service.saveCategoria(categoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categorias> putCategoria(@PathVariable Long id, @RequestBody Categorias categoria) {
        return ResponseEntity.ok(service.updateCategoria(id, categoria));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategoria(@PathVariable long id) {
        service.deleteCategoria(id);
        return ResponseEntity.ok().build();
    }
}
