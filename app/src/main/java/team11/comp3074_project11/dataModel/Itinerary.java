package team11.comp3074_project11.dataModel;

import java.util.List;

/**
 * Created by aline on 2017-12-07.
 */

public class Itinerary {
    private int itineraryId, clientId_FK;
    private String itineraryName;

    public Itinerary(int itineraryId, String itineraryName, int clientId_FK) {
        this.itineraryId = itineraryId;
        this.itineraryName = itineraryName;
        this.clientId_FK = clientId_FK;
    }

    public int getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(int itineraryId) {
        this.itineraryId = itineraryId;
    }

    public String getItineraryName() {
        return itineraryName;
    }

    public void setItineraryName(String itineraryName) {
        this.itineraryName = itineraryName;
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
                "itineraryId='" + itineraryId + '\'' +
                ", itineraryName='" + itineraryName + '\'' +
                ", clientId_FK='" + clientId_FK + '\'' +
                '}';
    }
}
