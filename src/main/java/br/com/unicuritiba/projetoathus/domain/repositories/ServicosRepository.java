package br.com.unicuritiba.projetoathus.domain.repositories;

import br.com.unicuritiba.projetoathus.domain.models.Servicos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServicosRepository extends JpaRepository<Servicos, Long> {

    Optional<Servicos> findByNome(String nome);

}
