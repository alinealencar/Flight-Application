package team11.comp3074_project11.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import team11.comp3074_project11.R;
import team11.comp3074_project11.dataModel.Flight;
import team11.comp3074_project11.dataModel.Itinerary;
import team11.comp3074_project11.database.FlightAppDatabaseHelper;
import team11.comp3074_project11.helper.SearchUtility;

public class ItinerariesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itineraries);

        ArrayList<Flight> itinerariesList;
        final FlightAppDatabaseHelper db = new FlightAppDatabaseHelper(getApplicationContext());
        try {
            //SQLiteOpenHelper dbHelper = new FlightAppDatabaseHelper(getApplicationContext());
            //SQLiteDatabase db = dbHelper.getReadableDatabase();

            //store selected flights by clientId to list
            itinerariesList = SearchUtility.getFlightByClient(db, db.getReadableDatabase(), 1);

            //populate listview
            ArrayAdapter<Flight> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,itinerariesList);
            ListView lv = findViewById(R.id.listItineraries);
            lv.setAdapter(adapter);
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
