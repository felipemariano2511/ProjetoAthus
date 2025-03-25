package br.com.unicuritiba.projetoathus.repositories;

import br.com.unicuritiba.projetoathus.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}
