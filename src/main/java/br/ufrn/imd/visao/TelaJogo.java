package br.ufrn.imd.visao;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import java.awt.GridLayout;
import javax.swing.JButton;

public class TelaJogo extends JInternalFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
    private JButton[][] quadrados = new JButton[8][8];

	public static void main(String[] args) {
		TelaJogo tela = new TelaJogo();
		tela.setVisible(true);
	}

	public TelaJogo() {
		setBounds(100, 100, 400, 200);
		getContentPane().setLayout(new GridLayout(8, 9, 0, 0));

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		/*if (e.getSource() == algo){
			
		}
		else if(e.getSource() == outro){
			
		}*/
	}

}
