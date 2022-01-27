package br.ufrn.imd.controle;

import br.ufrn.imd.modelo.*;

public class Jogo {

	private Tabuleiro tabuleiro;
	private EstadoDeJogo estado;
	
	public Jogo() {
		this.estado = EstadoDeJogo.START;
		this.tabuleiro = new Tabuleiro();

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
	
	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}
	
	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
	public void moverPeca(Peca peca, Posicao posicao) {
		Peca[][] campo = tabuleiro.getCampo();

		Integer linha = posicao.getLinha();
		Integer coluna = posicao.getColuna();

		// REI PEGO?
		if(campo[linha][coluna] instanceof Rei) {
			estado = EstadoDeJogo.GAMEOVER;
		}
		
		// MOVER
		campo[linha][coluna] = peca;
		campo[peca.getPosicao().getLinha()][peca.getPosicao().getColuna()] = null;
		peca.setPosicao(posicao);
		tabuleiro.setCampo(campo);
		
		// PROMOCAO DE PEAO?
		if(campo[linha][coluna] instanceof Peao && (linha == 7 || linha == 0)) {
			Rainha r = new Rainha();
			r.setCor(peca.getCor());
			campo[linha][coluna] = r;
			r.setPosicao(posicao);
			tabuleiro.setCampo(campo);
		}
		
		// TROCA A VEZ
		tabuleiro.setVezDasBrancas(!tabuleiro.isVezDasBrancas());
	}
	
}
