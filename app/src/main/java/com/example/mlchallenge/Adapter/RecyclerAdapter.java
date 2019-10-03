package com.example.mlchallenge.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mlchallenge.Model.Product;
import com.example.mlchallenge.Model.Results;
import com.example.mlchallenge.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ResultsViewHolder> {

    private List<Product> listItems;
   // final private ItemCLick onClickListener;


    public RecyclerAdapter(List<Product> items){
        listItems = items;
        //onClickListener = listener;
    }

    public void setData(List<Product> products) {
        listItems = products;
    }


    public interface ItemCLick{
        void onItemClick(int clickerItem);
    }


    @NonNull
    @Override
    public ResultsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context mContext = parent.getContext();
        int layoutIdItem = R.layout.product_row;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        boolean attach = false;
        View view = inflater.inflate(layoutIdItem, parent, attach);
        ResultsViewHolder viewHolder = new ResultsViewHolder(view);

        return  viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ResultsViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    class ResultsViewHolder extends RecyclerView.ViewHolder /*implements  View.OnClickListener*/{

        TextView nameResult;

        public ResultsViewHolder(@NonNull View itemView) {
            super(itemView);
            nameResult = itemView.findViewById(R.id.nameResult);
            //itemView.setOnClickListener(this);

        }

        void bind(int listIndex){
            Product product = listItems.get(listIndex);
            nameResult.setText(product.getTitle());
        }
        /*
        @Override
        public void onClick(View v) {
            int clickedItem = getAdapterPosition();
            onClickListener.onItemClick(clickedItem);
        }*/
    }

}
