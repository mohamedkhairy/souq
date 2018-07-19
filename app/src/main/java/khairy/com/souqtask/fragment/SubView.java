package khairy.com.souqtask.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import khairy.com.souqtask.R;
import khairy.com.souqtask.adapter.MainAdapter;
import khairy.com.souqtask.data.main.Category;

public class SubView  extends Fragment implements MainAdapter.SelectedCategory {

    public static final String cate_bundle = "infpData";
    public static final String save_bundle = "saveData";

    private Category category;


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private MainAdapter adapter;
    private GridLayoutManager layoutManager;



    public static SubView subcategory(Category cate) {
        SubView subView = new SubView();
        Bundle bundle = new Bundle();
        bundle.putSerializable(cate_bundle, cate);
        subView.setArguments(bundle);
        return subView;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        category = (Category) getArguments().getSerializable(cate_bundle);
        recyclerView = (RecyclerView) inflater.inflate(R.layout.main_view, container, false);
        ButterKnife.bind(this , recyclerView);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.backhome);
        recyclerView = set_view();
        return recyclerView;
    }

    public  RecyclerView set_view (){
        layoutManager = new GridLayoutManager(getContext() , 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MainAdapter(getContext() , category  , this);
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }


    @Override
    public void selected_handler(int position) {
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (category != null) {
            outState.putSerializable(save_bundle,category);
        }
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        if(savedInstanceState != null && category == null) {
            category = (Category) savedInstanceState.getSerializable(save_bundle);
        }
    }
}
