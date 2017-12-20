package team11.comp3074_project11.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import team11.comp3074_project11.R;
import team11.comp3074_project11.dataModel.Flight;
import team11.comp3074_project11.database.FlightAppDatabaseHelper;
import team11.comp3074_project11.helper.HelperUtility;
import team11.comp3074_project11.helper.SearchUtility;

public class SearchResultsActivity extends Activity {
    String origin, destination, departureDateStr;
    List<Flight> flightList = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        //Get the vertical layout
        final LinearLayout resultsLayout = (LinearLayout) findViewById(R.id.resultsLayout);

        //Hide no flights message
        findViewById(R.id.noFlightsTextView).setVisibility(View.INVISIBLE);

        final FlightAppDatabaseHelper db = new FlightAppDatabaseHelper(getApplicationContext());

        //Print all flights
        List<Flight> allFlights = SearchUtility.getAllFlights(db, db.getReadableDatabase());
        for(Flight f : allFlights)
            System.out.println(f.toString());

        //Populate sortSpinner
        String[] categories = {"Price", "Travel Time"};
        final Spinner sortSpinner = findViewById(R.id.sortSpinner);
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, categories);
        sortSpinner.setAdapter(categoriesAdapter);

        sortSpinner.setOnItemSelectedListener
                (new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(flightList != null && flightList.size() > 0){
                    resultsLayout.removeAllViews();
                    //Sort the flights by category
                    //By price
                    if (sortSpinner.getSelectedItemPosition() == 0) {
                        //Use the comparable interface to sort by price
                        Collections.sort(flightList, new Comparator<Flight>() {
                                    @Override public int compare(Flight f1, Flight f2) {
                                        return (int) (f1.getCost() - f2.getCost());
                                    }
                        });
                        System.out.println("SORT BY PRICE");

                    }
                    //By travel time
                    else if(sortSpinner.getSelectedItemPosition() == 1){
                        //Use the comparable interface to sort by travel time
                        Collections.sort(flightList, new Comparator<Flight>() {
                            @Override public int compare(Flight f1, Flight f2) {
                                return (int) (f1.getTravelTime() - f2.getTravelTime());
                            }
                        });
                        System.out.println("SORT BY TRAVEL TIME");
                    }

                    for(Flight aF : flightList){
                        LinearLayout individualResult = new LinearLayout(getBaseContext());
                        individualResult.setOrientation(LinearLayout.HORIZONTAL);
                        individualResult.setGravity(Gravity.CENTER);

                        //FlightInfo
                        TextView flightInfo = new TextView(getBaseContext());
                        String info = "<b>" + aF.getFlightNumber() +
                                        "</b><br>From: " + SearchUtility.getAirportNameByPK(db, aF.getOriginAirportId_FK()).getAirportName() +
                                        "<br>To:" +  SearchUtility.getAirportNameByPK(db, aF.getDestAirportId_FK()).getAirportName() +
                                        "<br>Departure: " + aF.getDepartureDate() + " at " + HelperUtility.doubleToHours(aF.getDepartureTime()) +
                                        "<br>Arrival: " + aF.getArrivalDate() + " at " + HelperUtility.sumHours(aF.getDepartureTime(), aF.getTravelTime()) +
                                        "<b><br>Duration: " + HelperUtility.doubleToHours(aF.getTravelTime()) +
                                        "</b><br><br><i>Operated By: " + SearchUtility.getAirlineByFlight(db, aF).getAirlineName() + "</i><br><br>";
                        flightInfo.setText(Html.fromHtml(info));
                        flightInfo.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 2f));
                        flightInfo.setTextColor(Color.parseColor("#5b5b5b"));

                        //FlightCost
                        TextView flightCost = new TextView(getBaseContext());
                        DecimalFormat df = new DecimalFormat("###.00");
                        flightCost.setText("$" + df.format(aF.getCost()));
                        flightCost.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
                        flightCost.setTypeface(null, Typeface.BOLD);
                        flightCost.setTextSize(20);
                        flightCost.setTextColor(Color.parseColor("#5b5b5b"));

                        individualResult.addView(flightInfo);
                        individualResult.addView(flightCost);
                        individualResult.setWeightSum(3f);

                        //Set background color of the result
                        individualResult.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.colorGray));
                        individualResult.setPadding(25, 15, 5, 5);

                        final String flightId = Integer.toString(aF.getFlightId());
                        individualResult.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getApplicationContext(), FlightDetailsActivity.class);
                                intent.putExtra("flightId", flightId);
                                startActivity(intent);
                            }
                        });

                        //Create division
                        Space division = new Space(getApplicationContext());
                        division.setMinimumHeight(45);

                        resultsLayout.addView(individualResult);
                        resultsLayout.addView(division);

                    }
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
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

}
