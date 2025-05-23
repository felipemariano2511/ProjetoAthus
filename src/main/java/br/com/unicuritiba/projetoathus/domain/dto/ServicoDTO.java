package br.com.unicuritiba.projetoathus.domain.dto;

import br.com.unicuritiba.projetoathus.domain.models.Categorias;

public record ServicoDTO (
    Long id,
    String nome,
    Categorias categoria_id
){}
