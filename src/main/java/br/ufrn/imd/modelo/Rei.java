package br.ufrn.imd.modelo;

import java.util.ArrayList;
import java.util.List;

import static br.ufrn.imd.util.PecaUtil.isColisao;
import static br.ufrn.imd.util.PecaUtil.isJogadaDisponivel;

public class Rei extends Peca {

    @Override
    public String getImagem() {
        if (this.getCor() == CorDaPeca.BRANCA) {
            return "caminho/para/imagem/da/rei/branca.png";
        } else {
            return "caminho/para/imagem/da/rei/preta.png";
        }
    }

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

