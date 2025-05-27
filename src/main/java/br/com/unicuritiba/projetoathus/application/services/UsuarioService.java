package br.com.unicuritiba.projetoathus.application.services;

import br.com.unicuritiba.projetoathus.domain.models.Usuario;
import br.com.unicuritiba.projetoathus.domain.repositories.UsuarioRepository;
import br.com.unicuritiba.projetoathus.domain.dto.UsuarioDTO;
import br.com.unicuritiba.projetoathus.infrastructure.exceptions.NoContentException;
import br.com.unicuritiba.projetoathus.infrastructure.exceptions.NotFoundException;
import br.com.unicuritiba.projetoathus.infrastructure.exceptions.UnauthorizedException;
import br.com.unicuritiba.projetoathus.mappers.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioMapper mapper;

    @Autowired
    private UserImageUploadService imageUploadService;

    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() throws NoContentException {
        return ResponseEntity.ok(repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList());
    }

    public ResponseEntity<?> getUsuario(Long id) throws NotFoundException{
        return ResponseEntity.ok(repository.findById(id)
                .stream()
                .map(mapper::toDTO));
    }

    public ResponseEntity<Usuario> putUsuario(MultipartFile imagem, Usuario usuario) throws NotFoundException {
        String email = getEmailUsuarioLogado();

        Usuario usuarioLogado = repository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado no banco de dados"));

        if (!imagem.isEmpty()) {
            imageUploadService.upload(imagem);

            String caminhoImagem = "storage/imagens/usuarios/" + imagem.getOriginalFilename();
            usuarioLogado.setImagemPerfil(caminhoImagem);
        }

        usuarioLogado.setNome(usuario.getNome());
        usuarioLogado.setTelefone(usuario.getTelefone());
        usuarioLogado.setCpf(usuario.getCpf());
        usuarioLogado.setDataNascimento(usuario.getDataNascimento());
        usuarioLogado.setPais(usuario.getPais());
        usuarioLogado.setEstado(usuario.getEstado());
        usuarioLogado.setCidade(usuario.getCidade());
        usuarioLogado.setCep(usuario.getCep());
        usuarioLogado.setRua(usuario.getRua());
        usuarioLogado.setNumero(usuario.getNumero());
        usuarioLogado.setApartamento(usuario.getApartamento());
        usuarioLogado.setLogradouro(usuario.getLogradouro());
        usuarioLogado.setNivel(usuario.getNivel());
        usuarioLogado.setPrestadorServico(usuario.isPrestadorServico());

        repository.saveAndFlush(usuarioLogado);

        return ResponseEntity.ok(usuarioLogado);
    }


    public ResponseEntity<?> deleteUsuario(Long id) {
        return repository.findById(id)
                .map(usuario -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado como id: " + id));
    }

    public ResponseEntity<UsuarioDTO> getUsuarioLogado(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof Usuario usuario){

            return ResponseEntity.ok(mapper.toDTO(usuario));

        } else if (principal instanceof String email){

           return repository.findByEmail(email)
           .map(usuario -> {
                return ResponseEntity.ok(mapper.toDTO(usuario));
           })
           .orElseThrow(() -> new NotFoundException("Usuário não encontrado com email: " + email));
           
        }

        throw new UnauthorizedException("Usuário não autenticado");
    }

    private String getEmailUsuarioLogado() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof String email) {
            return email;
        } else if (principal instanceof Usuario usuario) {
            return usuario.getEmail();
        }

        throw new UnauthorizedException("Usuário não autenticado");
    }

    public ResponseEntity<?> setAtivoUsuario(Long id, boolean newValue){
        return repository.findById(id)
                .map(usuario -> {
                    usuario.setAtivo(newValue);
                    repository.save(usuario);
                    return ResponseEntity.ok(Map.of("message","Atributo ativo atualizado com sucesso"));
                })
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado como id: " + id));
    }

}
