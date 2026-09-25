package org.unitins.br.api.dto;

import java.util.List;

public record GradeDTO(int largura, int altura, List<CelulaDTO> celulas) {}
