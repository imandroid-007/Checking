package com.example.realestate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerviewAdapterPropDetail extends RecyclerView.Adapter<RecyclerviewAdapterPropDetail.MyViewHolder> {

    private Context mContext;
    private List<Pojo_propDetails> mData;

    public RecyclerviewAdapterPropDetail(Context mContext, List<Pojo_propDetails> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);

        view = mInflater.inflate(R.layout.rv_layout_prop_details, parent, false);

        return new RecyclerviewAdapterPropDetail.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.propDetailTitleTv.setText(mData.get(position).getPropDetailTitle());

        holder.propDetailInfoTv.setText(mData.get(position).getPropDetailInfo());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView propDetailTitleTv, propDetailInfoTv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            propDetailTitleTv = (TextView) itemView.findViewById(R.id.propDetailTypeTitleTv);
            propDetailInfoTv = (TextView) itemView.findViewById(R.id.propDetailInfoTv);

        }
    }



}





















