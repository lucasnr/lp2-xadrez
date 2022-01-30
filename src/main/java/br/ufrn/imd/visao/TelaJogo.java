package br.ufrn.imd.visao;

import br.ufrn.imd.controle.Jogo;
import br.ufrn.imd.modelo.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;

/** Classe que exibe o jogo atualizando a depender dos comandos dados a ela pelo usuário
 * 
 * @author Brayan
 * @author Lucas
 * @version 1.0
 * 
 */
public class TelaJogo extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

    private JButton[][] quadrados = new JButton[8][8];

	private Jogo jogo;
	private Peca selecionada;

	public static void main(String[] args) {
		TelaJogo tela = new TelaJogo();
		tela.setVisible(true);
	}

	/** Construtor da classe TelaJogo que cria um objeto e inicia um jogo para esse objeto
	 * 
	 */
	public TelaJogo() {
		this.iniciarJogo();

		setSize(800,600);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Jogo de Xadrez");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/** Método que inicia um jogo e associa ele ao atributo jogo de TelaJogo iniciando o tabuleiro
	 * 
	 */
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
	
	/** Método que detecta cliques de mouses agindo de acordo com o local onde foi clicado em referência ao tabuleiro do jogo.
	 * Se o quadrado for marcado como a "moveplate" de alguma peça, ele moverá a peça para o quadrado clicado como "moveplate"
	 * da peça, se não, se clicar na peça para se mover, os "moveplates" somem permitindo que se poça clicar em outra peça para
	 * ver suas possibilidades de movimento desde que a peça clicada for a peça da vez e não faz nada se não for ou se o quadrado
	 * estiver vazio e por fim, ao se mover uma peça ou cancelar a seleção de uma peça ele atualiza o tabuleiro usando o método
	 * atualizarTabuleiro.
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String[] command = e.getActionCommand().split("-");
		int linha = Integer.parseInt(command[0]);
		int coluna = Integer.parseInt(command[1]);

		Tabuleiro tabuleiro = this.jogo.getTabuleiro();

		if (selecionada == null) {
			Peca peca = tabuleiro.getCampo()[linha][coluna];
			List<Posicao> posicoes = this.jogo.informarPossiveisJogadas(peca);
			// NÃ£o hÃ¡ possiveis movimentos
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

			// Cancelar seleÃ§Ã£o
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

	/** Método para atualizar o tabuleiro visualmente botando as imagens das peças e pintando os quadrados de preto
	 * e branco a depender da soma de suas posições, anunciar um xeque ao jogador pintando de vermelho o quadrado 
	 * do rei em perigo e declarar o fim de jogo ao detectar game-over do jogo
	 * 
	 */
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

	/** Método responsável por criar um pop-up com a indicação da cor das peças vencedoras ao final do jogo
	 * assim como criar um botão que possibilita um novo jogo criando um outro objeto jogo e associando-o ao
	 * objeto da classe TelaJogo através do método iniciarJogo
	 * 
	 */
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
