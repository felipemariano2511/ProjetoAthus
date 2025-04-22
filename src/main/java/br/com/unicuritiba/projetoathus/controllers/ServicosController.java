package br.com.unicuritiba.projetoathus.controllers;

import br.com.unicuritiba.projetoathus.domain.models.Servicos;
import br.com.unicuritiba.projetoathus.domain.repositories.ServicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServicosController {

    @Autowired
    private ServicosRepository repository;

    @GetMapping("/servicos")
    public ResponseEntity<List<Servicos>> getServicos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping("/servicos")
    public ResponseEntity<Servicos> saveServico(@RequestBody Servicos servico) {
        Servicos servicoSalvo = repository.save(servico);
        return ResponseEntity.ok(servicoSalvo);
    }

    @PutMapping("/servicos/{id}")
    public ResponseEntity<Servicos> updateServico(@PathVariable long id, @RequestBody Servicos servico) {
        servico.setId(id);
        Servicos servicoAtualizado = repository.save(servico);
        return ResponseEntity.ok(servicoAtualizado);
    }

    @DeleteMapping("/servicos/{id}")
    public void removeServico(@PathVariable long id) {
        repository.deleteById(id);
    }
}
