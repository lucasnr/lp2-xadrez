package br.ufrn.imd.modelo;

public class Tabuleiro {
    private Peca[][] campo = new Peca[8][8];
    private boolean vezDasBrancas = true;

    public boolean isVezDasBrancas() {
        return vezDasBrancas;
    }

    public void setVezDasBrancas(boolean vezDasBrancas) {
        this.vezDasBrancas = vezDasBrancas;
    }

    public Peca[][] getCampo() {
        return campo;
    }

    public void setCampo(Peca[][] campo) {
        this.campo = campo;
    }
}
