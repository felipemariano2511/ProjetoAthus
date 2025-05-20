package br.com.unicuritiba.projetoathus.mappers;

import org.springframework.stereotype.Component;

import br.com.unicuritiba.projetoathus.domain.models.Servicos;
import br.com.unicuritiba.projetoathus.dto.ServicoDTO;

@Component
public class ServicoMapper {
    
    public ServicoDTO toDTO(Servicos s){
        return new ServicoDTO(
                    s.getId(),
                    s.getNome(),
                    s.getCategoria()
                );
    }

}
