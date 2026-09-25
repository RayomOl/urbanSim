package org.unitins.br.api.model;

import java.util.List;

public class Regras {

    private static final Estado[] NIVEIS = {
            Estado.ABANDONADA,
            Estado.BAIXA_QUALIDADE,
            Estado.MEDIA_QUALIDADE,
            Estado.ALTA_QUALIDADE
    };

    public double calcularFelicidade(Populacao populacao) {
        double variacao = switch (populacao.getQualidadeVida()) {
            case 0 -> -1.5;
            case 2 -> 1.5;
            default -> 0.0;
        };
        return Math.max(0.0, Math.min(100.0, populacao.getFelicidade() + variacao));
    }

    public double calcularTaxaCrescimento(Populacao populacao) {
        return populacao.getFelicidade() / 100.0;
    }

    public Estado determinarQualidadeNovaConstrucao(Populacao populacao) {
        return switch (populacao.getQualidadeVida()) {
            case 0 -> Math.random() < 0.5 ? Estado.ABANDONADA : Estado.BAIXA_QUALIDADE;
            case 2 -> Estado.ALTA_QUALIDADE;
            default -> Estado.MEDIA_QUALIDADE;
        };
    }

    private double mediaNivelVizinhos(List<Construcao> vizinhos) {
        int soma = 0;
        int ocupados = 0;
        for (Construcao v : vizinhos) {
            if (v.getEstado() != null) {
                soma += v.getEstado().getID();
                ocupados++;
            }
        }
        return ocupados == 0 ? -1 : (double) soma / ocupados;
    }

    public Estado calcularProximoEstado(Construcao construcao, List<Construcao> vizinhos, Populacao populacao) {
        // Se a célula está vazia, há chance de nascer uma nova construção
        if (construcao.getEstado() == null) {
            double chanceCrescimento = calcularTaxaCrescimento(populacao);
            return Math.random() < chanceCrescimento ? determinarQualidadeNovaConstrucao(populacao) : null;
        }

        // Se já existe construção, calcula melhora ou degradação
        int nivelAtual = construcao.getEstado().getID();
        double mediaVizinhos = mediaNivelVizinhos(vizinhos);

        double taxa = calcularTaxaCrescimento(populacao);
        double chanceMelhora = taxa * 0.3;
        double chancePiora = (1.0 - taxa) * 0.3;

        // Vizinhança influencia
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

        return construcao.getEstado();
    }
}
