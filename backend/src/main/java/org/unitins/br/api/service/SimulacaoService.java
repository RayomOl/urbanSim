package org.unitins.br.api.service;

import org.springframework.stereotype.Service;
import org.unitins.br.api.model.*;
import org.unitins.br.api.dto.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimulacaoService {

    private Simulacao simulacao;

    public SimulacaoService() {
        this.simulacao = new Simulacao(20, 20, 1);
    }

    public EstadoSimulacaoDTO obterEstadoAtual() {
        return converterParaDTO(simulacao);
    }

    public EstadoSimulacaoDTO novaSimulacao(int largura, int altura, int qualidadeDeVida) {
        this.simulacao = new Simulacao(largura, altura, qualidadeDeVida);
        return converterParaDTO(simulacao);
    }

    public EstadoSimulacaoDTO executarCiclo() {
        this.simulacao.executarCiclo();
        return converterParaDTO(simulacao);
    }

    public EstadoSimulacaoDTO alterarQualidadeDeVida(int qualidadeDeVida) {
        this.simulacao.getPopulacao().setQualidadeVida(qualidadeDeVida);
        return converterParaDTO(simulacao);
    }

    private EstadoSimulacaoDTO converterParaDTO(Simulacao sim) {
        Cidade cidade = sim.getCidade();
        List<CelulaDTO> celulas = new ArrayList<>();

        for (int x = 0; x < cidade.getLargura(); x++) {
            for (int y = 0; y < cidade.getAltura(); y++) {
                Construcao c = cidade.getPosConstrucao(x, y);
                String estadoStr = c.getEstado() != null ? c.getEstado().name() : null;
                celulas.add(new CelulaDTO(x, y, estadoStr));
            }
        }

        GradeDTO gradeDTO = new GradeDTO(cidade.getLargura(), cidade.getAltura(), celulas);

        Populacao pop = sim.getPopulacao();
        PopulacaoDTO popDTO = new PopulacaoDTO(
                pop.getQualidadeVida(),
                pop.getFelicidade(),
                pop.getPopulacao(),
                pop.getTaxaCrescimento()
        );

        return new EstadoSimulacaoDTO(sim.getCiclo(), popDTO, gradeDTO);
    }
}
