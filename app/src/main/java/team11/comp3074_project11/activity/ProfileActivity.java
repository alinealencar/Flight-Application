package team11.comp3074_project11.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import team11.comp3074_project11.R;
import team11.comp3074_project11.dataModel.Client;
import team11.comp3074_project11.database.FlightAppDatabaseHelper;
import team11.comp3074_project11.helper.SearchUtility;

public class ProfileActivity extends Activity {

    String firstName, lastName, email, password, creditcardNo;
    int clientId=1;
    TextView etfirstName, etlastName, etemail, etpassword, etCcno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        /*FlightAppDatabaseHelper db = new FlightAppDatabaseHelper(getApplicationContext());

        etfirstName = (TextView)findViewById(R.id.txtFirstName);
        etlastName = (TextView) findViewById(R.id.txtLastName);
        etemail = (TextView) findViewById(R.id.txtEmail);
        etpassword = (TextView) findViewById(R.id.txtPassword);
        etCcno = (TextView) findViewById(R.id.txtccNo);

        // getting data
        Bundle data = getIntent().getExtras();
        //clientId = data.getInt("clientId");

        //((TextView) findViewById(R.id.txtFlightNumber)).setText(flight.getFlightNumber());
        etfirstName.setText(SearchUtility.getClientByPK(db,clientId).getFirstName());
        etlastName.setText(SearchUtility.getClientByPK(db,clientId).getLastName());
        etemail.setText(SearchUtility.getClientByPK(db,clientId).getEmail());
        etpassword.setText(SearchUtility.getClientByPK(db,clientId).getPassword());
        etCcno.setText(SearchUtility.getClientByPK(db,clientId).getCreditCardNo());*/
    }
}
