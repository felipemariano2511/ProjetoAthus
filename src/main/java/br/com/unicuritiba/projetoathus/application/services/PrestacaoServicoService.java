package br.com.unicuritiba.projetoathus.application.services;

import br.com.unicuritiba.projetoathus.domain.models.PrestacaoServico;
import br.com.unicuritiba.projetoathus.domain.models.Servicos;
import br.com.unicuritiba.projetoathus.domain.models.Usuario;
import br.com.unicuritiba.projetoathus.domain.repositories.PrestacaoServicoRepository;
import br.com.unicuritiba.projetoathus.domain.repositories.ServicosRepository;
import br.com.unicuritiba.projetoathus.domain.repositories.UsuarioRepository;
import br.com.unicuritiba.projetoathus.infrastructure.exceptions.NoContentException;
import br.com.unicuritiba.projetoathus.infrastructure.exceptions.NotFoundException;
import br.com.unicuritiba.projetoathus.infrastructure.exceptions.UnprocessableEntityException;
import br.com.unicuritiba.projetoathus.domain.dto.PrestacaoServicoDTO;
import br.com.unicuritiba.projetoathus.mappers.PrestacaoServicoMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PrestacaoServicoService {

    @Autowired
    private PrestacaoServicoRepository prestacaoServicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ServicosRepository servicosRepository;

    @Autowired
    private ImagesUploadService imagesUploadService;

    @Autowired
    private PrestacaoServicoMapper prestacaoServicoMapper;

    @Transactional
    public ResponseEntity<PrestacaoServicoDTO> criarPrestacaoServico(List<MultipartFile> imagens, PrestacaoServico prestacao) {
        // Se necessário, você pode validar e buscar os objetos completos:
        Usuario usuario = usuarioRepository.findById(prestacao.getUsuario().getId())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado!"));
        Servicos servico = servicosRepository.findById(prestacao.getServico().getId())
                .orElseThrow(() -> new NotFoundException("Serviço não encontrado!"));

        // Você pode substituir as referências com os objetos completos.
        prestacao.setUsuario(usuario);
        prestacao.setServico(servico);

        // Aplica regras de negócio antes de salvar:
        prestacao.setAtivo(true);
        prestacao.setDataCriacao(LocalDateTime.now());

        if (imagens != null && !imagens.isEmpty()) {
            if (imagens.size() < 5) {
                prestacao.setImagens(salvarImagens(imagens));
            } else {
                throw new UnprocessableEntityException("Limite de imagens excedido. Máximo 5 imagens.");
            }
        }

        PrestacaoServico saved = prestacaoServicoRepository.save(prestacao);
        PrestacaoServicoDTO dtoResposta = prestacaoServicoMapper.toDTO(saved);
        return ResponseEntity.ok(dtoResposta);
    }

    public ResponseEntity<List<PrestacaoServicoDTO>> buscarTodos() {
        List<PrestacaoServicoDTO> servicos = prestacaoServicoRepository.findAll()
                .stream()
                .map(prestacaoServicoMapper::toDTO)
                .toList();

        if (servicos.isEmpty()) {
            throw new NoContentException("Nenhuma prestação de serviço encontrada.");
        }

        return ResponseEntity.ok(servicos);
    }

    public ResponseEntity<PrestacaoServicoDTO> buscarPorId(Long servicoId) {
        PrestacaoServico prestacaoServico = prestacaoServicoRepository.findById(servicoId)
                .orElseThrow(() -> new NotFoundException(String.format("Prestação de Serviço não encontrada: %d", servicoId)));

        return ResponseEntity.ok(prestacaoServicoMapper.toDTO(prestacaoServico));
    }

    @Transactional
    public ResponseEntity<Void> deletarPrestacaoServico(Long servicoId) {
        prestacaoServicoRepository.findById(servicoId)
                .orElseThrow(() -> new NotFoundException(String.format("Prestação de Serviço não encontrada: %d", servicoId)));
        prestacaoServicoRepository.deleteById(servicoId);
        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity<PrestacaoServicoDTO> atualizarPrestacaoServico(List<MultipartFile> imagens, PrestacaoServicoDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.usuario().id())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado no banco de dados."));
        Servicos servico = servicosRepository.findById(dto.servico().id())
                .orElseThrow(() -> new NotFoundException("Serviço não encontrado no banco de dados."));

        PrestacaoServico prestacaoAtualizada = prestacaoServicoMapper.toEntity(dto, usuario, servico);
        prestacaoAtualizada.setDataCriacao(LocalDateTime.now());

        if (imagens != null && imagens.size() < 5) {
            prestacaoAtualizada.setImagens(salvarImagens(imagens));
        } else if (imagens != null && imagens.size() >= 5) {
            throw new UnprocessableEntityException("Limite de imagens excedido. Máximo 5 imagens.");
        }

        return ResponseEntity.ok(prestacaoServicoMapper.toDTO(prestacaoServicoRepository.saveAndFlush(prestacaoAtualizada)));
    }

    public ResponseEntity<List<PrestacaoServicoDTO>> procurarPorServico(String nomeServico) {
        Servicos servico = servicosRepository.findByNome(nomeServico)
                .orElseThrow(() -> new NotFoundException(String.format("Serviço com o nome '%s' não encontrado", nomeServico)));

        List<PrestacaoServico> prestacoes = prestacaoServicoRepository.findByServico(servico);
        if (prestacoes.isEmpty()) {
            throw new NoContentException("Nenhuma prestação de serviço encontrada para o serviço: " + nomeServico);
        }

        List<PrestacaoServicoDTO> dtos = prestacoes.stream()
                .map(prestacaoServicoMapper::toDTO)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @Transactional
    public ResponseEntity<List<PrestacaoServicoDTO>> procurarPorUsuario(Long usuarioId) {
        // Busca o usuário pelo ID
        Usuario usuarioEntity = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado: " + usuarioId));

        // Busca as prestações de serviço associadas a esse usuário
        List<PrestacaoServico> prestacoes = prestacaoServicoRepository.findByUsuario(usuarioEntity);

        if (prestacoes.isEmpty()) {
            throw new NoContentException("Nenhuma prestação de serviço encontrada para o usuário: " + usuarioId);
        }

        // Converte a lista para DTOs usando o mapper
        List<PrestacaoServicoDTO> dtos = prestacoes.stream()
                .map(prestacaoServicoMapper::toDTO)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<PrestacaoServicoDTO> desativarPrestacao(Long prestacaoId) {
        PrestacaoServico prestacaoServico = prestacaoServicoRepository.findById(prestacaoId)
                .orElseThrow(() -> new NotFoundException(String.format("Prestação de Serviço não encontrada: %d", prestacaoId)));

        prestacaoServico.setAtivo(false);
        PrestacaoServico updated = prestacaoServicoRepository.saveAndFlush(prestacaoServico);
        return ResponseEntity.ok(prestacaoServicoMapper.toDTO(updated));
    }

    public ResponseEntity<PrestacaoServicoDTO> ativarPrestacao(Long prestacaoId) {
        PrestacaoServico prestacaoServico = prestacaoServicoRepository.findById(prestacaoId)
                .orElseThrow(() -> new NotFoundException(String.format("Prestação de Serviço não encontrada: %d", prestacaoId)));

        prestacaoServico.setAtivo(true);
        PrestacaoServico updated = prestacaoServicoRepository.saveAndFlush(prestacaoServico);
        return ResponseEntity.ok(prestacaoServicoMapper.toDTO(updated));
    }


    private List<String> salvarImagens(List<MultipartFile> imagens) {
        imagesUploadService.upload(imagens);
        List<String> caminhoImagens = new ArrayList<>();

        for (MultipartFile imagem : imagens) {
            caminhoImagens.add(imagem.getOriginalFilename());
        }

        return caminhoImagens;
    }



}
