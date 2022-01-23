package br.ufrn.imd.modelo;

import java.util.ArrayList;
import java.util.List;

import static br.ufrn.imd.util.PecaUtil.isColisao;
import static br.ufrn.imd.util.PecaUtil.isJogadaDisponivel;

public class Bispo extends Peca {

    @Override
    public String getImagem() {
        if (this.getCor() == CorDaPeca.BRANCA) {
            return "caminho/para/imagem/da/bispo/branca.png";
        } else {
            return "caminho/para/imagem/da/bispo/preta.png";
        }
    }

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
