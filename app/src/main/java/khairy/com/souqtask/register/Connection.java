package khairy.com.souqtask.register;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import khairy.com.souqtask.R;
import khairy.com.souqtask.data.main.City;
import khairy.com.souqtask.data.main.Country;
import khairy.com.souqtask.retro.dataFetcher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressLint("ValidFragment")
public class Connection extends Fragment {
    private final String URL = "http://souq.hardtask.co/app/app.asmx/";
    private static Retrofit retrofit;
    private List<Country> Country_List;
    private List<City> City_List;
    private FragmentManager fragmentManager;
    private ListedDate listedData;

    public Connection(FragmentManager fManager) {
        this.fragmentManager = fManager;
        getCountry();
    }



    private void getCountry(){
        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        dataFetcher apiFetcher = retrofit.create(dataFetcher.class);
        Call<List<Country>> call = apiFetcher.getCountry();

        call.enqueue(new Callback<List<Country>>() {

            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                Country_List = response.body();
                getCity();
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {

                Log.d("MAIN" , t.getMessage() + "\n" + t.getLocalizedMessage());
                t.getStackTrace();
            }
        });
    }

    private void getCity(){
        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        dataFetcher apiFetcher = retrofit.create(dataFetcher.class);
        Call<List<City>> call = apiFetcher.getCity();

        call.enqueue(new Callback<List<City>>() {

            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                City_List = response.body();
                if (!Country_List.isEmpty() && !City_List.isEmpty()) {
                    listedData = new ListedDate(Country_List , City_List );
                    StartFragment(listedData);
                }

            }
                @Override
                public void onFailure(Call<List<City>> call, Throwable t) {

                    Log.d("MAIN" , t.getMessage() + "\n" + t.getLocalizedMessage());
                    t.getStackTrace();
                }
            });

    }

    private void StartFragment(ListedDate list){
        RegisterFrag register = new RegisterFrag();
        Fragment registerView = register.registerFrag(list);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.framContainer, registerView, RegisterFrag.class.getSimpleName());
        fragmentTransaction.commit();


    }


}