package br.com.unicuritiba.projetoathus.mappers;

import org.springframework.stereotype.Component;
import br.com.unicuritiba.projetoathus.domain.models.Categorias;
import br.com.unicuritiba.projetoathus.domain.dto.CategoriasDTO;

@Component
public class CategoriaMapper {

    public CategoriasDTO toDTO(Categorias c){
        return new CategoriasDTO(
            c.getId(),
            c.getNome()
            );
    }

    public Categorias toEntity(CategoriasDTO c){
        Categorias categoria = new Categorias();
        
        categoria.setId(c.id());
        categoria.setNome(c.nome());

        return categoria;
    }

}
