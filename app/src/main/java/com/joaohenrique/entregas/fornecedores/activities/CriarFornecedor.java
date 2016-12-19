package com.joaohenrique.entregas.fornecedores.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.joaohenrique.entregas.R;
import com.joaohenrique.entregas.fornecedores.modelo.Fornecedor;
import com.joaohenrique.entregas.fornecedores.webservice.FornecedorService;
import com.joaohenrique.entregas.fornecedores.webservice.Servico;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CriarFornecedor extends AppCompatActivity {

    Context contexto = this;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_fornecedor);
        id = getIntent().getIntExtra("id", -1);
        if (id != -1){
            setTitle("Editar Fornecedor");
            preencherForm();
        }
    }


    public void cadastrar(View v) {

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setCnpj(((EditText) findViewById(R.id.cnpj)).getText().toString());
        fornecedor.setDataCadastro(((EditText) findViewById(R.id.dataCadastro)).getText().toString());
        fornecedor.setFaturamentoAnual(new BigDecimal(((EditText) findViewById(R.id.faturamentoAnual)).getText().toString()));
        fornecedor.setNome(((EditText) findViewById(R.id.nome)).getText().toString());
        fornecedor.setEmail(((EditText) findViewById(R.id.email)).getText().toString());
        fornecedor.setFone(((EditText) findViewById(R.id.fone)).getText().toString());
        if (id == -1)
            criar(fornecedor);
        else
            atualizar(fornecedor);
    }

    private void atualizar(Fornecedor fornecedor) {
        setTitle("Editar Fornecedor");
        FornecedorService servico = Servico.criarServico(FornecedorService.class);
        Call<Void> chamada = servico.atualizar(id,fornecedor);
        chamada.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 204) {
                    Toast.makeText(contexto, "Fornecedor atualizado", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(contexto, ListaTodos.class);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    private void criar(final Fornecedor fornecedor) {

        FornecedorService servico = Servico.criarServico(FornecedorService.class);
        Call<Fornecedor> chamada = servico.criar(fornecedor);
        chamada.enqueue(new Callback<Fornecedor>() {
            @Override
            public void onResponse(Call<Fornecedor> call, Response<Fornecedor> response) {
                // Toast.makeText(contexto,"Código da resposta: " + response.code(),Toast.LENGTH_LONG).show();
                if (response.code() == 201) {
                    Toast.makeText(contexto, "Fornecedor criado", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(contexto, ListaTodos.class);
                    startActivity(i);
                } else if (response.code() == 400) {
                    response.body();
                    Toast.makeText(contexto,"Fornecedor com o mesmo nome cadastrado", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Fornecedor> call, Throwable t) {

            }
        });
    }

    private void preencherForm(){
        FornecedorService servico = Servico.criarServico(FornecedorService.class);
        Call<Fornecedor> chamada = servico.getFornecedor(id);

        chamada.enqueue(new Callback<Fornecedor>() {
            @Override
            public void onResponse(Call<Fornecedor> call, Response<Fornecedor> response) {
                // Toast.makeText(contexto,"Código da resposta: " + response.code(),Toast.LENGTH_LONG).show();
                if (response.isSuccessful()) {
                    Fornecedor fornecedor = response.body();


                    ((EditText) findViewById(R.id.faturamentoAnual)).setText(fornecedor.getFaturamentoAnual().toString());
                    ((EditText) findViewById(R.id.nome)).setText(fornecedor.getNome());
                    ((EditText) findViewById(R.id.cnpj)).setText(fornecedor.getCnpj());
                    ((EditText) findViewById(R.id.email)).setText(fornecedor.getEmail());
                    ((EditText) findViewById(R.id.fone)).setText(fornecedor.getEmail());

                    ((EditText) findViewById(R.id.dataCadastro)).setText(fornecedor.getDataCadastro());
                }
            }

            @Override
            public void onFailure(Call<Fornecedor> call, Throwable t) {

            }
        });
    }
}
