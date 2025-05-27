package br.com.unicuritiba.projetoathus.application.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioForm {

    private String nomeCompleto;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataNascimento;

    private String pais;
    private String estado;
    private String cidade;
    private String cep;
    private String rua;
    private Integer numero;
    private Integer apartamento;
    private String logradouro;
    private Boolean prestadorServico;

}
