package br.com.steven.alcoolougasolina.model;

public class Combustivel {

    private int id;
    private String nomeCombustivel;
    private double precoCombustivel;
    private String recomendacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCombustivel() {
        return nomeCombustivel;
    }

    public void setNomeCombustivel(String nomeCombustivel) {
        this.nomeCombustivel = nomeCombustivel;
    }

    public double getPrecoCombustivel() {
        return precoCombustivel;
    }

    public void setPrecoCombustivel(double precoCombustivel) {
        this.precoCombustivel = precoCombustivel;
    }

    public String getRecomendacao() {
        return recomendacao;
    }

    public void setRecomendacao(String recomendacao) {
        this.recomendacao = recomendacao;
    }

}
