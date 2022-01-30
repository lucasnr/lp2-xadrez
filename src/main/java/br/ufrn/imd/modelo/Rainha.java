package br.ufrn.imd.modelo;

import java.util.ArrayList;
import java.util.List;

/** Classe da pe�a rainha onde ser�o mantidos os valores e m�todos dela
 * 
 * @author Lucas
 * @version 1.0
 *
 */
public class Rainha extends Peca {

	/** M�todo que retorna o caminho da imagem da pe�a rainha a depender da cor
     * 
     * @return String - Caminho da imagem da pe�a rainha a depender da cor
     */
    @Override
    public String getImagem() {
        if (this.getCor() == CorDaPeca.BRANCA) {
            return "/sprites/white_queen.png";
        } else {
            return "/sprites/black_queen.png";
        }
    }

    /** M�todo que verifica a situa��o da rainha no tabuleiro e retorna um List com posi��es de jogadas possiveis
     * 
     * @param tabuleiro Tabuleiro - Objeto do tipo tabuleiro que cont�m a rainha para ser analisado a jogada
     * @return List<Posicao> - List com as posi��es disponiveis para movimenta��o
     */
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
