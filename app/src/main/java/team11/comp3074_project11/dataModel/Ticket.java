package team11.comp3074_project11.dataModel;

/**
 * Created by aline on 2017-12-07.
 */

public class Ticket {
    private String ticketId, flightId_FK, itinerary_FK;

    public Ticket(String ticketId, String flightId_FK, String itinerary_FK) {
        this.ticketId = ticketId;
        this.flightId_FK = flightId_FK;
        this.itinerary_FK = itinerary_FK;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getFlightId_FK() {
        return flightId_FK;
    }

    public void setFlightId_FK(String flightId_FK) {
        this.flightId_FK = flightId_FK;
    }

    public String getItinerary_FK() {
        return itinerary_FK;
    }

    public void setItinerary_FK(String itinerary_FK) {
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
