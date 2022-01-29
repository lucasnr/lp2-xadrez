package br.ufrn.imd.util;

import br.ufrn.imd.modelo.CorDaPeca;
import br.ufrn.imd.modelo.Peca;
import br.ufrn.imd.modelo.Tabuleiro;

/** Classe com m�todos usados por classes filhas de Peca para evitar redund�ncia de c�digo
 * 
 * @author Lucas
 * @version 1.0
 *
 */
public class PecaUtil {
	/** M�todo com o objetivo de verificar se a pe�a indicada no tabuleiro pode jogar na posi��o requisitada
	 * 
	 * @param linha Integer - Representa��o da linha onde deseja verificar
	 * @param coluna - Representa��o da coluna onde deseja verificar
	 * @param peca Peca - Objeto do tipo Peca que deseja se verificar
	 * @param tabuleiro Tabuleiro - Objeto do tipo Tabuleiro onde se encontra a pe�a que deseja se verificar
	 * @return boolean - Retorna true se a pe�a puder se mover para o local requisitado, false caso contr�rio
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

    /** M�todo que dado uma posi��o e pe�a especifica, verifica se h� uma pe�a da cor advers�ria na posi��o mencionada
     * 
     * @param linha Integer - Representa��o da linha onde deseja verificar
	 * @param coluna - Representa��o da coluna onde deseja verificar
	 * @param peca Peca - Objeto do tipo Peca que deseja se verificar
	 * @param tabuleiro Tabuleiro - Objeto do tipo Tabuleiro onde se encontra a pe�a que deseja se verificar
     * @return boolean - Retorna true caso haja uma pe�a de cor inimiga na posi��o requisitada, false caso contr�rio
     */
    public static boolean isColisao(Integer linha, Integer coluna, Peca peca, Tabuleiro tabuleiro) {
        Peca[][] campo = tabuleiro.getCampo();
        CorDaPeca cor = peca.getCor();

        Peca pecaNaPosicao = campo[linha][coluna];
        if (pecaNaPosicao == null) return false;

        boolean posicaoComPecaDeOutraCor = pecaNaPosicao.getCor() != cor;
        return posicaoComPecaDeOutraCor;
    }

    /** M�todo que dado uma posi��o, verifica se esta posi��o est� vazia
     * 
     * @param linha Integer - Representa��o da linha onde deseja verificar
	 * @param coluna - Representa��o da coluna onde deseja verificar
	 * @param tabuleiro Tabuleiro - Objeto do tipo Tabuleiro onde se encontra a pe�a que deseja se verificar
     * @return boolean - Retorna true se a posi��o requisitada est� vazia, false caso contr�rio
     */
    public static boolean isPosicaoVazia(Integer linha, Integer coluna, Tabuleiro tabuleiro) {
        Peca[][] campo = tabuleiro.getCampo();
        return campo[linha][coluna] == null;
    }
}
