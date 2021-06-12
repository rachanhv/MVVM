package com.example.mvvm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mvvm.R;
import com.example.mvvm.model.ShoppingModel;

import java.util.List;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.MyViewHolder> {

    private Context mContext;
    private List<ShoppingModel> listData;
    private ItemClickListener clickListener;

    public ShoppingListAdapter(Context context, List<ShoppingModel> list, ItemClickListener clickListener) {
        this.mContext = context;
        this.listData = list;
        this.clickListener = clickListener;
    }

    public void setShoppingList(List<ShoppingModel> list) {
        this.listData = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShoppingListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.shopping_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListAdapter.MyViewHolder holder, int position) {
        ShoppingModel model = listData.get(position);
    holder.title.setText(model.getTitle());
    holder.category.setText(model.getCategory());
        Glide.with(mContext)
                .load(model.getImage())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onShopClick(listData.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        if(this.listData != null) {
            return this.listData.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title;
        TextView category;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.thumbnail);
            title = (TextView) itemView.findViewById(R.id.title);
            category = (TextView) itemView.findViewById(R.id.category);
        }
    }

    public interface ItemClickListener{
        public void onShopClick(ShoppingModel model);
    }
}
