package org.unitins.br.automato;

public class Populacao {

    private int qualidadeDeVida; // 0, 1 ou 2
    private double felicidade;   // 0 a 100
    private int populacao;
    private double taxaDeCrescimento;

    public Populacao(int qualidadeDeVida) {
        setQualidadeDeVida(qualidadeDeVida);
        this.felicidade = 50.0;
        this.populacao = 0;
        this.taxaDeCrescimento = 0.0;
    }

    public int getQualidadeDeVida() {
        return qualidadeDeVida;
    }

    public void setQualidadeDeVida(int qualidadeDeVida) {
        if (qualidadeDeVida < 0 || qualidadeDeVida > 2) {
            throw new IllegalArgumentException("qualidadeDeVida deve ser 0, 1 ou 2");
        }
        this.qualidadeDeVida = qualidadeDeVida;
    }

    public double getFelicidade() {
        return felicidade;
    }

    public void setFelicidade(double felicidade) {
        this.felicidade = Math.max(0, Math.min(100, felicidade));
    }

    public int getPopulacao() {
        return populacao;
    }

    public void setPopulacao(int populacao) {
        this.populacao = populacao;
    }

    public double getTaxaDeCrescimento() {
        return taxaDeCrescimento;
    }

    public void setTaxaDeCrescimento(double taxaDeCrescimento) {
        this.taxaDeCrescimento = taxaDeCrescimento;
    }
}
