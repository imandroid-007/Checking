package com.example.realestate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

public class AccountActivity extends AppCompatActivity {

    private ImageView profileBack, emailIcon, contactIcon, postedPropIcon, savedPropIcon, verifiedIcon, chatIcon;

    private CollapsingToolbarLayout collapsingToolbarLayout;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        profileBack = (ImageView) findViewById(R.id.profileBackground);

        emailIcon = (ImageView) findViewById(R.id.emailIcon);
        contactIcon = (ImageView) findViewById(R.id.contactIcon);
        postedPropIcon = (ImageView) findViewById(R.id.postPropIcon);
        savedPropIcon = (ImageView) findViewById(R.id.savedPropIcon);
        verifiedIcon = (ImageView) findViewById(R.id.enquiriesIcon);
        chatIcon = (ImageView) findViewById(R.id.chatIcon);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        collapsingToolbarLayout.setTitleEnabled(false);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Picasso.get().load("https://images.wallpaperscraft.com/image/background_bumps_light_86951_1280x720.jpg").into(profileBack);
        Picasso.get().load("https://images.vexels.com/media/users/3/140138/isolated/preview/88e50689fa3280c748d000aaf0bad480-email-round-icon-1-by-vexels.png").into(emailIcon);
        Picasso.get().load("https://www.flaticon.com/premium-icon/icons/svg/277/277857.svg").into(contactIcon);
        Picasso.get().load("https://www.flaticon.com/premium-icon/icons/svg/373/373310.svg").into(postedPropIcon);
        Picasso.get().load("https://cdn3.iconfinder.com/data/icons/complete-set-icons/512/favourite512x512.png").into(savedPropIcon);
        Picasso.get().load("https://static.thenounproject.com/png/426713-200.png").into(verifiedIcon);
        Picasso.get().load("https://images-na.ssl-images-amazon.com/images/I/31LnydTF72L.png").into(chatIcon);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




}















