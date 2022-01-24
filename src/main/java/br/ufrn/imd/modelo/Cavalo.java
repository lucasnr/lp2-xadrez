package br.ufrn.imd.modelo;

import java.util.ArrayList;
import java.util.List;

import static br.ufrn.imd.util.PecaUtil.isColisao;
import static br.ufrn.imd.util.PecaUtil.isJogadaDisponivel;

public class Cavalo extends Peca {

    @Override
    public String getImagem() {
        if (this.getCor() == CorDaPeca.BRANCA) {
            return "caminho/para/imagem/da/cavalo/branca.png";
        } else {
            return "caminho/para/imagem/da/cavalo/preta.png";
        }
    }

    @Override
    public List<Posicao> informarPossiveisJogadas(Tabuleiro tabuleiro) {
        List<Posicao> posicoes = new ArrayList<>();
        Posicao posicao = this.getPosicao();

        // 2x - CIMA E ESQUERDA
        if (isJogadaDisponivel(posicao.getLinha() - 2, posicao.getColuna() - 1, this, tabuleiro)) {
            posicoes.add(new Posicao(posicao.getLinha() - 2, posicao.getColuna() - 1));
        }
        // 2x - CIMA E DIREITA
        if (isJogadaDisponivel(posicao.getLinha() - 2, posicao.getColuna() + 1, this, tabuleiro)) {
            posicoes.add(new Posicao(posicao.getLinha() - 2, posicao.getColuna() + 1));
        }
	    // 2x - BAIXO E ESQUERDA
        if (isJogadaDisponivel(posicao.getLinha() + 2, posicao.getColuna() - 1, this, tabuleiro)) {
            posicoes.add(new Posicao(posicao.getLinha() + 2, posicao.getColuna() - 1));
        }
        // 2x - BAIXO E DIREITA
        if (isJogadaDisponivel(posicao.getLinha() + 2, posicao.getColuna() + 1, this, tabuleiro)) {
            posicoes.add(new Posicao(posicao.getLinha() + 2, posicao.getColuna() + 1));
        }
        // 2x - ESQUERDA E CIMA
        if (isJogadaDisponivel(posicao.getLinha() - 1, posicao.getColuna() - 2, this, tabuleiro)) {
            posicoes.add(new Posicao(posicao.getLinha() - 1, posicao.getColuna() - 2));
        }
        // 2x - ESQUERDA E BAIXO
        if (isJogadaDisponivel(posicao.getLinha() + 1, posicao.getColuna() - 2, this, tabuleiro)) {
            posicoes.add(new Posicao(posicao.getLinha() + 1, posicao.getColuna() - 2));
        }
        // 2x - DIREITA E CIMA
        if (isJogadaDisponivel(posicao.getLinha() - 1, posicao.getColuna() + 2, this, tabuleiro)) {
            posicoes.add(new Posicao(posicao.getLinha() - 1, posicao.getColuna() + 2));
        }
        // 2x - DIREITA E BAIXO
        if (isJogadaDisponivel(posicao.getLinha() + 1, posicao.getColuna() + 2, this, tabuleiro)) {
            posicoes.add(new Posicao(posicao.getLinha() + 1, posicao.getColuna() + 2));
        }

        return posicoes;
    }
}