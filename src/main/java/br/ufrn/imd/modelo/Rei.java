package br.ufrn.imd.modelo;

import java.util.ArrayList;
import java.util.List;

import static br.ufrn.imd.util.PecaUtil.isJogadaDisponivel;

/** Classe da pe�a rei onde ser�o mantidos os valores e m�todos dela
 * 
 * @author Brayan
 * @version 1.0
 *
 */
public class Rei extends Peca {

	/** M�todo que retorna o caminho da imagem da pe�a rei a depender da cor
     * 
     * @return String - Caminho da imagem da pe�a rei a depender da cor
     */
    @Override
    public String getImagem() {
        if (this.getCor() == CorDaPeca.BRANCA) {
            return "/sprites/white_king.png";
        } else {
            return "/sprites/black_king.png";
        }
    }

    /** M�todo que verifica a situa��o do rei no tabuleiro e retorna um List com posi��es de jogadas possiveis
     * 
     * @param tabuleiro Tabuleiro - Objeto do tipo tabuleiro que cont�m o rei para ser analisado a jogada
     * @return List<Posicao> - List com as posi��es disponiveis para movimenta��o
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

