package team11.comp3074_project11.activity;

import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
import android.widget.TextView;

import team11.comp3074_project11.R;
import team11.comp3074_project11.dataModel.Flight;
import team11.comp3074_project11.database.FlightAppDatabaseHelper;
import team11.comp3074_project11.helper.HelperUtility;
import team11.comp3074_project11.helper.SearchUtility;

import static team11.comp3074_project11.activity.FlightDetailsActivity.FLIGHTID;

public class ConfirmationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        FlightAppDatabaseHelper db = new FlightAppDatabaseHelper(getApplicationContext());

        //Get the id of the flight booked by the client
        Bundle extras = getIntent().getExtras();
        int flightId = extras.getInt("flightId");

        //select flight from db
        Flight flight = SearchUtility.getFlightByPK(db, flightId);

        ((TextView) findViewById(R.id.txtFlightNumber)).setText(flight.getFlightNumber());
        ((TextView) findViewById(R.id.txtOrigin)).setText(SearchUtility.getAirportNameByPK(db, flight.getOriginAirportId_FK()).getAirportName());
        ((TextView) findViewById(R.id.txtDestination)).setText(SearchUtility.getAirportNameByPK(db, flight.getDestAirportId_FK()).getAirportName());
        ((TextView) findViewById(R.id.txtDeparture)).setText(flight.getDepartureDate() + " at " + HelperUtility.doubleToHours(flight.getDepartureTime()));
        ((TextView) findViewById(R.id.txtArrival)).setText(flight.getArrivalDate() + " at " + HelperUtility.sumHours(flight.getDepartureTime(), flight.getTravelTime()));
        ((TextView) findViewById(R.id.txtAirline)).setText(SearchUtility.getAirlineByFlight(db, flight).getAirlineName());

        Button btnItineraries = (Button) findViewById(R.id.btnItineraries);
        btnItineraries.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //take user to their itineraries page
                Intent intent = new Intent(ConfirmationActivity.this, ItinerariesActivity.class);
                startActivity(intent);
            }
        });
    }
}
