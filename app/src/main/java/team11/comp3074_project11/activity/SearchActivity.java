package team11.comp3074_project11.activity;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import team11.comp3074_project11.R;
import team11.comp3074_project11.dataModel.Airport;
import team11.comp3074_project11.database.FlightAppDatabaseHelper;
import team11.comp3074_project11.helper.SearchUtility;
import team11.comp3074_project11.helper.ValidationUtility;

public class SearchActivity extends Activity {
    List<Airport> airports;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //Hide error messages
        findViewById(R.id.originErrorTextView).setVisibility(View.INVISIBLE);
        findViewById(R.id.destErrorTextView).setVisibility(View.INVISIBLE);
        findViewById(R.id.dateErrorTextView).setVisibility(View.INVISIBLE);

        final FlightAppDatabaseHelper db = new FlightAppDatabaseHelper(getApplicationContext());

        //Get airports
        airports = SearchUtility.getAirports(db);
        List<String> airportNames = new ArrayList<String>();
        for(Airport ap: airports)
            airportNames.add(ap.getAirportName());

        //Set the 'to' and 'from' MultiAutoCompleteTextViews
        ArrayAdapter<String> airportsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, airportNames);

        final AutoCompleteTextView originTextView = (AutoCompleteTextView) findViewById(R.id.originAutoCompleteTextView);
        final AutoCompleteTextView destTextView = (AutoCompleteTextView) findViewById(R.id.destAutoCompleteTextView);

        originTextView.setAdapter(airportsAdapter);
        destTextView.setAdapter(airportsAdapter);


        //Load months in the spinnerMonth object
        Spinner spinnerMonth = (Spinner) findViewById(R.id.spinnerMonth);
        List<String> months = new ArrayList<String>();
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");

        final ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, months);

        spinnerMonth.setAdapter(monthAdapter);

        //Days in a month list
        final List<String> days = new ArrayList<String>();
        for (int i = 1; i <= 31; i++)
            days.add(Integer.toString(i));

        //Get spinnerDay object
        final Spinner spinnerDay = (Spinner) findViewById(R.id.spinnerDay);

        //Load days in the spinnerDay object according to the month selected
        spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int numOfDays;
                //Check how many days the month selected has
                if(i == 1)
                    numOfDays = 28;
                else if(i == 3 || i == 5 || i == 8 || i == 10)
                    numOfDays = 30;
                else
                    numOfDays = 31;

                //Adapter for the spinner according to the month selected
                ArrayAdapter<String> dayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_spinner_item, days.subList(0, numOfDays));

                spinnerDay.setAdapter(dayAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Get spinnerYear object
        final Spinner spinnerYear = (Spinner) findViewById(R.id.spinnerYear);

        //Load years in the spinnerYear
        final List<String> years = new ArrayList<String>();
        int curYear = Calendar.getInstance().get(Calendar.YEAR); //Current year
        for(int i = curYear; i <= curYear + 2; i ++)
            years.add(Integer.toString(i));

        final ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, years);

        spinnerYear.setAdapter(yearAdapter);

        //Adapter for search button
        final AdapterView.OnClickListener searchFlights =
                new AdapterView.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        boolean validEntries = true;

                        //Validate data entered by the user by reading the UI objects' values
                        if(!ValidationUtility.isValidAirport(db, originTextView.getText().toString())) {
                            originTextView.setError("Please enter a valid airport");
                            validEntries = false;
                        }

                        if(!ValidationUtility.isValidAirport(db, destTextView.getText().toString())) {
                            destTextView.setError("Please enter a valid airport");
                            validEntries = false;
                        }



                        //If all validations succeed, procceed to the next activity
                        if(validEntries){
                            Intent intent = new Intent(getApplicationContext(), SearchResultsActivity.class);
                            //Pass the data entered by the user to the next activity

                            startActivity(intent);
                        }
                    }
                };

        //Get search button
        Button btnSearchFlights = (Button) findViewById(R.id.btnSearchFlights);
        btnSearchFlights.setOnClickListener(searchFlights);

    }
}
