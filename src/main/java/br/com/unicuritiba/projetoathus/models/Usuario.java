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

    @Column(name = "cpf", nullable = false)
    @NotNull(message = "O CPF não pode ser nulo")
    private String cpf;

    @Column(name = "telefone", nullable = false)
    @NotNull(message = "O telefone não pode ser nulo")
    private String telefone;

    @Column(name = "endereco", nullable = false)
    @NotNull(message = "O endereço não pode ser nulo")
    private String endereco;


}
