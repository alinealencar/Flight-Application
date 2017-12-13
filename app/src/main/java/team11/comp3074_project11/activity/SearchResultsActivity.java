package team11.comp3074_project11.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
    List<Flight> flightList = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        final ListView resultsList = (ListView) findViewById(R.id.resultsListView);

        //Hide no flights message
        findViewById(R.id.noFlightsTextView).setVisibility(View.INVISIBLE);

        final FlightAppDatabaseHelper db = new FlightAppDatabaseHelper(getApplicationContext());

        //Populate sortSpinner
        String[] categories = {"Price", "Travel Time"};
        final Spinner sortSpinner = (Spinner) findViewById(R.id.sortSpinner);
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, categories);
        sortSpinner.setAdapter(categoriesAdapter);

        sortSpinner.setOnItemSelectedListener
                (new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(flightList != null && flightList.size() > 0){
                    //Sort the flights by category
                    //By price
                    if (sortSpinner.getSelectedItemPosition() == 0) {
                        //Use the comparable interface to sort by price
                        Collections.sort(flightList, new Comparator<Flight>() {
                                    @Override public int compare(Flight f1, Flight f2) {
                                        return (int) (f1.getCost() - f2.getCost());
                                    }
                        });
                    }
                    //By travel time
                    else if(sortSpinner.getSelectedItemPosition() == 1){
                        //Use the comparable interface to sort by travel time
                        Collections.sort(flightList, new Comparator<Flight>() {
                            @Override public int compare(Flight f1, Flight f2) {
                                return (int) (f1.getTravelTime() - f2.getTravelTime());
                            }
                        });

                    }

                    List<String> flightListStr = new ArrayList<>();
                    for(Flight aF : flightList)
                        flightListStr.add(aF.toString());

                    ArrayAdapter<String> resultsAdapter = new ArrayAdapter<String>(getApplicationContext(),
                            android.R.layout.simple_list_item_1, flightListStr);
                    resultsList.setAdapter(resultsAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Get search parameters
        origin = getIntent().getStringExtra("origin");
        destination = getIntent().getStringExtra("destination");
        departureDateStr = getIntent().getStringExtra("departureDate");


        //Get all flights that match the search criteria
        try {
            //Search for flights that match the search criteria by using the SearchUtility class
            flightList = SearchUtility.getFlights(db, SearchUtility.getAirportPKByName(db, origin), SearchUtility.getAirportPKByName(db, destination), departureDateStr);

            ArrayAdapter<String> resultsAdapter;
            //No flights found
            if(flightList.size() == 0){
                findViewById(R.id.noFlightsTextView).setVisibility(View.VISIBLE);
            }
            //Flights found
            else {
                List<String> flightListStr = new ArrayList<>();
                for(Flight aF : flightList) {
                    flightListStr.add(aF.toString());
                    System.out.println("FOUND: " + aF.toString());
                }
                resultsAdapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, flightListStr);
                resultsList.setAdapter(resultsAdapter);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
