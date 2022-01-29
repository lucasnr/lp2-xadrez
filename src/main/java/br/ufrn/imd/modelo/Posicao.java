package br.ufrn.imd.modelo;

import java.util.Objects;

/** Classe que serve para guardar representa��es de posi��es no tabuleiro
 * 
 * @author Lucas
 * @version 1.0
 *
 */
public class Posicao {
    private Integer linha;
    private Integer coluna;

    /** Construtor vazio da classe Posicao
     * 
     */
    public Posicao() {
    }

    /** Construtor da classe Posicao que recebe dois Integers linha e coluna, e cria um objeto com os atributos passados
     * 
     * @param linha Integer - Integer representando a linha
     * @param coluna Integer - Integer representando a coluna
     */
    public Posicao(Integer linha, Integer coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    /** M�todo que retorna o valor do atributo linha
     * 
     * @return Integer - Representa��o da linha
     */
    public Integer getLinha() {
        return linha;
    }

    /** M�todo que determina o valor do atributo linha
     * 
     * @param linha Integer - Representa��o da linha
     */
    public void setLinha(Integer linha) {
        this.linha = linha;
    }

    /** M�todo que retorna o valor do atributo coluna
     * 
     * @return Integer - Representa��o da coluna
     */
    public Integer getColuna() {
        return coluna;
    }

    /** M�todo que determina o valor do atributo coluna
     * 
     * @param coluna Integer - Representa��o da coluna
     */
    public void setColuna(Integer coluna) {
        this.coluna = coluna;
    }

    @Override
    public String toString() {
        return "Posicao{" +
                "linha=" + linha +
                ", coluna=" + coluna +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Posicao posicao = (Posicao) o;
        return Objects.equals(linha, posicao.linha) && Objects.equals(coluna, posicao.coluna);
    }

    @Override
    public int hashCode() {
        return Objects.hash(linha, coluna);
    }
}
