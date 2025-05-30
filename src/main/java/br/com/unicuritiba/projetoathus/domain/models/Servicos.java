package br.com.unicuritiba.projetoathus.domain.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "servicos")
@Getter
@Setter
public class Servicos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categorias categoria;

    @OneToMany(mappedBy = "servico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PrestacaoServico> prestacoes;

}
