package br.ufrn.imd.modelo;

import java.util.Objects;

public class Posicao {
    private Integer linha;
    private Integer coluna;

    public Posicao() {
    }

    public Posicao(Integer linha, Integer coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public Integer getLinha() {
        return linha;
    }

    public void setLinha(Integer linha) {
        this.linha = linha;
    }

    public Integer getColuna() {
        return coluna;
    }

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
