package team11.comp3074_project11.dataModel;

/**
 * Created by aline on 2017-12-07.
 */

public class Airline {
    private String airlineId, airlineName;

    public Airline(String airlineId, String airlineName) {
        this.airlineId = airlineId;
        this.airlineName = airlineName;
    }

    public String getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(String airlineId) {
        this.airlineId = airlineId;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    @Override
    public String toString() {
        return "Airline{" +
                "airlineId='" + airlineId + '\'' +
                ", airlineName='" + airlineName + '\'' +
                '}';
    }
}
