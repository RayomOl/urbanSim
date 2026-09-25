package org.unitins.br.api.model;

public class Populacao {

    private int qualidadeVida;

    private double felicidade;

    private int populacao;

    private double taxaCrescimento;

    public Populacao(int qualidadeVida) {
        setQualidadeVida(qualidadeVida);
        this.felicidade = 50.0;
        this.populacao = 0;
        this.taxaCrescimento = 0.0;
    }

    public int getQualidadeVida() {
        return qualidadeVida;
    }

    public void setQualidadeVida(int qualidadeVida) {
        if (qualidadeVida < 0 || qualidadeVida > 2) {
            throw new IllegalArgumentException("qualidadeDeVida deve ser 0, 1 ou 2");
        }
        this.qualidadeVida = qualidadeVida;
    }

    public double getFelicidade() {
        return felicidade;
    }

    public void setFelicidade(double felicidade) {
        this.felicidade = Math.max(0.0, Math.min(100.0, felicidade));
    }

    public int getPopulacao() {
        return populacao;
    }

    public void setPopulacao(int populacao) {
        this.populacao = Math.max(0, populacao);
    }

    public double getTaxaCrescimento() {
        return taxaCrescimento;
    }

    public void setTaxaCrescimento(double taxaCrescimento) {
        this.taxaCrescimento = taxaCrescimento;
    }
}
