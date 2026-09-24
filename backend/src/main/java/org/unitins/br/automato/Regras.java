package org.unitins.br.automato;

import java.util.List;

public class Regras {

    private static final double VARIACAO_QV_0 = -1.5;
    private static final double VARIACAO_QV_1 = 0.0;
    private static final double VARIACAO_QV_2 = 1.5;

    private static final EstadoCelula[] NIVEIS = {
            EstadoCelula.ABANDONADA,
            EstadoCelula.BAIXA_QUALIDADE,
            EstadoCelula.MEDIA_QUALIDADE,
            EstadoCelula.ALTA_QUALIDADE
    };

    public double calcularFelicidade(Populacao populacao) {
        double variacao = switch (populacao.getQualidadeDeVida()) {
            case 0 -> VARIACAO_QV_0;
            case 2 -> VARIACAO_QV_2;
            default -> VARIACAO_QV_1;
        };
        return Math.max(0, Math.min(100, populacao.getFelicidade() + variacao));
    }

    public double calcularTaxaCrescimento(Populacao populacao) {
        return populacao.getFelicidade() / 100.0;
    }

    public EstadoCelula determinarQualidadeNovaCelula(Populacao populacao) {
        return switch (populacao.getQualidadeDeVida()) {
            case 0 -> Math.random() < 0.5 ? EstadoCelula.ABANDONADA : EstadoCelula.BAIXA_QUALIDADE;
            case 2 -> EstadoCelula.ALTA_QUALIDADE;
            default -> EstadoCelula.MEDIA_QUALIDADE;
        };
    }

    public EstadoCelula calcularProximoEstado(Celula celula, List<Celula> vizinhos, Populacao populacao) {
        if (celula.getEstado() == null) {
            double chance = calcularTaxaCrescimento(populacao);
            return Math.random() < chance ? determinarQualidadeNovaCelula(populacao) : null;
        }

        int nivelAtual = indiceNivel(celula.getEstado());
        double mediaVizinhos = mediaNivelVizinhos(vizinhos);

        double chanceMelhora = calcularTaxaCrescimento(populacao) * 0.3;
        double chancePiora = (1 - calcularTaxaCrescimento(populacao)) * 0.3;

        if (mediaVizinhos >= 0) {
            if (mediaVizinhos > nivelAtual) {
                chanceMelhora += 0.1;
            } else if (mediaVizinhos < nivelAtual) {
                chancePiora += 0.1;
            }
        }

        double sorteio = Math.random();
        if (sorteio < chanceMelhora && nivelAtual < NIVEIS.length - 1) {
            return NIVEIS[nivelAtual + 1];
        }
        if (sorteio < chanceMelhora + chancePiora && nivelAtual > 0) {
            return NIVEIS[nivelAtual - 1];
        }
        return celula.getEstado();
    }

    private int indiceNivel(EstadoCelula estado) {
        for (int i = 0; i < NIVEIS.length; i++) {
            if (NIVEIS[i] == estado) {
                return i;
            }
        }
        return 0;
    }

    private double mediaNivelVizinhos(List<Celula> vizinhos) {
        int soma = 0;
        int ocupados = 0;
        for (Celula vizinho : vizinhos) {
            if (vizinho.getEstado() != null) {
                soma += indiceNivel(vizinho.getEstado());
                ocupados++;
            }
        }
        return ocupados == 0 ? -1 : (double) soma / ocupados;
    }
}
