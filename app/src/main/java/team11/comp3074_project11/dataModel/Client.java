package team11.comp3074_project11.dataModel;

import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.List;

/**
 * Created by aline on 2017-12-07.
 */

public class Client{
    private int clientId;
    private String firstName, lastName, email, password;
    private String creditCardNo;

    public Client(){}

    public Client(String firstName, String lastName, String email, String password, String creditCardNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.creditCardNo = creditCardNo;
    }

    public Client(int clientId, String firstName, String lastName,
                  String email, String password, String creditCardNo) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.creditCardNo = creditCardNo;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
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

    public String getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
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
                '}';
    }
}
