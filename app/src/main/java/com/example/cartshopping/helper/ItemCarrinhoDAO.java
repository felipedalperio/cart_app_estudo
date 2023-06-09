package com.example.cartshopping.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cartshopping.model.ItemCarrinho;
import com.example.cartshopping.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ItemCarrinhoDAO implements IItemCarrinhoDAO{

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public ItemCarrinhoDAO(Context context) {
        DbHelper db = new DbHelper(context);

        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(ItemCarrinho itemCarrinho) {
        ContentValues cv = new ContentValues();
        cv.put("idCarrinho", itemCarrinho.getCarrinhoId());
        cv.put("idProduto", itemCarrinho.getProdutoId());
        cv.put("quantidade", itemCarrinho.getQuantidade());

        String whereClause = "idProduto = ?";
        String[] whereArgs = {String.valueOf(itemCarrinho.getProdutoId())};

        try {
            int rowsAffected = escreve.update(DbHelper.TABELA_ITEMS_CARRINHO, cv, whereClause, whereArgs);
            if (rowsAffected == 0) {
                escreve.insert(DbHelper.TABELA_ITEMS_CARRINHO, null, cv);
            }
        } catch (Exception e) {
            Log.e("INFO", "Erro ao salvar " + e.getMessage());
            return false;
        }

        return true;
    }


    @Override
    public boolean atualizar(ItemCarrinho itemCarrinho) {
        return false;
    }

    @Override
    public boolean deletar(ItemCarrinho itemCarrinho) {
        String[] args = {itemCarrinho.getId().toString()};
        try {
            int linhasAfetadas = escreve.delete(DbHelper.TABELA_ITEMS_CARRINHO, "id=?", args);
            Log.d("INFO", "Linhas afetadas: " + linhasAfetadas);
            Log.d("INFO", "id: " + itemCarrinho.getId().toString());
            if (linhasAfetadas > 0) {
                Log.d("INFO", "Item removido com sucesso");
                return true;
            } else {
                Log.d("INFO", "Nenhum item removido");
                return false;
            }
        } catch (Exception e) {
            Log.e("INFO", "Erro ao remover item: " + e.getMessage());
            return false;
        }
    }


    @Override
    public List<ItemCarrinho> listar() {
        List<ItemCarrinho> itemCarrinhos = new ArrayList<>();

        String sql = "SELECT ic.*, c.idCliente, p.* FROM " + DbHelper.TABELA_ITEMS_CARRINHO + " ic " +
                "INNER JOIN Carrinho c ON c.id = ic.idCarrinho " +
                "INNER JOIN Produtos p ON p.id = ic.idProduto " +
                "WHERE c.idCliente = 1;";


        Cursor c = le.rawQuery(sql, null);
        while (c.moveToNext()){

            ItemCarrinho itemCarrinho = new ItemCarrinho();

            int itemId = c.getInt(c.getColumnIndex("id"));
            int carrinhoId = c.getInt(c.getColumnIndex("idCarrinho"));
            int produtoId = c.getInt(c.getColumnIndex("idProduto"));
            int quantidade = c.getInt(c.getColumnIndex("quantidade"));
            double precoProduto = c.getDouble(c.getColumnIndex("preco"));
            String descProduto = c.getString(c.getColumnIndex("description"));
            String nomeProduto = c.getString(c.getColumnIndex("nome"));

            Produto produto = new Produto(produtoId, precoProduto, descProduto, nomeProduto );

            itemCarrinho.setCarrinhoId(carrinhoId);
            itemCarrinho.setId(itemId);
            itemCarrinho.setQuantidade(quantidade);
            itemCarrinho.setProduto(produto);

            itemCarrinhos.add(itemCarrinho);
        }
        return itemCarrinhos;
    }

}
