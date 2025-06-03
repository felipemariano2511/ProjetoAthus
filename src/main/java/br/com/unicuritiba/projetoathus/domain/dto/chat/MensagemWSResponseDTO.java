package br.com.unicuritiba.projetoathus.domain.dto.chat;

import br.com.unicuritiba.projetoathus.domain.models.StatusMensagem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MensagemWSResponseDTO {
    private Long remetenteId;
    private Long destinatarioId;
    private String conteudo;
    private LocalDateTime dataHora;
    private StatusMensagem statusMensagem;
}