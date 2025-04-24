package br.com.unicuritiba.projetoathus.application.mails;

import br.com.unicuritiba.projetoathus.infrastructure.exceptions.BuisnessException;
import br.com.unicuritiba.projetoathus.infrastructure.exceptions.ForbiddenException;
import br.com.unicuritiba.projetoathus.infrastructure.exceptions.UnauthorizedException;
import br.com.unicuritiba.projetoathus.infrastructure.exceptions.UnprocessableEntityException;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
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
            throw new IllegalStateException("Usuário bloqueado até " + bloqueadoAte);
        }

        this.codigoGerado = 100000 + new Random().nextInt(900000);
        this.expiracao = LocalDateTime.now().plusMinutes(15);
        this.tentativasRestantes = MAX_TENTATIVAS;
        if (this.codigoativo) {
            throw new ForbiddenException("this works?");
        }
        this.codigoativo = true;
        return this.codigoGerado;
    }

    public boolean verificarCodigo(int codigoInformado) {
        if (estaBloqueado() || expiracao == null || LocalDateTime.now().isAfter(expiracao)) {
            destruirCodigo();
            this.codigoativo = false;
            return false;
        }

        if (tentativasRestantes <= 0) {
            bloquear();
            destruirCodigo();
            return false;
        }

        if (this.codigoGerado == codigoInformado) {
            this.emailVerificado = true;
            destruirCodigo();
            return true;
        } else {
            tentativasRestantes--;
            if (tentativasRestantes <= 0) {
                bloquear();
                destruirCodigo();
            }
            return false;
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
}
