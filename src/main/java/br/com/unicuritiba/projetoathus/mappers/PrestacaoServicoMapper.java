package br.com.unicuritiba.projetoathus.mappers;

import br.com.unicuritiba.projetoathus.domain.dto.categoria.CategoriasDTO;
import br.com.unicuritiba.projetoathus.domain.dto.prestacaoservicos.PSPUTDTO;
import br.com.unicuritiba.projetoathus.domain.dto.prestacaoservicos.PrestacaoServicoDTO;
import br.com.unicuritiba.projetoathus.domain.models.PrestacaoServico;
import br.com.unicuritiba.projetoathus.domain.models.Servicos;
import br.com.unicuritiba.projetoathus.domain.models.Usuario;
import org.springframework.stereotype.Component;

@Component
public class PrestacaoServicoMapper {

    public PrestacaoServicoDTO toDTO(PrestacaoServico prestacaoServico) {
        // Extrai os dados do usuário e do serviço
        Usuario usuarioEntity = prestacaoServico.getUsuario();
        Servicos servicoEntity = prestacaoServico.getServico();

        // Converte a categoria do serviço utilizando o DTO já existente
        CategoriasDTO categoriaDTO = new CategoriasDTO(
                servicoEntity.getCategoria().getId(),
                servicoEntity.getCategoria().getNome()
        );

        // Cria os DTOs resumidos para usuário e serviço
        PrestacaoServicoDTO.UsuarioResumoDTO usuarioResumo =
                new PrestacaoServicoDTO.UsuarioResumoDTO(
                        usuarioEntity.getId(),
                        usuarioEntity.getNome(),
                        usuarioEntity.getEmail()
                );

        PrestacaoServicoDTO.ServicoResumoDTO servicoResumo =
                new PrestacaoServicoDTO.ServicoResumoDTO(
                        servicoEntity.getId(),
                        servicoEntity.getNome(),
                        categoriaDTO
                );

        // Monta e retorna o PrestacaoServicoDTO completo
        return new PrestacaoServicoDTO(
                prestacaoServico.getId(),
                usuarioResumo,
                servicoResumo,
                prestacaoServico.getDescricaoCompleta(),
                prestacaoServico.getValor(),
                prestacaoServico.getDataCriacao(),
                prestacaoServico.getDescricaoCurta(),
                prestacaoServico.getImagens(),
                prestacaoServico.isAtivo()
        );
    }

    public PrestacaoServico toEntity(PrestacaoServicoDTO dto, Usuario usuario, Servicos servico) {
        PrestacaoServico prestacaoServico = new PrestacaoServico();
        prestacaoServico.setId(dto.id());
        prestacaoServico.setUsuario(usuario);
        prestacaoServico.setServico(servico);
        prestacaoServico.setDescricaoCompleta(dto.descricaoCompleta());
        prestacaoServico.setValor(dto.valor());
        prestacaoServico.setDataCriacao(dto.dataCriacao());
        prestacaoServico.setDescricaoCurta(dto.descricaoCurta());
        prestacaoServico.setImagens(dto.imagens());
        prestacaoServico.setAtivo(dto.ativo());
        return prestacaoServico;
    }

    public PrestacaoServico btoEntity(PSPUTDTO dto, Usuario usuario, Servicos servico) {
        PrestacaoServico prestacaoServico = new PrestacaoServico();
        prestacaoServico.setId(dto.id());
        prestacaoServico.setUsuario(usuario);
        prestacaoServico.setServico(servico);
        prestacaoServico.setDescricaoCompleta(dto.descricaoCompleta());
        prestacaoServico.setValor(dto.valor());
        prestacaoServico.setDataCriacao(dto.dataCriacao());
        prestacaoServico.setDescricaoCurta(dto.descricaoCurta());
        prestacaoServico.setImagens(dto.imagens());
        prestacaoServico.setAtivo(dto.ativo());
        return prestacaoServico;
    }
}
