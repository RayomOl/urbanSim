package org.unitins.br;

public enum EstadoCelular {

    ABANDONADA(1, "Abandonada"),
    BAIXAQUALIDADE(2, "Baixa Qualidade"),
    ALTAQUALIDADE(3, "Alta Qualidade");

    private final int ID;
    private final String NOME;

    EstadoCelular(int id, String nome) {
        this.ID = id;
        this.NOME = nome;
    }

    public int getID() {
        return ID;
    }

    public String getNome() {
        return NOME;
    }

    public static EstadoCelular valueOf(int id) {
        for (EstadoCelular e : values()) {
            if (e.getID() == id) {
                return e;
            }
        }
        return null;
    }



}
