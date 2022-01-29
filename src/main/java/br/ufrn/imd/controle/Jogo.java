package br.ufrn.imd.controle;

import br.ufrn.imd.modelo.*;

import java.util.ArrayList;
import java.util.List;

public class Jogo {

	private EstadoDeJogo estado;
	private Tabuleiro tabuleiro;
	private boolean vezDasBrancas;
	private Posicao xeque;

	public Jogo() {
		this.estado = EstadoDeJogo.START;
		this.tabuleiro = new Tabuleiro();
		this.vezDasBrancas = true;

		Peca[][] lugares = new Peca[8][8];

		CorDaPeca cores[] = { CorDaPeca.BRANCA, CorDaPeca.PRETA };
		for (CorDaPeca cor : cores) {
			// Peões
			for (int i = 0; i < 8; i++) {
				Integer linha = cor == CorDaPeca.BRANCA ? 6 : 1;
				Peca peao = new Peao();
				peao.setCor(cor);
				peao.setPosicao(new Posicao(linha, i));
				lugares[linha][i] = peao;
			}

			Torre torreEsquerda = new Torre();
			Cavalo cavaloEsquerda = new Cavalo();
			Bispo bispoEsquerda = new Bispo();
			Rainha rainha = new Rainha();
			Rei rei = new Rei();
			Bispo bispoDireita = new Bispo();
			Cavalo cavaloDireita = new Cavalo();
			Torre torreDireita = new Torre();

			Integer linha = cor == CorDaPeca.BRANCA ? 7 : 0;

			torreEsquerda.setCor(cor);
			torreEsquerda.setPosicao(new Posicao(linha, 0));
			cavaloEsquerda.setCor(cor);
			cavaloEsquerda.setPosicao(new Posicao(linha, 1));
			bispoEsquerda.setCor(cor);
			bispoEsquerda.setPosicao(new Posicao(linha, 2));
			rainha.setCor(cor);
			rainha.setPosicao(new Posicao(linha, 3));
			rei.setCor(cor);
			rei.setPosicao(new Posicao(linha, 4));
			bispoDireita.setCor(cor);
			bispoDireita.setPosicao(new Posicao(linha, 5));
			cavaloDireita.setCor(cor);
			cavaloDireita.setPosicao(new Posicao(linha, 6));
			torreDireita.setCor(cor);
			torreDireita.setPosicao(new Posicao(linha, 7));

			lugares[linha][0] = torreEsquerda;
			lugares[linha][1] = cavaloEsquerda;
			lugares[linha][2] = bispoEsquerda;
			lugares[linha][3] = rainha;
			lugares[linha][4] = rei;
			lugares[linha][5] = bispoDireita;
			lugares[linha][6] = cavaloDireita;
			lugares[linha][7] = torreDireita;
		}

		this.tabuleiro.setCampo(lugares);
	}

	public void moverPeca(Peca peca, Posicao posicao) {
		Peca[][] campo = this.tabuleiro.getCampo();

		Integer linha = posicao.getLinha();
		Integer coluna = posicao.getColuna();

		// Movimentação da peça
		campo[linha][coluna] = peca;
		campo[peca.getPosicao().getLinha()][peca.getPosicao().getColuna()] = null;
		peca.setPosicao(posicao);

		// Promoção de Peão
		if (campo[linha][coluna] instanceof Peao && (linha == 7 || linha == 0)) {
			Rainha rainha = new Rainha();
			rainha.setCor(peca.getCor());
			campo[linha][coluna] = rainha;
			rainha.setPosicao(posicao);
		}
		
		// Mudança da vez
		this.vezDasBrancas = !this.vezDasBrancas;

		Rei reiEmXeque = getReiEmXeque();
		if (reiEmXeque != null) {
			this.xeque = reiEmXeque.getPosicao();
		} else {
			this.xeque = null;
		}
		this.verificarXequeMate();
	}

	private Rei getReiEmXeque() {
		Peca[][] campo = this.tabuleiro.getCampo();

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Peca peca = campo[i][j];
				if (peca == null) {
					continue;
				}

				List<Posicao> posicoes = peca.informarPossiveisJogadas(this.tabuleiro);
				for (Posicao posicao : posicoes) {
					Peca inimigo = campo[posicao.getLinha()][posicao.getColuna()];
					if (inimigo != null && inimigo instanceof Rei)
						return (Rei) inimigo;
				}
			}
		}

		return null;
	}

	public boolean isMovimentoValidoNaoDeixandoReiEmXeque(Peca peca, Posicao nova) {
		Peca[][] campo = this.tabuleiro.getCampo();
		Posicao atual = peca.getPosicao();

		Peca naPosicaoNova = campo[nova.getLinha()][nova.getColuna()];
		// movimento
		campo[nova.getLinha()][nova.getColuna()] = peca;
		campo[atual.getLinha()][atual.getColuna()] = null;

		Rei reiEmXeque = this.getReiEmXeque();
		boolean movimentoValido = reiEmXeque == null || reiEmXeque.getCor() != peca.getCor();

		// desfazer movimento
		campo[nova.getLinha()][nova.getColuna()] = naPosicaoNova;
		campo[atual.getLinha()][atual.getColuna()] = peca;

		return movimentoValido;
	}

	public List<Posicao> informarPossiveisJogadas(Peca peca) {
		if (peca == null) {
			return new ArrayList<>();
		}

		CorDaPeca corDaVez = this.vezDasBrancas ? CorDaPeca.BRANCA : CorDaPeca.PRETA;
		if (peca.getCor() != corDaVez) {
			return new ArrayList<>();
		}

		List<Posicao> posicoes = peca.informarPossiveisJogadas(this.tabuleiro);
		// Remover possiveis jogadas que não tiram o xeque do rei
		for (int i = 0; i < posicoes.size(); i++) {
			Posicao posicao = posicoes.get(i);
			if (!this.isMovimentoValidoNaoDeixandoReiEmXeque(peca, posicao))
				posicoes.remove(i--);
		}

		return posicoes;
	}

	private void verificarXequeMate() {
		CorDaPeca corDaVez = this.vezDasBrancas ? CorDaPeca.BRANCA : CorDaPeca.PRETA;

		List<Peca> pecasDaCorDaVez = new ArrayList<>();
		Peca[][] campo = this.tabuleiro.getCampo();
		for (Peca[] linha : campo)
			for (Peca peca : linha)
				if (peca != null && peca.getCor() == corDaVez)
					pecasDaCorDaVez.add(peca);

		Rei reiDaVez = null;
		for (Peca peca : pecasDaCorDaVez)
			if (peca instanceof Rei)
				reiDaVez = (Rei) peca;

		List<Posicao> posicoes = reiDaVez.informarPossiveisJogadas(this.tabuleiro);
		if (posicoes.size() == 0)
			return;

		for (Posicao posicao : posicoes)
			// Se o rei tem algum movimento válido, logo não está em xeque-mate
			if (this.isMovimentoValidoNaoDeixandoReiEmXeque(reiDaVez, posicao))
				return;

		// O rei não está em xeque e existem outras peças
		if (this.xeque == null && pecasDaCorDaVez.size() > 1)
			return;

		this.estado = EstadoDeJogo.GAMEOVER;
	}

	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	public EstadoDeJogo getEstado() {
		return estado;
	}

	public Posicao getXeque() {
		return this.xeque;
	}

	public boolean isVezDasBrancas() {
		return this.vezDasBrancas;
	}
}
