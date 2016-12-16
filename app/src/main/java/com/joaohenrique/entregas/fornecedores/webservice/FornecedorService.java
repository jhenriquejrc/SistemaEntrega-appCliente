package com.joaohenrique.entregas.fornecedores.webservice;



import java.util.List;
import com.joaohenrique.entregas.fornecedores.modelo.Fornecedor;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface FornecedorService {

    @GET("fornecedores")
    Call<List<Fornecedor>> todos();

    @GET("fornecedores/{id}")
    Call<Fornecedor> getFornecedor(@Path("id") int id);

    @POST("fornecedores")
    Call<Fornecedor> criar(@Body Fornecedor fornecedor);

    @DELETE("fornecedores/{id}")
    Call<Void> deletar(@Path("id") int id);

    @PUT("fornecedores/{id}")
    Call<Void> atualizar(@Path("id") int id, @Body Fornecedor fornecedor);



}
