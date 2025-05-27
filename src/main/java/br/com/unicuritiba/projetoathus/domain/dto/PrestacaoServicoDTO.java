package br.com.unicuritiba.projetoathus.domain.dto;

import java.time.LocalDateTime;
import java.util.List;

public record PrestacaoServicoDTO(
        Long id,
        UsuarioResumoDTO usuario,
        ServicoResumoDTO servico,
        String descricaoCompleta,
        Double valor,
        LocalDateTime dataCriacao,
        String descricaoCurta,
        List<String> imagens,
        boolean ativo
) {
    // DTO resumido para o Usuário: apenas os dados que queremos expor
    public static record UsuarioResumoDTO(Long id, String nome, String email) {}

    // DTO resumido para o Serviço: utiliza id, nome e, para categoria, o DTO já existente
    public static record ServicoResumoDTO(Long id, String nome, CategoriasDTO categoria) {}
}
