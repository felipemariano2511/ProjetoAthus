package br.com.unicuritiba.projetoathus.controllers;

import br.com.unicuritiba.projetoathus.application.services.ResetSenhaService;
import br.com.unicuritiba.projetoathus.dto.ResetSenhaConfirmarDTO;
import br.com.unicuritiba.projetoathus.dto.ResetSenhaRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth/reset-senha")
@RequiredArgsConstructor
public class ResetSenhaController {

    private final ResetSenhaService resetSenhaService;

    @PostMapping("/enviar")
    public ResponseEntity<?> enviarTokenReset(@Valid @RequestBody ResetSenhaRequestDTO request) {
        return resetSenhaService.enviarTokenReset(request.email());
    }

    @PostMapping("/confirmar")
    public ResponseEntity<?> resetarSenha(@Valid @RequestBody ResetSenhaConfirmarDTO request) {
        return resetSenhaService.resetarSenha(request.token(), request.novaSenha());
    }
}
