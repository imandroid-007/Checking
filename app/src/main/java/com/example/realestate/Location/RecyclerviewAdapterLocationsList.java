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
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.R;

import java.util.List;

public class RecyclerviewAdapterLocationsList extends RecyclerView.Adapter<RecyclerviewAdapterLocationsList.MyViewHolder>  {

    private Context mContext;
    private List<Pojo_cityName> mData;
    private LocationTrans locationTrans;

    public RecyclerviewAdapterLocationsList(Context mContext, List<Pojo_cityName> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    public RecyclerviewAdapterLocationsList(Context mContext, List<Pojo_cityName> mData, LocationTrans locationTrans) {
        this.mContext = mContext;
        this.mData = mData;
        this.locationTrans = locationTrans;
    }

    @NonNull
    @Override
    public RecyclerviewAdapterLocationsList.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);

        view = mInflater.inflate(R.layout.rv_layout_locations_list, parent, false);

        return new RecyclerviewAdapterLocationsList.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerviewAdapterLocationsList.MyViewHolder holder, final int position) {

        holder.cityNameTv.setText(mData.get(position).getCityName());

        if(mData.get(position).isSelected()){

            holder.selectionIcon.setVisibility(View.VISIBLE);
            holder.relativeLayCotainer.setBackgroundResource(R.drawable.selected_button_background);

        } else {

            holder.selectionIcon.setVisibility(View.GONE);
            holder.relativeLayCotainer.setBackgroundResource(0);
        }


        //onCreateViewHolder().   need to do some research about this access.

        //Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), "Fonts/OpenSans-SemiBold.ttf");

        //holder.fileNameTv.setTypeface(typeface);

        /*Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        //view.setBackgroundColor(color);
        holder.backgroundImg.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);*/
        //holder.backgroundImg.getBackground().setColorFilter(mContext.getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);


        //holder.backgroundImg.getBackground().setColorFilter(Color.parseColor(mData.get(position).getCcIdColor()), PorterDuff.Mode.SRC_ATOP);

        holder.relativeLayCotainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mData.get(position).isSelected()){

                    mData.get(position).setSelected(false);
                    holder.relativeLayCotainer.setBackgroundResource(0);
                    holder.selectionIcon.setVisibility(View.GONE);

                    locationTrans.selectedLoc(mData.get(position));
                } else {

                    mData.get(position).setSelected(true);
                    holder.relativeLayCotainer.setBackgroundResource(R.drawable.selected_button_background);
                    holder.selectionIcon.setVisibility(View.VISIBLE);

                    locationTrans.selectedLoc(mData.get(position));
                }

            }
        });



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void updateList(List<Pojo_cityName> list) {
        this.mData = list;
        notifyDataSetChanged();
    }

    public void removeItem(int position){
        int newPosition = position;
        mData.remove(newPosition);
        notifyItemRemoved(newPosition);
        notifyItemRangeChanged(newPosition, mData.size());
    } // Not in use.

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private RelativeLayout relativeLayCotainer;
        private TextView cityNameTv;
        private ImageView selectionIcon;

        public MyViewHolder(View itemView){
            super(itemView);

            relativeLayCotainer = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);
            cityNameTv = (TextView) itemView.findViewById(R.id.cityNameTv);
            selectionIcon = (ImageView) itemView.findViewById(R.id.selectionIcon);

        }

    }


}
