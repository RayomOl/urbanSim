package org.unitins.br;

public enum Estado {

    VAZIA(0, "Vazia"),
    ABANDONADA(1, "Abandonada"),
    BAIXAQUALIDADE(2, "Baixa Qualidade"),
    ALTAQUALIDADE(3, "Alta Qualidade");

    private final int ID;
    private final String NOME;

    Estado(int id, String nome) {
        this.ID = id;
        this.NOME = nome;
    }

    public int getID() {
        return ID;
    }

    public String getNome() {
        return NOME;
    }

    public static Estado valueOf(int id) {
        for (Estado e : values()) {
            if (e.getID() == id) {
                return e;
            }
        }
        return null;
    }



}
