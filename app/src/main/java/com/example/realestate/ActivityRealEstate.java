package com.example.realestate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.realestate.RealEstate.FragmentBuy;
import com.example.realestate.RealEstate.FragmentTakeOnRent;
import com.example.realestate.RealEstate.FragmentProject;
import com.example.realestate.RealEstate.FragmentSell;
import com.example.realestate.RealEstate.ViewpagerAdapterRealEstate;
import com.google.android.material.tabs.TabLayout;

public class ActivityRealEstate extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_estate);

        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        tabLayout = (TabLayout) findViewById(R.id.sliding_tab);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        backBtn = (ImageView) findViewById(R.id.backImgBtn);

        ViewpagerAdapterRealEstate adapter = new ViewpagerAdapterRealEstate(getSupportFragmentManager());

        Bundle bundle = new Bundle();
        bundle.putString("CUSTOMER_ID", "custid");

        FragmentBuy buy = new FragmentBuy();
        buy.setArguments(bundle);
        adapter.addFragment(buy, "Buy");

        FragmentSell sell = new FragmentSell();
        sell.setArguments(bundle);
        adapter.addFragment(sell, "Sell");

        FragmentTakeOnRent commercial = new FragmentTakeOnRent();
        commercial.setArguments(bundle);
        adapter.addFragment(commercial, "Rent");

        FragmentProject project = new FragmentProject();
        project.setArguments(bundle);
        adapter.addFragment(project, "Project");

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        viewPager.setOffscreenPageLimit(4);

//        viewPager.setOnTouchListener(new View.OnTouchListener()
//        {
//            @Override
//            public boolean onTouch(View v, MotionEvent event)
//            {
//                return true;
//            }
//        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    @Override
    public void onBackPressed() {

        boolean status = false;

        Log.d("BACKPRESSLOG", "OnBackPressedLog()");

        if(status){
            super.onBackPressed();
        } else {
            Toast.makeText(getApplicationContext(), "Hey dont worry, not letting activity close", Toast.LENGTH_SHORT).show();
        }

        //Toast.makeText(this, "inside on back pressed", Toast.LENGTH_SHORT).show();

      /*  Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.viewpager);

        Toast.makeText(this, "inside on back pressed", Toast.LENGTH_SHORT).show();

        if(!(fragment instanceof IOnBackPressed) || !((IOnBackPressed) fragment).onBackPressed()){

            Toast.makeText(this, "its false", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        }

        if((fragment instanceof IOnBackPressed) || ((IOnBackPressed) fragment).onBackPressed()){

            Toast.makeText(this, "its true", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        }*/

    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(this, "Hey dont worry, not letting activity close", Toast.LENGTH_SHORT).show();
            //onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }




}














