package com.example.realestate.RealEstate;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.R;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class RecyclerviewAdapterTextOpts extends RecyclerView.Adapter<RecyclerviewAdapterTextOpts.MyViewHolder> {

    private Context mContext;
    private List<Pojo_TextOpts> mData;
    private boolean singleSelection = false;
    private int selectedPosition = -1;

    public RecyclerviewAdapterTextOpts(Context mContext, List<Pojo_TextOpts> mData, boolean singleSelection) {
        this.mContext = mContext;
        this.mData = mData;
        this.singleSelection = singleSelection;
    }

    public RecyclerviewAdapterTextOpts(Context mContext, List<Pojo_TextOpts> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerviewAdapterTextOpts.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);

        view = mInflater.inflate(R.layout.rv_layout_text_opts, parent, false);

        return new RecyclerviewAdapterTextOpts.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewAdapterTextOpts.MyViewHolder holder, final int position) {

        holder.optionTv.setText(mData.get(position).getTitle());

        if(mData.get(position).isSelected()){
            holder.cardView.setCardBackgroundColor(Color.parseColor("#f0d1dc"));
            holder.checkIcon.setVisibility(View.VISIBLE);
            selectedPosition = holder.getAdapterPosition();
        } else {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#ffffff"));
            holder.checkIcon.setVisibility(View.GONE);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mData.get(position).isSelected()){
                    mData.get(position).setSelected(false);
                    holder.cardView.setCardBackgroundColor(Color.parseColor("#ffffff"));
                    holder.checkIcon.setVisibility(View.GONE);

                    if(singleSelection) {
                        selectedPosition = -1;
                    }

                } else {

                    if(singleSelection) {

                        if(selectedPosition != -1) {
                            notifyItemChanged(selectedPosition);
                            mData.get(selectedPosition).setSelected(false);
                        }

                        selectedPosition = holder.getAdapterPosition();
                        mData.get(selectedPosition).setSelected(true);
                        holder.cardView.setCardBackgroundColor(Color.parseColor("#f0d1dc"));
                        holder.checkIcon.setVisibility(View.VISIBLE);

                       /* if (selectedPosition != -1) {
                            Toasty.error(mContext, "You can select only one!", Toasty.LENGTH_SHORT).show();
                        } else {
                            mData.get(position).setSelected(true);
                            holder.cardView.setCardBackgroundColor(Color.parseColor("#f0d1dc"));
                            holder.checkIcon.setVisibility(View.VISIBLE);
                            selectedPosition = holder.getAdapterPosition();
                        }*/

                    } else {
                        mData.get(position).setSelected(true);
                        holder.cardView.setCardBackgroundColor(Color.parseColor("#f0d1dc"));
                        holder.checkIcon.setVisibility(View.VISIBLE);
                    }
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        RelativeLayout relativeLayout;

        TextView optionTv;
        ImageView checkIcon;

        public MyViewHolder(final View itemView){
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardview_rv);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);

            optionTv = (TextView) itemView.findViewById(R.id.option_title);
            checkIcon = (ImageView) itemView.findViewById(R.id.check);

        }
    }

}



