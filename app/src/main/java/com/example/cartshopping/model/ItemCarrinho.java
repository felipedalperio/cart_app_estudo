package com.example.cartshopping.model;

import java.util.List;

public class ItemCarrinho {
    private Integer id;
    private Integer carrinhoId;
    private Integer produtoId;
    private int quantidade;

    private Produto produto;


    public ItemCarrinho() {
    }

    public ItemCarrinho(Integer id, Integer carrinhoId, Integer produtoId, int quantidade) {
        this.id = id;
        this.carrinhoId = carrinhoId;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }


    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCarrinhoId() {
        return carrinhoId;
    }

    public void setCarrinhoId(Integer carrinhoId) {
        this.carrinhoId = carrinhoId;
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
