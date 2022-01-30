package br.ufrn.imd.controle;

import br.ufrn.imd.modelo.*;

import java.util.ArrayList;
import java.util.List;

/** Classe responsável por controlar o tabuleiro e estado do jogo a depender da situação do tabuleiro e
 * do comandos dados pelo objeto da classe TelaJogo
 * 
 * @author Brayan
 * @author Lucas
 * @version 1.0
 * 
 */
public class Jogo {

	private EstadoDeJogo estado;
	private Tabuleiro tabuleiro;
	private boolean vezDasBrancas;
	private Posicao xeque;

	/** Construtor responsável por criar um objeto inicializando um tabuleiro, distribuindo as peças
	 * pelo tabuleiro em suas devidas posições iniciais, atribuindo o estado do jogo START e o boolean
	 * vezDasBrancas para true de acordo com as regras
	 * 
	 */
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

	/** Método responsável por mudar a peça de lugar e reagir com o tabuleiro de forma necessária a
	 * depender da situação, promovendo peões a rainhas caso cheguem ao outro lado, verificando
	 * possíveis xeques através do método verificarXequeMate e mudando a vez da jogada ao mover a peça
	 * 
	 * @param peca Peca - Objeto do tipo Peca que deve ser movido
	 * @param posicao Posicao - Objeto do tipo posição que representa linha e coluna aonde deve ser movido
	 */
	public void moverPeca(Peca peca, Posicao posicao) {
		Peca[][] campo = this.tabuleiro.getCampo();

		Integer linha = posicao.getLinha();
		Integer coluna = posicao.getColuna();

		// MovimentaÃ§Ã£o da peÃ§a
		campo[linha][coluna] = peca;
		campo[peca.getPosicao().getLinha()][peca.getPosicao().getColuna()] = null;
		peca.setPosicao(posicao);

		// PromoÃ§Ã£o de PeÃ£o
		if (campo[linha][coluna] instanceof Peao && (linha == 7 || linha == 0)) {
			Rainha rainha = new Rainha();
			rainha.setCor(peca.getCor());
			campo[linha][coluna] = rainha;
			rainha.setPosicao(posicao);
		}
		
		// MudanÃ§a da vez
		this.vezDasBrancas = !this.vezDasBrancas;

		Rei reiEmXeque = getReiEmXeque();
		if (reiEmXeque != null) {
			this.xeque = reiEmXeque.getPosicao();
		} else {
			this.xeque = null;
		}
		this.verificarXequeMate();
	}

	/** Método que verifica o tabuleiro para saber se há um xeque e retorna o Rei em xeque ou null caso 
	 * não haja um xeque naquele momento
	 * 
	 * @return Rei - Objeto do tipo Rei no tabuleiro que está em xeque, null caso não exista xeque
	 */
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

	/** Método que recebe peça e posição e verifica se aquele movimento deixará o rei em xeque retornando true
	 * ou não retornando false
	 * 
	 * @param peca Peca - Objeto do tipo Peca que deve ser movido
	 * @param nova Posicao - Objeto do tipo posição que representa linha e coluna aonde deve ser movido
	 * @return boolean - Retorna true caso o movimento informado deixe o rei em xeque e false caso contrário
	 */
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

	/** Método que recebe uma peça e informa as possíveis jogadas em uma List, jogadas essas que não deixaram
	 * o rei em xeque pelo uso do método isMovimentoValidoNaoDeixandoReiEmXeque
	 * 
	 * @param peca Peca - Objeto do tipo Peca que deve ser verificado as possibilidades de movimento
	 * @return List - Objeto do tipo List indicando as possibilidades de movimento daquela peça a depender dos
	 * movimentos dela e se aquilo trará perigo ao seu rei, deixando-o em xeque
	 */
	public List<Posicao> informarPossiveisJogadas(Peca peca) {
		if (peca == null) {
			return new ArrayList<>();
		}

		CorDaPeca corDaVez = this.vezDasBrancas ? CorDaPeca.BRANCA : CorDaPeca.PRETA;
		if (peca.getCor() != corDaVez) {
			return new ArrayList<>();
		}

		List<Posicao> posicoes = peca.informarPossiveisJogadas(this.tabuleiro);
		// Remover possiveis jogadas que nÃ£o tiram o xeque do rei
		for (int i = 0; i < posicoes.size(); i++) {
			Posicao posicao = posicoes.get(i);
			if (!this.isMovimentoValidoNaoDeixandoReiEmXeque(peca, posicao))
				posicoes.remove(i--);
		}

		return posicoes;
	}

	/** Método que verifica se existe um xeque-mate no tabuleiro a depender da situação e da vez
	 * da cor de peça atual atribuindo o estado de GAMEOVER caso não haja nada a fazer que evite um
	 * xeque-mate
	 * 
	 */
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
			// Se o rei tem algum movimento vÃ¡lido, logo nÃ£o estÃ¡ em xeque-mate
			if (this.isMovimentoValidoNaoDeixandoReiEmXeque(reiDaVez, posicao))
				return;

		// O rei nÃ£o estÃ¡ em xeque e existem outras peÃ§as
		if (this.xeque == null && pecasDaCorDaVez.size() > 1)
			return;

		this.estado = EstadoDeJogo.GAMEOVER;
	}

	/** Método que retorna o objeto do tipo Tabuleiro do atributo tabuleiro
	 * 
	 * @return Tabuleiro - Um objeto do tipo tabuleiro
	 */
	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	/** Método que retorna o valor do atributo estado
	 * 
	 * @return EstadoDeJogo - Um enum do tipo EstadoDeJogo
	 */
	public EstadoDeJogo getEstado() {
		return estado;
	}

	/** Método que retorna o valor do atributo xeque
	 * 
	 * @return boolean - Um boolean que indica se há xeques no jogo, false caso contrário
	 */
	public Posicao getXeque() {
		return this.xeque;
	}

	/** Método que retorna o valor do atributo vezDasBrancas
	 * 
	 * @return boolean - Um boolean que indica se é a vez das peças de cor branca, false caso contrário
	 */
	public boolean isVezDasBrancas() {
		return this.vezDasBrancas;
	}
}
