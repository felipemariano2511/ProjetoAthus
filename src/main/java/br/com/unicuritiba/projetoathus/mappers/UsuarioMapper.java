package br.com.unicuritiba.projetoathus.mappers;

import br.com.unicuritiba.projetoathus.domain.models.Usuario;
import br.com.unicuritiba.projetoathus.domain.dto.UsuarioDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioDTO toDTO(Usuario u) {
        return new UsuarioDTO(
                u.getId(),
                u.getNome(),
                u.getEmail(),
                u.getTelefone(),
                u.getCpf(),
                u.getDataNascimento(),
                u.getPais(),
                u.getEstado(),
                u.getCidade(),
                u.getCep(),
                u.getRua(),
                u.getNumero(),
                u.getApartamento(),
                u.getLogradouro(),
                u.getImagemPerfil(),
                u.isAtivo(),
                u.isPrestadorServico()
        );
    }

    public Usuario toEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setId(dto.id());
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setTelefone(dto.telefone());
        usuario.setCpf(dto.cpf());
        usuario.setDataNascimento(dto.dataNascimento());
        usuario.setPais(dto.pais());
        usuario.setEstado(dto.estado());
        usuario.setCidade(dto.cidade());
        usuario.setCep(dto.cep());
        usuario.setRua(dto.rua());
        usuario.setNumero(dto.numero());
        usuario.setApartamento(dto.apartamento());
        usuario.setLogradouro(dto.logradouro());
        usuario.setImagemPerfil(dto.imagemPerfil());
        usuario.setAtivo(dto.ativo());
        usuario.setPrestadorServico(dto.prestadorServico());
        return usuario;
    }
}