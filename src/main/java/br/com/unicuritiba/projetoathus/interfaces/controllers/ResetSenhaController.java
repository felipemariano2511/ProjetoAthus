package br.com.unicuritiba.projetoathus.interfaces.controllers;

import br.com.unicuritiba.projetoathus.application.services.ResetSenhaService;
import br.com.unicuritiba.projetoathus.domain.dto.auth.ResetSenhaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth/resetsenha")
public class ResetSenhaController {

    @Autowired
    private ResetSenhaService resetSenhaService;

    @PostMapping
    public ResponseEntity<?> solcitiarResetSenha(@RequestBody ResetSenhaDTO.ResetSenhaRequestDto request) {
        return ResponseEntity.ok(resetSenhaService.enviarTokenReset(request.email()));
    }

    @GetMapping("/confirmar")
    public String mostrarFormulario() {
        return "view-reset-senha";
    }

    @PostMapping("/confirmar")
    public ResponseEntity<?> resetarSenha(@Valid @RequestBody ResetSenhaDTO.ResetSenhaConfirmarDto request,
                                          @RequestParam("token") String token) {

        return resetSenhaService.resetarSenha(token, request.novaSenha());
    }

}
