package com.example.trabalho3.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.trabalho3.R;
import com.example.trabalho3.adapter.ProdutoAdapter;
import com.example.trabalho3.data.Produto;
import com.example.trabalho3.data.ProdutoDAO;
import com.example.trabalho3.dialog.DeleteDialog;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, DeleteDialog.OnDeleteListener {

    private ListView lista;
    private ProdutoAdapter adapter;
    private ProdutoDAO produtoDAO;
    private static final int REQ_EDIT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = findViewById(R.id.lista);

        adapter = new ProdutoAdapter(this);

        lista.setAdapter(adapter);

        lista.setOnItemClickListener(this);
        lista.setOnItemLongClickListener(this);

        produtoDAO = ProdutoDAO.getInstance(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            Intent intent = new Intent(getApplicationContext(), EditarProdutoActivity.class);
            startActivityForResult(intent, REQ_EDIT);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_EDIT && resultCode == RESULT_OK) {
            updateList();
        }
    }

    private void updateList() {
        List<Produto> produtos = produtoDAO.list();
        adapter.setItems(produtos);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getApplicationContext(), EditarProdutoActivity.class);
        intent.putExtra("produto", adapter.getItem(i));
        startActivityForResult(intent, REQ_EDIT);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        Produto produto = adapter.getItem(i);

        DeleteDialog dialog = new DeleteDialog();
        dialog.setProduto(produto);
        dialog.show(getSupportFragmentManager(), "deleteDialog");
        return true;
    }

    @Override
    public void onDelete(Produto produto) {
        produtoDAO.delete(produto);
        updateList();
    }
}