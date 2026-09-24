package org.unitins.br.api.dto;

import org.unitins.br.automato.Celula;

public record CelulaDTO(int x, int y, String estado) {

    public static CelulaDTO de(Celula celula) {
        String estado = celula.getEstado() == null ? null : celula.getEstado().name();
        return new CelulaDTO(celula.getX(), celula.getY(), estado);
    }
}
