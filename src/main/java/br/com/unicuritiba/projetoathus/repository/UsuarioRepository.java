package br.com.unicuritiba.projetoathus.repository;

import br.com.unicuritiba.projetoathus.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}
