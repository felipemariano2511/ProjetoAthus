package br.com.unicuritiba.projetoathus.controllers;

import br.com.unicuritiba.projetoathus.application.services.OAuth2GoogleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/oauth2/google")
public class OAuth2GoogleController {

    @Autowired
    private OAuth2GoogleService auth2GoogleService;

    @PostMapping("/autenticado")
    public ResponseEntity<?> autenticarComGoogle(@RequestBody Map<String, String> body) {
        return ResponseEntity.ok(auth2GoogleService.validarToken(body.get("access_token")));
    }
}