package com.example.cartshopping.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.cartshopping.R;
import com.example.cartshopping.RecyclerItemClickListener;
import com.example.cartshopping.adapter.CarrinhoAdapter;
import com.example.cartshopping.helper.CarrinhoDAO;
import com.example.cartshopping.helper.ItemCarrinhoDAO;
import com.example.cartshopping.model.Carrinho;
import com.example.cartshopping.model.ItemCarrinho;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoActivity extends AppCompatActivity {
    private RecyclerView recyclerViewCarrinho;
    private List<ItemCarrinho> listaCarrinho = new ArrayList<>();
    private CarrinhoAdapter carrinhoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        recyclerViewCarrinho = findViewById(R.id.recyclerViewCarrinho);


        carrinhoAdapter = new CarrinhoAdapter(listaCarrinho);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewCarrinho.setLayoutManager(layoutManager);
        recyclerViewCarrinho.setHasFixedSize(true);
        recyclerViewCarrinho.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        recyclerViewCarrinho.setAdapter(carrinhoAdapter);
        CarregarListaCarrinho();

        recyclerViewCarrinho.addOnItemTouchListener(new RecyclerItemClickListener(
                getApplicationContext(),
                recyclerViewCarrinho,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        ItemCarrinho itemCarrinho = listaCarrinho.get(position);
                        ItemCarrinhoDAO itemCarrinhoDAO = new ItemCarrinhoDAO(getApplicationContext());

                        if (itemCarrinhoDAO.deletar(itemCarrinho)) {
                            Toast.makeText(CarrinhoActivity.this, "Item removido: " + itemCarrinho.getProduto().getNome(), Toast.LENGTH_SHORT).show();
                            CarregarListaCarrinho();
                        } else {
                            Toast.makeText(CarrinhoActivity.this, "Erro ao remover o item", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                }
        ));


    }

    private void CarregarListaCarrinho(){
        listaCarrinho.clear();
        ItemCarrinhoDAO itemCarrinhoDAO = new ItemCarrinhoDAO(getApplicationContext());
        listaCarrinho.addAll(itemCarrinhoDAO.listar());
        carrinhoAdapter.notifyDataSetChanged();


    }
}