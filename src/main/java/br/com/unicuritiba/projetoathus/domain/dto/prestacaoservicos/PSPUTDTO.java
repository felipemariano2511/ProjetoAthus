package br.com.unicuritiba.projetoathus.domain.dto.prestacaoservicos;

import java.time.LocalDateTime;
import java.util.List;

public record PSPUTDTO(
    Long id,
    Long usuario,
    Long servico,
    String descricaoCompleta,
    Double valor,
    LocalDateTime dataCriacao,
    String descricaoCurta,
    List<String> imagens,
    boolean ativo){
}
