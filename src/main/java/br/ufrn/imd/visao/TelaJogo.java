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
	private Peca selecionada;

	public static void main(String[] args) {
		TelaJogo tela = new TelaJogo();
		tela.setVisible(true);
	}

	public TelaJogo() {
		this.iniciarJogo();

		setSize(800,600);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Jogo de Xadrez");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void iniciarJogo() {
		this.jogo = new Jogo();

		Container container = this.getContentPane();
		container.removeAll();
		container.setLayout(new GridLayout(8, 9, 0, 0));

		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				String command = i + "-" + j;
				JButton botao = new JButton("");
				botao.addActionListener(this);
				botao.setActionCommand(command);

				container.add(botao);
				this.quadrados[i][j] = botao;
			}
		}

		this.atualizarTabuleiro();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String[] command = e.getActionCommand().split("-");
		int linha = Integer.parseInt(command[0]);
		int coluna = Integer.parseInt(command[1]);

		Tabuleiro tabuleiro = this.jogo.getTabuleiro();

		if (selecionada == null) {
			Peca peca = tabuleiro.getCampo()[linha][coluna];
			List<Posicao> posicoes = this.jogo.informarPossiveisJogadas(peca);
			// Não há possiveis movimentos
			if (posicoes.size() == 0)
				return;

			this.selecionada = peca;
			for (Posicao posicao : posicoes) {
				JButton botao = this.quadrados[posicao.getLinha()][posicao.getColuna()];
				botao.setBackground(new Color(235, 217, 0));
			}
		} else {
			List<Posicao> posicoes = this.jogo.informarPossiveisJogadas(this.selecionada);
			Posicao nova = new Posicao(linha, coluna);

			// Cancelar seleção
			if (nova.equals(this.selecionada.getPosicao())) {
				this.selecionada = null;
				this.atualizarTabuleiro();
			}

			if (posicoes.contains(nova)) {
				this.jogo.moverPeca(this.selecionada, nova);

				this.selecionada = null;
				this.atualizarTabuleiro();
			}
		}
	}

	private void atualizarTabuleiro() {
		Peca[][] campo = this.jogo.getTabuleiro().getCampo();
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				Peca peca = campo[i][j];
				JButton quadrado = this.quadrados[i][j];

				if (peca == null) {
					quadrado.setIcon(null);
				} else {
					URL img = this.getClass().getResource(peca.getImagem());
					quadrado.setIcon(new ImageIcon(img));
				}

				if((i + j) % 2 == 0) {
					quadrado.setBackground(Color.WHITE);
				} else {
					quadrado.setBackground(Color.BLACK);
				}
			}
		}

		Posicao xeque = this.jogo.getXeque();
		if (xeque != null) {
			this.quadrados[xeque.getLinha()][xeque.getColuna()].setBackground(Color.RED);
		}
		
		if (this.jogo.getEstado() == EstadoDeJogo.GAMEOVER) {
			informarFimDeJogo();
		}
	}

	private void informarFimDeJogo() {
		String vencedor = "Vitória das peças ";

		if(this.jogo.isVezDasBrancas()) {
			vencedor += "pretas.";
		} else {
			vencedor += "brancas.";
		}

		String[] opcoes = {"Recomeçar"};
		int recomecar = JOptionPane.showOptionDialog(null, vencedor, "Fim de Jogo",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
		if (recomecar == 0) {
			this.iniciarJogo();
		}
	}

}
