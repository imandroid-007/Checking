package com.example.realestate.RvAutoSlider;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.MainActivity;
import com.example.realestate.POJO_Main;
import com.example.realestate.R;
import com.example.realestate.RecyclerviewAdapter;
import com.example.realestate.RecyclerviewAdapterChildHome;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class RecyclerviewAdapterSlider extends RecyclerView.Adapter<RecyclerviewAdapterSlider.MyViewHolder>  {

    private Context mContext;
    private List<POJO_Main> mData;

    RecyclerviewAdapterChildHome adapter;

    public RecyclerviewAdapterSlider(Context mContext, List<POJO_Main> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerviewAdapterSlider.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);

        view = mInflater.inflate(R.layout.rv_layout_home_main, parent, false);

        return new RecyclerviewAdapterSlider.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewAdapterSlider.MyViewHolder holder, final int position) {

        holder.title.setText(mData.get(position).getTitle());

        adapter = new RecyclerviewAdapterChildHome(mContext, mData.get(position).getList());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager.setStackFromEnd(false);

        holder.recyclerView.setLayoutManager(linearLayoutManager);
        holder.recyclerView.setAdapter(adapter);

        /*Timer timer = new Timer(linearLayoutManager, holder, holder.recyclerView);
        timer.scheduleAtFixedRate(new MyTimerTask(), 5000, 4000);*/

        /*     Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            public void run() {
                textview.setText(str);
                //other UI related stuff
            }
        }, 200);
*/

        int adapterCount = adapter.getItemCount();

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                //Toast.makeText(mContext, "exetuting in run", Toast.LENGTH_SHORT).show();
                Log.d("EXECUTIONLOG", "exetuting in run");

                ((MainActivity) mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Log.d("EXECUTIONLOG", "exetuting in runOnUiThread");

                       /* int position = 0;

                        if(position == mData.size()){
                            position = 0;
                        } else {
                            position++;
                        }*/

                        if((linearLayoutManager.findFirstCompletelyVisibleItemPosition() + 2) == mData.get(position).getList().size()){
                        //this is old line -> //if((linearLayoutManager.findFirstCompletelyVisibleItemPosition() + 1) == mData.get(position).getList().size()){

                           /*LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(holder.recyclerView.getContext()) {

                               @Override
                               protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {

                                   Log.d("LINEARSCROLLERLOG", "Density PPI =" + " " + displayMetrics.densityDpi);

                                   //return 100 / displayMetrics.densityDpi;
                                   return 1;
                               }
                           };

                           linearSmoothScroller.setTargetPosition(0);
                           linearLayoutManager.startSmoothScroll(linearSmoothScroller);*/
                            holder.recyclerView.smoothScrollToPosition(0);

                            Log.d("SLIDERCHECKLOG", "This is last item so we're going back to position 0");

                        } else {

                            LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(holder.recyclerView.getContext()) {

                                @Override
                                protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                                    Log.d("LINEARSCROLLERLOG", "Density PPI =" + " " + displayMetrics.densityDpi);
                                    return 500 / displayMetrics.densityDpi;
                                }
                            };


                            if((linearLayoutManager.findFirstCompletelyVisibleItemPosition() + 1) == 1){
                                linearSmoothScroller.setTargetPosition(linearLayoutManager.findFirstCompletelyVisibleItemPosition() + 2); // old = 2
                                linearLayoutManager.startSmoothScroll(linearSmoothScroller);


                            } else {

                                linearSmoothScroller.setTargetPosition(linearLayoutManager.findFirstCompletelyVisibleItemPosition() + 2); // old = 1
                                linearLayoutManager.startSmoothScroll(linearSmoothScroller);
                            }

                            //holder.recyclerView.smoothScrollToPosition(linearLayoutManager.findFirstCompletelyVisibleItemPosition() + 1);
                            //holder.recyclerView.scrollToPosition(linearLayoutManager.findFirstCompletelyVisibleItemPosition() + 1);
                        }

                        Log.d("EXECUTIONLOG", "Position =" + " " + linearLayoutManager.findFirstCompletelyVisibleItemPosition());

                        /*if(adapterCount > 0) {

                            int position = linearLayoutManager.findLastVisibleItemPosition();

                            if(position == mData.size()){
                                holder.recyclerView.scrollToPosition(0);
                            } else {
                                position++;
                                holder.recyclerView.scrollToPosition(position);
                            }

                        }*/
                    }
                });
            }
        }, 4000, 4000);

    }

    class MyTimerTask extends TimerTask {

        int adapterCount = adapter.getItemCount();

        LinearLayoutManager linearLayoutManager;
        RecyclerView.ViewHolder holder;
        RecyclerView recyclerView;

        public MyTimerTask(LinearLayoutManager linearLayoutManager, RecyclerView.ViewHolder holder, RecyclerView recyclerView) {
            this.linearLayoutManager = linearLayoutManager;
            this.holder = holder;
            this.recyclerView = recyclerView;
        }

        @Override
        public void run() {

            ((MainActivity) mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if(adapterCount > 0) {

                        int position = linearLayoutManager.findFirstVisibleItemPosition();
                        if(position == mData.size()){
                            recyclerView.scrollToPosition(0);
                        } else {
                            position++;
                            recyclerView.scrollToPosition(position);
                        }

                    }


                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        RecyclerView recyclerView;

        RelativeLayout relativeLayout;

        public MyViewHolder(final View itemView){
            super(itemView);

            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);

            title = (TextView) itemView.findViewById(R.id.title_tv);

            recyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerview);

        }

    }



}




























