package team11.comp3074_project11.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import team11.comp3074_project11.R;
import team11.comp3074_project11.dataModel.Client;
import team11.comp3074_project11.database.FlightAppContract;
import team11.comp3074_project11.database.FlightAppDatabaseHelper;
import team11.comp3074_project11.helper.ValidationUtility;

import static team11.comp3074_project11.database.FlightAppContract.ClientEntry.*;

/**
 * Created by Owner on 12/10/2017.
 */

public class SignUpActivity extends AppCompatActivity {
    private EditText fNameEditText;
    private EditText lNameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText creditCardNoEditText;

    /** Database helper that will provide us access to the database*/
    private FlightAppDatabaseHelper dbHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Intent intent = getIntent();

        /*
        find all relevant views
         */
        fNameEditText = (EditText) findViewById(R.id.editFirstName);
        lNameEditText = (EditText) findViewById(R.id.editLastName);
        emailEditText = (EditText) findViewById(R.id.editEmail);
        passwordEditText = (EditText) findViewById(R.id.editPassword);
        creditCardNoEditText = (EditText) findViewById(R.id.editCreditCardNo);

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        dbHelper = new FlightAppDatabaseHelper(this);
    }

    //when click Create Account button, the page change to Dashboard Activity
    public void onClickCreateAccount(View v){
        //read from input field
        String firstName = fNameEditText.getText().toString().trim();
        String lastName = lNameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String creditcardNo = creditCardNoEditText.getText().toString().trim();

        /***************************
        //validate user input
        ***************************/

        //create database helper
        FlightAppDatabaseHelper helper = new FlightAppDatabaseHelper(this);

        //get the database in write mode
        SQLiteDatabase dbWrite =  helper.getWritableDatabase();
        //get the database in reading mode
        SQLiteDatabase dbRead =  helper.getReadableDatabase();

        /*
        check if there is not an account using same email
         */
        if(FlightAppDatabaseHelper.isNewClient(dbRead, email)==false){
            //the imputed email is already used
            Toast.makeText(this, "The email address is already used. \n Please use another email address.", Toast.LENGTH_SHORT).show();
        }
        else{
            //the inputed email is new
            /*
            insert values to database
             */
            //create client object
            Client client = new Client(firstName, lastName, email, password, creditcardNo);

            //insert a new row for client in the database
            //returning the ID for that new row
            long newRowId = FlightAppDatabaseHelper.insertClient(dbWrite, client);

            //show a toast message depend on insertion result
            if (newRowId == -1) {
                //if the row ID is -1, then there was an error with insertion.
                Toast.makeText(this, "Error with saving new profile", Toast.LENGTH_SHORT).show();
            } else {
                //otherwise, the insertion was successful
                Toast.makeText(this, "Your account is created successfully.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUpActivity.this, DashboardActivity.class);
                intent.putExtra("firstName", firstName);
                intent.putExtra("lastName", lastName);
                startActivity(intent);
            }
        }

    }

    //when click Cancel button, the page change to LogIn Activity
    public void onClickCancel(View v){
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
    }

}
