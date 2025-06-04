package br.com.unicuritiba.projetoathus.interfaces.controllers;

import br.com.unicuritiba.projetoathus.application.services.PrestacaoServicoService;
import br.com.unicuritiba.projetoathus.domain.dto.prestacaoservicos.PSPUTDTO;
import br.com.unicuritiba.projetoathus.domain.dto.prestacaoservicos.PrestacaoServicoDTO;
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

    // Endpoint para buscar todas as prestações de serviço
    @GetMapping
    public ResponseEntity<List<PrestacaoServicoDTO>> findAll() {
        return service.buscarTodos();
    }

    // Endpoint para buscar uma prestação de serviço pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<PrestacaoServicoDTO> findById(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    // buscar por id de usuário
    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<PrestacaoServicoDTO>> findByUsuario(@PathVariable Long id) {
        return service.procurarPorUsuario(id);
    }


    @GetMapping("/servico/{nome}")
    public ResponseEntity<List<PrestacaoServicoDTO>> findByServico(@PathVariable String nome) {
        return service.procurarPorServico(nome);
    }


    // Endpoint para criar uma nova prestação de serviço
    // O DTO recebido (via @ModelAttribute) deve conter os objetos aninhados com, ao menos, os IDs em dto.usuario().id() e dto.servico().id()
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PrestacaoServicoDTO> postPrestacaoServico(
            @RequestParam("imagem") List<MultipartFile> imagens,
            @ModelAttribute("prestacao") PrestacaoServico prestacao) {
        return service.criarPrestacaoServico(imagens, prestacao);
    }

    // Endpoint para atualizar uma prestação de serviço existente
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PrestacaoServicoDTO> putPrestacaoServico(
            @RequestParam("imagem") List<MultipartFile> imagens,
            @ModelAttribute PSPUTDTO dto) {
        return service.atualizarPrestacaoServico(imagens, dto);
    }

    // operação de desativar:
    @PutMapping("/desativar/{id}")
    public ResponseEntity<PrestacaoServicoDTO> desativarPrestacaoServico(@PathVariable Long id) {
        return service.desativarPrestacao(id);
    }

    // operação de ativar:
    @PutMapping("/ativar/{id}")
    public ResponseEntity<PrestacaoServicoDTO> ativarPrestacaoServico(@PathVariable Long id) {
        return service.ativarPrestacao(id);
    }


    // Endpoint para deletar uma prestação de serviço
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrestacaoServico(@PathVariable Long id) {
        return service.deletarPrestacaoServico(id);
    }
}
