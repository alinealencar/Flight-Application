package team11.comp3074_project11.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import team11.comp3074_project11.R;
import team11.comp3074_project11.dataModel.Client;
import team11.comp3074_project11.database.FlightAppDatabaseHelper;
import team11.comp3074_project11.helper.SearchUtility;

/**
 * Created by Owner on 12/9/2017.
 */

public class DashboardActivity extends AppCompatActivity{
    static int intentClientId;

    int clientId;
    String fName, lName, email, password, ccNo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Bundle extras = getIntent().getExtras();

        //Get Client id and name from the intent
        if(Integer.valueOf(extras.getInt("clientId")) != null){
            intentClientId = extras.getInt("clientId");
        }

        //Create client object
        FlightAppDatabaseHelper db = new FlightAppDatabaseHelper(getApplicationContext());
        Client client = SearchUtility.getClientByPK(db, intentClientId);

        //Access properties of the object to compose the client's full name
        String clientFullName = " " + client.getFirstName() + " " + client.getLastName();

        //display signed in user
        TextView clientView = (TextView)findViewById(R.id.txtClientName);
        clientView.setText(clientFullName);


    }

    //when click Search button, the page change to SearchActivity
    public void onClickSearch(View v){
        Intent intent = new Intent(DashboardActivity.this, SearchActivity.class);
        startActivity(intent);
    }

    //when click Itineraries button, the page change to ItinerariesActivity
    public void onClickItineraries(View v){
        Intent intent = new Intent(DashboardActivity.this, ItinerariesActivity.class);
        intent.putExtra("clientId", intentClientId);
        startActivity(intent);
    }

    //when click My Profile button, the page change to ProfileActivity
    public void onClickProfile(View v){
        Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
        intent.putExtra("clientId", intentClientId);
        startActivity(intent);
    }

    //when click Log Out button, the page change to LoginActivity
    public void onClickLogOut(View v){
        Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    // Block the user from going to the previous activity
    @Override
    public void onBackPressed() {

    }
}
