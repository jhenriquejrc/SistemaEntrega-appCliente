package com.joaohenrique.entregas.fornecedores.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.joaohenrique.entregas.fornecedores.modelo.Fornecedor;
import com.joaohenrique.entregas.fornecedores.view.ListaTodosAdaptador;
import com.joaohenrique.entregas.fornecedores.webservice.FornecedorService;
import com.joaohenrique.entregas.fornecedores.webservice.Servico;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import com.joaohenrique.entregas.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaTodos extends AppCompatActivity {

    Context contexto = this;
    ListView listView;
    List<Fornecedor> dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listatodos);
        listView = (ListView) findViewById(R.id.minha_lista);
        consultaServidor();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle("Opções");
        menu.add(0, dados.get(info.position).getCodFornecedor(), dados.get(info.position).getCodFornecedor(), "Excluir");
        menu.add(0, dados.get(info.position).getCodFornecedor(), dados.get(info.position).getCodFornecedor(), "Alterar");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "Excluir") {
            excluir(item.getItemId());
        } else if (item.getTitle() == "Alterar") {
            Intent i = new Intent(this,CriarFornecedor.class);
            i.putExtra("id",item.getItemId());
            startActivity(i);
        } else {
            return false;
        }
        return true;
    }

    public void consultaServidor() {

        FornecedorService servico = Servico.criarServico(FornecedorService.class);
        Call<List<Fornecedor>> chamada = servico.todos();

        chamada.enqueue(new Callback<List<Fornecedor>>() {
            @Override
            public void onResponse(Call<List<Fornecedor>> chamada, Response<List<Fornecedor>> resposta) {
                if (resposta.isSuccessful()) {
                    dados = resposta.body();
                    //verifica aqui se o corpo da resposta não é nulo
                    if (dados != null) {
                        final ListaTodosAdaptador adaptador = new ListaTodosAdaptador(contexto, dados);
                        listView.setAdapter(adaptador);
                        registerForContextMenu(listView);
                    } else {
                        Toast.makeText(getApplicationContext(), "Resposta nula do servidor", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Resposta não foi sucesso", Toast.LENGTH_SHORT).show();
                    // segura os erros de requisição
                    ResponseBody errorBody = resposta.errorBody();
                }
            }

            @Override
            public void onFailure(Call<List<Fornecedor>> chamada, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
                Log.d("debug", t.getMessage());
            }
        });
    }

    public void excluir(int id) {

        FornecedorService servico = Servico.criarServico(FornecedorService.class);
        Call<Void> chamada = servico.deletar(id);

        chamada.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> chamada, Response<Void> resposta) {
                if (resposta.isSuccessful()) {
                    consultaServidor();
                } else {
                    try {
                        JSONObject json = new JSONObject((resposta.errorBody()).string());
                        Toast.makeText(getApplicationContext(), "Erro: "+ (String)json.get("descricao"), Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<Void> chamada, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
                Log.d("debug", t.getMessage());
            }
        });
    }
}
