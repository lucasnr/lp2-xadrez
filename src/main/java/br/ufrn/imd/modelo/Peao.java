package br.ufrn.imd.modelo;

import java.util.ArrayList;
import java.util.List;

import static br.ufrn.imd.util.PecaUtil.isPosicaoVazia;

/** Classe da pe�a pe�o onde ser�o mantidos os valores e m�todos dela
 * 
 * @author Brayan
 * @version 1.0
 * 
 */
public class Peao extends Peca {

	/** M�todo que retorna o caminho da imagem da pe�a pe�o a depender da cor
     * 
     * @return String - Caminho da imagem da pe�a pe�o a depender da cor
     */
    @Override
    public String getImagem() {
        if (this.getCor() == CorDaPeca.BRANCA) {
            return "/sprites/white_pawn.png";
        } else {
            return "/sprites/black_pawn.png";
        }
    }

    /** M�todo que verifica a situa��o do pe�o no tabuleiro e retorna um List com posi��es de jogadas possiveis
     * 
     * @param tabuleiro Tabuleiro - Objeto do tipo tabuleiro que cont�m o pe�o para ser analisado a jogada
     * @return List<Posicao> - List com as posi��es disponiveis para movimenta��o
     */
    @Override
    public List<Posicao> informarPossiveisJogadas(Tabuleiro tabuleiro) {
        List<Posicao> posicoes = new ArrayList<>();

		Posicao posicao = this.getPosicao();
		Integer linha = posicao.getLinha();
		Integer coluna = posicao.getColuna();

		Peca[][] tab = tabuleiro.getCampo();
        
        if (this.getCor()==CorDaPeca.BRANCA) {
        	// PARA CIMA
        	if (linha > 0 && isPosicaoVazia(linha - 1, coluna, tabuleiro)) {
                posicoes.add(new Posicao(linha - 1, coluna));
            }
        	
        	// PASSO DUPLO
        	if (linha == 6 && isPosicaoVazia(linha - 1, coluna, tabuleiro) && isPosicaoVazia(linha - 2, coluna, tabuleiro)) {
        		posicoes.add(new Posicao(linha - 2, coluna));
        	}
        	
        	//COMER NA SUPERIOR ESQUERDA
			if (linha > 0 && coluna > 0) {
				Peca superiorEsquerda = tab[linha - 1][coluna - 1];
				if (superiorEsquerda != null && superiorEsquerda.getCor() != this.getCor()) {
					posicoes.add(new Posicao(linha - 1, coluna - 1));
				}
			}
        	
        	// COMER NA SUPERIOR DIREITA
			if (linha > 0 && coluna < 7) {
				Peca superiorDireita = tab[linha - 1][coluna + 1];
				if (superiorDireita != null && superiorDireita.getCor() != this.getCor()) {
					posicoes.add(new Posicao(linha - 1, coluna + 1));
				}
			}
        	
        } else {
        	// PARA BAIXO
			if (linha < 8 && isPosicaoVazia(linha + 1, coluna, tabuleiro)) {
				posicoes.add(new Posicao(linha + 1, coluna));
			}
        	
        	// PASSO DUPLO
			if (linha == 1 && isPosicaoVazia(linha + 1, coluna, tabuleiro) && isPosicaoVazia(linha + 2, coluna, tabuleiro)) {
				posicoes.add(new Posicao(linha + 2, coluna));
			}
        	
        	// COMER NA INFERIOR ESQUERDA
			if (linha < 7 && coluna > 0) {
				Peca inferiorEsquerda = tab[linha + 1][coluna - 1];
				if (inferiorEsquerda != null && inferiorEsquerda.getCor() != this.getCor()) {
					posicoes.add(new Posicao(linha +1, coluna - 1));
				}
			}
        	
        	// COMER NA INFERIOR DIREITA
			if (linha < 7 && coluna < 7) {
				Peca inferiorDireita = tab[linha + 1][coluna + 1];
				if (inferiorDireita != null && inferiorDireita.getCor() != this.getCor()) {
					posicoes.add(new Posicao(linha + 1, coluna + 1));
				}
			}
        }
        

        return posicoes;
    }
}