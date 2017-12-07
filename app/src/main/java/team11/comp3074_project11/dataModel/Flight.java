package team11.comp3074_project11.dataModel;

import java.util.Date;

/**
 * Created by aline on 2017-12-07.
 */

public class Flight {
    private String flightId, flightNumber;
    private Date departureDateTime, arrivalDateTime;
    private double cost, travelTime;
    private Airport origin, destination;
    private Airline airline;

    public Flight(String flightId, String flightNumber, Date departureDateTime,
                  Date arrivalDateTime, double cost, double travelTime, Airport origin,
                  Airport destination, Airline airline) {
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.cost = cost;
        this.travelTime = travelTime;
        this.origin = origin;
        this.destination = destination;
        this.airline = airline;
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

    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightId='" + flightId + '\'' +
                ", flightNumber='" + flightNumber + '\'' +
                ", departureDateTime=" + departureDateTime +
                ", arrivalDateTime=" + arrivalDateTime +
                ", cost=" + cost +
                ", travelTime=" + travelTime +
                ", origin=" + origin +
                ", destination=" + destination +
                ", airline=" + airline +
                '}';
    }
}
