package br.com.unicuritiba.projetoathus.interfaces.controllers;

import br.com.unicuritiba.projetoathus.application.services.ChatService;
import br.com.unicuritiba.projetoathus.domain.dto.chat.MensagemWSRequestDTO;
import br.com.unicuritiba.projetoathus.domain.dto.chat.MensagemWSResponseDTO;
import br.com.unicuritiba.projetoathus.domain.models.Mensagem;
import br.com.unicuritiba.projetoathus.domain.models.StatusMensagem;
import br.com.unicuritiba.projetoathus.domain.repositories.MensagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class WebSocketChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private MensagemRepository mensagemRepository;

    @MessageMapping("/mensagem")
    public void processarMensagem(MensagemWSRequestDTO dto) {
        // **Garantir persistência antes do envio**
        Mensagem mensagemSalva = chatService.salvarMensagemWebSocket(dto);

        // Criar resposta para envio via WebSocket
        MensagemWSResponseDTO responseDto = new MensagemWSResponseDTO(
                mensagemSalva.getRemetente().getId(),
                mensagemSalva.getDestinatario().getId(),
                mensagemSalva.getConteudo(),
                mensagemSalva.getDataEnvio(),
                mensagemSalva.getStatus()
        );

        // Enviar a mensagem ao WebSocket do destinatário
        simpMessagingTemplate.convertAndSend("/chat/usuario/" + dto.getDestinatarioId(), responseDto);
    }

    @PatchMapping("/api/chat/status/{id}")
    public ResponseEntity<Void> marcarComoLida(@PathVariable Long id) {
        chatService.atualizarStatusMensagem(id, StatusMensagem.LIDA);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/chat/historico/{usuario1Id}/{usuario2Id}")
    public ResponseEntity<List<MensagemWSResponseDTO>> consultarHistorico(
            @PathVariable Long usuario1Id, @PathVariable Long usuario2Id) {

        List<Mensagem> mensagens = chatService.obterHistorico(usuario1Id, usuario2Id);

        List<MensagemWSResponseDTO> historico = mensagens.stream()
                .map(m -> new MensagemWSResponseDTO(
                        m.getRemetente().getId(),
                        m.getDestinatario().getId(),
                        m.getConteudo(),
                        m.getDataEnvio(),
                        m.getStatus()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(historico);
    }
}
