package org.unitins.br.automato;

import java.util.List;

public class Simulacao {

    private final Grade grade;
    private final Populacao populacao;
    private final Regras regras;
    private int ciclo;

    public Simulacao(Grade grade, Populacao populacao) {
        this.grade = grade;
        this.populacao = populacao;
        this.regras = new Regras();
        this.ciclo = 0;
    }

    public void executarCiclo() {
        populacao.setFelicidade(regras.calcularFelicidade(populacao));
        populacao.setTaxaDeCrescimento(regras.calcularTaxaCrescimento(populacao));

        int largura = grade.getLargura();
        int altura = grade.getAltura();
        EstadoCelula[][] proximosEstados = new EstadoCelula[largura][altura];

        for (int x = 0; x < largura; x++) {
            for (int y = 0; y < altura; y++) {
                Celula celula = grade.obterCelula(x, y);
                List<Celula> vizinhos = grade.obterVizinhos(x, y);
                proximosEstados[x][y] = regras.calcularProximoEstado(celula, vizinhos, populacao);
            }
        }

        for (int x = 0; x < largura; x++) {
            for (int y = 0; y < altura; y++) {
                grade.obterCelula(x, y).setEstado(proximosEstados[x][y]);
            }
        }

        ciclo++;
    }

    public Grade getGrade() {
        return grade;
    }

    public Populacao getPopulacao() {
        return populacao;
    }

    public int getCiclo() {
        return ciclo;
    }
}
