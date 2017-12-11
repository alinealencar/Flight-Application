package team11.comp3074_project11.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import team11.comp3074_project11.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    //when click Login button, the page change to DashboardActivity
    public void onClickLogIn(View v){
        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
        startActivity(intent);

    }

    //when click SignUp button, the page change to SignUpActivity
    public void onClickSignUp(View v){
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);

    }
}
