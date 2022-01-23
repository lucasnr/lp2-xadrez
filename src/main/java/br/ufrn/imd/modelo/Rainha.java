package br.ufrn.imd.modelo;

import java.util.ArrayList;
import java.util.List;

public class Rainha extends Peca {

    @Override
    public String getImagem() {
        if (this.getCor() == CorDaPeca.BRANCA) {
            return "caminho/para/imagem/da/rainha/branca.png";
        } else {
            return "caminho/para/imagem/da/rainha/preta.png";
        }
    }

    @Override
    public List<Posicao> informarPossiveisJogadas(Tabuleiro tabuleiro) {
        List<Posicao> posicoes = new ArrayList<>();

        Torre torre = new Torre();
        torre.setPosicao(this.getPosicao());
        torre.setCor(this.getCor());

        Bispo bispo = new Bispo();
        bispo.setPosicao(this.getPosicao());
        bispo.setCor(this.getCor());

        posicoes.addAll(torre.informarPossiveisJogadas(tabuleiro));
        posicoes.addAll(bispo.informarPossiveisJogadas(tabuleiro));

        return posicoes;
    }
}
