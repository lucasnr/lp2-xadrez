package br.ufrn.imd.visao;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import br.ufrn.imd.controle.Jogo;
import br.ufrn.imd.modelo.CorDaPeca;
import br.ufrn.imd.modelo.EstadoDeJogo;
import br.ufrn.imd.modelo.Peca;
import br.ufrn.imd.modelo.Posicao;
import br.ufrn.imd.modelo.Tabuleiro;

public class TelaJogo extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

    private JButton[][] quadrados = new JButton[8][8];

	private Jogo jogo;
	private Peca pecaSelecionada = null;

	public static void main(String[] args) {
		TelaJogo tela = new TelaJogo();
		tela.setVisible(true);
	}
	
	public void iniciarJogo() {
		this.jogo = new Jogo();

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

	public TelaJogo() {
		iniciarJogo();
		
		setSize(800,600);
		setResizable(false);
		setTitle("Jogo de Xadrez");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//RESETAR?
		if(e.getActionCommand().equals("reset")) {
			System.out.println("denovo!");
			Container ctn = getContentPane();
			ctn.removeAll();
			
			iniciarJogo();
			return;
		}
		
		//N�O RESETAR
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
			if (selecionada.equals(this.pecaSelecionada.getPosicao())) {
				// cancelar seleção
				pecaSelecionada = null;
				this.atualizarTabuleiro();
			}

			if (posicoes.contains(selecionada)) {
				this.jogo.moverPeca(this.pecaSelecionada, selecionada);
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
		
		if(jogo.getEstado() == EstadoDeJogo.GAMEOVER) {
			System.out.println("Game over");

			String vencedor = "Vit�ria das pe�as ";
			
			if(jogo.getTabuleiro().isVezDasBrancas()) {
				System.out.println("Vit�ria das pe�as pretas");
				vencedor = vencedor + "pretas.";
			} else {
				System.out.println("Vit�ria das pe�as brancas");
				vencedor = vencedor + "brancas.";
			}
			
			JInternalFrame telaDeMensagem = new JInternalFrame();
			telaDeMensagem.setSize(800,600);
			telaDeMensagem.setResizable(false);
			telaDeMensagem.setVisible(true);
			
			JLabel vcd = new JLabel(vencedor);
			vcd.setBounds(100,100,100,100);
			
			JButton denovo = new JButton("Recome�ar?");
			denovo.setBounds(200,200,100,100);
			denovo.addActionListener(this);
			denovo.setActionCommand("reset");
			
			Container ctn = getContentPane();
			ctn.removeAll();
			ctn.add(telaDeMensagem);
			ctn.add(vcd);
			ctn.add(denovo);
		}
	}

}
