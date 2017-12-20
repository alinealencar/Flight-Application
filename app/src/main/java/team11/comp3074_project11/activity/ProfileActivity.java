package team11.comp3074_project11.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import team11.comp3074_project11.R;
import team11.comp3074_project11.dataModel.Client;
import team11.comp3074_project11.database.FlightAppDatabaseHelper;
import team11.comp3074_project11.helper.HelperUtility;
import team11.comp3074_project11.helper.SearchUtility;

public class ProfileActivity extends Activity {

    int clientId;
    Button btnEdit, btnDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        FlightAppDatabaseHelper db = new FlightAppDatabaseHelper(getApplicationContext());

        final TextView etfirstName = (TextView)findViewById(R.id.txtFirstName);
        TextView etlastName = (TextView) findViewById(R.id.txtLastName);
        TextView etemail = (TextView) findViewById(R.id.txtEmail);
        TextView etpassword = (TextView) findViewById(R.id.txtPassword);
        TextView etCcno = (TextView) findViewById(R.id.txtccNo);

        //get the id of the client
        Bundle data = getIntent().getExtras();
        clientId = data.getInt("clientId");

        //Get client
        Client client = SearchUtility.getClientByPK(db,clientId);

        etfirstName.setText(client.getFirstName());
        etlastName.setText(client.getLastName());
        etemail.setText(client.getEmail());
        etpassword.setText(HelperUtility.stringToStars(client.getPassword()));
        etCcno.setText(client.getCreditCardNo());

        btnEdit = (Button) findViewById(R.id.btnEditProfile);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                intent.putExtra("clientId", clientId);
                startActivity(intent);
            }
        });

        btnDashboard = (Button) findViewById(R.id.btnDashboard);
        btnDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, DashboardActivity.class);
                intent.putExtra("clientId", clientId);
                startActivity(intent);
            }
        });
    }
}