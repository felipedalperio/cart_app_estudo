package com.example.cartshopping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartshopping.R;
import com.example.cartshopping.model.ItemCarrinho;
import com.example.cartshopping.model.Produto;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class CarrinhoAdapter extends RecyclerView.Adapter<CarrinhoAdapter.MyViewHolder> {

    private List<ItemCarrinho> listaItemCarrinho;
    private Context context;

    public CarrinhoAdapter(List<ItemCarrinho> listaItemCarrinho) {
        this.listaItemCarrinho = listaItemCarrinho;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.carrinho_adapter, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String quant = listaItemCarrinho.get(position).getQuantidade() + "";

        holder.nome.setText(listaItemCarrinho.get(position).getProduto().getNome());
        holder.qnt.setText(quant);
    }

    @Override
    public int getItemCount() {
        return listaItemCarrinho.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nome, qnt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            qnt =  itemView.findViewById(R.id.textViewQuantdCart);
            nome =  itemView.findViewById(R.id.textViewProdutoCart);
        }
    }
}
