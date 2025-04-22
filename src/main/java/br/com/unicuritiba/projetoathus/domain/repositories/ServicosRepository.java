package br.com.unicuritiba.projetoathus.domain.repositories;

import br.com.unicuritiba.projetoathus.domain.models.Servicos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicosRepository extends JpaRepository<Servicos, Long> {
}
