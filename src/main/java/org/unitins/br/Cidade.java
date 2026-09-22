package org.unitins.br;

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
                cidade[x][y] = new Construcao(x, y, Estado.VAZIA);
            }
        }
    }

    public void posicionarConstrucao(int x, int y, int idQualidadeConstrucao) {
        this.cidade[x][y].setPosX(x);
        this.cidade[x][y].setPosY(y);
        this.cidade[x][y].setEstado(Estado.valueOf(idQualidadeConstrucao));
    }

    Construcao getPosConstrucao(int posX, int posY) {
        if (posX >= 0 && posX < largura && posY >= 0 && posY < altura) {
            return cidade[posX][posY];
        }
        return null;
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

    public Construcao[][] getMatriz() {
        return cidade;
    }

}
