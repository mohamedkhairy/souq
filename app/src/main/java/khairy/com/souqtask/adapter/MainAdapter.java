package khairy.com.souqtask.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import butterknife.BindView;
import butterknife.ButterKnife;
import khairy.com.souqtask.R;
import khairy.com.souqtask.data.main.Category;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.cardHolder> {



    private Category category;
    private Context context;
    private List<Category> categoryList = new ArrayList<>();
    final private SelectedCategory selected ;
    private int count ;


    public MainAdapter(Context cont , List<Category> cate , SelectedCategory sub) {

        this.count = cate.size();
        this.context = cont;
        this.categoryList = cate;
        this.selected = sub;

    }

    public MainAdapter(Context cont , Category cate , SelectedCategory sub ) {

        this.count = cate.getSubCategories().size();
        this.context = cont;
        this.category = cate;
        this.selected = sub;


    }

    public interface SelectedCategory{
        void selected_handler(int position);
    }

    @NonNull
    @Override
    public MainAdapter.cardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int mainPageLayout = R.layout.card_view;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(mainPageLayout , parent , false);
        MainAdapter.cardHolder holder = new MainAdapter.cardHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.cardHolder holder, int position) {


        if (!categoryList.isEmpty()) {
            if (Locale.getDefault().getLanguage().equals("en")) {
                String categoryTitle = context.getString(R.string.title, categoryList.get(position).getTitleEN());
                holder.title.setText(categoryTitle);
            }else {
                String categoryTitle = context.getString(R.string.title, categoryList.get(position).getTitleAR());
                holder.title.setText(categoryTitle);
            }
            String imgUrl = "http://souq.hardtask.co//Images/no_image.png";
            if (!imgUrl.equals(categoryList.get(position).getPhoto())) {
                Glide.with(context).load(categoryList.get(position).getPhoto()).into(holder.cover);
            }
        } else {
            if (Locale.getDefault().getLanguage().equals("en")) {
                String categoryTitle = context.getString(R.string.title, category.getSubCategories().get(position).getTitleEN());
                holder.title.setText(categoryTitle);
            }else {
                String categoryTitle = context.getString(R.string.title, category.getSubCategories().get(position).getTitleAR());
                holder.title.setText(categoryTitle);
            }
            String imgUrl = "http://souq.hardtask.co//Images/no_image.png";
            if (!imgUrl.equals(category.getSubCategories().get(position).getPhoto())) {
                Glide.with(context).load(category.getPhoto()).into(holder.cover);
            }
        }

    }

    @Override
    public int getItemCount() {
        return count;
    }

    public class cardHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.cover)
        ImageView cover;


        public cardHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this , itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            selected.selected_handler(position);
        }
    }
}
