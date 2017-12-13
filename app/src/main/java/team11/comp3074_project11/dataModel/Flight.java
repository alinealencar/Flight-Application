package team11.comp3074_project11.dataModel;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by aline on 2017-12-07.
 */

public class Flight implements Comparable<Flight> {
    private int flightId, originAirportId_FK, destAirportId_FK, airlineId_FK;
    private String flightNumber, departureDateTime, arrivalDateTime;
    private double cost, travelTime;

    public Flight() {
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public int getOriginAirportId_FK() {
        return originAirportId_FK;
    }

    public void setOriginAirportId_FK(int originAirportId_FK) {
        this.originAirportId_FK = originAirportId_FK;
    }

    public int getDestAirportId_FK() {
        return destAirportId_FK;
    }

    public void setDestAirportId_FK(int destAirportId_FK) {
        this.destAirportId_FK = destAirportId_FK;
    }

    public int getAirlineId_FK() {
        return airlineId_FK;
    }

    public void setAirlineId_FK(int airlineId_FK) {
        this.airlineId_FK = airlineId_FK;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(String departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public String getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(String arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(double travelTime) {
        this.travelTime = travelTime;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightId=" + flightId +
                ", originAirportId_FK=" + originAirportId_FK +
                ", destAirportId_FK=" + destAirportId_FK +
                ", airlineId_FK=" + airlineId_FK +
                ", flightNumber='" + flightNumber + '\'' +
                ", departureDateTime='" + departureDateTime + '\'' +
                ", arrivalDateTime='" + arrivalDateTime + '\'' +
                ", cost=" + cost +
                ", travelTime=" + travelTime +
                '}';
    }

    @Override
    public int compareTo(@NonNull Flight flight) {
        return Double.compare(this.getCost(), flight.getCost());
    }
}
