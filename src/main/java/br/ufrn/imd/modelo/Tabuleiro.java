package br.ufrn.imd.modelo;

/** Classe que serve para representar o tabuleiro do jogo, guardando as pe�as ao longo do jogo
 * 
 * @author Lucas
 * @version 1.0
 *
 */
public class Tabuleiro {
    private Peca[][] campo = new Peca[8][8];

    /** M�todo que retorna o valor do atributo campo
     * 
     * @return Peca[][] - Uma matriz do tipo Peca
     */
    public Peca[][] getCampo() {
        return campo;
    }

    /** M�todo que atribui uma matriz ao atributo campo
     * 
     * @param campo Peca[][] - Matriz do tipo Peca
     */
    public void setCampo(Peca[][] campo) {
        this.campo = campo;
    }
}
