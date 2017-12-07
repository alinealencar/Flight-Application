package team11.comp3074_project11.dataModel;

import java.util.Date;

/**
 * Created by aline on 2017-12-07.
 */

public class Flight {
    private String flightId, flightNumber, originAirportId_FK, destAirportId_FK, airlineId_FK;
    private Date departureDateTime, arrivalDateTime;
    private double cost, travelTime;

    public Flight(String flightId, String flightNumber, String originAirportId_FK,
                  String destAirportId_FK, String airlineId_FK, Date departureDateTime,
                  Date arrivalDateTime, double cost, double travelTime) {
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.originAirportId_FK = originAirportId_FK;
        this.destAirportId_FK = destAirportId_FK;
        this.airlineId_FK = airlineId_FK;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.cost = cost;
        this.travelTime = travelTime;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOriginAirportId_FK() {
        return originAirportId_FK;
    }

    public void setOriginAirportId_FK(String originAirportId_FK) {
        this.originAirportId_FK = originAirportId_FK;
    }

    public String getDestAirportId_FK() {
        return destAirportId_FK;
    }

    public void setDestAirportId_FK(String destAirportId_FK) {
        this.destAirportId_FK = destAirportId_FK;
    }

    public String getAirlineId_FK() {
        return airlineId_FK;
    }

    public void setAirlineId_FK(String airlineId_FK) {
        this.airlineId_FK = airlineId_FK;
    }

    public Date getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(Date departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public Date getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(Date arrivalDateTime) {
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
                "flightId='" + flightId + '\'' +
                ", flightNumber='" + flightNumber + '\'' +
                ", originAirportId_FK='" + originAirportId_FK + '\'' +
                ", destAirportId_FK='" + destAirportId_FK + '\'' +
                ", airlineId_FK='" + airlineId_FK + '\'' +
                ", departureDateTime=" + departureDateTime +
                ", arrivalDateTime=" + arrivalDateTime +
                ", cost=" + cost +
                ", travelTime=" + travelTime +
                '}';
    }
}
