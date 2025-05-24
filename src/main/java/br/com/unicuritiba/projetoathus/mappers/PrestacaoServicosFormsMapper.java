package br.com.unicuritiba.projetoathus.mappers;

import br.com.unicuritiba.projetoathus.application.forms.PrestacaoServicoForms;
import br.com.unicuritiba.projetoathus.domain.models.PrestacaoServico;

public class PrestacaoServicosFormsMapper {

    public static PrestacaoServico toEntity(PrestacaoServicoForms forms) {

        PrestacaoServico prestacaoServico = new PrestacaoServico();

        prestacaoServico.setUsuario(forms.usuario());
        prestacaoServico.setServico(forms.servico());
        prestacaoServico.setDescricaoCompleta(forms.descricaoCompleta());
        prestacaoServico.setValor(forms.valor());
        prestacaoServico.setDescricaoCurta(forms.DescricaoCurta());
        prestacaoServico.setAtivo(forms.ativo());

        return prestacaoServico;
    }
}
