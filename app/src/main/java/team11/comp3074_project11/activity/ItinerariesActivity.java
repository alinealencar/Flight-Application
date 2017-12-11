package team11.comp3074_project11.activity;

import android.app.Activity;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import team11.comp3074_project11.R;
import team11.comp3074_project11.dataModel.Itinerary;
import team11.comp3074_project11.database.FlightAppDatabaseHelper;
import team11.comp3074_project11.helper.SearchUtility;

public class ItinerariesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itineraries);

        ArrayList<Itinerary> itinerariesList = new ArrayList<Itinerary>();
        try {
            SQLiteOpenHelper dbHelper = new FlightAppDatabaseHelper(getApplicationContext());
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            //store selected flights by clientId to list
            //itinerariesList = SearchUtility.getFlightByClient(db,client);

            //populate listview
            //ArrayAdapter<Itinerary> adapter = new ArrayAdapter<Itinerary>(this,android.R.layout.simple_list_item_1,itinerariesList);
            //ListView lv = (ListView) findViewById(R.id.listItineraries);
            //lv.setAdapter(adapter);
        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(), "Database error. " + e + "Please try again.", Toast.LENGTH_SHORT).show();
        }

    }
}
