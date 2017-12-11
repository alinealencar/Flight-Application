package team11.comp3074_project11.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import team11.comp3074_project11.R;

public class ConfirmationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        Button btnItineraries = (Button) findViewById(R.id.btnItineraries);
        btnItineraries.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent nextActivity = new Intent(ConfirmationActivity.this, ItinerariesActivity.class);
                startActivity(nextActivity);
            }
        });
    }
}
