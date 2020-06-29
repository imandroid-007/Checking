package com.example.realestate.RealEstate;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerviewAdapterAmenities extends RecyclerView.Adapter<RecyclerviewAdapterAmenities.MyViewHolder> {

    private Context mContext;
    private List<POJO_Amenities> mData;

    private boolean isSelectable;

    public RecyclerviewAdapterAmenities(Context mContext, List<POJO_Amenities> mData, boolean isSelectable) {
        this.mContext = mContext;
        this.mData = mData;
        this.isSelectable = isSelectable;
    }


    @NonNull
    @Override
    public RecyclerviewAdapterAmenities.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);

        view = mInflater.inflate(R.layout.rv_layout_proptype, parent, false);

        return new RecyclerviewAdapterAmenities.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewAdapterAmenities.MyViewHolder holder, final int position) {

        Picasso.get().load(mData.get(position).getImgUrl()).into(holder.imggThumb);
        holder.propTypeTv.setText(mData.get(position).getAmenitiesType());

        if(!isSelectable) {
            holder.checkIcon.setVisibility(View.VISIBLE);
            mData.get(position).setSelected(true);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSelectable) {
                    if (mData.get(position).isSelected()) {
                        mData.get(position).setSelected(false);
                        holder.cardView.setCardBackgroundColor(Color.parseColor("#ffffff"));
                        holder.checkIcon.setVisibility(View.GONE);
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

        ImageView imggThumb, checkIcon;
        TextView propTypeTv;

        public MyViewHolder(final View itemView){
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardview_rv_proptype);

            imggThumb = (ImageView) itemView.findViewById(R.id.propType_img);
            checkIcon = (ImageView) itemView.findViewById(R.id.check);
            propTypeTv = (TextView) itemView.findViewById(R.id.proptype_title);

        }
    }

}