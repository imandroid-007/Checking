package com.example.realestate.RealEstate;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.LoadMenuFragmentsActivity;
import com.example.realestate.POJO_Main;
import com.example.realestate.R;
import com.example.realestate.RecyclerviewAdapter;
import com.example.realestate.RecyclerviewAdapterChildHome;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class RecyclerviewPropTypes extends RecyclerView.Adapter<RecyclerviewPropTypes.MyViewHolder> {

    private Context mContext;
    private List<POJO_PropType> mData;
    private String newPropSpecType = null, oldropSpecType = null;
    private boolean singleSelection = false;
    private boolean isSelected = false;

    //private ArrayList<Integer> selectedPosition = new ArrayList<>();
    private int selectedPosition = -1;

    private String propType1 = "";
    private int selectionCount = 0;

    private String activityName = null;

    private String type = "NONE";

    public RecyclerviewPropTypes(Context mContext, List<POJO_PropType> mData, boolean singleSelection, String activityName, String type) {
        this.mContext = mContext;
        this.mData = mData;
        this.singleSelection = singleSelection;
        this.activityName = activityName;
        this.type = type;
    }

    public RecyclerviewPropTypes(Context mContext, List<POJO_PropType> mData, boolean singleSelection, String activityName) {
        this.mContext = mContext;
        this.mData = mData;
        this.activityName = activityName;
        this.singleSelection = singleSelection;
    }

    public RecyclerviewPropTypes(Context mContext, List<POJO_PropType> mData, boolean singleSelection) {
        this.mContext = mContext;
        this.mData = mData;
        this.singleSelection = singleSelection;
    }

    public RecyclerviewPropTypes(Context mContext, List<POJO_PropType> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerviewPropTypes.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);

        view = mInflater.inflate(R.layout.rv_layout_proptype, parent, false);

        return new RecyclerviewPropTypes.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewPropTypes.MyViewHolder holder, final int position) {

        Picasso.get().load(mData.get(position).getImgUrl()).into(holder.imggThumb);
        holder.propTypeTv.setText(mData.get(position).getPropType());

        if(mData.get(position).isSelected()){

        } else {
            mData.get(position).setSelected(false);
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

                    selectionCount--;

                    if(selectionCount == 0){
                        propType1 = "";
                    }

                    //selectedPosition.add(holder.getAdapterPosition());

                    //isSelected = true;
                } else {

                    if(singleSelection){

                        if(activityName != null){

                            if(!type.matches("NONE")){

                                if(type.matches("TAKE_ON_RENT") || type.matches("GIVE_ON_RENT")) {

                                    Intent intent = new Intent(mContext, LoadMenuFragmentsActivity.class);
                                    intent.putExtra("MENU_NAME", activityName);
                                    intent.putExtra("PROP_TYPE", mData.get(position).getPropType());
                                    intent.putExtra("DISPLAY_TYPE", type);
                                    mContext.startActivity(intent);

                                } else if(type.matches("REGISTER_PROJECT") || type.matches("BUY_PROJECT")){

                                    Intent intent = new Intent(mContext, LoadMenuFragmentsActivity.class);
                                    intent.putExtra("MENU_NAME", activityName);
                                    intent.putExtra("PROP_TYPE", mData.get(position).getPropType());
                                    intent.putExtra("DISPLAY_TYPE", type);
                                    mContext.startActivity(intent);

                                }

                            } else {

                                Intent intent = new Intent(mContext, LoadMenuFragmentsActivity.class);
                                intent.putExtra("MENU_NAME", activityName);
                                intent.putExtra("PROP_TYPE", mData.get(position).getPropType());
                                mContext.startActivity(intent);

                            }
                        }

                        //Toast.makeText(mContext, "Intent will start new activity here", Toast.LENGTH_SHORT).show();


                    } else {

                        if(propType1.matches("")) {

                            propType1 = mData.get(position).getPropSpecType();

                            mData.get(position).setSelected(true);
                            holder.cardView.setCardBackgroundColor(Color.parseColor("#f0d1dc"));
                            holder.checkIcon.setVisibility(View.VISIBLE);
                            selectedPosition = holder.getAdapterPosition();
                            selectionCount++;

                        } else {

                            if(propType1.matches(mData.get(position).getPropSpecType())){

                                mData.get(position).setSelected(true);
                                holder.cardView.setCardBackgroundColor(Color.parseColor("#f0d1dc"));
                                holder.checkIcon.setVisibility(View.VISIBLE);
                                selectedPosition = holder.getAdapterPosition();
                                selectionCount++;

                            } else {
                                Toasty.warning(mContext, "Cannot select" + " " + propType1 + " " + "and" + " " + mData.get(position).getPropSpecType() + " " + "at the same time", Toasty.LENGTH_LONG).show();
                            }

                        }


                    }

                    /*if(singleSelection) {

                        if (selectedPosition != -1) {
                            Toasty.error(mContext, "You can select only one property type here!", Toasty.LENGTH_SHORT).show();
                        } else {

                            mData.get(position).setSelected(true);
                            holder.cardView.setCardBackgroundColor(Color.parseColor("#f0d1dc"));
                            holder.checkIcon.setVisibility(View.VISIBLE);
                            selectedPosition = holder.getAdapterPosition();

                        }

                    } else {

                        if(propType1.matches("")) {

                            propType1 = mData.get(position).getPropSpecType();

                            mData.get(position).setSelected(true);
                            holder.cardView.setCardBackgroundColor(Color.parseColor("#f0d1dc"));
                            holder.checkIcon.setVisibility(View.VISIBLE);
                            selectedPosition = holder.getAdapterPosition();
                            selectionCount++;

                        } else {

                            if(propType1.matches(mData.get(position).getPropSpecType())){

                                mData.get(position).setSelected(true);
                                holder.cardView.setCardBackgroundColor(Color.parseColor("#f0d1dc"));
                                holder.checkIcon.setVisibility(View.VISIBLE);
                                selectedPosition = holder.getAdapterPosition();
                                selectionCount++;

                            } else {
                                Toasty.warning(mContext, "Cannot select" + " " + propType1 + " " + "and" + " " + mData.get(position).getPropSpecType() + " " + "at the same time", Toasty.LENGTH_LONG).show();
                            }

                        }


                    }*/


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






























