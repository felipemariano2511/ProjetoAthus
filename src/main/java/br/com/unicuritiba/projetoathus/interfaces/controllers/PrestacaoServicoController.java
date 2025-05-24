package br.com.unicuritiba.projetoathus.interfaces.controllers;

import br.com.unicuritiba.projetoathus.application.forms.PrestacaoServicoForms;
import br.com.unicuritiba.projetoathus.application.services.PrestacaoServicoService;
import br.com.unicuritiba.projetoathus.domain.models.PrestacaoServico;
import br.com.unicuritiba.projetoathus.mappers.PrestacaoServicosFormsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/prestacaoservicos")
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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> postPrestacaoServico(
            @RequestParam("imagem") List<MultipartFile> imagens,
            @ModelAttribute PrestacaoServicoForms forms) {

        PrestacaoServico servico = PrestacaoServicosFormsMapper.toEntity(forms);
        return ResponseEntity.ok(service.criarPrestacaoServico(imagens, servico));
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> putPrestacaoServico(
            @RequestParam("imagem") List<MultipartFile> imagens,
            @ModelAttribute PrestacaoServicoForms forms) {

        PrestacaoServico servico = PrestacaoServicosFormsMapper.toEntity(forms);
        return ResponseEntity.ok(service.atualizarPrestacaoServico(imagens, servico));
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
