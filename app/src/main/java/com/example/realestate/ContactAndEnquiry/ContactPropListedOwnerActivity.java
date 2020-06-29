package com.example.realestate.ContactAndEnquiry;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.realestate.R;

import java.util.ArrayList;

public class ContactPropListedOwnerActivity extends AppCompatActivity {

    private ImageView closeIcon;
    private TextView closeTv, verifyDetailsTv, termsAndCondTv;
    private EditText nameEdtx, emailEdtx, mobileNoEdtx;
    private Button callAgentBtn;
    private Spinner countryCodeSpin;
    private ArrayList<String> countryCodeList;
    private ArrayList<String> countryList;

    private Typeface typeface;

    private String type = "NULL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_prop_listed_owner);

        countryCodeList = new ArrayList<>();
        countryList = new ArrayList<>();

        typeface = Typeface.createFromAsset(getAssets(), "fonts/SourceSansPro-SemiBold.ttf");

        initializeViews();

        countryCodeList.add("+91 - India");
        countryCodeList.add("+1 - USA");
        countryCodeList.add("+971 - UAE");
        countryCodeList.add("+966 - Saudi Arabia");
        countryCodeList.add("+65 - Singapore");
        countryCodeList.add("+974 - Qatar");
        countryCodeList.add("+61 - Australia");
        countryCodeList.add("+86 - China");
        countryCodeList.add("+506 - Costa Rica");
        countryCodeList.add("+354 - Iceland");
        countryCodeList.add("+972 - Israel");
        countryCodeList.add("+81 - Japan");
        countryCodeList.add("+60 - Malaysia");
        countryCodeList.add("+52 - Mexico");
        countryCodeList.add("+7 - Russia");

        countryList.add("India");
        countryList.add("USA");
        countryList.add("UAE");
        countryList.add("Saudi Arabia");
        countryList.add("Singapore");
        countryList.add("Qatar");
        countryList.add("Australia");
        countryList.add("China");
        countryList.add("Costa Rica");
        countryList.add("Iceland");
        countryList.add("Israel");
        countryList.add("Japan");
        countryList.add("Malaysia");
        countryList.add("Mexico");
        countryList.add("Russia");

        final ArrayAdapter<String> spinnerArrayAdapterMax = new ArrayAdapter<String>(ContactPropListedOwnerActivity.this, android.R.layout.simple_spinner_item, countryCodeList);
        spinnerArrayAdapterMax.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countryCodeSpin.setAdapter(spinnerArrayAdapterMax);

        closeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        closeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private void initializeViews() {

        closeIcon = (ImageView) findViewById(R.id.closeIcon);
        closeTv = (TextView) findViewById(R.id.closeTv);

        verifyDetailsTv = (TextView) findViewById(R.id.verifyDetailesTitleTv);
        termsAndCondTv = (TextView) findViewById(R.id.TandCtextV);

        nameEdtx = (EditText) findViewById(R.id.nameEdtx);
        emailEdtx = (EditText) findViewById(R.id.emailEdtx);
        mobileNoEdtx = (EditText) findViewById(R.id.mobileNoEdtx);

        countryCodeSpin = (Spinner) findViewById(R.id.countryCodeSpin);

        callAgentBtn = (Button) findViewById(R.id.callListerBtn);

        verifyDetailsTv.setTypeface(typeface);
        closeTv.setTypeface(typeface);
        termsAndCondTv.setTypeface(typeface);
        callAgentBtn.setTypeface(typeface);

        nameEdtx.setTypeface(typeface);
        emailEdtx.setTypeface(typeface);
        mobileNoEdtx.setTypeface(typeface);
    }


}






















