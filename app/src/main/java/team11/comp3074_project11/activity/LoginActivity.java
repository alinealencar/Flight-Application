package team11.comp3074_project11.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import team11.comp3074_project11.R;
import team11.comp3074_project11.dataModel.Client;
import team11.comp3074_project11.database.FlightAppContract;
import team11.comp3074_project11.database.FlightAppDatabaseHelper;

import static team11.comp3074_project11.database.FlightAppContract.*;

public class LoginActivity extends AppCompatActivity{

    //value from EditText view
    EditText emailEditText;
    EditText passwordEditText;

    /** Database helper that will provide us access to the database*/
    private FlightAppDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*find all relevant view*/
        emailEditText = (EditText)findViewById(R.id.editEmail);
        passwordEditText = (EditText)findViewById(R.id.editPassword);

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        dbHelper = new FlightAppDatabaseHelper(this);
    }

    //when click Login button, the page change to DashboardActivity
    public void onClickLogIn(View v){
        /************************************************************
         * comment out for Developing purpose

        //read from input field
        String inputtedEmail = emailEditText.getText().toString().trim();
        String inputtedPassword = passwordEditText.getText().toString().trim();

        //validate if it is valid client.
        //get client information from database
        Client existClient = dbHelper.authenticateClient(inputtedEmail, inputtedPassword);

        if(existClient !=  null){
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            intent.putExtra("clientId", existClient.getClientId());
            intent.putExtra("firstName", existClient.getFirstName());
            startActivity(intent);
        }else{
            Toast.makeText(this, "Invalid email and/or password.", Toast.LENGTH_LONG).show();
        }
        *******************************************************************/
        // following should be deleted after work has done
        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
        startActivity(intent);

        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
        startActivity(intent);
    }

    //when click SignUp button, the page change to SignUpActivity
    public void onClickSignUp(View v){
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);

    }


}
