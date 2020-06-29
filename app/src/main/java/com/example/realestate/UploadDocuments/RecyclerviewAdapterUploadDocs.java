package com.example.realestate.UploadDocuments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.R;
import com.example.realestate.RecyclerviewAdapter;

import java.util.List;

public class RecyclerviewAdapterUploadDocs extends RecyclerView.Adapter<RecyclerviewAdapterUploadDocs.DocsViewHolder> {

    private Context mContext;
    private List<Pojo_UploadDocs> mData;

    @NonNull
    @Override
    public DocsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);

        view = inflater.inflate(R.layout.rv_layout_upload_documents, parent, false);

        return new DocsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocsViewHolder holder, int position) {

        holder.edtxDocNum.setHint(mData.get(position).getEdtxHintName());
        holder.noteDocUploadTv.setText(mData.get(position).getNoteMessage());
        holder.docImageUploadTitleTv.setText(mData.get(position).getDocImgTitleName());

        if(mData.get(position).isUploadedToServer()){
            holder.uploadStatusTv.setText("Upload Success");
            holder.uploadStatusTv.setTextColor(mContext.getResources().getColor(R.color.green_success_color));
            holder.uploadStatusImg.setImageResource(R.drawable.success_icon);
        } else {
            holder.uploadStatusTv.setText("Upload Failed !");
            holder.uploadStatusTv.setTextColor(mContext.getResources().getColor(R.color.red_failure_color));
            holder.uploadStatusImg.setImageResource(R.drawable.failure_icon);
        }

        holder.removeDocImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class DocsViewHolder extends RecyclerView.ViewHolder{

        private EditText edtxDocNum;
        private TextView noteDocUploadTv, docImageUploadTitleTv, uploadStatusTv;
        private ImageView removeDocImg, docPreviewImg, uploadStatusImg;
        private ProgressBar progressBarDocUpload;

        public DocsViewHolder(@NonNull View itemView) {
            super(itemView);

            edtxDocNum = (EditText) itemView.findViewById(R.id.docNumEdtx);

            noteDocUploadTv = (TextView) itemView.findViewById(R.id.noteDocUploadTv);
            docImageUploadTitleTv = (TextView) itemView.findViewById(R.id.noteDocUploadTitleTv);
            uploadStatusTv = (TextView) itemView.findViewById(R.id.uploadStatusTv);

            removeDocImg = (ImageView) itemView.findViewById(R.id.removeDocImg);
            docPreviewImg = (ImageView) itemView.findViewById(R.id.docPreviewImg);
            uploadStatusImg = (ImageView) itemView.findViewById(R.id.uploadStatusImg);

            progressBarDocUpload = (ProgressBar) itemView.findViewById(R.id.progressBarUploadProgress);

        }
    }







}
