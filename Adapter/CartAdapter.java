package com.rich.stylespace.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.rich.stylespace.Domain.PopularDomain;
import com.rich.stylespace.Helper.ChangeNumberItemsListener;
import com.rich.stylespace.Helper.ManagmentCart;
import com.rich.stylespace.databinding.ViewholderCartBinding;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private ArrayList<PopularDomain> items;
    private Context context;
    private ChangeNumberItemsListener changeNumberItemsListener;
    private ManagmentCart managmentCart;

    public CartAdapter(ArrayList<PopularDomain> items, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.items = items;
        this.context = context;
        this.changeNumberItemsListener = changeNumberItemsListener;
        this.managmentCart = new ManagmentCart(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewholderCartBinding binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PopularDomain item = items.get(position);

        holder.binding.titleTxt.setText(item.getTitle());
        holder.binding.feeEachItem.setText("$" + item.getPrice());
        holder.binding.totalEachItem.setText("$" + Math.round(item.getNumberInCart() * item.getPrice()));
        holder.binding.numberItemTxt.setText(String.valueOf(item.getNumberInCart()));

        int drawableResourceId = holder.itemView.getResources().getIdentifier(item.getPicUrl(),
                "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(context)
                .load(drawableResourceId)
                .transform(new GranularRoundedCorners(30, 30, 0, 0))
                .into(holder.binding.pic);

        holder.binding.plusCartBtn.setOnClickListener(v -> {
            managmentCart.plusNumberItem(items, position, new ChangeNumberItemsListener() {
                @Override
                public void change() {
                    notifyDataSetChanged();
                    changeNumberItemsListener.change();
                }
            });
        });

        holder.binding.minusCartBtn.setOnClickListener(v -> {
            managmentCart.minusNumberItem(items, position, new ChangeNumberItemsListener() {
                @Override
                public void change() {
                    notifyDataSetChanged();
                    changeNumberItemsListener.change();
                }
            });
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderCartBinding binding;

        public ViewHolder(ViewholderCartBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
