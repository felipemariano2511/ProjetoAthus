package br.com.unicuritiba.projetoathus.domain.dto.usuario;

import java.util.Date;

public record AdminDTO(
        Long id,
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
        String apartamento,
        String logradouro,
        String imagemPerfil,
        boolean ativo,
        int nivel,
        boolean prestadorServico,
        boolean banido
) {}
