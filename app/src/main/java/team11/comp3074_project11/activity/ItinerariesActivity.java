package team11.comp3074_project11.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceConfigurationError;

import team11.comp3074_project11.R;
import team11.comp3074_project11.dataModel.Flight;
import team11.comp3074_project11.dataModel.Itinerary;
import team11.comp3074_project11.database.FlightAppDatabaseHelper;
import team11.comp3074_project11.helper.HelperUtility;
import team11.comp3074_project11.helper.SearchUtility;

public class ItinerariesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itineraries);

        List<Flight> itinerariesList = new ArrayList<>();
        final FlightAppDatabaseHelper db = new FlightAppDatabaseHelper(getApplicationContext());

        LinearLayout itineraryLayout = (LinearLayout) findViewById(R.id.itineraryLayout);

        //Hide no itineraries message
        findViewById(R.id.noItinerariesTextView).setVisibility(View.INVISIBLE);

        //Get the id of the client
        //Bundle extras = getIntent().getExtras();
        //int clientId = extras.getInt("clientId");

        try {
            //store selected flights by clientId to list
            itinerariesList = SearchUtility.getFlightByClient(db, 1);

            ArrayAdapter<String> adapter;
            List<String> itinerariesListStr = new ArrayList<>();

            //check if there are itineraries to show
            if(itinerariesList.size() == 0){
                findViewById(R.id.noItinerariesTextView).setVisibility(View.VISIBLE);
            }

            else {
                for (Flight aF : itinerariesList) {
                    LinearLayout perItinerary = new LinearLayout(getApplicationContext());
                    perItinerary.setOrientation(LinearLayout.HORIZONTAL);
                    perItinerary.setGravity(Gravity.CENTER);

                    //FlightInfo
                    TextView flightInfo = new TextView(getApplicationContext());
                    String info = "<b>" + aF.getFlightNumber() +
                            "</b><br>From: " + SearchUtility.getAirportNameByPK(db, aF.getOriginAirportId_FK()).getAirportName() +
                            "<br>To:" + SearchUtility.getAirportNameByPK(db, aF.getDestAirportId_FK()).getAirportName() +
                            "<br>Departure: " + aF.getDepartureDate() + " at " + HelperUtility.doubleToHours(aF.getDepartureTime()) +
                            "<br>Arrival: " + aF.getArrivalDate() + " at " + HelperUtility.sumHours(aF.getDepartureTime(), aF.getTravelTime()) +
                            "<b><br>Duration: " + HelperUtility.doubleToHours(aF.getTravelTime()) +
                            "</b><br><br><i>Operated By: " + SearchUtility.getAirlineByFlight(db, aF).getAirlineName() + "</i><br><br>";
                    flightInfo.setText(Html.fromHtml(info));
                    flightInfo.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 2f));

                    perItinerary.addView(flightInfo);

                    //Set background color of the result
                    perItinerary.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorGray));
                    perItinerary.setPadding(25, 15, 5, 5);

                    itineraryLayout.addView(perItinerary);
                }
            }

        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(), "Database error. " + e + "Please try again.", Toast.LENGTH_SHORT).show();
        }

        Button btnBackToHome = (Button) findViewById(R.id.btnBackToHome);
        btnBackToHome.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent = new Intent(ItinerariesActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });

    }
}
