package br.com.unicuritiba.projetoathus.dto;


public class ResetSenhaDTO {

    public record ResetSenhaRequestDto(String email){}

    public record ResetSenhaConfirmarDto(String novaSenha){}

}