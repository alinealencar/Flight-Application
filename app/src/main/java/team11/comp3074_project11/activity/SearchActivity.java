package team11.comp3074_project11.activity;

import android.app.Activity;
import android.app.IntentService;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import team11.comp3074_project11.R;
import team11.comp3074_project11.dataModel.Airport;
import team11.comp3074_project11.database.FlightAppDatabaseHelper;
import team11.comp3074_project11.helper.SearchUtility;

public class SearchActivity extends Activity {
    List<Airport> airports;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        FlightAppDatabaseHelper db = new FlightAppDatabaseHelper(getApplicationContext());

        //Get airports
        airports = SearchUtility.getAirports(db);
        List<String> airportNames = new ArrayList<String>();
        for(Airport ap: airports)
            airportNames.add(ap.getAirportName());

        //Set the 'to' and 'from' MultiAutoCompleteTextViews
        ArrayAdapter<String> airportsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, airportNames);

        MultiAutoCompleteTextView originTextView = (MultiAutoCompleteTextView) findViewById(R.id.OriginAutoCompleteTxtView);
        MultiAutoCompleteTextView destTextView = (MultiAutoCompleteTextView) findViewById(R.id.DestAutoCompleteTxtView);

        originTextView.setAdapter(airportsAdapter);
        destTextView.setAdapter(airportsAdapter);

        originTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        destTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());



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
        for(int i = 2017; i <= 2019; i ++)
            years.add(Integer.toString(i));

        final ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, years);

        spinnerYear.setAdapter(yearAdapter);


    }
}
