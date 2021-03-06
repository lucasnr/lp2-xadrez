package br.ufrn.imd.modelo;

import java.util.ArrayList;
import java.util.List;

import static br.ufrn.imd.util.PecaUtil.isColisao;
import static br.ufrn.imd.util.PecaUtil.isJogadaDisponivel;

/** Classe da pe?a bispo onde ser?o mantidos os valores e m?todos dela
 * 
 * @author Lucas
 * @version 1.0
 *
 */
public class Bispo extends Peca {

	/** M?todo que retorna o caminho da imagem da pe?a bispo a depender da cor
     * 
     * @return String - Caminho da imagem da pe?a bispo a depender da cor
     */
    @Override
    public String getImagem() {
        if (this.getCor() == CorDaPeca.BRANCA) {
            return "/sprites/white_bishop.png";
        } else {
            return "/sprites/black_bishop.png";
        }
    }

    /** M?todo que verifica a situa??o do bispo no tabuleiro e retorna um List com posi??es de jogadas possiveis
     * 
     * @param tabuleiro Tabuleiro - Objeto do tipo tabuleiro que cont?m o bispo para ser analisado a jogada
     * @return List<Posicao> - List com as posi??es disponiveis para movimenta??o
     */
    @Override
    public List<Posicao> informarPossiveisJogadas(Tabuleiro tabuleiro) {
        List<Posicao> posicoes = new ArrayList<>();
        Posicao posicao = this.getPosicao();

        // PARA DIAGONAL SUPERIOR-ESQUERDA
        for (int linha = posicao.getLinha() - 1, coluna = posicao.getColuna() - 1; linha >= 0 && coluna >= 0; linha--, coluna--) {
            if (isJogadaDisponivel(linha, coluna, this, tabuleiro))
                posicoes.add(new Posicao(linha, coluna));
            else break;

            if (isColisao(linha, coluna, this, tabuleiro)) break;
        }

        // PARA DIAGONAL SUPERIOR-DIREITA
        for (int linha = posicao.getLinha() - 1, coluna = posicao.getColuna() + 1; linha >= 0 && coluna < 8; linha--, coluna++) {
            if (isJogadaDisponivel(linha, coluna, this, tabuleiro))
                posicoes.add(new Posicao(linha, coluna));
            else break;

            if (isColisao(linha, coluna, this, tabuleiro)) break;
        }

        // PARA DIAGONAL INFERIOR-DIREITA
        for (int linha = posicao.getLinha() + 1, coluna = posicao.getColuna() + 1; linha < 8 && coluna < 8; linha++, coluna++) {
            if (isJogadaDisponivel(linha, coluna, this, tabuleiro))
                posicoes.add(new Posicao(linha, coluna));
            else break;

            if (isColisao(linha, coluna, this, tabuleiro)) break;
        }

        // PARA DIAGONAL INFERIOR-ESQUERDA
        for (int linha = posicao.getLinha() + 1, coluna = posicao.getColuna() - 1; linha < 8 && coluna >= 0; linha++, coluna--) {
            if (isJogadaDisponivel(linha, coluna, this, tabuleiro))
                posicoes.add(new Posicao(linha, coluna));
            else break;

            if (isColisao(linha, coluna, this, tabuleiro)) break;
        }

        return posicoes;
    }
}
