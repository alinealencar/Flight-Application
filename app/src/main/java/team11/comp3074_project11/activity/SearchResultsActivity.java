package team11.comp3074_project11.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ServiceConfigurationError;

import team11.comp3074_project11.R;
import team11.comp3074_project11.dataModel.Airport;
import team11.comp3074_project11.dataModel.Flight;
import team11.comp3074_project11.database.FlightAppDatabaseHelper;
import team11.comp3074_project11.helper.SearchUtility;

public class SearchResultsActivity extends Activity {
    String origin, destination, departureDateStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        final FlightAppDatabaseHelper db = new FlightAppDatabaseHelper(getApplicationContext());

        //Populate sortSpinner
        String[] categories = {"Price", "Travel Time"};
        Spinner sortSpinner = (Spinner) findViewById(R.id.sortSpinner);
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, categories);
        sortSpinner.setAdapter(categoriesAdapter);

        //Get search parameters
        origin = getIntent().getStringExtra("origin");
        destination = getIntent().getStringExtra("destination");
        departureDateStr = getIntent().getStringExtra("departureDate");


        //Get all flights that match the search criteria
        try {
            List<Flight> allFlights = SearchUtility.getAllFlights(db, db.getReadableDatabase());
            for(Flight f : allFlights)
                System.out.println(f.toString());

            List<Flight> flightList = SearchUtility.getFlights(db, SearchUtility.getAirportPKByName(db, origin), SearchUtility.getAirportPKByName(db, destination), departureDateStr);

            ListView resultsList = (ListView) findViewById(R.id.resultsListView);

            ArrayAdapter<String> resultsAdapter;
            //No flights found
            if(flightList.size() == 0){
                List<String> noResults = new ArrayList<>();
                noResults.add("There are no flights from " + origin + " to " +
                                destination + " on " + departureDateStr + ".");
                 resultsAdapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, noResults);
            }
            //Flights found
            else {
                List<String> flightListStr = new ArrayList<>();
                for(Flight aF : flightList)
                    flightListStr.add(aF.toString());

                resultsAdapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, flightListStr);
            }

            sortSpinner.setAdapter(resultsAdapter);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
