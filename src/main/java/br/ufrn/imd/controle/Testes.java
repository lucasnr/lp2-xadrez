package br.ufrn.imd.controle;

import br.ufrn.imd.modelo.*;

import java.util.List;

public class Testes {
    public static void main(String[] args) {
        Peca peca = new Rainha();
        peca.setCor(CorDaPeca.BRANCA);
        peca.setPosicao(new Posicao(4, 4));

        Peca outraPeca = new Bispo();
        outraPeca.setCor(CorDaPeca.PRETA);
        outraPeca.setPosicao(new Posicao(3, 3));

        Tabuleiro tabuleiro = new Tabuleiro();
        tabuleiro.getCampo()[peca.getPosicao().getLinha()][peca.getPosicao().getColuna()] = peca;
        tabuleiro.getCampo()[outraPeca.getPosicao().getLinha()][outraPeca.getPosicao().getColuna()] = outraPeca;

        List<Posicao> possiveisJogadas = peca.informarPossiveisJogadas(tabuleiro);

        String[][] tabuleiroVisual = new String[8][8]; // para simular a interface visual
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tabuleiroVisual[i][j] = "[ ]";
            }
        }
        tabuleiroVisual[peca.getPosicao().getLinha()][peca.getPosicao().getColuna()] = "[0]";
        tabuleiroVisual[outraPeca.getPosicao().getLinha()][outraPeca.getPosicao().getColuna()] = "[1]";

        for (Posicao posicao : possiveisJogadas) {
            tabuleiroVisual[posicao.getLinha()][posicao.getColuna()] = "[X]";
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(tabuleiroVisual[i][j]);
            }
            System.out.println();
        }
    }
}
