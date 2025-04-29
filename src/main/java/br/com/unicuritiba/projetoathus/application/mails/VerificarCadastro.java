package br.com.unicuritiba.projetoathus.application.mails;

import br.com.unicuritiba.projetoathus.infrastructure.exceptions.ConflictException;
import br.com.unicuritiba.projetoathus.infrastructure.exceptions.UnprocessableEntityException;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Getter
public class VerificarCadastro {

    private static final int MAX_TENTATIVAS = 5;

    private boolean emailVerificado;
    private int codigoGerado;
    private LocalDateTime expiracao;
    private int tentativasRestantes;
    private LocalDateTime bloqueadoAte;
    private boolean codigoativo = false;

    public VerificarCadastro() {
        this.emailVerificado = false;
    }

    public int gerarNovoCodigo() {
        if (estaBloqueado()) {
            throw new UnprocessableEntityException("Usuário bloqueado até " + bloqueadoAte);
        }

        this.codigoGerado = 100000 + new Random().nextInt(900000);
        this.expiracao = LocalDateTime.now().plusMinutes(15);
        this.tentativasRestantes = MAX_TENTATIVAS;
        if (this.codigoativo) {
            throw new ConflictException("this works?");
        }
        this.codigoativo = true;
        return this.codigoGerado;
    }


    public VerificacaoStatus verificarCodigo(int codigoInformado) {
        if (estaBloqueado() || expiracao == null || LocalDateTime.now().isAfter(expiracao)) {
            destruirCodigo();
            this.codigoativo = false;
            return VerificacaoStatus.expirado();
        }

        if (expiracao == null || LocalDateTime.now().isAfter(expiracao)) {
            destruirCodigo();
            return VerificacaoStatus.expirado();
        }

        if (this.codigoGerado == codigoInformado) {
            this.emailVerificado = true;
            destruirCodigo();
            return VerificacaoStatus.sucessoStatus();
        } else {
            tentativasRestantes--;
            if (tentativasRestantes <= 0) {
                bloquear();
                destruirCodigo();
                return VerificacaoStatus.bloqueado(bloqueadoAte);
            }
            return VerificacaoStatus.invalido(tentativasRestantes);
        }
    }

    private void destruirCodigo() {
        this.codigoGerado = 0;
        this.expiracao = null;
    }

    private void bloquear() {
        this.bloqueadoAte = LocalDateTime.now().plusMinutes(60);
    }

    private boolean estaBloqueado() {
        return bloqueadoAte != null && LocalDateTime.now().isBefore(bloqueadoAte);
    }

    public record VerificacaoStatus(boolean sucesso, String mensagem) {
        public static VerificacaoStatus sucessoStatus() {
            return new VerificacaoStatus(true, "Código verificado com sucesso!");
        }

        public static VerificacaoStatus bloqueado(LocalDateTime ate) {
            long minutosRestantes = ChronoUnit.MINUTES.between(LocalDateTime.now(), ate);
            return new VerificacaoStatus(false, "Muitas solicitações. Aguarde " + minutosRestantes + " minutos.");
        }

        public static VerificacaoStatus expirado() {
            return new VerificacaoStatus(false, "O código expirou. Solicite um novo.");
        }

        public static VerificacaoStatus invalido(int tentativasRestantes) {
            return new VerificacaoStatus(false, "Código inválido. Tentativas restantes: " + tentativasRestantes);
        }
    }
}
