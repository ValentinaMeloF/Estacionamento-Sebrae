package br.sp.gov.etec.estacionamento.service;

import br.sp.gov.etec.estacionamento.entity.UsuarioEntity;
import br.sp.gov.etec.estacionamento.model.Usuario;
import br.sp.gov.etec.estacionamento.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository repository;

    @Override
    public String cadastroUsuario(Usuario usuario) {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        // esse primeiro UsuarioEntity é a gnt declarando um tipo de dado que criamos
        usuarioEntity.setNome(usuario.getNome());
        usuarioEntity.setCpf(usuario.getCpf());
        usuarioEntity.setEmail(usuario.getEmail());
        usuarioEntity.setDatanasc(usuario.getDatanasc());
        usuarioEntity.setSenha(usuario.getSenha());
        usuarioEntity.setTelefone(usuario.getTelefone());

        repository.save(usuarioEntity);

        return "Usuário cadastrado com sucesso!";
    }

    @Override
    public List<Usuario> listarUsuario() {
        return List.of();
    }

    @Override
    public String atualizarUsuario(Usuario usuario) {
        return "";
    }

    @Override
    public String deletarUsuario(Long id) {
        return "";
    }

    @Override
    public Usuario buscaUsuarioPorEmail(String email) {
        UsuarioEntity entity = repository.findByEmail(email);
        Usuario user =toUsuario(entity);
        return user;
    }

    private Usuario toUsuario(UsuarioEntity entity){
        Usuario usuario = new Usuario();
        usuario.setEmail(entity.getEmail());
        usuario.setNome(entity.getNome());
        usuario.setSenha(entity.getSenha());
        usuario.setCpf(entity.getCpf());
        usuario.setDatanasc(entity.getDatanasc());
        usuario.setTelefone(entity.getTelefone());

        return usuario;
    }
}
