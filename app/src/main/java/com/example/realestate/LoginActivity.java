package com.example.realestate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.developer.kalert.KAlertDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private static String URL_LOGIN = "http://192.168.1.115/real_estate/login.php";

    private Button loginBtn, signUpBtn;
    private ProgressBar progressBar;
    private TextView guest_login_testview, appNameTv;
    private EditText usernameEdtx, passwordEdtx;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        pref = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);

        loginBtn = (Button) findViewById(R.id.loginBtn);
        signUpBtn = (Button) findViewById(R.id.signUpBtn);
        progressBar = (ProgressBar) findViewById(R.id.loginProgress);
        guest_login_testview = (TextView)findViewById(R.id.guest_login_testview);
        appNameTv = (TextView)findViewById(R.id.appNameTv);

        usernameEdtx = (EditText) findViewById(R.id.usernameEdtx);
        passwordEdtx = (EditText) findViewById(R.id.passwordEdtx);

        typeface = Typeface.createFromAsset(getAssets(), "fonts/SourceSansPro-SemiBold.ttf");
        appNameTv.setTypeface(typeface);

        guest_login_testview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, Guest_login.class);

                startActivity(intent);

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(LoginActivity.this, MainActivity.class));

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

               /* String username = usernameEdtx.getText().toString();
                String password = passwordEdtx.getText().toString();

                //startActivity(new Intent(LoginActivity.this, MainActivity.class));

                if (username.matches("")) {
                    usernameEdtx.setError("Enter Username");
                } else if (password.matches("")) {
                    passwordEdtx.setError("Enter Password");
                } else {
                    //doLogin(username, password);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    //intent.putExtra("LOGIN_ID", loginId);
                   // intent.putExtra("PROFILE_NAME", profileName);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }*/

            }
        });


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

        String loginId = pref.getString("LOGIN_ID", null);
        String profileName = pref.getString("PROFILE_NAME", null);

        if(loginId != null && profileName != null){

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("LOGIN_ID", loginId);
            intent.putExtra("PROFILE_NAME", profileName);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }


    }


    private void doLogin(String username, String password) {

        progressBar.setVisibility(View.VISIBLE);
        loginBtn.setEnabled(false);
        loginBtn.setAlpha(0.5f);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("EXCEPTION", "Response : " + " " + response);
                        //Toast.makeText(LoginActivity.this, "Response : " + " " + response, Toast.LENGTH_LONG).show();

                        try {
                            JSONArray ja = new JSONArray(response);
                            JSONObject jo = null;

                            String loginId = null;
                            String profileName = null;

                            /*JSONObject jsonObject = new JSONObject(response);
                            custId = jsonObject.getString("Message");*/

                            for (int i = 0; i < ja.length(); i++) {
                                jo = ja.getJSONObject(i);

                                loginId = jo.getString("login_id");

                                if(loginId.matches("0")){

                                } else {
                                    profileName = jo.getString("profile_name");
                                }

                            }

                            //Toast.makeText(LoginActivity.this, "Message : " + " " + custId, Toast.LENGTH_SHORT).show();

                            if (loginId != null) {

                                if (loginId.equals("0")) {

                                    new KAlertDialog(LoginActivity.this, KAlertDialog.ERROR_TYPE)
                                            .setTitleText("Error!")
                                            .setContentText("The Username or Password that you've entered is incorrect or the account does not exist!")
                                            .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                                                @Override
                                                public void onClick(KAlertDialog kAlertDialog) {
                                                    kAlertDialog.dismiss();
                                                }
                                            })
                                            .confirmButtonColor(R.drawable.kalert_button_background)
                                            .show();

                                } else {

                                    editor = pref.edit();
                                    editor.putString("LOGIN_ID", loginId);
                                    editor.putString("PROFILE_NAME", profileName);
                                    editor.commit();
                                    editor.apply();

                                    loginId = null;

                                    loginId = pref.getString("LOGIN_ID", null);

                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.putExtra("LOGIN_ID", loginId);
                                    intent.putExtra("PROFILE_NAME", profileName);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();

                                }

                            } else {
                                new KAlertDialog(LoginActivity.this, KAlertDialog.ERROR_TYPE)
                                        .setTitleText("Error!")
                                        .setContentText("Some unexpected error occurred, Please try again later or contact service provider!")
                                        .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                                            @Override
                                            public void onClick(KAlertDialog kAlertDialog) {
                                                kAlertDialog.dismiss();
                                            }
                                        })
                                        .confirmButtonColor(R.drawable.kalert_button_background)
                                        .show();
                            }

                            progressBar.setVisibility(View.INVISIBLE);
                            loginBtn.setEnabled(true);
                            loginBtn.setAlpha(1);

                        } catch (JSONException e) {
                            e.printStackTrace();

                            //Toast.makeText(LoginActivity.this, "JSON Exception : " + " " + e.toString(), Toast.LENGTH_SHORT).show();
                            Log.d("EXCEPTION", "JSON Exception : " + " " + e.toString());

                            new KAlertDialog(LoginActivity.this, KAlertDialog.ERROR_TYPE)
                                    .setTitleText("Error!")
                                    .setContentText("Ops, Something went wrong!")
                                    .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                                        @Override
                                        public void onClick(KAlertDialog kAlertDialog) {
                                            kAlertDialog.dismiss();
                                        }
                                    })
                                    .confirmButtonColor(R.drawable.kalert_button_background)
                                    .show();

                            progressBar.setVisibility(View.INVISIBLE);
                            loginBtn.setEnabled(true);
                            loginBtn.setAlpha(1);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //Toast.makeText(LoginActivity.this, "Volley Error : " + " " + error.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("EXCEPTION", "Volley Error : " + " " + error.toString());

                        new KAlertDialog(LoginActivity.this, KAlertDialog.ERROR_TYPE)
                                .setTitleText("Error!")
                                .setContentText("Can't communicate with server, Please try again.")
                                .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                                    @Override
                                    public void onClick(KAlertDialog kAlertDialog) {
                                        kAlertDialog.dismiss();
                                    }
                                })
                                .confirmButtonColor(R.drawable.kalert_button_background)
                                .show();

                        progressBar.setVisibility(View.INVISIBLE);
                        loginBtn.setEnabled(true);
                        loginBtn.setAlpha(1);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("user_login", username);
                params.put("user_pass", password);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        requestQueue.add(stringRequest);
    }


}

























