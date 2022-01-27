package br.ufrn.imd.modelo;

import java.util.ArrayList;
import java.util.List;

import static br.ufrn.imd.util.PecaUtil.isJogadaDisponivel;
import static br.ufrn.imd.util.PecaUtil.isPosicaoVazia;

public class Peao extends Peca {

    @Override
    public String getImagem() {
        if (this.getCor() == CorDaPeca.BRANCA) {
            return "/sprites/white_pawn.png";
        } else {
            return "/sprites/black_pawn.png";
        }
    }

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
			if (linha < 8 && coluna > 0) {
				Peca inferiorEsquerda = tab[linha + 1][coluna - 1];
				if (inferiorEsquerda != null && inferiorEsquerda.getCor() != this.getCor()) {
					posicoes.add(new Posicao(linha +1, coluna - 1));
				}
			}
        	
        	// COMER NA INFERIOR DIREITA
			if (linha < 8 && coluna < 7) {
				Peca inferiorDireita = tab[linha + 1][coluna + 1];
				if (inferiorDireita != null && inferiorDireita.getCor() != this.getCor()) {
					posicoes.add(new Posicao(linha + 1, coluna + 1));
				}
			}
        }
        

        return posicoes;
    }
}