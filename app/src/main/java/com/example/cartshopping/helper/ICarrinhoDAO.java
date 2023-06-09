package com.example.cartshopping.helper;

import com.example.cartshopping.model.Carrinho;

import java.util.List;

public interface ICarrinhoDAO {

    public boolean salvar(Carrinho carrinho);
    public boolean atualizar(Carrinho carrinho);
    public boolean deletar(Carrinho carrinho);
    public List<Carrinho> listar();
}
