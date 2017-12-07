package team11.comp3074_project11.dataModel;

import java.util.List;

/**
 * Created by aline on 2017-12-07.
 */

public class Itinerary {
    private String itineraryId, itineraryName, clientId_FK;

    public Itinerary(String itineraryId, String itineraryName, String clientId_FK) {
        this.itineraryId = itineraryId;
        this.itineraryName = itineraryName;
        this.clientId_FK = clientId_FK;
    }

    public String getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(String itineraryId) {
        this.itineraryId = itineraryId;
    }

    public String getItineraryName() {
        return itineraryName;
    }

    public void setItineraryName(String itineraryName) {
        this.itineraryName = itineraryName;
    }

    public String getClientId_FK() {
        return clientId_FK;
    }

    public void setClientId_FK(String clientId_FK) {
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
