package br.com.unicuritiba.projetoathus.application.services;

import br.com.unicuritiba.projetoathus.domain.models.Categorias;
import br.com.unicuritiba.projetoathus.domain.repositories.CategoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriasService {

    @Autowired
    private CategoriasRepository repository;

    public List<Categorias> getAllCategorias() {return repository.findAll();}

    public Categorias saveCategoria(Categorias categoria) {return repository.save(categoria);}

    public Categorias updateCategoria(Long id, Categorias categoria) {
        Optional<Categorias> categoriaEncontrada = repository.findById(id);
        if (categoriaEncontrada.isPresent()) {
            Categorias categoriaAtualizada = categoriaEncontrada.get();
            categoriaAtualizada.setNome(categoria.getNome());
            return repository.saveAndFlush(categoriaAtualizada);
        } else {
            throw new RuntimeException("Não encontrado categoria com nome " + categoria.getNome());
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
