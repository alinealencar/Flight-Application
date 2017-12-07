package team11.comp3074_project11.dataModel;

/**
 * Created by aline on 2017-12-07.
 */

public class Airport {
    private String airportId, airportName;

    public Airport(String airportId, String airportName) {
        this.airportId = airportId;
        this.airportName = airportName;
    }

    public String getAirportId() {
        return airportId;
    }

    public void setAirportId(String airportId) {
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
