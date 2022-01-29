package br.ufrn.imd.util;

import br.ufrn.imd.modelo.CorDaPeca;
import br.ufrn.imd.modelo.Peca;
import br.ufrn.imd.modelo.Tabuleiro;

/** Classe com métodos usados por classes filhas de Peca para evitar redundância de código
 * 
 * @author Lucas
 * @version 1.0
 *
 */
public class PecaUtil {
	/** Método com o objetivo de verificar se a peça indicada no tabuleiro pode jogar na posição requisitada
	 * 
	 * @param linha Integer - Representação da linha onde deseja verificar
	 * @param coluna - Representação da coluna onde deseja verificar
	 * @param peca Peca - Objeto do tipo Peca que deseja se verificar
	 * @param tabuleiro Tabuleiro - Objeto do tipo Tabuleiro onde se encontra a peça que deseja se verificar
	 * @return boolean - Retorna true se a peça puder se mover para o local requisitado, false caso contrário
	 */
    public static boolean isJogadaDisponivel(Integer linha, Integer coluna, Peca peca, Tabuleiro tabuleiro) {
        Peca[][] campo = tabuleiro.getCampo();

        if (linha < 0 || coluna < 0 || linha >= campo.length || coluna >= campo[0].length) {
            return false;
        }

        if (isPosicaoVazia(linha, coluna, tabuleiro)) {
            return true;
        }

        return isColisao(linha, coluna, peca, tabuleiro);
    }

    /** Método que dado uma posição e peça especifica, verifica se há uma peça da cor adversária na posição mencionada
     * 
     * @param linha Integer - Representação da linha onde deseja verificar
	 * @param coluna - Representação da coluna onde deseja verificar
	 * @param peca Peca - Objeto do tipo Peca que deseja se verificar
	 * @param tabuleiro Tabuleiro - Objeto do tipo Tabuleiro onde se encontra a peça que deseja se verificar
     * @return boolean - Retorna true caso haja uma peça de cor inimiga na posição requisitada, false caso contrário
     */
    public static boolean isColisao(Integer linha, Integer coluna, Peca peca, Tabuleiro tabuleiro) {
        Peca[][] campo = tabuleiro.getCampo();
        CorDaPeca cor = peca.getCor();

        Peca pecaNaPosicao = campo[linha][coluna];
        if (pecaNaPosicao == null) return false;

        boolean posicaoComPecaDeOutraCor = pecaNaPosicao.getCor() != cor;
        return posicaoComPecaDeOutraCor;
    }

    /** Método que dado uma posição, verifica se esta posição está vazia
     * 
     * @param linha Integer - Representação da linha onde deseja verificar
	 * @param coluna - Representação da coluna onde deseja verificar
	 * @param tabuleiro Tabuleiro - Objeto do tipo Tabuleiro onde se encontra a peça que deseja se verificar
     * @return boolean - Retorna true se a posição requisitada está vazia, false caso contrário
     */
    public static boolean isPosicaoVazia(Integer linha, Integer coluna, Tabuleiro tabuleiro) {
        Peca[][] campo = tabuleiro.getCampo();
        return campo[linha][coluna] == null;
    }
}
