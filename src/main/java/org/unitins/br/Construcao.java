package org.unitins.br;

public class Celula {

    private int posX;
    private int posY;
    private EstadoCelular estado;

    public Celula(int posX, int posY, EstadoCelular estado) {
        this.posX = posX;
        this.posY = posY;
        this.estado = estado;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public EstadoCelular getEstado() {
        return estado;
    }

    public void setEstado(EstadoCelular estado) {
        this.estado = estado;
    }
}
