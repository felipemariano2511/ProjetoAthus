package br.com.unicuritiba.projetoathus.dto;

public record RegisterRequestDTO(
        String nome,
        String email,
        String senha
) {}
