package br.ufrn.imd.controle;

public class Jogo {

	private Tabuleiro tabuleiro;
	private EstadoDeJogo estado;
	
	public void Jogo() {
		estado = EstadoDeJogo.START;
		Tabuleiro tab = new Tabuleiro();
		Peca[8][8] lugares;
		
		for(int a = 0; a < 8; a++) {
			for(int b = 0; b < 8; b++) {
				lugares[a][b] = null;
			}
		}
		
		Peao pb0 = new Peao();
		Peao pb1 = new Peao();
		Peao pb2 = new Peao();
		Peao pb3 = new Peao();
		Peao pb4 = new Peao();
		Peao pb5 = new Peao();
		Peao pb6 = new Peao();
		Peao pb7 = new Peao();
		
		pb0.setCor(CorDaPeca.BRANCA);
		pb0.setPosicao(new Posicao(6, 0));
		pb1.setCor(CorDaPeca.BRANCA);
		pb1.setPosicao(new Posicao(6, 1));
		pb2.setCor(CorDaPeca.BRANCA);
		pb2.setPosicao(new Posicao(6, 2));
		pb3.setCor(CorDaPeca.BRANCA);
		pb3.setPosicao(new Posicao(6, 3));
		pb4.setCor(CorDaPeca.BRANCA);
		pb4.setPosicao(new Posicao(6, 4));
		pb5.setCor(CorDaPeca.BRANCA);
		pb5.setPosicao(new Posicao(6, 5));
		pb6.setCor(CorDaPeca.BRANCA);
		pb6.setPosicao(new Posicao(6, 6));
		pb7.setCor(CorDaPeca.BRANCA);
		pb7.setPosicao(new Posicao(6, 7));
		
		lugares[6] = {pb0, pb1, pb2, pb3, pb4, pb5, pb6, pb7};
		
		Peao pp0 = new Peao();
		Peao pp1 = new Peao();
		Peao pp2 = new Peao();
		Peao pp3 = new Peao();
		Peao pp4 = new Peao();
		Peao pp5 = new Peao();
		Peao pp6 = new Peao();
		Peao pp7 = new Peao();
		
		pp0.setCor(CorDaPeca.PRETA);
		pp0.setPosicao(new Posicao(1, 0));
		pp1.setCor(CorDaPeca.PRETA);
		pp1.setPosicao(new Posicao(1, 1));
		pp2.setCor(CorDaPeca.PRETA);
		pp2.setPosicao(new Posicao(1, 2));
		pp3.setCor(CorDaPeca.PRETA);
		pp3.setPosicao(new Posicao(1, 3));
		pp4.setCor(CorDaPeca.PRETA);
		pp4.setPosicao(new Posicao(1, 4));
		pp5.setCor(CorDaPeca.PRETA);
		pp5.setPosicao(new Posicao(1, 5));
		pp6.setCor(CorDaPeca.PRETA);
		pp6.setPosicao(new Posicao(1, 6));
		pp7.setCor(CorDaPeca.PRETA);
		pp7.setPosicao(new Posicao(1, 7));
		
		lugares[1] = {pp0, pp1, pp2, pp3, pp4, pp5, pp6, pp7};
		
		Torre tb0 = new Torre();
		Cavalo cb0 = new Cavalo();
		Bispo bb0 = new Bispo();
		Rainha rab = new Rainha();
		Rei reb = new Rei();
		Bispo bb1 = new Bispo();
		Cavalo cb1 = new Cavalo();
		Torre tb1 = new Torre();
		
		tb0.setCor(CorDaPeca.BRANCA);
		tb0.setPosicao(new Posicao(7, 0));
		cb0.setCor(CorDaPeca.BRANCA);
		cb0.setPosicao(new Posicao(7, 1));
		bb0.setCor(CorDaPeca.BRANCA);
		bb0.setPosicao(new Posicao(7, 2));
		rab.setCor(CorDaPeca.BRANCA);
		rab.setPosicao(new Posicao(7, 3));
		reb.setCor(CorDaPeca.BRANCA);
		reb.setPosicao(new Posicao(7, 4));
		bb1.setCor(CorDaPeca.BRANCA);
		bb1.setPosicao(new Posicao(7, 5));
		cb1.setCor(CorDaPeca.BRANCA);
		cb1.setPosicao(new Posicao(7, 6));
		tb1.setCor(CorDaPeca.BRANCA);
		tb1.setPosicao(new Posicao(7, 7));
		
		lugares[7] = {tb0, cb0, bb0, rab, reb, bb1, cb1, tb1};
		
		Torre tp0 = new Torre();
		Cavalo cp0 = new Cavalo();
		Bispo bp0 = new Bispo();
		Rainha rap = new Rainha();
		Rei rep = new Rei();
		Bispo bp1 = new Bispo();
		Cavalo cp1 = new Cavalo();
		Torre tp1 = new Torre();
		
		tp0.setCor(CorDaPeca.PRETA);
		tp0.setPosicao(new Posicao(0, 0));
		cp0.setCor(CorDaPeca.PRETA);
		cp0.setPosicao(new Posicao(0, 1));
		bp0.setCor(CorDaPeca.PRETA);
		bp0.setPosicao(new Posicao(0, 2));
		rap.setCor(CorDaPeca.PRETA);
		rap.setPosicao(new Posicao(0, 3));
		rep.setCor(CorDaPeca.PRETA);
		rep.setPosicao(new Posicao(0, 4));
		bp1.setCor(CorDaPeca.PRETA);
		bp1.setPosicao(new Posicao(0, 5));
		cp1.setCor(CorDaPeca.PRETA);
		cp1.setPosicao(new Posicao(0, 6));
		tp1.setCor(CorDaPeca.PRETA);
		tp1.setPosicao(new Posicao(0, 7));
		
		lugares[0] = {tp0, cp0, bp0, rap, rep, bp1, cp1, tp1};
		
		tab.setCampo(lugares);
		tabuleiro = tab;
	}
	
	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}
	
	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
	public void moverPeca(Peca peca, Posicao posicao) {
		Peca[][] camAux = tabuleiro.getCampo();
		
		//REI PEGO?
		if(camAux[posicao.getLinha()][posicao.getColuna()] instanceof Rei) {
			estado = EstadoDeJogo.GAMEOVER;
		}
		
		//PROMOCAO DE PEAO?
		if(camAux[posicao.getLinha()][posicao.getColuna()] instanceof Peao && (posicao.getLinha == 7 || posicao.getLinha == 0)) {
			Rainha r = new Rainha();
			r.setCor(peca.getCor());
			camAux[posicao.getLinha()][posicao.getColuna()] = r;
			r.setPosicao(posicao);
			tabuleiro.setCampo(camAux);
		} else {
			camAux[posicao.getLinha()][posicao.getColuna()] = peca;
			peca.setPosicao(posicao);
			tabuleiro.setCampo(camAux);
		}
		
		// Troca a vez
		tabuleiro.setVezDasBrancas(!tabuleiro.getVezDasBrancas());
	}
	
}
