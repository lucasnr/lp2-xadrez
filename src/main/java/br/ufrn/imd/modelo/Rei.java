package br.ufrn.imd.modelo;

import java.util.ArrayList;
import java.util.List;

import static br.ufrn.imd.util.PecaUtil.isJogadaDisponivel;

/** Classe da peça rei onde serão mantidos os valores e métodos dela
 * 
 * @author Brayan
 * @version 1.0
 *
 */
public class Rei extends Peca {

	/** Método que retorna o caminho da imagem da peça rei a depender da cor
     * 
     * @return String - Caminho da imagem da peça rei a depender da cor
     */
    @Override
    public String getImagem() {
        if (this.getCor() == CorDaPeca.BRANCA) {
            return "/sprites/white_king.png";
        } else {
            return "/sprites/black_king.png";
        }
    }

    /** Método que verifica a situação do rei no tabuleiro e retorna um List com posições de jogadas possiveis
     * 
     * @param tabuleiro Tabuleiro - Objeto do tipo tabuleiro que contém o rei para ser analisado a jogada
     * @return List<Posicao> - List com as posições disponiveis para movimentação
     */
    @Override
    public List<Posicao> informarPossiveisJogadas(Tabuleiro tabuleiro) {
        List<Posicao> posicoes = new ArrayList<>();
        Posicao posicao = this.getPosicao();

        // PARA DIAGONAL SUPERIOR-ESQUERDA
        if (isJogadaDisponivel(posicao.getLinha() - 1, posicao.getColuna() - 1, this, tabuleiro)) {
            posicoes.add(new Posicao(posicao.getLinha() - 1, posicao.getColuna() - 1));
        }
        // PARA DIAGONAL SUPERIOR-DIREITA
        if (isJogadaDisponivel(posicao.getLinha() - 1, posicao.getColuna() + 1, this, tabuleiro)) {
            posicoes.add(new Posicao(posicao.getLinha() - 1, posicao.getColuna() + 1));
        }
	    // PARA DIAGONAL INFERIOR-ESQUERDA
        if (isJogadaDisponivel(posicao.getLinha() + 1, posicao.getColuna() - 1, this, tabuleiro)) {
            posicoes.add(new Posicao(posicao.getLinha() + 1, posicao.getColuna() - 1));
        }
        // PARA DIAGONAL INFERIOR-DIREITA
        if (isJogadaDisponivel(posicao.getLinha() + 1, posicao.getColuna() + 1, this, tabuleiro)) {
            posicoes.add(new Posicao(posicao.getLinha() + 1, posicao.getColuna() + 1));
        }
        // PARA CIMA
        if (isJogadaDisponivel(posicao.getLinha() - 1, posicao.getColuna(), this, tabuleiro)) {
            posicoes.add(new Posicao(posicao.getLinha() - 1, posicao.getColuna()));
        }
        // PARA BAIXO
        if (isJogadaDisponivel(posicao.getLinha() + 1, posicao.getColuna(), this, tabuleiro)) {
            posicoes.add(new Posicao(posicao.getLinha() + 1, posicao.getColuna()));
        }
        // PARA ESQUERDA
        if (isJogadaDisponivel(posicao.getLinha(), posicao.getColuna() - 1, this, tabuleiro)) {
            posicoes.add(new Posicao(posicao.getLinha(), posicao.getColuna() - 1));
        }
        // PARA DIREITA
        if (isJogadaDisponivel(posicao.getLinha(), posicao.getColuna() + 1, this, tabuleiro)) {
            posicoes.add(new Posicao(posicao.getLinha(), posicao.getColuna() + 1));
        }

        return posicoes;
    }
}

