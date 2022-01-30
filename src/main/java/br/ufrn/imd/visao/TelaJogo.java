package br.ufrn.imd.visao;

import br.ufrn.imd.controle.Jogo;
import br.ufrn.imd.modelo.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;

/** Classe que exibe o jogo atualizando a depender dos comandos dados a ela pelo usu�rio
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

	/** M�todo que inicia um jogo e associa ele ao atributo jogo de TelaJogo iniciando o tabuleiro
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
	
	/** M�todo que detecta cliques de mouses agindo de acordo com o local onde foi clicado em refer�ncia ao tabuleiro do jogo.
	 * Se o quadrado for marcado como a "moveplate" de alguma pe�a, ele mover� a pe�a para o quadrado clicado como "moveplate"
	 * da pe�a, se n�o, se clicar na pe�a para se mover, os "moveplates" somem permitindo que se po�a clicar em outra pe�a para
	 * ver suas possibilidades de movimento desde que a pe�a clicada for a pe�a da vez e n�o faz nada se n�o for ou se o quadrado
	 * estiver vazio e por fim, ao se mover uma pe�a ou cancelar a sele��o de uma pe�a ele atualiza o tabuleiro usando o m�todo
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

	/** M�todo para atualizar o tabuleiro visualmente botando as imagens das pe�as e pintando os quadrados de preto
	 * e branco a depender da soma de suas posi��es, anunciar um xeque ao jogador pintando de vermelho o quadrado 
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

	/** M�todo respons�vel por criar um pop-up com a indica��o da cor das pe�as vencedoras ao final do jogo
	 * assim como criar um bot�o que possibilita um novo jogo criando um outro objeto jogo e associando-o ao
	 * objeto da classe TelaJogo atrav�s do m�todo iniciarJogo
	 * 
	 */
	private void informarFimDeJogo() {
		String vencedor = "Vit�ria das pe�as ";

		if(this.jogo.isVezDasBrancas()) {
			vencedor += "pretas.";
		} else {
			vencedor += "brancas.";
		}

		String[] opcoes = {"Recome�ar"};
		int recomecar = JOptionPane.showOptionDialog(null, vencedor, "Fim de Jogo",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
		if (recomecar == 0) {
			this.iniciarJogo();
		}
	}

}
