package org.unitins.br.api;

import org.springframework.web.bind.annotation.*;
import org.unitins.br.api.dto.EstadoSimulacaoDTO;
import org.unitins.br.api.dto.NovaSimulacaoRequest;
import org.unitins.br.api.dto.QualidadeDeVidaRequest;

@RestController
@RequestMapping("/api/simulacao")
public class SimulacaoController {

    private final SimulacaoService service;

    public SimulacaoController(SimulacaoService service) {
        this.service = service;
    }

    @GetMapping
    public EstadoSimulacaoDTO obterAtual() {
        return EstadoSimulacaoDTO.de(service.obterAtual());
    }

    @PostMapping("/nova")
    public EstadoSimulacaoDTO nova(@RequestBody NovaSimulacaoRequest requisicao) {
        return EstadoSimulacaoDTO.de(service.nova(requisicao.largura(), requisicao.altura(), requisicao.qualidadeDeVida()));
    }

    @PostMapping("/ciclo")
    public EstadoSimulacaoDTO executarCiclo() {
        return EstadoSimulacaoDTO.de(service.executarCiclo());
    }

    @PutMapping("/qualidade-de-vida")
    public EstadoSimulacaoDTO alterarQualidadeDeVida(@RequestBody QualidadeDeVidaRequest requisicao) {
        return EstadoSimulacaoDTO.de(service.alterarQualidadeDeVida(requisicao.qualidadeDeVida()));
    }
}
