package br.ufrn.imd.visao;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.plaf.synth.ColorType;

import br.ufrn.imd.controle.Jogo;
import br.ufrn.imd.modelo.CorDaPeca;
import br.ufrn.imd.modelo.Peca;
import br.ufrn.imd.modelo.Posicao;

public class TelaJogo extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private static Jogo jogo;
	
    private JButton[][] quadrados = new JButton[8][8];

	public static void main(String[] args) {
		Jogo j = new Jogo();
		jogo = j;
		TelaJogo tela = new TelaJogo();
		tela.setVisible(true);
	}

	public TelaJogo() {
		
		Container ctn = getContentPane();
		ctn.setLayout(new GridLayout(8, 9, 0, 0));
		
		for(int a = 0; a < 8; a++) {
			for(int b = 0; b < 8; b++) {
				String act = ""+a+""+b+"000";
				quadrados[a][b] = new JButton("");
				quadrados[a][b].addActionListener(this);
				quadrados[a][b].setActionCommand(act);
				if((a + b) % 2 == 0) {
					quadrados[a][b].setBackground(Color.WHITE);
				} else {
					quadrados[a][b].setBackground(Color.BLACK);
				}
			}
		}
		
		for(int a = 0; a < 8; a++) {
			for(int b = 0; b < 8; b++) {
				if(jogo.getTabuleiro().getCampo()[a][b] != null) {
					quadrados[a][b].setIcon(new ImageIcon(jogo.getTabuleiro().getCampo()[a][b].getImagem()));
				}
			}
		}
		
		for(int a = 0; a < 8; a++) {
			for(int b = 0; b < 8; b++) {
				ctn.add(quadrados[a][b]);
			}
		}
		
		setSize(800,600);
		setResizable(false);
		setTitle("Jogo de xadrez");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int linha = Character.getNumericValue(e.getActionCommand().charAt(0));
		int coluna = Character.getNumericValue(e.getActionCommand().charAt(1));
		int moveplace = Character.getNumericValue(e.getActionCommand().charAt(2));
		
		Peca quadClic = jogo.getTabuleiro().getCampo()[linha][coluna];
		
		CorDaPeca corDaVez = CorDaPeca.PRETA;
		if(jogo.getTabuleiro().isVezDasBrancas()) {
			corDaVez = CorDaPeca.BRANCA;
		}
		
		if(moveplace == 1) {
			int lp = Character.getNumericValue(e.getActionCommand().charAt(3));
			int cp = Character.getNumericValue(e.getActionCommand().charAt(4));
			Peca pecam = jogo.getTabuleiro().getCampo()[lp][cp];
			jogo.moverPeca(pecam, new Posicao(lp, cp));
			
			//limpar campo dos moveplates
			for(int a = 0; a < 8; a++) {
				for(int b = 0; b < 8; b++) {
					if(quadrados[a][b].getActionCommand().charAt(2) == 1) {
						String normalAct = ""+a+b+"000";
						quadrados[a][b].setActionCommand(normalAct);
						//quadrados[a][b].setBorder((new LineBorder(Color.BLACK)));
						quadrados[a][b].setBorder((UIManager.getBorder("Button.border")));
					}
				}
			}
		} else if(quadClic != null) {
			if(quadClic.getCor() == corDaVez) {
				List<Posicao> jogadas = quadClic.informarPossiveisJogadas(jogo.getTabuleiro());
				for(int x = 0; x < jogadas.size(); x++) {
					quadrados[jogadas.get(x).getLinha()][jogadas.get(x).getColuna()].setBorder((new LineBorder(Color.BLUE)));
				}
			}
		}
	}

}
