package br.com.unicuritiba.projetoathus.domain.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome_completo", nullable = false)
    @NotNull(message = "O nome não pode ser nulo")
    private String nomeCompleto;

    private String nome;

    @Column(name = "email", nullable = false)
    @NotNull(message = "O email não pode ser nulo")
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "senha")
    private String senha;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @Column(name = "pais")
    private String pais;

    @Column(name = "estado")
    private String estado;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "cep")
    private String cep;

    @Column(name = "rua")
    private String rua;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "apartamento")
    private Integer apartamento;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "nivel")
    private Short nivel;

    @Column(name = "imagem_perfil")
    private String imagemPerfil;

    @Column(name = "ativo")
    private boolean ativo;

    @Column(name = "prestador_servico")
    private boolean prestadorServico;

    @Column(name = "banido")
    private boolean banido;

    @OneToMany(mappedBy = "usuario")
    private List<PrestacaoServico> prestacaoServicos;

}
