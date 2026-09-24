package org.unitins.br.automato;

public class Celula {

    private final int x;
    private final int y;
    // null representa uma célula vazia, sem construção
    private EstadoCelula estado;

    public Celula(int x, int y) {
        this(x, y, null);
    }

    public Celula(int x, int y, EstadoCelula estado) {
        this.x = x;
        this.y = y;
        this.estado = estado;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public EstadoCelula getEstado() {
        return estado;
    }

    public void setEstado(EstadoCelula estado) {
        this.estado = estado;
    }
}
