package com.example.trabalho3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.trabalho3.R;
import com.example.trabalho3.data.Produto;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProdutoAdapter extends BaseAdapter {
    private Context context;
    private static final NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("PT","BR"));
    private List<Produto> produtos = new ArrayList<>();

    public ProdutoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return produtos.size();
    }

    @Override
    public Produto getItem(int i) {
        return produtos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return produtos.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_listprodutos, viewGroup, false);

        TextView txtNome = v.findViewById(R.id.txt_nome);
        TextView txtValor = v.findViewById(R.id.txt_valor);

        Produto produto = produtos.get(i);
        txtNome.setText(produto.getNome());
        txtValor.setText(nf.format(produto.getValor()));

        return v;
    }

    public void setItems(List<Produto> produtos) {
        this.produtos = produtos;
        notifyDataSetChanged();
    }
}