package com.example.realestate.CCComponent;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.R;

import java.util.ArrayList;

public class DialogLocationSelector extends AppCompatDialogFragment {

    FragmentActivity activity;
    Context mContext;

    public static interface OnLocationSelectionListener {
        public abstract void onCCSelect(Pojo_CC selectedCC);
    }

    private OnLocationSelectionListener onLocationSelectionListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            activity = (FragmentActivity) context;
        }

        try {
            this.onLocationSelectionListener = (OnLocationSelectionListener) getActivity();
        } catch (final ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement OnCompleteListener");
        }
    }

    private EditText searchCcEdtx;

    private TextView title, cancel, ok;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private RecyclerviewAdapterLocCcSelectionD adapter;
    private ArrayList<Pojo_CC> ccList;

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_files_and_locationcc_selection, null);

        builder.setView(view);

        ccList = new ArrayList<>();

        Bundle bundle = getArguments();

        //ccList = (ArrayList<Pojo_CC>) bundle.getSerializable("CCLIST_SERIALIZABLE");


        ccList.add(new Pojo_CC("1", "Kolhapur", "Maharashtra", "#8e78d6"));
        ccList.add(new Pojo_CC("2", "Satara", "Maharashtra", "#fc657b"));
        ccList.add(new Pojo_CC("3", "Pune", "Maharashtra", "#fbbc41"));
        ccList.add(new Pojo_CC("4", "Mumbai", "Maharashtra", "#2da1f9"));
        ccList.add(new Pojo_CC("5", "Nashik", "Maharashtra", "#e44236"));
        ccList.add(new Pojo_CC("6", "Sangli", "Maharashtra", "#ff7300"));

        //Toast.makeText(activity, "CClist.size() = " + " " + ccList.size(), Toast.LENGTH_SHORT).show();

        /* filePathsList = bundle.getStringArrayList("FILE_PATH_LIST");
        fileNameList = bundle.getStringArrayList("FILE_NAME_LIST");
        fileExtensionList = bundle.getStringArrayList("FILE_EXTENSION_LIST");
        fileUploadStatusList = bundle.getStringArrayList("FILE_UPLOADING_STATUS_LIST");*/

        title = (TextView) view.findViewById(R.id.dialogTitleTv);
        ok = (TextView) view.findViewById(R.id.okTv);
        cancel = (TextView) view.findViewById(R.id.cancelTv);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvApprovers);
        progressBar = (ProgressBar) view.findViewById(R.id.progressDialog);
        searchCcEdtx = (EditText) view.findViewById(R.id.searchLocationEdtx);

        if (ccList.size() > 0) {
            int spanCount = 1;
            int spacing = 30;
            boolean includeEdge = true;
            //recyclerView.addItemDecoration(new GridSpacingDecoration(spanCount, spacing, includeEdge));
            adapter = new RecyclerviewAdapterLocCcSelectionD(activity, ccList, new SelectedCC() {
                @Override
                public void selectedCC(Pojo_CC selectedCC) {
                    onLocationSelectionListener.onCCSelect(selectedCC);
                    dismiss();
                }
            });

            recyclerView.setLayoutManager(new LinearLayoutManager(activity));
            recyclerView.setAdapter(adapter);
        }


        searchCcEdtx.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });



        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onCCSelectionListener.onCCSelect(fileList);
                dismiss();
            }
        });


        return builder.create();
    }


    public void filter(String text) {

        ArrayList<Pojo_CC> temp = new ArrayList<>();

        for (Pojo_CC d : ccList) {
            if (d.getCityName().toLowerCase().contains(text.toLowerCase())) {
                temp.add(d);
            }
        }
        adapter.updateList(temp);
    }






}






















































































