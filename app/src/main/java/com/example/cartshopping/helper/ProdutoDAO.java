package com.example.cartshopping.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.cartshopping.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements IProdutoDAO{

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public ProdutoDAO(Context context) {
        DbHelper db = new DbHelper(context);

        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Produto produto) {
        ContentValues cv = new ContentValues();
        cv.put("nome", produto.getNome());
        cv.put("description", produto.getDesc());
        cv.put("preco", produto.getPreco());
        try {
            escreve.insert(DbHelper.TABELA_PRODUTO, null, cv);
        }catch (Exception e){
            Log.e("INFO", "Erro ao salvar " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Produto produto) {
        return false;
    }

    @Override
    public boolean deletar(Produto produto) {
        return false;
    }

    @Override
    public List<Produto> listar() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM " +DbHelper.TABELA_PRODUTO+ " ;";

        Cursor c = le.rawQuery(sql, null);

        while (c.moveToNext()){
            Produto produto = new Produto();

            int id = c.getInt(c.getColumnIndex("id"));
            String nome = c.getString(c.getColumnIndex("nome"));
            String desc = c.getString(c.getColumnIndex("description"));
            Double preco = c.getDouble(c.getColumnIndex("preco"));

            produto.setId(id);
            produto.setDesc(desc);
            produto.setPreco(preco);
            produto.setNome(nome);

            produtos.add(produto);
        }

        return produtos;
    }
}
