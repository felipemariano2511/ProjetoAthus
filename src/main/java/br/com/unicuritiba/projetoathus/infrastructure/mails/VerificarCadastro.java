package br.com.unicuritiba.projetoathus.infrastructure.mails;

import br.com.unicuritiba.projetoathus.infrastructure.exceptions.ConflictException;
import br.com.unicuritiba.projetoathus.infrastructure.exceptions.UnprocessableEntityException;
import br.com.unicuritiba.projetoathus.infrastructure.exceptions.handler.CreatedException;
import lombok.Getter;

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
    private boolean desbloqueado;
    private boolean codigoativo = false;
    private int quantidadeSolicitacoesExtra = 0;

    public VerificarCadastro() {
        this.emailVerificado = false;
    }

    public int gerarNovoCodigo() {
        if (estaBloqueado()) {
            int minutosRestantes = (int) ChronoUnit.MINUTES.between(LocalDateTime.now(), bloqueadoAte);
            throw new UnprocessableEntityException(String.format("Usuário bloqueado. Aguarde %s minutos.", minutosRestantes));
        } else {
            desbloquear();
        }

        if (this.codigoativo) {
            throw new CreatedException("Código já enviado");
        }

        this.codigoGerado = 100000 + new Random().nextInt(900000);
        this.expiracao = LocalDateTime.now().plusMinutes(15);
        this.tentativasRestantes = MAX_TENTATIVAS;
        this.codigoativo = true;
        this.quantidadeSolicitacoesExtra = 0;
        return this.codigoGerado;
    }

    public int solicitarCodigoExtra() {
        if (estaBloqueado()) {
            int minutosRestantes = (int) ChronoUnit.MINUTES.between(LocalDateTime.now(), bloqueadoAte);
            throw new UnprocessableEntityException(String.format("Usuário bloqueado. Aguarde %s minutos.", minutosRestantes));
        } else {
            desbloquear();
        }

        quantidadeSolicitacoesExtra++;
        int tempoBloqueio = (int) Math.pow(2, quantidadeSolicitacoesExtra); // Exponencial
        this.bloqueadoAte = LocalDateTime.now().plusMinutes(tempoBloqueio);
        this.codigoGerado = 100000 + new Random().nextInt(900000);
        this.expiracao = LocalDateTime.now().plusMinutes(15);
        this.tentativasRestantes = MAX_TENTATIVAS;
        this.codigoativo = true;

        return this.codigoGerado;
    }

    public VerificacaoStatus verificarCodigo(int codigoInformado) {
        if (estaBloqueado()) {
            destruirCodigo();
            this.codigoativo = false;
            return VerificacaoStatus.bloqueado(bloqueadoAte);
        }

        if (expiracao == null || LocalDateTime.now().isAfter(expiracao)) {
            destruirCodigo();
            this.codigoativo = false;
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
                this.codigoativo = false;
                return VerificacaoStatus.bloqueado(bloqueadoAte);
            }
            return VerificacaoStatus.invalido(tentativasRestantes);
        }
    }

    private void destruirCodigo() {
        this.codigoGerado = 0;
        this.expiracao = null;
        this.codigoativo = false;
    }

    private void bloquear() {
        this.desbloqueado = false;
        this.bloqueadoAte = LocalDateTime.now().plusMinutes(30);
    }

    private void desbloquear() {
        this.desbloqueado = true;
    }

    private boolean estaBloqueado() {
        return bloqueadoAte != null && LocalDateTime.now().isBefore(bloqueadoAte);
    }

    public record VerificacaoStatus(boolean sucesso, String mensagem) {
        public static VerificacaoStatus sucessoStatus() {
            return new VerificacaoStatus(true, "Código verificado com sucesso!");
        }

        public static VerificacaoStatus bloqueado(LocalDateTime ate) {
            int minutosRestantes = (int) ChronoUnit.MINUTES.between(LocalDateTime.now(), ate);
            return new VerificacaoStatus(false, String.format("Usuário bloqueado. Aguarde %s minutos.", minutosRestantes));
        }

        public static VerificacaoStatus expirado() {
            return new VerificacaoStatus(false, "O código expirou. Solicite um novo.");
        }

        public static VerificacaoStatus invalido(int tentativasRestantes) {
            return new VerificacaoStatus(false, "Código inválido. Tentativas restantes: " + tentativasRestantes);
        }
    }
}
