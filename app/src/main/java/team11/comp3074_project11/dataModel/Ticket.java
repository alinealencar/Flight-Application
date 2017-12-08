package team11.comp3074_project11.dataModel;

/**
 * Created by aline on 2017-12-07.
 */

public class Ticket {
    private int ticketId, flightId_FK, itinerary_FK;

    public Ticket(int ticketId, int flightId_FK, int itinerary_FK) {
        this.ticketId = ticketId;
        this.flightId_FK = flightId_FK;
        this.itinerary_FK = itinerary_FK;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getFlightId_FK() {
        return flightId_FK;
    }

    public void setFlightId_FK(int flightId_FK) {
        this.flightId_FK = flightId_FK;
    }

    public int getItinerary_FK() {
        return itinerary_FK;
    }

    public void setItinerary_FK(int itinerary_FK) {
        this.itinerary_FK = itinerary_FK;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId='" + ticketId + '\'' +
                ", flightId_FK='" + flightId_FK + '\'' +
                ", itinerary_FK='" + itinerary_FK + '\'' +
                '}';
    }
}
