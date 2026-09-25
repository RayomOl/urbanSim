package org.unitins.br.api.model;

public class Construcao {

    private int posX;
    private int posY;
    private Estado estado;

    public Construcao(int posX, int posY, Estado estado) {
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
