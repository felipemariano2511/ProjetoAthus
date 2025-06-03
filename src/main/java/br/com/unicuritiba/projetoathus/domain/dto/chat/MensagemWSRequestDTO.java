package br.com.unicuritiba.projetoathus.domain.dto.chat;

import br.com.unicuritiba.projetoathus.domain.models.Mensagem;
import br.com.unicuritiba.projetoathus.domain.models.StatusMensagem;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MensagemWSRequestDTO {
    private Long id;
    private Long remetenteId;
    private Long destinatarioId;
    private String conteudo;
    private StatusMensagem statusMensagem;
    private LocalDateTime dataEnvio;

    public static MensagemWSRequestDTO from(Mensagem m) {
        MensagemWSRequestDTO dto = new MensagemWSRequestDTO();
        dto.id = m.getId();
        dto.remetenteId = m.getRemetente().getId();
        dto.destinatarioId = m.getDestinatario().getId();
        dto.conteudo = m.getConteudo();
        dto.statusMensagem = m.getStatus();
        dto.dataEnvio = m.getDataEnvio();
        return dto;
    }
}
