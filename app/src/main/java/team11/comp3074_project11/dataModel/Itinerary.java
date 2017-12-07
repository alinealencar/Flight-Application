package team11.comp3074_project11.dataModel;

import java.util.List;

/**
 * Created by aline on 2017-12-07.
 */

public class Itinerary {
    private String itineraryId, itineraryTitle;
    private List<Ticket> ticketList;

    public Itinerary(String itineraryId, String itineraryTitle, List<Ticket> ticketList) {
        this.itineraryId = itineraryId;
        this.itineraryTitle = itineraryTitle;
        this.ticketList = ticketList;
    }

    public String getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(String itineraryId) {
        this.itineraryId = itineraryId;
    }

    public String getItineraryTitle() {
        return itineraryTitle;
    }

    public void setItineraryTitle(String itineraryTitle) {
        this.itineraryTitle = itineraryTitle;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    @Override
    public String toString() {
        return "Itinerary{" +
                "itineraryId='" + itineraryId + '\'' +
                ", itineraryTitle='" + itineraryTitle + '\'' +
                ", ticketList=" + ticketList +
                '}';
    }
}
