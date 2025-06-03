package br.com.unicuritiba.projetoathus.domain.repositories;

import br.com.unicuritiba.projetoathus.domain.models.Mensagem;
import br.com.unicuritiba.projetoathus.domain.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    List<Mensagem> findByRemetenteAndDestinatarioOrDestinatarioAndRemetenteOrderByDataEnvioAsc(
            Usuario remetente, Usuario destinatario, Usuario destinatario2, Usuario remetente2
    );
}