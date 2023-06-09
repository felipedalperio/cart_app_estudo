package com.example.cartshopping.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 2;
    public static String NOME_DB = "DB_CART";
    public static String TABELA_PRODUTO = "Produtos";
    public static String TABELA_CLIENTES = "Clientes";
    public static String TABELA_CARRINHO = "Carrinho";
    public static String TABELA_ITEMS_CARRINHO = "Items_Carrinho";

    public DbHelper(Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlProduct = "CREATE TABLE IF NOT EXISTS " + TABELA_PRODUTO
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " nome TEXT NOT NULL, " +
                " description TEXT NOT NULL, " +
                " preco DOUBLE NOT NULL )";

        String sqlClient = "CREATE TABLE IF NOT EXISTS " + TABELA_CLIENTES
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " nome TEXT NOT NULL, " +
                " email TEXT NOT NULL)";

        String sqlCarrinho = "CREATE TABLE IF NOT EXISTS " + TABELA_CARRINHO
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " idCliente INTEGER NOT NULL, " +
                " FOREIGN KEY (idCliente) REFERENCES "+ TABELA_CLIENTES +" (id));";

        String sqlItemCarrinho = "CREATE TABLE IF NOT EXISTS " + TABELA_ITEMS_CARRINHO
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " quantidade INTEGER NOT NULL, " +
                " idCarrinho INTEGER NOT NULL, " +
                " idProduto INTEGER NOT NULL, " +
                " FOREIGN KEY (idCarrinho) REFERENCES "+ TABELA_CARRINHO +" (id), " +
                " FOREIGN KEY (idProduto) REFERENCES "+ TABELA_PRODUTO +" (id));";


        try {
            db.execSQL(sqlProduct);
            db.execSQL(sqlClient);
            db.execSQL(sqlCarrinho);
            db.execSQL(sqlItemCarrinho);
            Log.i("INFO DB", "Sucesso ao criar tabela");
        }catch (Exception e){
            e.printStackTrace();
        }

        db.execSQL("INSERT INTO "+ TABELA_PRODUTO +"(id,nome,description,preco)VALUES(1,'produto1','Desc1',50)");
        db.execSQL("INSERT INTO "+ TABELA_PRODUTO +"(id,nome,description,preco)VALUES(2,'produto2','Desc2',50)");
        db.execSQL("INSERT INTO "+ TABELA_PRODUTO +"(id,nome,description,preco)VALUES(3,'produto3','Desc3',50)");

        db.execSQL("INSERT INTO "+ TABELA_CLIENTES +"(id,nome,email)VALUES(1,'cliente1','cliente1@gmail.com')");

        db.execSQL("INSERT INTO "+ TABELA_CARRINHO +"(id,idCliente)VALUES(1,1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
