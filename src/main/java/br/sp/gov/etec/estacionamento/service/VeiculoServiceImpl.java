package br.sp.gov.etec.estacionamento.service;

import br.sp.gov.etec.estacionamento.entity.UsuarioEntity;
import br.sp.gov.etec.estacionamento.entity.VeiculoEntity;
import br.sp.gov.etec.estacionamento.model.Usuario;
import br.sp.gov.etec.estacionamento.model.Veiculo;
import br.sp.gov.etec.estacionamento.repository.VeiculoRepository;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@Service
public class VeiculoServiceImpl implements VeiculoService{

    @Autowired
    VeiculoRepository repository;

    @Override
    public void cadastrarVeiculo(Veiculo veiculo) {
        repository.save(toEntity(veiculo));
    }

    @Override
    public List<VeiculoEntity> listaVeiculo() {
        List<VeiculoEntity> veiculos = repository.findAll();
        return veiculos;
    }

    @Override
    public boolean deletarVeiculo(Long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public VeiculoEntity atualizarVeiculo(VeiculoEntity veiculo) {
        return repository.save(veiculo);
    }

    @Override
    public VeiculoEntity buscaVeiculoPorId(Long id) {
        return repository.findById(id).orElseThrow();
    }

    private VeiculoEntity toEntity(Veiculo veiculo){
        VeiculoEntity entity = new VeiculoEntity();
        entity.setHoraEntrada(LocalDateTime.now());
        entity.setCor(veiculo.getCor());
        entity.setModelo(veiculo.getModelo());
        entity.setPlaca(veiculo.getPlaca());
        entity.setObservacao(veiculo.getObservacao());

        return entity;
    }

    private List<Veiculo> toListVeiculo(List<VeiculoEntity> entities){
        List<Veiculo> veiculos = new ArrayList<>();
        for (VeiculoEntity v : entities){
            Veiculo veiculo = new Veiculo();
            veiculo.setCor(v.getCor());
            veiculo.setPlaca(v.getPlaca());
            veiculo.setModelo(v.getModelo());
            veiculo.setObservacao(v.getObservacao());
            veiculos.add(veiculo);

            return veiculos;
        }
        return veiculos;
    }
}

