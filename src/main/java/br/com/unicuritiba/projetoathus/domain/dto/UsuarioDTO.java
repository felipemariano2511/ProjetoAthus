package br.com.unicuritiba.projetoathus.domain.dto;

import java.util.Date;

public record UsuarioDTO(
        Long id,
        String nomeCompleto,
        String nome,
        String email,
        String telefone,
        String cpf,
        Date dataNascimento,
        String pais,
        String estado,
        String cidade,
        String cep,
        String rua,
        int numero,
        int apartamento,
        String logradouro,
        String imagemPerfil,
        boolean ativo,
        boolean prestadorServico
) {}
