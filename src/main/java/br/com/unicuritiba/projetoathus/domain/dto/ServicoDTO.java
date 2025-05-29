package br.com.unicuritiba.projetoathus.domain.dto;

public record ServicoDTO (
    Long id,
    String nome,
    CategoriasDto categoriasDto
){
    public static record CategoriasDto(Long id, String nome) {}
}
