package br.com.unicuritiba.projetoathus.controllers;

import br.com.unicuritiba.projetoathus.application.services.PrestacaoServicoService;
import br.com.unicuritiba.projetoathus.domain.models.PrestacaoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prestacao-servicos")
public class PrestacaoServicoController {
    @Autowired
    private PrestacaoServicoService service;

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(service.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> findByIdUsuario(@PathVariable Long id){
        return ResponseEntity.ok(service.procurarPorUsuario(id));
    }

    @GetMapping("/servico/{nome}")
    public ResponseEntity<?> findByNameServico(@PathVariable String nome){
        return ResponseEntity.ok(service.procurarPorServico(nome));
    }

    @PostMapping
    public ResponseEntity<?> postPrestacaoServico(@RequestBody PrestacaoServico prestacaoServico){
        return ResponseEntity.ok(service.criarPrestacaoServico(prestacaoServico));
    }

    @PutMapping
    public ResponseEntity<?> updatePrestacaoServico(@RequestBody PrestacaoServico prestacaoServico){
        return ResponseEntity.ok(service.atualizarPrestacaoServico(prestacaoServico));
    }

    @PutMapping("/desativar")
    public ResponseEntity<?> desativarPrestacaoServico(@RequestBody PrestacaoServico prestacaoServico){
        return ResponseEntity.ok(service.desativarPrestacao(prestacaoServico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePrestacaoServico(@PathVariable Long id){
        return ResponseEntity.ok(service.deletarPrestacaoServico(id));
    }
    
}
