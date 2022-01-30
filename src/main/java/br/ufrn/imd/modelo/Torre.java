package br.ufrn.imd.modelo;

import java.util.ArrayList;
import java.util.List;

import static br.ufrn.imd.util.PecaUtil.isColisao;
import static br.ufrn.imd.util.PecaUtil.isJogadaDisponivel;

/** Classe da peça torre onde serão mantidos os valores e métodos dela
 * 
 * @author Lucas
 * @version 1.0
 *
 */
public class Torre extends Peca {

	/** Método que retorna o caminho da imagem da peça torre a depender da cor
     * 
     * @return String - Caminho da imagem da peça torre a depender da cor
     */
    @Override
    public String getImagem() {
        if (this.getCor() == CorDaPeca.BRANCA) {
            return "/sprites/white_rook.png";
        } else {
            return "/sprites/black_rook.png";
        }
    }

    /** Método que verifica a situação da torre no tabuleiro e retorna um List com posições de jogadas possiveis
     * 
     * @param tabuleiro Tabuleiro - Objeto do tipo tabuleiro que contém a torre para ser analisado a jogada
     * @return List<Posicao> - List com as posições disponiveis para movimentação
     */
    @Override
    public List<Posicao> informarPossiveisJogadas(Tabuleiro tabuleiro) {
        List<Posicao> posicoes = new ArrayList<>();
        Posicao posicao = this.getPosicao();

        // PARA CIMA
        for (int linha = posicao.getLinha() - 1; linha >= 0; linha--) {
            Integer coluna = posicao.getColuna();

            if (isJogadaDisponivel(linha, coluna, this, tabuleiro))
                posicoes.add(new Posicao(linha, coluna));
            else break;

            if (isColisao(linha, coluna, this, tabuleiro)) break;
        }

        // PARA DIREITA
        for (int coluna = posicao.getColuna() + 1; coluna < 8; coluna++) {
            Integer linha = posicao.getLinha();

            if (isJogadaDisponivel(linha, coluna, this, tabuleiro))
                posicoes.add(new Posicao(linha, coluna));
            else break;

            if (isColisao(linha, coluna, this, tabuleiro)) break;
        }

        // PARA BAIXO
        for (int linha = posicao.getLinha() + 1; linha < 8; linha++) {
            Integer coluna = posicao.getColuna();

            if (isJogadaDisponivel(linha, coluna, this, tabuleiro))
                posicoes.add(new Posicao(linha, coluna));
            else break;

            if (isColisao(linha, coluna, this, tabuleiro)) break;
        }

        // PARA ESQUERDA
        for (int coluna = posicao.getColuna() - 1; coluna >= 0; coluna--) {
            Integer linha = posicao.getLinha();

            if (isJogadaDisponivel(linha, coluna, this, tabuleiro))
                posicoes.add(new Posicao(linha, coluna));
            else break;

            if (isColisao(linha, coluna, this, tabuleiro)) break;
        }

        return posicoes;
    }

}
