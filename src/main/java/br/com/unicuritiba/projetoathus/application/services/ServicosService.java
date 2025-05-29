package br.com.unicuritiba.projetoathus.application.services;

import br.com.unicuritiba.projetoathus.domain.dto.PrestacaoServicoDTO;
import br.com.unicuritiba.projetoathus.domain.dto.ServicoDTO;
import br.com.unicuritiba.projetoathus.domain.models.Categorias;
import br.com.unicuritiba.projetoathus.domain.models.Servicos;
import br.com.unicuritiba.projetoathus.domain.repositories.CategoriasRepository;
import br.com.unicuritiba.projetoathus.domain.repositories.ServicosRepository;
import br.com.unicuritiba.projetoathus.infrastructure.exceptions.NoContentException;
import br.com.unicuritiba.projetoathus.infrastructure.exceptions.NotFoundException;
import br.com.unicuritiba.projetoathus.mappers.ServicoMapper;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServicosService {

    @Autowired
    private ServicosRepository repository;

    @Autowired
    private ServicoMapper mapper;

    public ResponseEntity<List<ServicoDTO>> getAllServicos() {
        List<ServicoDTO> servicos = repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();

        if (servicos.isEmpty()) {
            throw new NoContentException("Nenhuma prestação de serviço encontrada.");
        }

        return ResponseEntity.ok(servicos);
    }


    public ResponseEntity<?> getServico(Long id) throws NotFoundException{

        return ResponseEntity.ok(repository.findById(id)
                                .stream()
                                .map(mapper::toDTO));

    }

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
