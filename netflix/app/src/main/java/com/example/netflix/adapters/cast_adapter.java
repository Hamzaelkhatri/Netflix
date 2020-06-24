/*
 * Create by Hamza elkhatri
 */

/*
 * Create by Hamza elkhatri
 */

package com.example.netflix.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.netflix.models.cast;
import com.example.netflix.R;

import java.util.List;

public class cast_adapter extends RecyclerView.Adapter<cast_adapter.CastViewHolder>
        {
            private Context mContext;
            private List<cast> mdata;

            public cast_adapter(Context mContext, List<cast> mdata) {
                this.mContext = mContext;
                this.mdata = mdata;
            }

            @NonNull
            @Override
            public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_cast,parent,false);
                return new CastViewHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull CastViewHolder holder, int position) {

                Glide.with(mContext).load(mdata.get(position).getImg_link()).into(holder.img);
            }

            @Override
            public int getItemCount() {
                return mdata.size();
            }

           class CastViewHolder extends RecyclerView.ViewHolder
{
ImageView img;
    CastViewHolder(@NonNull View itemView) {
        super(itemView);
        img= itemView.findViewById(R.id.img_cast);
    }
}
}

