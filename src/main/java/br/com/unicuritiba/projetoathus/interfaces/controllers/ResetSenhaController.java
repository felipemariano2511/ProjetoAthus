package br.com.unicuritiba.projetoathus.controllers;

import br.com.unicuritiba.projetoathus.application.services.ResetSenhaService;
import br.com.unicuritiba.projetoathus.dto.ResetSenhaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth/resetsenha/confirmar")
public class ResetSenhaController {

    @Autowired
    private ResetSenhaService resetSenhaService;

    @GetMapping
    public String mostrarFormulario() {
        return "view-reset-senha";
    }

    @PostMapping
    public ResponseEntity<?> resetarSenha(@Valid @RequestBody ResetSenhaDTO.ResetSenhaConfirmarDto request,
                                          @RequestParam("token") String token) {

        return resetSenhaService.resetarSenha(token, request.novaSenha());
    }

}
