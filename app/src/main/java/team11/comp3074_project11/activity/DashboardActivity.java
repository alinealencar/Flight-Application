package team11.comp3074_project11.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import team11.comp3074_project11.R;

/**
 * Created by Owner on 12/9/2017.
 */

public class DashboardActivity extends AppCompatActivity{
    String intentClientId;
    String intentClientFirstName;
    String intentClientLastName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Intent intent = getIntent();
        //Get Client id and name from the intent
        intentClientId = intent.getStringExtra("clientId");//client id
        intentClientFirstName = intent.getStringExtra("firstName");//client first name
        intentClientLastName = intent.getStringExtra("lastName");//client last name

        String clientFullName = " " + intentClientFirstName + " " + intentClientLastName;

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
        startActivity(intent);
    }

    //when click My Profile button, the page change to ProfileActivity
    public void onClickProfile(View v){
        Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    //when click Log Out button, the page change to LoginActivity
    public void onClickLogOut(View v){
        Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
