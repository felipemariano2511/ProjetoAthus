package br.com.unicuritiba.projetoathus.domain.dto.auth;


public class ResetSenhaDTO {

    public record ResetSenhaRequestDto(String email){}

    public record ResetSenhaConfirmarDto(String novaSenha){}

}