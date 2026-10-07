package br.sp.gov.etec.estacionamento.controller;

import br.sp.gov.etec.estacionamento.entity.VeiculoEntity;
import br.sp.gov.etec.estacionamento.model.Usuario;
import br.sp.gov.etec.estacionamento.service.UsuarioService;
import br.sp.gov.etec.estacionamento.service.VeiculoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UsuarioService service;

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping("/")
    public String index() {
        return "login";
    }

    @GetMapping("/cadastro")
    public String cadastrar() {
        return "tela-cadastrar";
    }

    @PostMapping("/efetuar-cadastro")
    public String efetuarCadastro(@ModelAttribute Usuario usuario) {
        log.info("Cadastrando usuário: {}", usuario);
        service.cadastroUsuario(usuario);
        return "cadastro-sucess";
    }

    @PostMapping("/autenticar")
    public String autenticar(
            @RequestParam("email") String email,
            @RequestParam("senha") String senha,
            Model model) {

        Usuario usuario = service.buscaUsuarioPorEmail(email);

        if (usuario != null && senha.equals(usuario.getSenha())) {
            List<VeiculoEntity> veiculos = veiculoService.listaVeiculo();
            model.addAttribute("veiculos", veiculos);
            return "painel";
        } else {
            model.addAttribute("erro", "E-mail ou senha inválidos.");
            return "erro";
        }
    }
}