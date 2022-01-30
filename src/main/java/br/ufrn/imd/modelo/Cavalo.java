package br.ufrn.imd.modelo;

import java.util.ArrayList;
import java.util.List;

import static br.ufrn.imd.util.PecaUtil.isJogadaDisponivel;

/** Classe da peça cavalo onde serão mantidos os valores e métodos dela
 * 
 * @author Brayan
 * @version 1.0
 *
 */
public class Cavalo extends Peca {

	/** Método que retorna o caminho da imagem da peça cavalo a depender da cor
     * 
     * @return String - Caminho da imagem da peça cavalo a depender da cor
     */
    @Override
    public String getImagem() {
        if (this.getCor() == CorDaPeca.BRANCA) {
            return "/sprites/white_knight.png";
        } else {
            return "/sprites/black_knight.png";
        }
    }

    /** Método que verifica a situação do cavalo no tabuleiro e retorna um List com posições de jogadas possiveis
     * 
     * @param tabuleiro Tabuleiro - Objeto do tipo tabuleiro que contém o cavalo para ser analisado a jogada
     * @return List<Posicao> - List com as posições disponiveis para movimentação
     */
    @Override
    public List<Posicao> informarPossiveisJogadas(Tabuleiro tabuleiro) {
        List<Posicao> posicoes = new ArrayList<>();

        Posicao posicao = this.getPosicao();
        Integer linha = posicao.getLinha();
        Integer coluna = posicao.getColuna();

        // 2x - CIMA E ESQUERDA
        if (isJogadaDisponivel(linha - 2, coluna - 1, this, tabuleiro)) {
            posicoes.add(new Posicao(linha - 2, coluna - 1));
        }
        // 2x - CIMA E DIREITA
        if (isJogadaDisponivel(linha - 2, coluna + 1, this, tabuleiro)) {
            posicoes.add(new Posicao(linha - 2, coluna + 1));
        }
	    // 2x - BAIXO E ESQUERDA
        if (isJogadaDisponivel(linha + 2, coluna - 1, this, tabuleiro)) {
            posicoes.add(new Posicao(linha + 2, coluna - 1));
        }
        // 2x - BAIXO E DIREITA
        if (isJogadaDisponivel(linha + 2, coluna + 1, this, tabuleiro)) {
            posicoes.add(new Posicao(linha + 2, coluna + 1));
        }
        // 2x - ESQUERDA E CIMA
        if (isJogadaDisponivel(linha - 1, coluna - 2, this, tabuleiro)) {
            posicoes.add(new Posicao(linha - 1, coluna - 2));
        }
        // 2x - ESQUERDA E BAIXO
        if (isJogadaDisponivel(linha + 1, coluna - 2, this, tabuleiro)) {
            posicoes.add(new Posicao(linha + 1, coluna - 2));
        }
        // 2x - DIREITA E CIMA
        if (isJogadaDisponivel(linha - 1, coluna + 2, this, tabuleiro)) {
            posicoes.add(new Posicao(linha - 1, coluna + 2));
        }
        // 2x - DIREITA E BAIXO
        if (isJogadaDisponivel(linha + 1, coluna + 2, this, tabuleiro)) {
            posicoes.add(new Posicao(linha + 1, coluna + 2));
        }

        return posicoes;
    }
}