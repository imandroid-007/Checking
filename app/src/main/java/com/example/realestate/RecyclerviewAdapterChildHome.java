package com.example.realestate;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.ContactAndEnquiry.ContactPropListedOwnerActivity;
import com.example.realestate.RealEstate.PropDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerviewAdapterChildHome extends RecyclerView.Adapter<RecyclerviewAdapterChildHome.MyViewHolder>  {

    private Context mContext;
    private List<POJO_PropList> mData;

    public RecyclerviewAdapterChildHome(Context mContext, List<POJO_PropList> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerviewAdapterChildHome.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);

        view = mInflater.inflate(R.layout.rv_layout_insider, parent, false);

        return new RecyclerviewAdapterChildHome.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewAdapterChildHome.MyViewHolder holder, final int position) {

        Picasso.get().load(mData.get(position).getImgUrl()).into(holder.imgThumbnail);

        Log.d("IMAGE_URL", mData.get(position).getImgUrl());

        holder.price.setText(mData.get(position).getPrice());
        holder.bedrooms.setText(mData.get(position).getBedroom());
        holder.propName.setText(mData.get(position).getPropName());
        holder.address.setText(mData.get(position).getAddress());
        holder.status.setText(mData.get(position).getStatus());
        holder.callTitle.setText(mData.get(position).getCallTitle());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(mContext, "Click Event", Toast.LENGTH_SHORT).show();
                //mContext.startActivity(new Intent(mContext, PropDetailsActivity.class));

                Intent intent = new Intent(mContext, PropDetailsActivity.class);
                intent.putExtra("IMAGE_URL", mData.get(position).getImgUrl());
                mContext.startActivity(intent);
            }
        });

        holder.callOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mContext.startActivity(new Intent(mContext, ContactPropListedOwnerActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }





    public static class MyViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView imgThumbnail;
        TextView price, bedrooms, propName, address, status, callTitle;
        LinearLayout callOwner;

        public MyViewHolder(final View itemView){
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardviewInsider);

            imgThumbnail = (ImageView) itemView.findViewById(R.id.propImage);

            price = (TextView) itemView.findViewById(R.id.pricingTv);
            bedrooms = (TextView) itemView.findViewById(R.id.propBedroomsTv);
            propName = (TextView) itemView.findViewById(R.id.propNameTv);
            address = (TextView) itemView.findViewById(R.id.addresTv);
            status = (TextView) itemView.findViewById(R.id.statusTv);
            callTitle = (TextView) itemView.findViewById(R.id.callTitleTv);

            callOwner = (LinearLayout) itemView.findViewById(R.id.callOwnerLinLayout);


        }

    }

}
