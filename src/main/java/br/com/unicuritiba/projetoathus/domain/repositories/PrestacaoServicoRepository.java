package br.com.unicuritiba.projetoathus.domain.repositories;

import br.com.unicuritiba.projetoathus.domain.models.PrestacaoServico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestacaoServicoRepository  extends JpaRepository<PrestacaoServico, Long> { }
