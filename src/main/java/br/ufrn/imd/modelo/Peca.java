package br.ufrn.imd.modelo;

import java.util.List;

public abstract class Peca {
    private CorDaPeca cor;
    private Posicao posicao;

    public abstract List<Posicao> informarPossiveisJogadas(Tabuleiro tabuleiro);

    public abstract String getImagem();

    public CorDaPeca getCor() {
        return cor;
    }

    public void setCor(CorDaPeca cor) {
        this.cor = cor;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }
}
