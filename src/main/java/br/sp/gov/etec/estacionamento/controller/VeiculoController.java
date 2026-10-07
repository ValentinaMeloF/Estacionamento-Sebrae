package br.sp.gov.etec.estacionamento.controller;

import br.sp.gov.etec.estacionamento.entity.VeiculoEntity;
import br.sp.gov.etec.estacionamento.model.Veiculo;
import br.sp.gov.etec.estacionamento.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/veiculo")
public class VeiculoController {

    @Autowired
    private VeiculoService service;

    @PostMapping("/cadastrar")
    public String cadastrar(@ModelAttribute Veiculo veiculo) {
        service.cadastrarVeiculo(veiculo);
        return "redirect:/veiculo/registrar-entrada";
    }

    @GetMapping("/registrar-entrada")
    public String registrarEntrada() {
        return "registrar-entrada";
    }

    @GetMapping("/registrar-saida")
    public String registrarSaida(Model model) {
        List<VeiculoEntity> veiculos = service.listaVeiculo();
        model.addAttribute("veiculos", veiculos);
        return "registrar-saida";
    }

    @GetMapping("/saida/{id}")
    public String getVeiculo(Model model, @PathVariable("id") Long id) {
        List<VeiculoEntity> veiculos = service.listaVeiculo();
        VeiculoEntity veiculoSelecionado = service.buscaVeiculoPorId(id);

        model.addAttribute("veiculos", veiculos);
        model.addAttribute("veiculo", veiculoSelecionado);

        return "registrar-saida";
    }
}