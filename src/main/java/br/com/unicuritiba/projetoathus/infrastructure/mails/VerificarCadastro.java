package br.com.unicuritiba.projetoathus.infrastructure.mails;

import br.com.unicuritiba.projetoathus.infrastructure.exceptions.UnprocessableEntityException;
import br.com.unicuritiba.projetoathus.infrastructure.exceptions.CreatedException;
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
    private LocalDateTime bloqueadoParaTokenAte;
    private LocalDateTime bloqueadoParaVerificacaoAte;
    private boolean desbloqueado;
    private boolean codigoativo = false;
    private int quantidadeSolicitacoesExtra = 0;

    public VerificarCadastro() {
        this.emailVerificado = false;
    }

    public int gerarCodigo(boolean forcarGeracao) {
        if (estaBloqueadoParaToken()) {
            int segundos = (int) ChronoUnit.SECONDS.between(LocalDateTime.now(), bloqueadoParaTokenAte);
            throw new UnprocessableEntityException("Emissão de novo código bloqueada. Aguarde " + segundos + " segundos.");
        }

        if (!forcarGeracao && this.codigoativo) {
            throw new CreatedException("Código já enviado.");
        }

        if (forcarGeracao) {
            quantidadeSolicitacoesExtra = Math.min(quantidadeSolicitacoesExtra + 1, 5);
            if (quantidadeSolicitacoesExtra > 1) {
                int tempoBloqueio = (int) Math.pow(2, quantidadeSolicitacoesExtra);
                this.bloqueadoParaTokenAte = LocalDateTime.now().plusMinutes(tempoBloqueio);
            }
        } else {
            quantidadeSolicitacoesExtra = 0;
        }

        this.codigoGerado = 100000 + new Random().nextInt(900000);
        this.expiracao = LocalDateTime.now().plusMinutes(15);
        this.tentativasRestantes = MAX_TENTATIVAS;
        this.codigoativo = true;
        return this.codigoGerado;
    }

    public VerificacaoStatus verificarCodigo(int codigoInformado) {
        if (estaBloqueadoParaVerificacao()) {
            destruirCodigo();
            this.codigoativo = false;
            return VerificacaoStatus.bloqueado(bloqueadoParaVerificacaoAte);
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
                return VerificacaoStatus.bloqueado(bloqueadoParaVerificacaoAte);
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
        this.bloqueadoParaVerificacaoAte = LocalDateTime.now().plusMinutes(30);
    }

    private void desbloquear() {
        this.desbloqueado = true;
    }

    private boolean estaBloqueadoParaToken() {
        return bloqueadoParaTokenAte != null && LocalDateTime.now().isBefore(bloqueadoParaTokenAte);
    }

    private boolean estaBloqueadoParaVerificacao() {
        return bloqueadoParaVerificacaoAte != null && LocalDateTime.now().isBefore(bloqueadoParaVerificacaoAte);
    }

    public record VerificacaoStatus(boolean sucesso, String mensagem) {
        public static VerificacaoStatus sucessoStatus() {
            return new VerificacaoStatus(true, "Código verificado com sucesso!");
        }

        public static VerificacaoStatus bloqueado(LocalDateTime ate) {
            int segundosRestantes = (int) ChronoUnit.SECONDS.between(LocalDateTime.now(), ate);
            return new VerificacaoStatus(false, String.format("Usuário bloqueado. Aguarde %s segundos.", segundosRestantes));
        }

        public static VerificacaoStatus expirado() {
            return new VerificacaoStatus(false, "O código expirou. Solicite um novo.");
        }

        public static VerificacaoStatus invalido(int tentativasRestantes) {
            return new VerificacaoStatus(false, "Código inválido. Tentativas restantes: " + tentativasRestantes);
        }
    }
}
