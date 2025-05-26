package br.com.unicuritiba.projetoathus.domain.repositories;

import br.com.unicuritiba.projetoathus.domain.models.PrestacaoServico;
import br.com.unicuritiba.projetoathus.domain.models.Servicos;
import br.com.unicuritiba.projetoathus.domain.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrestacaoServicoRepository  extends JpaRepository<PrestacaoServico, Long> {
    List<PrestacaoServico> findByServico(Servicos servico);
    List<PrestacaoServico> findByUsuario(Usuario usuario);

}
