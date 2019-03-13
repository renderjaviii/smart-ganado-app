package com.app.smartganado.smart_ganado.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.Cattle;
import com.app.smartganado.smart_ganado.remote.APIService;
import com.app.smartganado.smart_ganado.remote.APIUtils;
import com.app.smartganado.smart_ganado.view.adapter.CattleAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewCattleActivity extends AppCompatActivity {
    ListView ListaGanados;
    String[][] datos = {
            {"1234", "edad", "Proposito", "Genero", "Tipo", "Raza", "Descripcion"},
            {"4321", "edad", "Proposito", "Genero", "Tipo", "Raza", "Descripcion"},
            {"2468", "edad", "Proposito", "Genero", "Tipo", "Raza", "Descripcion"},
            {"4567a", "edad", "Proposito", "Genero", "Tipo", "Raza", "Descripcion"},
    };

    int[] datosImg = {R.drawable.vaca1, R.drawable.vaca2, R.drawable.vaca3, R.drawable.vaca4};

    private APIService myApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cattle);

        ListaGanados = (ListView) findViewById(R.id.listaGanado);

        ListaGanados.setAdapter(new CattleAdapter(this, datos, datosImg));

        init();
    }

    private void init() {
        //Cattle list obtain of the server (quita esto del metodo onCreate)
        if (myApiService == null)
            myApiService = APIUtils.getAPIService();

        myApiService.getCattle("getAll", "cattle", getIntent().getIntExtra("phone", 0)).enqueue(new Callback<List<Cattle>>() {
            @Override
            public void onResponse(Call<List<Cattle>> call, Response<List<Cattle>> response) {
                if (response.isSuccessful()) //Se valida que la respuesta sea correcta
                    for (Cattle cattle : response.body())
                        Log.i("server ", cattle.toString());
                else Log.i("server", "Error on response");

            }

            @Override
            public void onFailure(Call<List<Cattle>> call, Throwable t) {
                Log.i("server", t.getMessage());
            }
        });


    }
}