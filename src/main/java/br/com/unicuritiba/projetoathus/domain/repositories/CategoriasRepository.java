package br.com.unicuritiba.projetoathus.domain.repositories;

import br.com.unicuritiba.projetoathus.domain.models.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriasRepository extends JpaRepository<Categorias, Long> {
}
