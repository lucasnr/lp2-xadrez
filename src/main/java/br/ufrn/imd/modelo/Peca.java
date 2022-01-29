package br.ufrn.imd.modelo;

import java.util.List;

/** Classe para todas as peças onde serão mantidos os valores e métodos delas
 * 
 * @author Lucas
 * @version 1.0
 * 
 */

public abstract class Peca {
    private CorDaPeca cor;
    private Posicao posicao;

    /** Método que verifica a situação da peça no tabuleiro e retorna um List com posições de jogadas possiveis
     * 
     * @param tabuleiro Tabuleiro - Objeto do tipo tabuleiro que contém aquela peça para ser analisado a jogada
     * @return List<Posicao> - List com as posições disponiveis para movimentação
     */
    public abstract List<Posicao> informarPossiveisJogadas(Tabuleiro tabuleiro);

    /** Método que retorna o caminho da imagem de determinada peça
     * 
     * @return String - Caminho da imagem da peça
     */
    public abstract String getImagem();

    /** Método que retorna a cor da peça
     * 
     * @return CorDaPeca - Cor da peça específica
     */
    public CorDaPeca getCor() {
        return cor;
    }

    /** Método que determina a cor da peça
     * 
     * @param cor CorDaPeca - cor da peça específica
     */
    public void setCor(CorDaPeca cor) {
        this.cor = cor;
    }

    /** Método que retorna a posição da peça no tabuleiro
     * 
     * @return Posicao - posição de linha e coluna da peça
     */
    public Posicao getPosicao() {
        return posicao;
    }

    /** Método que determina a posição da peça no tabuleiro
     * 
     * @param posicao Posicao - posição de linnha e coluna da peça
     */
    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }
}
