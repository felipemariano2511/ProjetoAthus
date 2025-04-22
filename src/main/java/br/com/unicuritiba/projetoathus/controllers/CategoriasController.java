package br.com.unicuritiba.projetoathus.controllers;

import br.com.unicuritiba.projetoathus.domain.models.Categorias;
import br.com.unicuritiba.projetoathus.domain.repositories.CategoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoriasController {

    @Autowired
    private CategoriasRepository repository;

    @GetMapping("/categorias")
    public ResponseEntity<List<Categorias>> getCategorias() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping("/categorias")
    public ResponseEntity<Categorias> saveCategoria(@RequestBody Categorias categoria) {
        Categorias categoriaSalva = repository.save(categoria);
        return ResponseEntity.ok(categoriaSalva);
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<Categorias> updateCategoria(@PathVariable long id, @RequestBody Categorias categoria) {
        categoria.setId(id);
        Categorias categoriaAtualizada = repository.save(categoria);
        return ResponseEntity.ok(categoriaAtualizada);
    }

    @DeleteMapping("/categorias/{id}")
    public void removeCategoria(@PathVariable long id) {
        repository.deleteById(id);
    }
}
