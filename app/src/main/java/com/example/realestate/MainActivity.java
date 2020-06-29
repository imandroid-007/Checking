package com.example.realestate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.realestate.Maps.MapsActivity;
import com.example.realestate.Projects.ProjectsPropTypeActivity;
import com.example.realestate.RealEstate.InvestInPropActivity;
import com.example.realestate.RealEstate.PropTypeActivity;
import com.example.realestate.RealEstate.RentPropTypeActivity;
import com.example.realestate.RvAutoSlider.RecyclerviewAdapterSlider;
import com.google.android.material.navigation.NavigationView;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    //private RecyclerviewAdapter adapter;
    private RecyclerviewAdapterSlider adapter;
    private ArrayList<POJO_Main> lstcategory;
    private ArrayList<POJO_PropList> lstcategoryInsider;
    private EditText search;

    private CircularImageView buy, sell, rent, invest, projects, explore;
    private TextView buyTv, sellTv, rentTv, investTv, projectsTv, exploreTv;
    private LinearLayout buyLayBtn, sellLayBtn, rentLayBtn, investLayBtn, projectsLayBtn, exploreLayBtn;

    private String LOGIN_ID_STR = null, profileName = null;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        Intent intent = getIntent();
        LOGIN_ID_STR = intent.getStringExtra("LOGIN_ID");
        profileName = intent.getStringExtra("PROFILE_NAME");

        //Toast.makeText(this, "lOGIN Id from Main Activity : " + " " + LOGIN_ID_STR, Toast.LENGTH_SHORT).show();
        Log.d("LOGIN_ID_LOG", "lOGIN Id from Main Activity : " + " " + LOGIN_ID_STR);

        lstcategory = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_Home);
        search = (EditText) findViewById(R.id.searchEdtx);

        buy = (CircularImageView) findViewById(R.id.buyImg);
        sell = (CircularImageView) findViewById(R.id.sellImg);
        rent = (CircularImageView) findViewById(R.id.rentImg);
        invest = (CircularImageView) findViewById(R.id.investImg);
        projects = (CircularImageView) findViewById(R.id.projectsImg);
        explore = (CircularImageView) findViewById(R.id.exploreImg);

        buyTv = (TextView) findViewById(R.id.buyTv);
        sellTv = (TextView) findViewById(R.id.sellTv);
        rentTv = (TextView) findViewById(R.id.rentTv);
        investTv = (TextView) findViewById(R.id.investTv);
        projectsTv = (TextView) findViewById(R.id.projectsTv);
        exploreTv = (TextView) findViewById(R.id.exploreTv);

        //buyLayBtn, sellLayBtn, rentLayBtn, investLayBtn, projectsLayBtn, exploreLayBtn;
        buyLayBtn = (LinearLayout) findViewById(R.id.buyLayoutBtn);
        sellLayBtn = (LinearLayout) findViewById(R.id.sellLayoutBtn);
        rentLayBtn = (LinearLayout) findViewById(R.id.rentLayoutBtn);
        investLayBtn = (LinearLayout) findViewById(R.id.investLayoutBtn);
        projectsLayBtn = (LinearLayout) findViewById(R.id.projectsLayoutBtn);
        exploreLayBtn = (LinearLayout) findViewById(R.id.exploreLayoutBtn);

        buyLayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(MainActivity.this, LoadMenuFragmentsActivity.class);
                intent.putExtra("MENU_NAME", "BUY");
                startActivity(intent);*/

                Intent intent1 = new Intent(MainActivity.this, PropTypeActivity.class);
                intent1.putExtra("FRAGMENT_TYPE", "BUY");
                startActivity(intent1);

                //startActivity(new Intent(MainActivity.this, PropTypeActivity.class));

            }
        });

        sellLayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Intent intent = new Intent(MainActivity.this, LoadMenuFragmentsActivity.class);
                intent.putExtra("MENU_NAME", "SELL");
                startActivity(intent);*/

                Intent intent1 = new Intent(MainActivity.this, PropTypeActivity.class);
                intent1.putExtra("FRAGMENT_TYPE", "SELL");
                startActivity(intent1);

                //startActivity(new Intent(MainActivity.this, PropTypeActivity.class));
            }
        });

        rentLayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(MainActivity.this, RentPropTypeActivity.class);
                intent1.putExtra("FRAGMENT_TYPE", "RENT");
                startActivity(intent1);

                /*Intent intent = new Intent(MainActivity.this, LoadMenuFragmentsActivity.class);
                intent.putExtra("MENU_NAME", "RENT");
                startActivity(intent);*/
            }
        });

        investLayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InvestInPropActivity.class);
                startActivity(intent);
            }
        });

        projectsLayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProjectsPropTypeActivity.class);
                intent.putExtra("FRAGMENT_TYPE", "PROJECTS");
                startActivity(intent);
            }
        });

        explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Intent intent = new Intent(MainActivity.this, TempProjectsActivity.class);
                intent.putExtra("TITLE", "All Properties");
                startActivity(intent);*/
                startActivity(new Intent(MainActivity.this, MapsActivity.class));
            }
        });

        Typeface sourceSansPro = Typeface.createFromAsset(getAssets(), "fonts/SourceSansPro-SemiBold.ttf");
        buyTv.setTypeface(sourceSansPro);
        sellTv.setTypeface(sourceSansPro);
        rentTv.setTypeface(sourceSansPro);
        investTv.setTypeface(sourceSansPro);
        projectsTv.setTypeface(sourceSansPro);
        exploreTv.setTypeface(sourceSansPro);

        Picasso.get().load("https://image.flaticon.com/icons/png/512/589/589528.png").into(buy);
        Picasso.get().load("https://image.flaticon.com/icons/png/512/197/premium/197295.png").into(sell);
        Picasso.get().load("https://image.flaticon.com/icons/png/512/197/197738.png").into(rent);
        Picasso.get().load("https://image.flaticon.com/icons/png/512/242/242619.png").into(invest);
        Picasso.get().load("https://image.flaticon.com/icons/png/512/1527/1527807.png").into(projects);

        //Picasso.get().load("https://www.shareicon.net/data/2017/01/06/868288_view_512x512.png").into(explore);

        Picasso.get().load("https://www.shareicon.net/data/512x512/2016/09/21/831108_view_512x512.png").into(explore);

        //Picasso.get().load("https://image.flaticon.com/icons/png/512/1862/1862367.png").into(explore);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        drawer.animate();

        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        navigationView.setNavigationItemSelectedListener(this);

        updateNavHeader();

        lstcategory = new ArrayList<>();
        lstcategoryInsider = new ArrayList<>();

        lstcategoryInsider.add(new POJO_PropList("https://media.equityapartments.com/images/c_crop,x_0,y_0,w_1920,h_1080/c_fill,w_1920,h_1080/q_80/4147-23/the-hesby-apartments-exterior.jpg", "₹ 66 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        lstcategoryInsider.add(new POJO_PropList("https://images.wallpaperscraft.com/image/living_room_design_flat_interior_design_style_metropolis_90530_1280x720.jpg", "₹ 72 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        lstcategoryInsider.add(new POJO_PropList("https://images.wallpaperscraft.com/image/living_room_modern_design_interior_design_furniture_sofa_tv_70088_1280x720.jpg", "₹ 88 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        lstcategoryInsider.add(new POJO_PropList("https://images.wallpaperscraft.com/image/living_room_desk_furniture_design_interior_design_modernism_25616_1280x720.jpg", "₹ 56 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        lstcategoryInsider.add(new POJO_PropList("https://images.wallpaperscraft.com/image/apartments_living_room_bathroom_furniture_design_interior_108940_1280x720.jpg", "₹ 95 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        //lstcategoryInsider.add(new POJO_PropList("https://images.wallpaperscraft.com/image/living_room_furniture_style_design_39327_1280x720.jpg", "₹ 69 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        lstcategoryInsider.add(new POJO_PropList("https://images.wallpaperscraft.com/image/living_room_sofa_furniture_comfort_interior_75478_1280x720.jpg", "₹ 67 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        lstcategoryInsider.add(new POJO_PropList("https://images.wallpaperscraft.com/image/living_room_furniture_cosiness_style_comfort_39308_1280x720.jpg", "₹ 78 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        //lstcategoryInsider.add(new POJO_PropList("https://images.wallpaperscraft.com/image/living_room_girl_furniture_style_comfort_39343_1280x720.jpg", "₹ 59 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        lstcategoryInsider.add(new POJO_PropList("https://images.wallpaperscraft.com/image/living_room_modern_design_interior_design_furniture_sofa_tv_70088_1280x720.jpg", "₹ 88 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        lstcategoryInsider.add(new POJO_PropList("https://images.wallpaperscraft.com/image/living_room_furniture_style_interior_design_modern_39374_1280x720.jpg", "₹ 82 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        lstcategoryInsider.add(new POJO_PropList("https://images.wallpaperscraft.com/image/living_room_sofa_furniture_interior_style_design_95260_1280x720.jpg", "₹ 80 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        lstcategoryInsider.add(new POJO_PropList("https://images.wallpaperscraft.com/image/living_room_sofa_furniture_comfort_interior_75478_1280x720.jpg", "₹ 67 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));
        lstcategoryInsider.add(new POJO_PropList("https://images.wallpaperscraft.com/image/living_room_furniture_sofa_table_comfort_design_70084_1280x720.jpg", "₹ 96 Lac", "2 BHK Flat", "VTP Hilife", "Vakad, Pune.", "Possession by Dec 2020", "Call Owner", "9503696428"));

        lstcategory.add(new POJO_Main(lstcategoryInsider, "Owner Properties"));
        lstcategory.add(new POJO_Main(lstcategoryInsider, "Fresh Properties For You"));
        lstcategory.add(new POJO_Main(lstcategoryInsider, "Hot Deals For You"));
        lstcategory.add(new POJO_Main(lstcategoryInsider, "Ready to Move Projects"));
        lstcategory.add(new POJO_Main(lstcategoryInsider, "Newly Launched Projects"));
        lstcategory.add(new POJO_Main(lstcategoryInsider, "Projects in Demand"));

        adapter = new RecyclerviewAdapterSlider(MainActivity.this, lstcategory);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, true));
        recyclerView.setAdapter(adapter);

        toolbar.setContentInsetsAbsolute(0, 0);

        search.setInputType(InputType.TYPE_NULL);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityRealEstate.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        if (id == R.id.home) {
            // Handle the camera action
            Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();

            //startActivity(new Intent(MainActivity.this, ActivityRealEstate.class));

        } else if (id == R.id.buyProp) {
            //Toast.makeText(getApplicationContext(), "Buy Prop", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, LoadMenuFragmentsActivity.class);
            intent.putExtra("MENU_NAME", "BUY");
            startActivity(intent);

        } else if (id == R.id.rentProp) {

            Intent intent = new Intent(MainActivity.this, LoadMenuFragmentsActivity.class);
            intent.putExtra("MENU_NAME", "RENT");
            startActivity(intent);
            //Toast.makeText(getApplicationContext(), "Rent Prop", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.investInProp) {

            startActivity(new Intent(MainActivity.this, InvestInPropActivity.class));

        } else if (id == R.id.newProject) {
            //Toast.makeText(getApplicationContext(), "New Project", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, LoadMenuFragmentsActivity.class);
            intent.putExtra("MENU_NAME", "PROJECTS");
            startActivity(intent);
            //startActivity(new Intent(MainActivity.this, ActivityRealEstate.class));

        } else if (id == R.id.exploreLocalities) {
            Toast.makeText(getApplicationContext(), "Explore Localities", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.sellProp) {
            //startActivity(new Intent(MainActivity.this, SellPropActivity.class));
            Intent intent = new Intent(MainActivity.this, LoadMenuFragmentsActivity.class);
            intent.putExtra("MENU_NAME", "SELL");
            startActivity(intent);
            //Toast.makeText(getApplicationContext(), "Post Prop", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.advertise) {
            Toast.makeText(getApplicationContext(), "Advertise", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(MainActivity.this, OTP_Activity.class));

        } else if (id == R.id.propworth) {
            Toast.makeText(getApplicationContext(), "Propworth", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(MainActivity.this, ScrollingActivityTest.class));

        } else if (id == R.id.ratesAndTrends) {
            Toast.makeText(getApplicationContext(), "Rates & Trends", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.forum) {
            Toast.makeText(getApplicationContext(), "Forum", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.emiCal) {
            Toast.makeText(getApplicationContext(), "EMI Calculator", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.loan) {
            Toast.makeText(getApplicationContext(), "Loan", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.propindex) {
            Toast.makeText(getApplicationContext(), "Prop Index", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.propValuation) {
            Toast.makeText(getApplicationContext(), "Prop Valuation", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.advocateCall) {
            Toast.makeText(getApplicationContext(), "Advocate Call", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.feedback) {
            //Toast.makeText(getApplicationContext(), "Advocate Call", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, ContactUsActivity.class));

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }


    public void updateNavHeader() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        TextView profile = headerView.findViewById(R.id.drawerFirmTitleTextView);
        Button logout = headerView.findViewById(R.id.logoutBtn);

        profile.setText("Welcome," + " " + profileName);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AccountActivity.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor = pref.edit();
                editor.putString("LOGIN_ID", null);
                editor.putString("PROFILE_NAME", null);
                editor.commit();
                editor.apply();

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });

        // TextView compName = headerView.findViewById(R.id.drawerFirmTitleTextView);
        //TextView userName = headerView.findViewById(R.id.drawerUserNameTextView);
        //TextView mobile = headerView.findViewById(R.id.drawerUserMobileTextView);

        // compName.setText("Intelligence Methods");
        // userName.setText("Welcome, Shubham Shinde");
        //  mobile.setText("Mobile: 9503696428");


        //very old

        //TextView navUsername = headerView.findViewById(R.id.nav_username);
        //TextView navUserMail = headerView.findViewById(R.id.nav_user_mail);
        //ImageView navuserPhoto = headerView.findViewById(R.id.nav_user_photo);

        //navUsername.setText("Shubham");
        //navUserMail.setText("shindesshubham@gmail.com");

        //navUsername.setTextColor(Color.parseColor("#000000"));
        //navUserMail.setTextColor(Color.parseColor("#000000"));

        //navuserPhoto.setImageResource(R.drawable.photo_male);

    }

    private Picasso buildCustomePicassoInstance(){

        Picasso.Builder builder = new Picasso.Builder(this);
        //set 12% of available app memory for image cache
        builder.memoryCache(new LruCache(getBytesForMemCache(12)));
        //set request transformer
        Picasso.RequestTransformer requestTransformer =  new Picasso.RequestTransformer() {
            @Override
            public Request transformRequest(Request request) {
                Log.d("image request", request.toString());
                return request;
            }
        };
        builder.requestTransformer(requestTransformer);

        return builder.build();
    }

    private int getBytesForMemCache(int percent){

        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager)
                getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);

        double availableMemory= mi.availMem;

        return (int)(percent*availableMemory/100);
    }


}
































