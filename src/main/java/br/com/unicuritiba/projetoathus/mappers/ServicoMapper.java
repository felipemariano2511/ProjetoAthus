package br.com.unicuritiba.projetoathus.mappers;

import br.com.unicuritiba.projetoathus.domain.models.Categorias;
import org.springframework.stereotype.Component;
import br.com.unicuritiba.projetoathus.domain.models.Servicos;
import br.com.unicuritiba.projetoathus.domain.dto.servico.ServicoDTO;

@Component
public class ServicoMapper {
    
    public ServicoDTO toDTO(Servicos s){

        Categorias categoriaEntity = s.getCategoria();

        ServicoDTO.CategoriasDto categoriasDto = new ServicoDTO.CategoriasDto(
                                                s.getCategoria().getId(),
                                                s.getCategoria().getNome()
                                                );

        return new ServicoDTO(
                    s.getId(),
                    s.getNome(),
                    categoriasDto
        );
    }
}
