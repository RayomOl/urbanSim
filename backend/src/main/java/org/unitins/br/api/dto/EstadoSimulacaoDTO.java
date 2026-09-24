package org.unitins.br.api.dto;

import org.unitins.br.automato.Simulacao;

public record EstadoSimulacaoDTO(int ciclo, PopulacaoDTO populacao, GradeDTO grade) {

    public static EstadoSimulacaoDTO de(Simulacao simulacao) {
        return new EstadoSimulacaoDTO(
                simulacao.getCiclo(),
                PopulacaoDTO.de(simulacao.getPopulacao()),
                GradeDTO.de(simulacao.getGrade())
        );
    }
}
