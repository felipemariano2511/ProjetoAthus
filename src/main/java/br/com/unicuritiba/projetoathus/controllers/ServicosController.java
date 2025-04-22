package br.com.unicuritiba.projetoathus.controllers;

import br.com.unicuritiba.projetoathus.application.services.ServicosService;
import br.com.unicuritiba.projetoathus.domain.models.Servicos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicosController {

    @Autowired
    private ServicosService service;

    @GetMapping
    public ResponseEntity<List<Servicos>> getAllServicos() {
        List<Servicos> servicos = service.getAllServicos();
        return ResponseEntity.ok(servicos);
    }

    @PostMapping
    public ResponseEntity<Servicos> postServico(@RequestBody Servicos servico) {
        Servicos servicoCadastrado = service.saveServicos(servico);
        return ResponseEntity.ok(servicoCadastrado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servicos> putServico(@PathVariable Long id, @RequestBody Servicos servico) {
        Servicos servicoAtualizado = service.updateServicos(id, servico);
        return ResponseEntity.ok(servicoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteServico(@PathVariable Long id) {
        service.deleteServicos(id);
        return ResponseEntity.ok().build();
    }
}
