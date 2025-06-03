package br.com.unicuritiba.projetoathus.domain.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "mensagens")
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Usuario remetente;

    @ManyToOne(optional = false)
    private Usuario destinatario;

    @Enumerated(EnumType.STRING)
    private StatusMensagem status;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String conteudo;

    private LocalDateTime dataEnvio;

}
