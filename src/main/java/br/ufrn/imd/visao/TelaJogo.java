package br.ufrn.imd.visao;

import br.ufrn.imd.controle.Jogo;
import br.ufrn.imd.modelo.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;

public class TelaJogo extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

    private JButton[][] quadrados = new JButton[8][8];

	private Jogo jogo;
	private Peca pecaSelecionada;
	private Posicao xeque;

	public static void main(String[] args) {
		TelaJogo tela = new TelaJogo();
		tela.setVisible(true);
	}

	public TelaJogo() {
		iniciarJogo();

		setSize(800,600);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Jogo de Xadrez");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void iniciarJogo() {
		this.jogo = new Jogo();

		Container ctn = getContentPane();
		ctn.removeAll();
		ctn.setLayout(new GridLayout(8, 9, 0, 0));

		for(int a = 0; a < 8; a++) {
			for(int b = 0; b < 8; b++) {
				String command = a + "-" + b;
				JButton botao = new JButton("");
				botao.addActionListener(this);
				botao.setActionCommand(command);

				ctn.add(botao);
				quadrados[a][b] = botao;
			}
		}

		this.atualizarTabuleiro();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// RESETAR
		if(e.getActionCommand().equals("reset")) {
			Container ctn = getContentPane();
			ctn.removeAll();
			
			iniciarJogo();
			return;
		}
		
		// NÃO RESETAR
		String[] posicaoSelecionadda = e.getActionCommand().split("-");
		int linha = Integer.parseInt(posicaoSelecionadda[0]);
		int coluna = Integer.parseInt(posicaoSelecionadda[1]);

		Tabuleiro tabuleiro = jogo.getTabuleiro();
		Peca peca = tabuleiro.getCampo()[linha][coluna];

		CorDaPeca corDaVez = CorDaPeca.PRETA;
		if(tabuleiro.isVezDasBrancas()) {
			corDaVez = CorDaPeca.BRANCA;
		}

		if (pecaSelecionada == null) {
			if (peca == null) {
				return;
			}
			if (peca.getCor() != corDaVez) {
				return;
			}

			List<Posicao> posicoes = peca.informarPossiveisJogadas(tabuleiro);

			// Remover possiveis jogadas que não tiram o xeque do rei
			if (this.xeque != null || peca instanceof Rei) {
				for (int i = 0; i < posicoes.size(); i++) {
					Posicao posicao = posicoes.get(i);
					boolean movimentoValido = this.jogo.isMovimentoValidoTendoReiEmXeque(peca, posicao);
					if (!movimentoValido)
						posicoes.remove(i--);
				}
			}

			// Não há possiveis movimentos
			if (posicoes.size() == 0) {
				return;
			}

			this.pecaSelecionada = peca;
			for (Posicao posicao : posicoes) {
				JButton botao = quadrados[posicao.getLinha()][posicao.getColuna()];
				botao.setBackground(new Color(235, 217, 0));
			}
		} else {
			List<Posicao> posicoes = this.pecaSelecionada.informarPossiveisJogadas(tabuleiro);
			Posicao selecionada = new Posicao(linha, coluna);

			// Cancelar seleção
			if (selecionada.equals(pecaSelecionada.getPosicao())) {
				pecaSelecionada = null;
				this.atualizarTabuleiro();
			}

			if (posicoes.contains(selecionada)) {
				Rei reiEmXeque = this.jogo.moverPeca(pecaSelecionada, selecionada);

				if (reiEmXeque != null) {
					this.xeque = reiEmXeque.getPosicao();
					this.verificarXequeMate();
				} else {
					this.xeque = null;
				}

				this.pecaSelecionada = null;
				this.atualizarTabuleiro();
			}
		}
	}

	private void atualizarTabuleiro() {
		Peca[][] campo = jogo.getTabuleiro().getCampo();
		for(int a = 0; a < 8; a++) {
			for(int b = 0; b < 8; b++) {
				Peca peca = campo[a][b];
				JButton quadrado = quadrados[a][b];

				if (peca == null) {
					quadrado.setIcon(null);
				} else {
					URL img = this.getClass().getResource(peca.getImagem());
					quadrado.setIcon(new ImageIcon(img));
				}

				if((a + b) % 2 == 0) {
					quadrado.setBackground(Color.WHITE);
				} else {
					quadrado.setBackground(Color.BLACK);
				}
			}
		}

		if (xeque != null) {
			quadrados[xeque.getLinha()][xeque.getColuna()].setBackground(Color.RED);
		}
		
		if (jogo.getEstado() == EstadoDeJogo.GAMEOVER) {
			informarFimDeJogo();
		}
	}

	private void verificarXequeMate() {
		if (this.xeque == null) {
			return;
		}

		Tabuleiro tabuleiro = this.jogo.getTabuleiro();
		Peca peca = tabuleiro.getCampo()[xeque.getLinha()][xeque.getColuna()];
		List<Posicao> posicoes = peca.informarPossiveisJogadas(tabuleiro);

		// Remover possiveis jogadas que não tiram o xeque do rei
		for (int i = 0; i < posicoes.size(); i++) {
			Posicao posicao = posicoes.get(i);
			boolean movimentoValido = this.jogo.isMovimentoValidoTendoReiEmXeque(peca, posicao);
			if (!movimentoValido)
				posicoes.remove(i--);
		}

		boolean xequeMate = posicoes.size() == 0;
		if (xequeMate) {
			this.jogo.setEstado(EstadoDeJogo.GAMEOVER);
			this.xeque = null;
			atualizarTabuleiro();
		}
	}

	private void informarFimDeJogo() {
		String vencedor = "Vitória das peças ";

		if(this.jogo.getTabuleiro().isVezDasBrancas()) {
			vencedor += "pretas.";
		} else {
			vencedor += "brancas.";
		}

		String[] opcoes = {"Recomeçar"};
		int recomecar = JOptionPane.showOptionDialog(null, vencedor, "Fim de jogo",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
		if (recomecar == 0) {
			this.iniciarJogo();
		}
	}

}
