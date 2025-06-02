package br.com.unicuritiba.projetoathus.interfaces.controllers;

import br.com.unicuritiba.projetoathus.application.services.PrestacaoServicoService;
import br.com.unicuritiba.projetoathus.domain.dto.PrestacaoServicoDTO;
import br.com.unicuritiba.projetoathus.domain.models.PrestacaoServico;
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
    public ResponseEntity<List<PrestacaoServicoDTO>> findAll() {
        return service.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestacaoServicoDTO> findById(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<PrestacaoServicoDTO>> findByUsuario(@PathVariable Long id) {
        return service.procurarPorUsuario(id);
    }


    @GetMapping("/servico/{nome}")
    public ResponseEntity<List<PrestacaoServicoDTO>> findByServico(@PathVariable String nome) {
        return service.procurarPorServico(nome);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PrestacaoServicoDTO> postPrestacaoServico(
            @RequestParam("imagem") List<MultipartFile> imagens,
            @ModelAttribute("prestacao") PrestacaoServico prestacao) {
        return service.criarPrestacaoServico(imagens, prestacao);
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PrestacaoServicoDTO> putPrestacaoServico(
            @RequestParam("imagem") List<MultipartFile> imagens,
            @ModelAttribute PrestacaoServicoDTO dto) {
        return service.atualizarPrestacaoServico(imagens, dto);
    }

    @PutMapping("/desativar/{id}")
    public ResponseEntity<PrestacaoServicoDTO> desativarPrestacaoServico(@PathVariable Long id) {
        return service.desativarPrestacao(id);
    }

    @PutMapping("/ativar/{id}")
    public ResponseEntity<PrestacaoServicoDTO> ativarPrestacaoServico(@PathVariable Long id) {
        return service.ativarPrestacao(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrestacaoServico(@PathVariable Long id) {
        return service.deletarPrestacaoServico(id);
    }
}
