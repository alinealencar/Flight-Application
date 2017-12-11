package team11.comp3074_project11.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import team11.comp3074_project11.R;

/**
 * Created by Owner on 12/10/2017.
 */

public class SignUpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Get User id and name from the intent
        Intent intent = getIntent();
    }

    //when click Create Account button, the page change to Dashboard Activity
    public void onClickCreateAccount(View v){
        Intent intent = new Intent(SignUpActivity.this, DashboardActivity.class);
        startActivity(intent);
    }

    //when click Cancel button, the page change to LogIn Activity
    public void onClickCancel(View v){
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
    }

}
