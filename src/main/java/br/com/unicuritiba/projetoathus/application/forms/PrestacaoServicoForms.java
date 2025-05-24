package br.com.unicuritiba.projetoathus.application.forms;

import br.com.unicuritiba.projetoathus.domain.models.Servicos;
import br.com.unicuritiba.projetoathus.domain.models.Usuario;
import java.time.LocalDateTime;

public record PrestacaoServicoForms(
        Usuario usuario,
        Servicos servico,
        String descricaoCompleta,
        Double valor,
        String DescricaoCurta,
        Boolean ativo) {
}
