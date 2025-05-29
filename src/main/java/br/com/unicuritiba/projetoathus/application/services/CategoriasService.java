package br.com.unicuritiba.projetoathus.application.services;

import br.com.unicuritiba.projetoathus.domain.models.Categorias;
import br.com.unicuritiba.projetoathus.domain.repositories.CategoriasRepository;
import br.com.unicuritiba.projetoathus.domain.dto.CategoriasDTO;
import br.com.unicuritiba.projetoathus.infrastructure.exceptions.NoContentException;
import br.com.unicuritiba.projetoathus.infrastructure.exceptions.NotFoundException;
import br.com.unicuritiba.projetoathus.mappers.CategoriaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriasService {

    @Autowired
    private CategoriasRepository repository;

    @Autowired
    private CategoriaMapper mapper;

    public ResponseEntity<List<CategoriasDTO>> getAllCategorias() throws NoContentException {
        return ResponseEntity.ok(repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList());
    }

    public ResponseEntity<?> getCategoria(Long id) throws NotFoundException{ 
        return ResponseEntity.ok(repository.findById(id)
                .stream()
                .map(mapper::toDTO));
    }

    public Categorias saveCategoria(Categorias categoria) {return repository.save(categoria);}

    public Categorias updateCategoria(Long id, Categorias categoria) {
        Optional<Categorias> categoriaEncontrada = repository.findById(id);
        if (categoriaEncontrada.isPresent()) {
            Categorias categoriaAtualizada = categoriaEncontrada.get();
            categoriaAtualizada.setNome(categoria.getNome());
            return repository.saveAndFlush(categoriaAtualizada);
        } else {
            throw new NotFoundException("Não encontrado categoria com nome " + categoria.getNome());
        }
    }

    public void deleteCategoria(Long id) {
        Optional<Categorias> categoriaEncontrada = repository.findById(id);
        if (categoriaEncontrada.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Não encontrado categoria com id " + id);
        }
    }
}
