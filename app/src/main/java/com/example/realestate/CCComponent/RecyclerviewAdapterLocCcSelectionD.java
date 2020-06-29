package com.example.realestate.CCComponent;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.R;
import com.example.realestate.RecyclerviewAdapter;

import java.util.List;

public class RecyclerviewAdapterLocCcSelectionD extends RecyclerView.Adapter<RecyclerviewAdapterLocCcSelectionD.MyViewHolder>  {

    private Context mContext;
    private List<Pojo_CC> mData;
    private SelectedCC selectedCC;

    /*public RecyclerviewAdapterCCListD(Context mContext, List<Pojo_CCList> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }*/

    public RecyclerviewAdapterLocCcSelectionD(Context mContext, List<Pojo_CC> mData, SelectedCC selectedCC) {
        this.mContext = mContext;
        this.mData = mData;
        this.selectedCC = selectedCC;
    }

    @NonNull
    @Override
    public RecyclerviewAdapterLocCcSelectionD.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);

        view = mInflater.inflate(R.layout.rv_layout_cc_list_d, parent, false);

        return new RecyclerviewAdapterLocCcSelectionD.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewAdapterLocCcSelectionD.MyViewHolder holder, final int position) {

        holder.ccNameTv.setText(mData.get(position).getCityName());
        holder.ccNameLetterTv.setText(String.valueOf(mData.get(position).getCityName().charAt(0)));
        holder.ccDepartmentTv.setText(mData.get(position).getLabel());

        //holder.ccBackgroundCir.getBackground().setColorFilter(Color.parseColor(mData.get(position).getCcIdColor()), PorterDuff.Mode.SRC_ATOP);

        holder.ccBackgroundCir.setImageResource(R.drawable.circle_dark_gray);
        //holder.ccBackgroundCir.set(-36096);
        holder.ccBackgroundCir.setColorFilter(Color.parseColor(mData.get(position).getCcIdColor()));

        //Toast.makeText(mContext, "Color =" + " " + Color.parseColor(mData.get(position).getCcIdColor()), Toast.LENGTH_SHORT).show();

        //holder.ccBackgroundCir.getBackground().setColorFilter(Color.parseColor(mData.get(position).getCcIdColor()), PorterDuff.Mode.SRC_ATOP);

        holder.cardCC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCC.selectedCC(mData.get(position));
            }
        });

        //Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), "Fonts/OpenSans-SemiBold.ttf");

        //holder.fileNameTv.setTypeface(typeface);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void updateList(List<Pojo_CC> list) {
        this.mData = list;
        notifyDataSetChanged();
    }

    public void removeItem(int position){
        int newPosition = position;
        mData.remove(newPosition);
        notifyItemRemoved(newPosition);
        notifyItemRangeChanged(newPosition, mData.size());
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private CardView cardCC;
        private TextView ccNameTv, ccDepartmentTv, ccNameLetterTv;
        private ImageView ccBackgroundCir;

        public MyViewHolder(View itemView){
            super(itemView);

            cardCC = (CardView) itemView.findViewById(R.id.cardview);
            ccNameTv = (TextView) itemView.findViewById(R.id.nameTv);
            ccNameLetterTv = (TextView) itemView.findViewById(R.id.letterTv);
            ccDepartmentTv = (TextView) itemView.findViewById(R.id.departmentTv);
            ccBackgroundCir = (ImageView) itemView.findViewById(R.id.letterBackground);

        }
    }


}








































