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
        Integer linha = posicao.getLinha();
        Integer coluna = posicao.getColuna();

        // PARA DIAGONAL SUPERIOR-ESQUERDA
        if (isJogadaDisponivel(linha - 1, coluna - 1, this, tabuleiro)) {
            posicoes.add(new Posicao(linha - 1, coluna - 1));
        }
        // PARA DIAGONAL SUPERIOR-DIREITA
        if (isJogadaDisponivel(linha - 1, coluna + 1, this, tabuleiro)) {
            posicoes.add(new Posicao(linha - 1, coluna + 1));
        }
	    // PARA DIAGONAL INFERIOR-ESQUERDA
        if (isJogadaDisponivel(linha + 1, coluna - 1, this, tabuleiro)) {
            posicoes.add(new Posicao(linha + 1, coluna - 1));
        }
        // PARA DIAGONAL INFERIOR-DIREITA
        if (isJogadaDisponivel(linha + 1, coluna + 1, this, tabuleiro)) {
            posicoes.add(new Posicao(linha + 1, coluna + 1));
        }
        // PARA CIMA
        if (isJogadaDisponivel(linha - 1, coluna, this, tabuleiro)) {
            posicoes.add(new Posicao(linha - 1, coluna));
        }
        // PARA BAIXO
        if (isJogadaDisponivel(linha + 1, coluna, this, tabuleiro)) {
            posicoes.add(new Posicao(linha + 1, coluna));
        }
        // PARA ESQUERDA
        if (isJogadaDisponivel(linha, coluna - 1, this, tabuleiro)) {
            posicoes.add(new Posicao(linha, coluna - 1));
        }
        // PARA DIREITA
        if (isJogadaDisponivel(linha, coluna + 1, this, tabuleiro)) {
            posicoes.add(new Posicao(linha, coluna + 1));
        }

        return posicoes;
    }
}

