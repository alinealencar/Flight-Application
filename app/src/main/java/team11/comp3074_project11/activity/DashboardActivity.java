package team11.comp3074_project11.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import team11.comp3074_project11.R;

/**
 * Created by Owner on 12/9/2017.
 */

public class DashboardActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Get User id and name from the intent
        Intent intent = getIntent();
    }

    //when click Search button, the page change to SearchActivity
    public void onClickSearch(View v){
        Intent intent = new Intent(DashboardActivity.this, SearchActivity.class);
        startActivity(intent);
    }

    //when click Itineraries button, the page change to ItinerariesActivity
    public void onClickItineraries(View v){
        Intent intent = new Intent(DashboardActivity.this, ItinerariesActivity.class);
        startActivity(intent);
    }

/*    //when click My Profile button, the page change to ProfileActivity
    public void onClickProfile(View v){
        Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
        startActivity(intent);
    }
<<<<<<< HEAD
*/
||||||| merged common ancestors

=======
>>>>>>> 65f1a82d4662989fcdb19d854af8d481de24c6c9
    //when click Log Out button, the page change to LoginActivity
    public void onClickLogOut(View v){
        Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
