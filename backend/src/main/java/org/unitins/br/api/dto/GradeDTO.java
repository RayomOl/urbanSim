package org.unitins.br.api.dto;

import org.unitins.br.automato.Celula;
import org.unitins.br.automato.Grade;

import java.util.ArrayList;
import java.util.List;

public record GradeDTO(int largura, int altura, List<CelulaDTO> celulas) {

    public static GradeDTO de(Grade grade) {
        List<CelulaDTO> celulas = new ArrayList<>();
        for (Celula[] coluna : grade.getCelulas()) {
            for (Celula celula : coluna) {
                celulas.add(CelulaDTO.de(celula));
            }
        }
        return new GradeDTO(grade.getLargura(), grade.getAltura(), celulas);
    }
}
