package team11.comp3074_project11.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import team11.comp3074_project11.R;
import team11.comp3074_project11.dataModel.Client;
import team11.comp3074_project11.database.FlightAppDatabaseHelper;
import team11.comp3074_project11.helper.SearchUtility;
import team11.comp3074_project11.helper.ValidationUtility;

public class EditProfileActivity extends Activity {

    /** Database helper that will provide us access to the database*/
    //FlightAppDatabaseHelper dbHelper;
    Button btnSave, btnCancelEdit;
    int clientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        final FlightAppDatabaseHelper db = new FlightAppDatabaseHelper(getApplicationContext());

        final EditText etfirstName = (EditText) findViewById(R.id.editFirstName);
        final EditText etlastName = (EditText) findViewById(R.id.editLastName);
        final EditText etemail = (EditText) findViewById(R.id.editEmail);
        final EditText etpassword = (EditText) findViewById(R.id.editPassword);
        final EditText etCcno = (EditText) findViewById(R.id.editCreditCardNo);

        //get the id of the client
        Bundle data = getIntent().getExtras();
        clientId = data.getInt("clientId");

        etfirstName.setText(SearchUtility.getClientByPK(db, clientId).getFirstName());
        etlastName.setText(SearchUtility.getClientByPK(db, clientId).getLastName());
        etemail.setText(SearchUtility.getClientByPK(db, clientId).getEmail());
        etpassword.setText(SearchUtility.getClientByPK(db, clientId).getPassword());
        etCcno.setText(SearchUtility.getClientByPK(db, clientId).getCreditCardNo());

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String firstName = etfirstName.getText().toString().trim();
                String lastName = etlastName.getText().toString().trim();
                String email = etemail.getText().toString().trim();
                String password = etpassword.getText().toString().trim();
                String creditCardNo = etCcno.getText().toString().trim();

                /***************************
                 //validate user input
                 ***************************/
                //check first name in the form filled
                if (ValidationUtility.isMissing(firstName)) {
                    etfirstName.setError("First name is required.");
                }//check first name is all alphabet
                else if (!ValidationUtility.isAlphabet(firstName)) {
                    etfirstName.setError("First name must be alphabet only.");
                } //check last name in the form filled
                else if (ValidationUtility.isMissing(lastName)) {
                    etlastName.setError("Last name is required.");
                }//check first name and last name are all alphabet
                else if (!ValidationUtility.isAlphabet(lastName)) {
                    etlastName.setError("Last name must be alphabet only.");
                    ;
                }//check email in the form filled
                else if (ValidationUtility.isMissing(email)) {
                    etemail.setError("Email is required");
                }//check if email is following email format
                else if (!ValidationUtility.isEmail(email)) {
                    etemail.setError("Invalid email.");
                }//check all fields in the form filled
                else if (ValidationUtility.isMissing(password)) {
                    etpassword.setError("Password is required.");
                }//check all fields in the form filled
                else if (ValidationUtility.isMissing(creditCardNo)) {
                    etCcno.setError("Credit Card Number is required.");
                }//check if valid credit card number
                else if (!ValidationUtility.isCreditCardValid(creditCardNo)) {
                    etCcno.setError("Credit card number must be 16 digits.");
                }//when valid user input
                else {

                    //create client object
                    Client client = new Client(firstName, lastName, email, password, creditCardNo);

                    //create database helper
                    FlightAppDatabaseHelper helper = new FlightAppDatabaseHelper(getApplicationContext());
                    SQLiteDatabase db = helper.getWritableDatabase();
                    SQLiteDatabase dbB = helper.getReadableDatabase();
                    helper.updateClient(db, client, clientId);


                    //get client ID from DB
                    int newClientId = FlightAppDatabaseHelper.getNewClientId(dbB, email);
                    System.out.println(email);
                    System.out.println(newClientId);

                    Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                    intent.putExtra("clientId", newClientId);
                    intent.putExtra("firstName", firstName);
                    intent.putExtra("lastName", lastName);
                    startActivity(intent);
                }
            }
        });

        btnCancelEdit = (Button) findViewById(R.id.btnCancelEdit);
        btnCancelEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}