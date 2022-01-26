package br.ufrn.imd.modelo;

import java.util.ArrayList;
import java.util.List;

import static br.ufrn.imd.util.PecaUtil.isJogadaDisponivel;

public class Peao extends Peca {

    @Override
    public String getImagem() {
        if (this.getCor() == CorDaPeca.BRANCA) {
            return "../../../../../../../Sprites/white_pawn.png";
        } else {
            return "../../../../../../../Sprites/black_pawn.png";
        }
    }

    @Override
    public List<Posicao> informarPossiveisJogadas(Tabuleiro tabuleiro) {
        List<Posicao> posicoes = new ArrayList<>();
        Posicao posicao = this.getPosicao();
        
        Peca[][] tab = tabuleiro.getCampo();
        
        if(this.getCor()==CorDaPeca.BRANCA) {
        	// PARA CIMA
        	if (isJogadaDisponivel(posicao.getLinha() - 1, posicao.getColuna(), this, tabuleiro) && tab[posicao.getLinha() - 1][posicao.getColuna()] == null) {
                posicoes.add(new Posicao(posicao.getLinha() - 1, posicao.getColuna()));
            }
        	
        	// PASSO DUPLO
        	if (posicao.getLinha() == 6 && isJogadaDisponivel(posicao.getLinha() - 2, posicao.getColuna(), this, tabuleiro) && tab[posicao.getLinha() - 2][posicao.getColuna()] == null) {
        		posicoes.add(new Posicao(posicao.getLinha() - 2, posicao.getColuna()));
        	}
        	
        	//COMER NA SUPERIOR ESQUERDA
        	if(tab[posicao.getLinha() - 1][posicao.getColuna() - 1] != null) {
        		if(tab[posicao.getLinha() - 1][posicao.getColuna() - 1].getCor() != this.getCor()) {
            		posicoes.add(new Posicao(posicao.getLinha() - 1, posicao.getColuna() - 1));
            	}
        	}
        	
        	// COMER NA SUPERIOR DIREITA
        	if(tab[posicao.getLinha() - 1][posicao.getColuna() + 1] != null) {
        		if(tab[posicao.getLinha() - 1][posicao.getColuna() + 1].getCor() != this.getCor()) {
            		posicoes.add(new Posicao(posicao.getLinha() - 1, posicao.getColuna() + 1));
            	}
        	}
        	
        } else {
        	// PARA BAIXO
        	if (isJogadaDisponivel(posicao.getLinha() + 1, posicao.getColuna(), this, tabuleiro) && tab[posicao.getLinha() + 1][posicao.getColuna()] == null) {
                posicoes.add(new Posicao(posicao.getLinha() + 1, posicao.getColuna()));
            }
        	
        	// PASSO DUPLO
        	if (posicao.getLinha() == 1 && isJogadaDisponivel(posicao.getLinha() + 2, posicao.getColuna(), this, tabuleiro) && tab[posicao.getLinha() + 2][posicao.getColuna()] == null) {
        		posicoes.add(new Posicao(posicao.getLinha() + 2, posicao.getColuna()));
        	}
        	
        	// COMER NA INFERIOR ESQUERDA
        	if(tab[posicao.getLinha() + 1][posicao.getColuna() - 1] != null) {
        		if(tab[posicao.getLinha() + 1][posicao.getColuna() - 1].getCor() != this.getCor()) {
            		posicoes.add(new Posicao(posicao.getLinha()  +1, posicao.getColuna() - 1));
            	}
        	}
        	
        	// COMER NA INFERIOR DIREITA
        	if(tab[posicao.getLinha() + 1][posicao.getColuna() + 1] != null) {
        		if(tab[posicao.getLinha() + 1][posicao.getColuna() + 1].getCor() != this.getCor()) {
            		posicoes.add(new Posicao(posicao.getLinha() + 1, posicao.getColuna() + 1));
            	}
        	}
        }
        

        return posicoes;
    }
}