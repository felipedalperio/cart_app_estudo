package com.example.cartshopping.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cartshopping.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoDAO implements IProdutoDAO{

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public CarrinhoDAO(Context context) {
        DbHelper db = new DbHelper(context);

        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Produto produto) {

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
        return null;
    }

}
