package br.ufrn.imd.modelo;

public class Tabuleiro {
    private Peca[][] campo = new Peca[8][8];

    public Peca[][] getCampo() {
        return campo;
    }

    public void setCampo(Peca[][] campo) {
        this.campo = campo;
    }
}
