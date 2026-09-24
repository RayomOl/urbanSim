package org.unitins.br.api.dto;

import org.unitins.br.automato.Populacao;

public record PopulacaoDTO(int qualidadeDeVida, double felicidade, int populacao, double taxaDeCrescimento) {

    public static PopulacaoDTO de(Populacao populacao) {
        return new PopulacaoDTO(
                populacao.getQualidadeDeVida(),
                populacao.getFelicidade(),
                populacao.getPopulacao(),
                populacao.getTaxaDeCrescimento()
        );
    }
}
