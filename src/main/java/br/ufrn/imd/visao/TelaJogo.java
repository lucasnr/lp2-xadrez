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
	private Posicao cheque;

	public static void main(String[] args) {
		TelaJogo tela = new TelaJogo();
		tela.setVisible(true);
	}

	public TelaJogo() {
		iniciarJogo();
		
		setSize(800,600);
		setResizable(false);
		setTitle("Jogo de Xadrez");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void iniciarJogo() {
		this.jogo = new Jogo();

		Peca[][] campo = this.jogo.getTabuleiro().getCampo();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Peca peca = campo[i][j];
				if (peca != null && peca.getCor() == CorDaPeca.PRETA && !(peca instanceof Rei)) {
					campo[i][j] = null;
				}
				if (peca != null && peca instanceof Peao) {
					campo[i][j] = null;
				}
			}
		}

		Container ctn = getContentPane();
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

			// Remover possiveis jogadas que não tiram o cheque do rei
			if (this.cheque != null || peca instanceof Rei) {
				for (int i = 0; i < posicoes.size(); i++) {
					Posicao posicao = posicoes.get(i);
					boolean movimentoValido = this.jogo.isMovimentoValidoTendoReiEmCheque(peca, posicao);
					if (!movimentoValido) {
						posicoes.remove(i);
						i--;
					}
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
				Rei reiEmCheque = this.jogo.moverPeca(pecaSelecionada, selecionada);

				if (reiEmCheque != null) {
					this.cheque = reiEmCheque.getPosicao();
				} else {
					this.cheque = null;
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

		if (cheque != null) {
			quadrados[cheque.getLinha()][cheque.getColuna()].setBackground(Color.RED);
		}
		
		if (jogo.getEstado() == EstadoDeJogo.GAMEOVER) {
			informarFimDeJogo();
		}
	}

	private void informarFimDeJogo() {
		String vencedor = "Vitória das peças ";

		if(this.jogo.getTabuleiro().isVezDasBrancas()) {
			vencedor += "pretas.";
		} else {
			vencedor += "brancas.";
		}

		JPanel mensagem = new JPanel();
		mensagem.setSize(800,600);
		mensagem.setVisible(true);

		JLabel vcd = new JLabel(vencedor);
		vcd.setBounds(100,100,100,100);

		JButton denovo = new JButton("Recomeçar");
		denovo.setBounds(200,200,100,100);
		denovo.addActionListener(this);
		denovo.setActionCommand("reset");

		Container ctn = getContentPane();
		ctn.removeAll();
		ctn.add(mensagem);
		ctn.add(vcd);
		ctn.add(denovo);
	}

}
