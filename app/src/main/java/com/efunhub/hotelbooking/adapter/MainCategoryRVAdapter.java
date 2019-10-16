package com.efunhub.hotelbooking.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.efunhub.hotelbooking.R;
import com.efunhub.hotelbooking.model.CategoryModel;

import java.util.ArrayList;

public class MainCategoryRVAdapter extends RecyclerView.Adapter<MainCategoryRVAdapter.ItemViewHolder> {

    private Context mContext;
    private ArrayList<CategoryModel> arrayList;

    public MainCategoryRVAdapter(Context context, ArrayList<CategoryModel> categoryModelArrayList) {
        this.mContext = context;
        this.arrayList = categoryModelArrayList;
    }



    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemViewHolder viewHolder;

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item, null);

        viewHolder = new ItemViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        CategoryModel categoryModel = arrayList.get(position);

        holder.txtCategoryName.setText(categoryModel.getCategoryName());
        holder.ivCategoryImg.setImageResource(categoryModel.getImage());

       /* Picasso.with(mContext)
                .load(categoryModel.getCategoryImageUrl())
                .into(holder.ivCategoryImg);*/
       //.placeholder(R.drawable.mazzalo_placeholder)

        /*.memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_CACHE)*/
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView ivCategoryImg;
        TextView txtCategoryName;

        ItemViewHolder(View itemView) {
            super(itemView);

            ivCategoryImg = itemView.findViewById(R.id.ivCategoryImg);
            txtCategoryName = itemView.findViewById(R.id.txtCategoryName);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, CategoryActivity.class);
                    intent.putExtra("position", getAdapterPosition());
                    mContext.startActivity(intent);
                }
            });*/
        }
    }
}
