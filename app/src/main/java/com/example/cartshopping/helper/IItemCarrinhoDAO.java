package com.example.cartshopping.helper;

import com.example.cartshopping.model.ItemCarrinho;

import java.util.List;

public interface IItemCarrinhoDAO {

    public boolean salvar(ItemCarrinho itemCarrinho);
    public boolean atualizar(ItemCarrinho itemCarrinho);
    public boolean deletar(ItemCarrinho itemCarrinho);
    public List<ItemCarrinho> listar();
}
