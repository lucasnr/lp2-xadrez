package br.ufrn.imd.util;

import br.ufrn.imd.modelo.CorDaPeca;
import br.ufrn.imd.modelo.Peca;
import br.ufrn.imd.modelo.Tabuleiro;

public class PecaUtil {
    public static boolean isJogadaDisponivel(Integer linha, Integer coluna, Peca peca, Tabuleiro tabuleiro) {
        Peca[][] campo = tabuleiro.getCampo();

        boolean posicaoVazia = campo[linha][coluna] == null;
        if (posicaoVazia) {
            return true;
        }

        return isColisao(linha, coluna, peca, tabuleiro);
    }

    public static boolean isColisao(Integer linha, Integer coluna, Peca peca, Tabuleiro tabuleiro) {
        Peca[][] campo = tabuleiro.getCampo();
        CorDaPeca cor = peca.getCor();

        Peca pecaNaPosicao = campo[linha][coluna];
        if (pecaNaPosicao == null) return false;

        boolean posicaoComPecaDeOutraCor = pecaNaPosicao.getCor() != cor;
        return posicaoComPecaDeOutraCor;
    }
}
