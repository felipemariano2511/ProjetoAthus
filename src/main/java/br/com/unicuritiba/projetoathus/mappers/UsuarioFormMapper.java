package br.com.unicuritiba.projetoathus.mappers;

import br.com.unicuritiba.projetoathus.application.forms.UsuarioForm;
import br.com.unicuritiba.projetoathus.domain.models.Usuario;

public class UsuarioFormMapper {

    public static Usuario toUsuario(UsuarioForm form) {
        Usuario usuario = new Usuario();

        usuario.setNomeCompleto(form.getNomeCompleto());
        usuario.setNome(form.getNome());
        usuario.setEmail(form.getEmail());
        usuario.setTelefone(form.getTelefone());
        usuario.setCpf(form.getCpf());
        usuario.setDataNascimento(form.getDataNascimento());
        usuario.setPais(form.getPais());
        usuario.setEstado(form.getEstado());
        usuario.setCidade(form.getCidade());
        usuario.setCep(form.getCep());
        usuario.setRua(form.getRua());
        usuario.setNumero(form.getNumero());
        usuario.setApartamento(form.getApartamento());
        usuario.setLogradouro(form.getLogradouro());
        usuario.setPrestadorServico(Boolean.TRUE.equals(form.getPrestadorServico()));

        return usuario;
    }
}
