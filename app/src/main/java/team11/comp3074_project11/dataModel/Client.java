package team11.comp3074_project11.dataModel;

import java.util.List;

/**
 * Created by aline on 2017-12-07.
 */

public class Client {
    private String clientId, firstName, lastName, email, password;
    private int creditCardNo;
    private List<Itinerary> itineraryList;

    public Client(String clientId, String firstName, String lastName,
                  String email, String password, int creditCardNo, List<Itinerary> itineraryList) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.creditCardNo = creditCardNo;
        this.itineraryList = itineraryList;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(int creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public List<Itinerary> getItineraryList() {
        return itineraryList;
    }

    public void setItineraryList(List<Itinerary> itineraryList) {
        this.itineraryList = itineraryList;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId='" + clientId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", creditCardNo=" + creditCardNo +
                ", itineraryList=" + itineraryList +
                '}';
    }
}
