package com.example.cartshopping.model;

public class Produto {
    private Integer id;
    private Double preco;
    private String desc;
    private String nome;

    public Produto() {
    }

    public Produto(Integer id, Double preco, String desc, String nome) {
        this.id = id;
        this.preco = preco;
        this.desc = desc;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
