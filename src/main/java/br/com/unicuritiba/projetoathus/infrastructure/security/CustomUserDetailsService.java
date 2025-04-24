package br.com.unicuritiba.projetoathus.infrastructure.security;

import br.com.unicuritiba.projetoathus.domain.models.Usuario;
import br.com.unicuritiba.projetoathus.domain.repositories.UsuarioRepository;
import br.com.unicuritiba.projetoathus.infrastructure.exceptions.NotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private UsuarioRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws NotFoundException {
        Usuario usuario = this.repository.findByEmail(username).orElseThrow(() -> new NotFoundException("Usuário não encontrado!"));

        return new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getSenha(), new ArrayList<>());
    }
}
