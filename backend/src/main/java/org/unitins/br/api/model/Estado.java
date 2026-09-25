package org.unitins.br.api.model;

public enum Estado {

    ABANDONADA(0, "Abandonada"),
    BAIXA_QUALIDADE(1, "Baixa Qualidade"),
    MEDIA_QUALIDADE(2, "Media Qualidade"),
    ALTA_QUALIDADE(3, "Alta Qualidade");

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
