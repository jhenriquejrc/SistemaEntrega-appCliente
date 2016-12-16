package com.joaohenrique.entregas.fornecedores.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.joaohenrique.entregas.fornecedores.modelo.Fornecedor;

import java.util.List;

import com.joaohenrique.entregas.R;

/**
 * Created by angot on 22/11/2016.
 */

public class ListaTodosAdaptador extends ArrayAdapter<Fornecedor> {

    private Context context;
    private List<Fornecedor> lista = null;

    public ListaTodosAdaptador(Context context, List<Fornecedor> lista) {
        super(context, 0, lista);
        this.lista = lista;
        this.context = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Fornecedor fornecedor = lista.get(position);

        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.listatodos2, null);

        TextView nome  = (TextView) view.findViewById(R.id.nome);
        nome.setText(fornecedor.getNome());

        TextView cnpj  = (TextView) view.findViewById(R.id.cnpj);
        cnpj.setText(fornecedor.getCnpj());

        TextView email  = (TextView) view.findViewById(R.id.email);
        email.setText(fornecedor.getEmail());

        TextView fone  = (TextView) view.findViewById(R.id.fone);
        fone.setText(fornecedor.getFone());

        TextView faturamentoAnual  = (TextView) view.findViewById(R.id.faturamentoAnual);
        faturamentoAnual.setText(fornecedor.getFaturamentoAnual()+"");

        TextView dataCadastro  = (TextView) view.findViewById(R.id.dataCadastro);
        dataCadastro.setText(fornecedor.getDataCadastro());

        return view;
    }
}
