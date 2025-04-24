package br.com.unicuritiba.projetoathus.application.services;

import br.com.unicuritiba.projetoathus.domain.models.Servicos;
import br.com.unicuritiba.projetoathus.domain.repositories.ServicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicosService {

    @Autowired
    private ServicosRepository repository;

    public List<Servicos> getAllServicos() {return repository.findAll();}

    public Servicos saveServicos(Servicos servico) {return repository.save(servico);}

    public Servicos updateServicos(Long id, Servicos servico) {
        Optional<Servicos> servicoEncontrado = repository.findById(id);
        if (servicoEncontrado.isPresent()) {
            Servicos servicoAtualizado = servicoEncontrado.get();
            servicoAtualizado.setNome(servico.getNome());
            return repository.saveAndFlush(servicoAtualizado);
        } else {
            throw new RuntimeException("Não encontrado serviço com nome " + servico.getNome());
        }
    }

    public void deleteServicos(Long id) {
        Optional<Servicos> servicoEncontrado = repository.findById(id);
        if (servicoEncontrado.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Não encontrado serviço com id " + id);
        }
    }
}
