package br.ufrn.imd.modelo;

import java.util.List;

/** Classe para todas as pe�as onde ser�o mantidos os valores e m�todos delas
 * 
 * @author Lucas
 * @version 1.0
 * 
 */

public abstract class Peca {
    private CorDaPeca cor;
    private Posicao posicao;

    /** M�todo que verifica a situa��o da pe�a no tabuleiro e retorna um List com posi��es de jogadas possiveis
     * 
     * @param tabuleiro Tabuleiro - Objeto do tipo tabuleiro que cont�m aquela pe�a para ser analisado a jogada
     * @return List<Posicao> - List com as posi��es disponiveis para movimenta��o
     */
    public abstract List<Posicao> informarPossiveisJogadas(Tabuleiro tabuleiro);

    /** M�todo que retorna o caminho da imagem de determinada pe�a
     * 
     * @return String - Caminho da imagem da pe�a
     */
    public abstract String getImagem();

    /** M�todo que retorna a cor da pe�a
     * 
     * @return CorDaPeca - Cor da pe�a espec�fica
     */
    public CorDaPeca getCor() {
        return cor;
    }

    /** M�todo que determina a cor da pe�a
     * 
     * @param cor CorDaPeca - cor da pe�a espec�fica
     */
    public void setCor(CorDaPeca cor) {
        this.cor = cor;
    }

    /** M�todo que retorna a posi��o da pe�a no tabuleiro
     * 
     * @return Posicao - posi��o de linha e coluna da pe�a
     */
    public Posicao getPosicao() {
        return posicao;
    }

    /** M�todo que determina a posi��o da pe�a no tabuleiro
     * 
     * @param posicao Posicao - posi��o de linnha e coluna da pe�a
     */
    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }
}
