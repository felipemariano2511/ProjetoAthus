package br.com.unicuritiba.projetoathus.mappers;

import org.springframework.stereotype.Component;

import br.com.unicuritiba.projetoathus.domain.models.Categorias;
import br.com.unicuritiba.projetoathus.dto.CategoriaDTO;

@Component
public class CategoriaMapper {

    public CategoriaDTO toDTO(Categorias c){
        return new CategoriaDTO(
            c.getId(),
            c.getNome()
            );
    }

    public Categorias toEntity(CategoriaDTO c){
        Categorias categoria = new Categorias();
        
        categoria.setId(c.id());
        categoria.setNome(c.nome());

        return categoria;
    }

}
