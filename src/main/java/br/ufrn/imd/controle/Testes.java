package br.ufrn.imd.controle;

import br.ufrn.imd.modelo.*;

import java.util.List;

public class Testes {
    public static void main(String[] args) {
        Peca peca = new Cavalo();
        peca.setCor(CorDaPeca.BRANCA);
        peca.setPosicao(new Posicao(0, 5));

        Peca peca2 = new Peao();
        peca2.setCor(CorDaPeca.PRETA);
        peca2.setPosicao(new Posicao(0, 4));

        Tabuleiro tabuleiro = new Tabuleiro();
        adicionarPeca(tabuleiro, peca);
        adicionarPeca(tabuleiro, peca2);

        List<Posicao> possiveisJogadas = peca.informarPossiveisJogadas(tabuleiro);

        String[][] tabuleiroVisual = new String[8][8]; // para simular a interface visual
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tabuleiroVisual[i][j] = "[ ]";
            }
        }

        adicionarPecaVisual(tabuleiroVisual, peca, "[0]");
        adicionarPecaVisual(tabuleiroVisual, peca2, "[1]");

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

    private static void adicionarPecaVisual(String[][] tabuleiroVisual, Peca peca, String s) {
        tabuleiroVisual[peca.getPosicao().getLinha()][peca.getPosicao().getColuna()] = s;
    }

    private static void adicionarPeca(Tabuleiro tabuleiro, Peca peca) {
        tabuleiro.getCampo()[peca.getPosicao().getLinha()][peca.getPosicao().getColuna()] = peca;
    }
}
