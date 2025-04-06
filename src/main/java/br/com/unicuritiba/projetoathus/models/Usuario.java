package br.com.unicuritiba.projetoathus.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "usuario")
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "nome", nullable = false)
    @NotNull(message = "O nome não pode ser nulo")
    private String nome;

    @Column(name = "email", nullable = false)
    @NotNull
    private String email;

    @Column(name = "telefone", nullable = false)
    @NotNull(message = "O telefone não pode ser nulo")
    private String telefone;

    @Column(name = "senha", nullable = false)
    @NotNull
    private String senha;

    @Column(name = "cpf", nullable = false)
    @NotNull(message = "O CPF não pode ser nulo")
    private String cpf;

    @Column(name = "data_nascimento", nullable = false)
    @NotNull
    private Date dataNascimento;

    @Column(name = "pais", nullable = false)
    private String pais;

    @Column(name = "estado", nullable = false)
    @NotNull
    private String estado;

    @Column(name = "cidade", nullable = false)
    @NotNull
    private String cidade;

    @Column(name = "cep", nullable = false)
    @NotNull
    private String cep;

    @Column(name = "rua", nullable = false)
    @NotNull
    private String rua;

    @Column(name = "numero")
    private int numero;

    @Column(name = "apartamento")
    private String apartamento;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "nivel", nullable = false)
    @NotNull
    private boolean nivel;

    @Column(name = "imagem_perfil")
    private String imagemPerfil;

    @Column(name = "ativo", nullable = false)
    @NotNull
    private boolean ativo;


    @Column(name = "prestador_servico", nullable = false)
    @NotNull
    private boolean prestadorServico;

    @Column(name = "banido", nullable = false)
    @NotNull
    private boolean banido;


}
