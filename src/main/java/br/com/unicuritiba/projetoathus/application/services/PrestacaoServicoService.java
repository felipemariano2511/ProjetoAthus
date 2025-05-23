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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PrestacaoServicoService {

    @Autowired
    private PrestacaoServicoRepository prestacaoServicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ServicosRepository servicosRepository;

    public ResponseEntity<PrestacaoServico> criarPrestacaoServico(PrestacaoServico prestacao) {
        Usuario usuario = usuarioRepository.findById(prestacao.getUsuario().getId()).orElseThrow(() ->
                new NotFoundException("Usuário não encontrado no banco de dados"));
        Servicos servico = servicosRepository.findById(prestacao.getServico().getId()).orElseThrow(() ->
                new NotFoundException("Serviço não encontrado no banco de dados"));

        if(prestacao.getImagens() != null && prestacao.getImagens().size() < 5) {
            throw new UnprocessableEntityException("Limite de Imagens excedida. Máximo 5 imagens.");
        }

        PrestacaoServico prestacaoCadastro = new PrestacaoServico();
        prestacaoCadastro.setUsuario(usuario);
        prestacaoCadastro.setServico(servico);
        prestacaoCadastro.setAtivo(true);
        prestacaoCadastro.setDataCriacao(LocalDateTime.now());
        prestacaoCadastro.setDescricaoCurta(prestacao.getDescricaoCurta());
        prestacaoCadastro.setDescricaoCompleta(prestacao.getDescricaoCompleta());
        prestacaoCadastro.setValor(prestacao.getValor());
        prestacaoCadastro.setImagens(prestacao.getImagens());

        return ResponseEntity.ok(prestacaoServicoRepository.save(prestacaoCadastro));
    }

    public ResponseEntity<List<PrestacaoServico>> buscarTodos() throws NoContentException {
        return ResponseEntity.ok(prestacaoServicoRepository.findAll());
    }

    public ResponseEntity<PrestacaoServico> buscarPorId(Long servicoId){
        return ResponseEntity.ok(prestacaoServicoRepository.findById(servicoId)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Prestação de Serviço não encontrada: %d", servicoId))));
    }

    public ResponseEntity<PrestacaoServico> deletarPrestacaoServico(Long servicoId){
        prestacaoServicoRepository.findById(servicoId).orElseThrow(
            () -> new RuntimeException(String.format("Prestação de Servico não encontrada: %d", servicoId))
        );
        prestacaoServicoRepository.deleteById(servicoId);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<PrestacaoServico> atualizarPrestacaoServico(PrestacaoServico prestacao){

        Usuario usuario = usuarioRepository.findById(prestacao.getUsuario().getId()).orElseThrow(() ->
                new NotFoundException("Usuário não encontrado no banco de dados."));
        Servicos servico = servicosRepository.findById(prestacao.getServico().getId()).orElseThrow(() ->
                new NotFoundException("Serviço não encontrado no banco de dados."));

        if(prestacao.getImagens() != null && prestacao.getImagens().size() < 5) {
            throw new UnprocessableEntityException("Limite de Imagens excedida. Máximo 5 imagens.");
        }

        PrestacaoServico prestacaoAtualizada = new PrestacaoServico();
        prestacaoAtualizada.setUsuario(usuario);
        prestacaoAtualizada.setServico(servico);
        prestacaoAtualizada.setAtivo(true);
        prestacaoAtualizada.setDataCriacao(LocalDateTime.now());
        prestacaoAtualizada.setDescricaoCurta(prestacao.getDescricaoCurta());
        prestacaoAtualizada.setDescricaoCompleta(prestacao.getDescricaoCompleta());
        prestacaoAtualizada.setValor(prestacao.getValor());
        prestacaoAtualizada.setImagens(prestacao.getImagens());

        return ResponseEntity.ok(prestacaoServicoRepository.saveAndFlush(prestacaoAtualizada));

    }

    public ResponseEntity<PrestacaoServico> procurarPorServico(String servico){
        Servicos servicoEncontrado = servicosRepository.findByNome(servico).orElseThrow(
                () -> new NotFoundException(String.format("Serviço com o nome: %s não encontrado.", servico ))
        );

        return ResponseEntity.ok(prestacaoServicoRepository.findById(servicoEncontrado.getId())
                .orElseThrow(() -> new NotFoundException(String.format(
                        "Nenhuma Prestação de Serviço encontrada com o Serviço: %s", servicoEncontrado.getNome()
                ))));

    }

    public ResponseEntity<PrestacaoServico> procurarPorUsuario(Long usuarioId){
        Usuario usuarioEncontrado = usuarioRepository.findById(usuarioId).orElseThrow(
                () -> new NotFoundException(String.format("Prestador não encontrado: %d", usuarioId
                )));

        return ResponseEntity.ok(prestacaoServicoRepository.findById(usuarioEncontrado.getId()).orElseThrow(
                () -> new NotFoundException(String.format("Nenhuma Prestação de Serviçp criada por esse Usuário: %s", usuarioEncontrado.getNome()
                ))));
    }

    public ResponseEntity<PrestacaoServico> desativarPrestacao(PrestacaoServico prestacao){
        prestacaoServicoRepository.findById(prestacao.getServico().getId()).orElseThrow(
                () -> new NotFoundException("Prestação de Serviço não encontrada")
        );
        prestacao.setAtivo(false);
        return ResponseEntity.ok(prestacaoServicoRepository.saveAndFlush(prestacao));
    }

}
