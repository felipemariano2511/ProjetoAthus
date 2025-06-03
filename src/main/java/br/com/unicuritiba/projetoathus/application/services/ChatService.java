package br.com.unicuritiba.projetoathus.application.services;

import br.com.unicuritiba.projetoathus.domain.dto.chat.MensagemWSRequestDTO;
import br.com.unicuritiba.projetoathus.domain.models.Mensagem;
import br.com.unicuritiba.projetoathus.domain.models.StatusMensagem;
import br.com.unicuritiba.projetoathus.domain.models.Usuario;
import br.com.unicuritiba.projetoathus.domain.repositories.MensagemRepository;
import br.com.unicuritiba.projetoathus.domain.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    private MensagemRepository mensagemRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    public Mensagem salvarMensagemWebSocket(MensagemWSRequestDTO dto) {
        Mensagem mensagem = new Mensagem();
        mensagem.setRemetente(usuarioRepository.findById(dto.getRemetenteId()).orElseThrow());
        mensagem.setDestinatario(usuarioRepository.findById(dto.getDestinatarioId()).orElseThrow());
        mensagem.setConteudo(dto.getConteudo());
        mensagem.setStatus(StatusMensagem.ENVIADA);
        mensagem.setDataEnvio(LocalDateTime.now());
        mensagemRepository.save(mensagem);
        return mensagem;
    }

    public void atualizarStatusMensagem(Long mensagemId, StatusMensagem novoStatus) {
        Mensagem mensagem = mensagemRepository.findById(mensagemId).orElseThrow();
        mensagem.setStatus(novoStatus);
        mensagemRepository.save(mensagem);
    }


    public List<Mensagem> obterHistorico(Long usuario1Id, Long usuario2Id) {
        Usuario usuario1 = usuarioRepository.findById(usuario1Id).orElseThrow();
        Usuario usuario2 = usuarioRepository.findById(usuario2Id).orElseThrow();

        return mensagemRepository.findByRemetenteAndDestinatarioOrDestinatarioAndRemetenteOrderByDataEnvioAsc(
                usuario1, usuario2, usuario2, usuario1);
    }
}
