package br.sp.gov.etec.estacionamento.service;

import br.sp.gov.etec.estacionamento.model.Usuario;

import java.util.List;

//isso é o crud
//c - create
//r - read
//u - update
//d - delete

public interface UsuarioService {
    String cadastroUsuario(Usuario usuario);
    List<Usuario> listarUsuario();
    String atualizarUsuario(Usuario usuario);
    String deletarUsuario(Long id);
    Usuario buscaUsuarioPorEmail(String email);
}
