package br.ufrn.imd.controle;

import br.ufrn.imd.modelo.CorDaPeca;
import br.ufrn.imd.modelo.Peca;

public class TestesJogo {
    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        Peca[][] campo = jogo.getTabuleiro().getCampo();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Peca peca = campo[i][j];
                if (peca != null) {
                    String nome = peca.getClass().getSimpleName();
                    CorDaPeca cor = peca.getCor();
                    System.out.printf("[%s%s]", nome, cor);
                } else {
                    System.out.print("[ ]");
                }
            }
            System.out.println();
        }
    }
}
