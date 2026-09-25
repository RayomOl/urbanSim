package org.unitins.br.api.controller;

import org.springframework.web.bind.annotation.*;
import org.unitins.br.api.dto.*;
import org.unitins.br.api.service.SimulacaoService;

@RestController
@RequestMapping("/api/simulacao")
public class SimulacaoController {

    private final SimulacaoService service;

    public SimulacaoController(SimulacaoService service) {
        this.service = service;
    }

    @GetMapping
    public EstadoSimulacaoDTO obterAtual() {
        return service.obterEstadoAtual();
    }

    @PostMapping("/nova")
    public EstadoSimulacaoDTO novaSimulacao(@RequestBody NovaSimulacaoRequest request) {
        return service.novaSimulacao(request.largura(), request.altura(), request.qualidadeDeVida());
    }

    @PostMapping("/ciclo")
    public EstadoSimulacaoDTO executarCiclo() {
        return service.executarCiclo();
    }

    @PutMapping("/qualidade-de-vida")
    public EstadoSimulacaoDTO alterarQualidadeDeVida(@RequestBody QualidadeDeVidaRequest request) {
        return service.alterarQualidadeDeVida(request.qualidadeDeVida());
    }
}
