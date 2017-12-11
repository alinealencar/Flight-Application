package team11.comp3074_project11.dataModel;

/**
 * Created by aline on 2017-12-07.
 */

public class Airline {
    private int airlineId;
    private String airlineName;
    private String airlineInitials;

    public Airline(String airlineName, String airlineInitials) {
        this.airlineName = airlineName;
        this.airlineInitials = airlineInitials;
    }

    public Airline(int airlineId, String airlineName, String airlineInitials) {
        this.airlineId = airlineId;
        this.airlineName = airlineName;
        this.airlineInitials = airlineInitials;
    }

    public int getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(int airlineId) {
        this.airlineId = airlineId;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getAirlineInitials() {
        return airlineInitials;
    }

    public void setAirlineInitials(String airlineInitials) {
        this.airlineInitials = airlineInitials;
    }

    @Override
    public String toString() {
        return "Airline{" +
                "airlineId=" + airlineId +
                ", airlineName='" + airlineName + '\'' +
                ", airlineInitials='" + airlineInitials + '\'' +
                '}';
    }
}
