package com.example.cartshopping.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartshopping.R;
import com.example.cartshopping.helper.ItemCarrinhoDAO;
import com.example.cartshopping.model.ItemCarrinho;
import com.example.cartshopping.model.Produto;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.MyViewHolder> {

    private List<Produto> listaProdutos;
    private Context context;

    public ProdutoAdapter(List<Produto> listaProdutos,Context context) {
        this.listaProdutos = listaProdutos;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.produtos_adapter, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int produtoId = listaProdutos.get(position).getId();

        holder.nome.setText(listaProdutos.get(position).getNome());
        holder.desc.setText(listaProdutos.get(position).getDesc());
        holder.preco.setText(listaProdutos.get(position).getPreco().toString());

        QuantidadeTextWatcher quantidadeTextWatcher = new QuantidadeTextWatcher(holder);
        holder.qnt.addTextChangedListener(quantidadeTextWatcher);

        holder.buttonCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantidade = quantidadeTextWatcher.getQuantidade();

                ItemCarrinho itemCarrinho = new ItemCarrinho();
                itemCarrinho.setProduto(listaProdutos.get(position));
                itemCarrinho.setQuantidade(quantidade);
                itemCarrinho.setProdutoId(produtoId);
                itemCarrinho.setCarrinhoId(1);

                ItemCarrinhoDAO itemCarrinhoDAO = new ItemCarrinhoDAO(context);
                itemCarrinhoDAO.salvar(itemCarrinho);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listaProdutos.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nome, desc, preco;
        EditText qnt;
        Button buttonCart;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            qnt =  itemView.findViewById(R.id.editTextQnt);
            preco =  itemView.findViewById(R.id.textViewPreco);
            desc =  itemView.findViewById(R.id.textViewDesc);
            nome =  itemView.findViewById(R.id.textViewNome);
            buttonCart =  itemView.findViewById(R.id.buttonCart);
        }
    }

    private class QuantidadeTextWatcher implements TextWatcher {
        private MyViewHolder viewHolder;
        private String quantidade;

        public QuantidadeTextWatcher(MyViewHolder viewHolder) {
            this.viewHolder = viewHolder;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // Não é necessário fazer nada aqui
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // Atualizar o valor da quantidade quando o texto for alterado
            quantidade = charSequence.toString();
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // Não é necessário fazer nada aqui
        }

        public int getQuantidade() {
            return quantidade != null ? Integer.parseInt(quantidade) : 1;
        }
    }
}
