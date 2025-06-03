package br.com.unicuritiba.projetoathus.domain.dto.chat;

public record NovaMensagemDTO(
        Long remetenteId,
        Long destinatarioId,
        String conteudo
) {}
