package khairy.com.souqtask.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import khairy.com.souqtask.R;
import khairy.com.souqtask.adapter.MainAdapter;
import khairy.com.souqtask.data.main.Category;
import khairy.com.souqtask.retro.dataFetcher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainView extends Fragment implements MainAdapter.SelectedCategory{

    public static final String BACK_STACK_ROOT_TAG = "root_fragment";
    private final String URL = "http://souq.hardtask.co/app/app.asmx/";
    private static Retrofit retrofit;
    private List<Category> categories_List;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private MainAdapter adapter;
    private GridLayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getMainPageData();
        recyclerView = (RecyclerView) inflater.inflate(R.layout.main_view, container, false);
        ButterKnife.bind(this , recyclerView);
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        return recyclerView;
    }

    public void getMainPageData(){
        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        dataFetcher apiFetcher = retrofit.create(dataFetcher.class);
        Call<List<Category>> call = apiFetcher.getCategories();

        call.enqueue(new Callback<List<Category>>() {

            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                categories_List = response.body();
                set_view();
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(getContext() , t.getLocalizedMessage() , Toast.LENGTH_LONG).show();
                Log.d("MAIN" , t.getMessage() + "\n" + t.getLocalizedMessage());
                t.getStackTrace();
            }
        });
    }

    public  RecyclerView set_view (){
        layoutManager = new GridLayoutManager(getContext() , 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MainAdapter(getContext() , categories_List , this);
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }

    @Override
    public void selected_handler(int position) {
        if (!categories_List.get(position).getSubCategories().isEmpty()) {
            SubView subFragment = new SubView();
            Fragment fragment = subFragment.subcategory(categories_List.get(position));
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framContainer, fragment, SubView.class.getSimpleName())
                    .addToBackStack(BACK_STACK_ROOT_TAG)
                    .commit();
        }
    }
}
