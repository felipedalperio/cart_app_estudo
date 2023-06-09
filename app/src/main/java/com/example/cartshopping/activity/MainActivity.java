package com.example.cartshopping.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.cartshopping.R;
import com.example.cartshopping.adapter.ProdutoAdapter;
import com.example.cartshopping.helper.ProdutoDAO;
import com.example.cartshopping.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewProdutos;
    private List<Produto> listaProdutos = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewProdutos = findViewById(R.id.recyclerViewProdutos);


        ProdutoAdapter produtoAdapter = new ProdutoAdapter(listaProdutos, getApplicationContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewProdutos.setLayoutManager(layoutManager);
        recyclerViewProdutos.setHasFixedSize(true);
        recyclerViewProdutos.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        CarregarListaProdutos();
        recyclerViewProdutos.setAdapter(produtoAdapter);


    }

    private void CarregarListaProdutos(){
        ProdutoDAO produtoDAO = new ProdutoDAO(getApplicationContext());
        listaProdutos.addAll(produtoDAO.listar());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.carrinho:
                Intent intent = new Intent(getApplicationContext(),CarrinhoActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}