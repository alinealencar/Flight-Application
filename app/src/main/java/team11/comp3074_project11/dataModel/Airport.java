package team11.comp3074_project11.dataModel;


import team11.comp3074_project11.activity.SearchActivity;
import team11.comp3074_project11.helper.SearchUtility;

/**
 * Created by aline on 2017-12-07.
 */

public class Airport {
    private int airportId;
    private String airportName;

    public Airport() {
    }

    public Airport(String airportName) {
        this.airportName = airportName;
    }

    public Airport(int airportId, String airportName) {
        this.airportId = airportId;
        this.airportName = airportName;
    }

    public int getAirportId() {
        return airportId;
    }

    public void setAirportId(int airportId) {
        this.airportId = airportId;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "airportId='" + airportId + '\'' +
                ", airportName='" + airportName + '\'' +
                '}';
    }

}
