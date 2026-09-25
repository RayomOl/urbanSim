package org.unitins.br.api.model;

import org.unitins.br.api.model.*;
import java.util.ArrayList;
import java.util.List;

public class Cidade {

    private Construcao[][] cidade;
    private int largura;
    private int altura;

    public Cidade(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
        this.cidade = new Construcao[largura][altura];
        inicializar();
    }

    public void inicializar() {
        for (int x = 0; x < largura; x++) {
            for (int y = 0; y < altura; y++) {
                cidade[x][y] = new Construcao(x, y, null);
            }
        }
    }

    public void posicionarConstrucao(int x, int y, int idQualidadeConstrucao) {
        Construcao construcao = getPosConstrucao(x, y);
        if (construcao != null) {
            construcao.setEstado(Estado.valueOf(idQualidadeConstrucao));
        }
    }

    public Construcao getPosConstrucao(int posX, int posY) {
        if (posX >= 0 && posX < largura && posY >= 0 && posY < altura) {
            return cidade[posX][posY];
        }
        return null;
    }

    public boolean isVazia(int posX, int posY) {
        Construcao construcao = getPosConstrucao(posX, posY);
        return construcao == null || construcao.getEstado() == null;
    }

    public List<Construcao> getVizinhos(int posX, int posY) {
        List<Construcao> vizinhos = new ArrayList<>();

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) {
                    continue;
                }
                int vizinhoPosX = posX + dx;
                int vizinhoPosY = posY + dy;

                Construcao vizinho = getPosConstrucao(vizinhoPosX, vizinhoPosY);
                if (vizinho != null && vizinho.getEstado() != null) {
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

    public Construcao[][] getMatriz() {
        return cidade;
    }

}
