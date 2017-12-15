package team11.comp3074_project11.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

import team11.comp3074_project11.R;
import team11.comp3074_project11.dataModel.Client;
import team11.comp3074_project11.dataModel.Flight;
import team11.comp3074_project11.dataModel.Itinerary;
import team11.comp3074_project11.database.FlightAppDatabaseHelper;
import team11.comp3074_project11.helper.HelperUtility;
import team11.comp3074_project11.helper.SearchUtility;

public class FlightDetailsActivity extends AppCompatActivity {
    static int CLIENTID = 0;
    static int FLIGHTID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_details);

        FlightAppDatabaseHelper db = new FlightAppDatabaseHelper(getApplicationContext());
        DecimalFormat df = new DecimalFormat("###.00");

        //Get Flight object
        FLIGHTID = Integer.valueOf(getIntent().getStringExtra("flightId"));

        Flight flight = SearchUtility.getFlightByPK(db, FLIGHTID);

        //Get Client object
        Client client = SearchUtility.getClientByPK(db, CLIENTID);

        //Set all TextViews with the data stored in the client and flight objects
        ((TextView) findViewById(R.id.txtFlightNumber)).setText(flight.getFlightNumber());
        ((TextView) findViewById(R.id.txtOrigin)).setText(SearchUtility.getAirportNameByPK(db, flight.getOriginAirportId_FK()).getAirportName());
        ((TextView) findViewById(R.id.txtDestination)).setText(SearchUtility.getAirportNameByPK(db, flight.getDestAirportId_FK()).getAirportName());
        ((TextView) findViewById(R.id.txtDeparture)).setText(flight.getDepartureDate());
        ((TextView) findViewById(R.id.txtArrival)).setText(flight.getArrivalDate());
        ((TextView) findViewById(R.id.txtAirline)).setText(SearchUtility.getAirlineByFlight(db, flight).getAirlineName());
        ((TextView) findViewById(R.id.txtTravelTime)).setText(HelperUtility.doubleToHours(flight.getTravelTime()));
        ((TextView) findViewById(R.id.txtCost)).setText("$" + df.format(flight.getCost()));

        ((TextView) findViewById(R.id.txtClientName)).setText(client.getFirstName() + " " + client.getLastName());
        ((TextView) findViewById(R.id.txtCreditCard)).setText(client.getCreditCardNo());
    }

    public void onClickBackToResults(View v){
        finish();
    }

    public void onClickBook(View v){
        //Create Itinerary object
        Itinerary itinerary = new Itinerary();
        itinerary.setClientId_FK(CLIENTID);
        itinerary.setFlightId_FK(FLIGHTID);

        //Store Itinerary in the database
        FlightAppDatabaseHelper db = new FlightAppDatabaseHelper(getApplicationContext());
        db.insertItinerary(itinerary);

        //Send the client to the confirmation page
        Intent intent = new Intent(FlightDetailsActivity.this, ConfirmationActivity.class);
        intent.putExtra("flightId", FLIGHTID);
        startActivity(intent);
    }
}
