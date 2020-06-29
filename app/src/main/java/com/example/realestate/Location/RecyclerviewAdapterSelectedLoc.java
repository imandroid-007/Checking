package com.example.realestate.Location;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.R;

import java.util.List;

public class RecyclerviewAdapterSelectedLoc extends RecyclerView.Adapter<RecyclerviewAdapterSelectedLoc.MyViewHolder>  {

    private Context mContext;
    private List<Pojo_cityName> mData;
    private LocationTrans locationTrans;

    public RecyclerviewAdapterSelectedLoc(Context mContext, List<Pojo_cityName> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    public RecyclerviewAdapterSelectedLoc(Context mContext, List<Pojo_cityName> mData, LocationTrans locationTrans) {
        this.mContext = mContext;
        this.mData = mData;
        this.locationTrans = locationTrans;
    }

    @NonNull
    @Override
    public RecyclerviewAdapterSelectedLoc.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);

        view = mInflater.inflate(R.layout.rv_layout_location_chips, parent, false);

        return new RecyclerviewAdapterSelectedLoc.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerviewAdapterSelectedLoc.MyViewHolder holder, final int position) {

        holder.ccNameTv.setText(mData.get(position).getCityName());

        holder.ccFirstCharTv.setText(String.valueOf(mData.get(position).getCityName().charAt(0)));

        //onCreateViewHolder().   need to do some research about this access.

        //Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), "Fonts/OpenSans-SemiBold.ttf");

        //holder.fileNameTv.setTypeface(typeface);

        /*Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        //view.setBackgroundColor(color);
        holder.backgroundImg.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);*/
        //holder.backgroundImg.getBackground().setColorFilter(mContext.getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);

        Log.d("EXCEPTION_DATA_THREE", mData.get(position).getColorCode());

        holder.backgroundImg.getBackground().setColorFilter(Color.parseColor(mData.get(position).getColorCode()), PorterDuff.Mode.SRC_ATOP);

        holder.ccChipMainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.closeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //approverSelectINTFC.selectedApprover(mData.get(position).getApprover());
                removeItem(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void removeItem(int position){
        int newPosition = position;
        locationTrans.selectedLoc(mData.get(newPosition));
        mData.remove(newPosition);
        notifyItemRemoved(newPosition);
        notifyItemRangeChanged(newPosition, mData.size());
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout ccChipMainLayout;
        private TextView ccNameTv, ccFirstCharTv;
        private ImageView closeImg, backgroundImg;

        public MyViewHolder(View itemView){
            super(itemView);

            ccChipMainLayout = (LinearLayout) itemView.findViewById(R.id.ccChipMainLayout);
            ccNameTv = (TextView) itemView.findViewById(R.id.ccNameTv);
            ccFirstCharTv = (TextView) itemView.findViewById(R.id.tvFirstLetter);
            closeImg = (ImageView) itemView.findViewById(R.id.closeImg);
            backgroundImg = (ImageView) itemView.findViewById(R.id.backgroundImg);

        }
    }


}















