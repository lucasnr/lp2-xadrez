package br.ufrn.imd.modelo;

import java.util.ArrayList;
import java.util.List;

/** Classe da peça rainha onde serão mantidos os valores e métodos dela
 * 
 * @author Lucas
 * @version 1.0
 *
 */
public class Rainha extends Peca {

	/** Método que retorna o caminho da imagem da peça rainha a depender da cor
     * 
     * @return String - Caminho da imagem da peça rainha a depender da cor
     */
    @Override
    public String getImagem() {
        if (this.getCor() == CorDaPeca.BRANCA) {
            return "/sprites/white_queen.png";
        } else {
            return "/sprites/black_queen.png";
        }
    }

    /** Método que verifica a situação da rainha no tabuleiro e retorna um List com posições de jogadas possiveis
     * 
     * @param tabuleiro Tabuleiro - Objeto do tipo tabuleiro que contém a rainha para ser analisado a jogada
     * @return List<Posicao> - List com as posições disponiveis para movimentação
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
