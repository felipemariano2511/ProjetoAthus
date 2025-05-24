package br.com.unicuritiba.projetoathus.domain.models;

import br.com.unicuritiba.projetoathus.infrastructure.persistence.StringListConverter;
import jakarta.persistence.*;
import jakarta.websocket.Encoder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "prestacao_servico")
@Getter
@Setter
public class PrestacaoServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name ="id_usuario" , nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name ="id_servico" , nullable = false)
    private Servicos servico;

    @Column(columnDefinition = "TEXT")
    private String descricaoCompleta;

    @Column
    private Double valor;

    @Column
    private LocalDateTime dataCriacao;

    @Column
    private String descricaoCurta;

    @Column
    @Convert(converter = StringListConverter.class)
    private List<String> imagens;

    @Column
    private boolean ativo;

}
