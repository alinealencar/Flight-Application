package team11.comp3074_project11.dataModel;

/**
 * Created by aline on 2017-12-07.
 */

public class Itinerary {
    private int ticketId, flightId_FK, clientId_FK;

    public Itinerary(int ticketId, int flightId_FK, int clientId_FK) {
        this.ticketId = ticketId;
        this.flightId_FK = flightId_FK;
        this.clientId_FK = clientId_FK;
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

    public int getClientId_FK() {
        return clientId_FK;
    }

    public void setClientId_FK(int clientId_FK) {
        this.clientId_FK = clientId_FK;
    }

    @Override
    public String toString() {
        return "Itinerary{" +
                "ticketId=" + ticketId +
                ", flightId_FK=" + flightId_FK +
                ", clientId_FK=" + clientId_FK +
                '}';
    }
}
