package team11.comp3074_project11.dataModel;

/**
 * Created by aline on 2017-12-07.
 */

public class Ticket {
    private String ticketId, ticketNo;
    private Flight flight;

    public Ticket(String ticketId, String ticketNo, Flight flight) {
        this.ticketId = ticketId;
        this.ticketNo = ticketNo;
        this.flight = flight;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId='" + ticketId + '\'' +
                ", ticketNo='" + ticketNo + '\'' +
                ", flight=" + flight +
                '}';
    }
}
