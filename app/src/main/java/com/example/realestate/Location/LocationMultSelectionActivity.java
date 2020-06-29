package com.example.realestate.Location;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.realestate.R;

import java.util.ArrayList;
import java.util.Locale;

public class LocationMultSelectionActivity extends AppCompatActivity implements LocationTrans {

    private LinearLayout selectLocationBtn;
    private Button doneLocationSelectionBtn;
    private RecyclerView rvSelectedLocations;
    private RecyclerView rvLocationList;
    private EditText locationsSearchEdtx;
    private TextView currentLocationTv;
    private ImageView currentLocationIcon;

    private RecyclerviewAdapterSelectedLoc adapterSelectedLoc;
    private ArrayList<Pojo_cityName> selectedLocationList;

    private RecyclerviewAdapterLocationsList adapterLocationsList;
    private ArrayList<Pojo_cityName> locationsList;

    private ImageView clearIcon, voiceToTextIcon;

    final int VOICE_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_mult_selection);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        selectedLocationList = new ArrayList<>();
        locationsList = new ArrayList<>();

        selectLocationBtn = (LinearLayout) findViewById(R.id.locationSelectBtn);
        doneLocationSelectionBtn = (Button) findViewById(R.id.doneBtn);

        rvSelectedLocations = (RecyclerView) findViewById(R.id.rvAddedLocations);
        rvLocationList = (RecyclerView) findViewById(R.id.rvLocations);

        locationsSearchEdtx = (EditText) findViewById(R.id.locationsSearchEdtx);

        currentLocationIcon = (ImageView) findViewById(R.id.currentLocationIcon);
        currentLocationTv = (TextView) findViewById(R.id.currentLocationTv);

        clearIcon = (ImageView) findViewById(R.id.clearIcon);
        voiceToTextIcon = (ImageView) findViewById(R.id.speechToTextIcon);

        currentLocationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        currentLocationTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        locationsSearchEdtx.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {

                if(s.length() > 0){
                    clearIcon.setVisibility(View.VISIBLE);
                } else {
                    clearIcon.setVisibility(View.GONE);
                }

                filter(s.toString());
            }
        });

        clearIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = locationsSearchEdtx.getText().toString();

                if(s.length() > 0){
                    locationsSearchEdtx.setText("");
                }

            }
        });

        locationsList.add(new Pojo_cityName("1", "Sadar Bazar, Satara.", "Maharashtra", "#F3B431" ,false));
        locationsList.add(new Pojo_cityName("2", "Godoli, Satara.", "Maharashtra", "#F3B431" ,false));
        locationsList.add(new Pojo_cityName("3", "Nagthane, Satara.", "Maharashtra", "#F3B431" ,false));
        locationsList.add(new Pojo_cityName("4", "Atit, Satara.", "Maharashtra", "#F3B431" ,false));
        locationsList.add(new Pojo_cityName("5", "Wai, Satara.", "Maharashtra", "#F3B431" ,false));

        locationsList.add(new Pojo_cityName("6", "Mangalwar Peth, Kolhapur.", "Maharashtra", "#F3B431" ,false));
        locationsList.add(new Pojo_cityName("7", "Rajarampuri, Kolhapur.", "Maharashtra", "#F3B431" ,false));
        locationsList.add(new Pojo_cityName("8", "Petala, Kolhapur.", "Maharashtra", "#F3B431" ,false));
        locationsList.add(new Pojo_cityName("9", "RK Nagar, Kolhapur.", "Maharashtra", "#F3B431" ,false));

        locationsList.add(new Pojo_cityName("10", "Katraj, Pune", "Maharashtra", "#F3B431" ,false));
        locationsList.add(new Pojo_cityName("11", "Wakad, Pune", "Maharashtra", "#F3B431" ,false));

        adapterLocationsList = new RecyclerviewAdapterLocationsList(LocationMultSelectionActivity.this, locationsList, new LocationTrans() {
            @Override
            public void selectedLoc(Pojo_cityName city) {

                Log.d("SELECTIONLOG", "Id =" + " " + city.getId() + "\nCity Name =" + " " + city.getCityName() + "\n label =" + city.getStateNameLabel() + "\ncolor code =" + " " + city.getColorCode() + "\n isSelected =" + " " + city.isSelected());

                if(city.isSelected()){

                        Log.d("SELECTIONLOG", "Does Not contains");
                        selectedLocationList.add(new Pojo_cityName(city.getId(), city.getCityName(), city.getCityName(), "#FAD02E", true));
                        adapterSelectedLoc.notifyDataSetChanged();

                } else {

                    int pos = -1;

                    for(int i = 0; i < selectedLocationList.size(); i++){

                        if(selectedLocationList.get(i).getId().equals(city.getId())){
                            pos = i;
                        }
                    }

                    if(pos != -1) {

                        selectedLocationList.remove(pos);
                        adapterSelectedLoc.notifyDataSetChanged();
                    }
                }

            }
        });

        rvLocationList.setLayoutManager(new LinearLayoutManager(LocationMultSelectionActivity.this));
        rvLocationList.setAdapter(adapterLocationsList);

        voiceToTextIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voice_to_text();
            }
        });

        adapterSelectedLoc = new RecyclerviewAdapterSelectedLoc(LocationMultSelectionActivity.this, selectedLocationList, new LocationTrans() {
            @Override
            public void selectedLoc(Pojo_cityName city) {

                int pos = -1;

                Log.d("UNSELECTIONLOG", "listener");

                for(int i = 0; i < locationsList.size(); i++){

                    if(locationsList.get(i).getId().equals(city.getId())){

                        if(locationsList.get(i).isSelected()){

                            locationsList.get(i).setSelected(false);
                            adapterLocationsList.notifyItemChanged(i);
                            adapterSelectedLoc.notifyDataSetChanged();

                            Log.d("UNSELECTIONLOG", "is selected found");
                            //adapterSelectedLoc.notifyItemChanged();
                        }
                    }
                }

            }
        });

        rvSelectedLocations.setLayoutManager(new LinearLayoutManager(LocationMultSelectionActivity.this));
        rvSelectedLocations.setAdapter(adapterSelectedLoc);

        rvSelectedLocations.setLayoutManager(new GridLayoutManager(LocationMultSelectionActivity.this, 2));
        rvSelectedLocations.setAdapter(adapterLocationsList);


    }

    public void filter(String text) {

        ArrayList<Pojo_cityName> temp = new ArrayList<>();

        for (Pojo_cityName d : locationsList) {
            if (d.getCityName().toLowerCase().contains(text)) {
                temp.add(d);
            }
        }
        adapterLocationsList.updateList(temp);
    }


    private void voice_to_text() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Voice2Text \n Say Something!!");
        try {
            startActivityForResult(intent, VOICE_CODE);
        } catch (ActivityNotFoundException e) {

        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case VOICE_CODE: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    locationsSearchEdtx.setText(result.get(0));
                }
                break;
            }
        }
    }


    @Override
    public void selectedLoc(Pojo_cityName city) {

       /* if(city.isSelected()){

            selectedLocationList.add(new PojoSelectedLocList(city.getId(), city.getCityName(), city.getCityName(), "#FAD02E"));
            adapterSelectedLoc.notifyDataSetChanged();

        } else {

            if(selectedLocationList.contains(new PojoSelectedLocList(city.getId(), city.getCityName(), city.getStateNameLabel(), city.getColorCode()))){

                selectedLocationList.remove(new PojoSelectedLocList(city.getId(), city.getCityName(), city.getStateNameLabel(), city.getColorCode()));

                adapterSelectedLoc.notifyDataSetChanged();
            }

        }*/

    }


}
































