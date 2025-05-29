package br.com.unicuritiba.projetoathus.interfaces.controllers;

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
    public ResponseEntity<?> getAllServicos() {

        return ResponseEntity.ok(service.getAllServicos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getServico(@PathVariable Long id) {
        return ResponseEntity.ok(service.getServico(id));
    }

    @PostMapping
    public ResponseEntity<?> postServico(@RequestBody Servicos servico) {
        return ResponseEntity.ok(service.saveServicos(servico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putServico(@PathVariable Long id, @RequestBody Servicos servico) {
        return ResponseEntity.ok(service.updateServicos(id, servico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteServico(@PathVariable Long id) {
        service.deleteServicos(id);
        return ResponseEntity.ok().build();
    }
}
