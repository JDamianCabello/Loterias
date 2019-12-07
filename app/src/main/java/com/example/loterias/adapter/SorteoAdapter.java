package com.example.loterias.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loterias.R;
import com.example.loterias.data.model.Sorteo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SorteoAdapter extends RecyclerView.Adapter<SorteoAdapter.ViewHolder> {
    List<Sorteo> sorteoList;
    private OnViewHolderClick onViewHolderClickListener;

    public SorteoAdapter(OnViewHolderClick onViewHolderClickListener) {
        this.sorteoList = new ArrayList<>();
        this.onViewHolderClickListener = onViewHolderClickListener;
    }

    public void addAll(ArrayList<Sorteo> sorteoList){
        this.sorteoList.addAll(sorteoList);
    }

    public void clear(){
        this.sorteoList.clear();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.sorteo_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTop.setText(String.format("Sorteo %d (%s)", sorteoList.get(position).getId(), sorteoList.get(position).getFecha()));
        holder.tvBot.setText(sorteoList.get(position).toString());

        holder.imageView.setImageResource(sorteoList.get(position).getIdFoto());

        holder.bind(sorteoList.get(position),onViewHolderClickListener);


    }

    @Override
    public int getItemCount() {
        return sorteoList.size();
    }

    public void sortByDate(Comparator comparator){
        Collections.sort(sorteoList,comparator);
    }

    public interface OnViewHolderClick{
        void onModify(Sorteo sorteo);
        void onDelete(Sorteo sorteo);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTop, tvBot;
        private ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTop = itemView.findViewById(R.id.sorteoItem_tvTop);
            tvBot = itemView.findViewById(R.id.sorteoItem_tvBot);
            imageView = itemView.findViewById(R.id.sorteoItem_ivSorteo);
        }

        public void bind(final Sorteo sorteo, final OnViewHolderClick onViewHolderClick){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onViewHolderClick.onModify(sorteo);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onViewHolderClick.onDelete(sorteo);
                    return false;
                }
            });
        }
    }
}
