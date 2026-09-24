package org.unitins.br.automato;

import java.util.ArrayList;
import java.util.List;

public class Grade {

    private final Celula[][] celulas;
    private final int largura;
    private final int altura;

    public Grade(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
        this.celulas = new Celula[largura][altura];
        inicializar();
    }

    public void inicializar() {
        for (int x = 0; x < largura; x++) {
            for (int y = 0; y < altura; y++) {
                celulas[x][y] = new Celula(x, y);
            }
        }
    }

    public Celula obterCelula(int x, int y) {
        if (x >= 0 && x < largura && y >= 0 && y < altura) {
            return celulas[x][y];
        }
        return null;
    }

    public List<Celula> obterVizinhos(int x, int y) {
        List<Celula> vizinhos = new ArrayList<>();

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) {
                    continue;
                }
                Celula vizinho = obterCelula(x + dx, y + dy);
                if (vizinho != null) {
                    vizinhos.add(vizinho);
                }
            }
        }
        return vizinhos;
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    public Celula[][] getCelulas() {
        return celulas;
    }
}
