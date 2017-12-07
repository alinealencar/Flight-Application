package team11.comp3074_project11.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;
import java.text.SimpleDateFormat;

import team11.comp3074_project11.dataModel.Airline;
import team11.comp3074_project11.dataModel.Airport;
import team11.comp3074_project11.dataModel.Client;
import team11.comp3074_project11.dataModel.Flight;
import team11.comp3074_project11.dataModel.Itinerary;
import team11.comp3074_project11.dataModel.Ticket;

/**
 * Created by aline on 2017-12-07.
 */

public class FlightAppDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "flightAppDB";
    private static final int DB_VERSION = 1;

    //Tables
    private static final String CREATE_FLIGHT_TABLE = "CREATE TABLE tbl_flight (" +
            "flightId_PK INTEGER PRIMARY KEY AUTOINCREMENT," +
            "flightNumber TEXT," +
            "departureDateTime DATETIME," +
            "arrivalDateTime DATETIME," +
            "cost REAL," +
            "travelTime REAL," +
            "airlineId_FK INTEGER," +
            "originAiportId_FK INTEGER," +
            "destAirportId_FK INTEGER);";

    private static final String CREATE_AIRLINE_TABLE = "CREATE TABLE tbl_airline (" +
            "airlineId_PK INTEGER PRIMARY KEY AUTOINCREMENT," +
            "airlineName TEXT);";

    private static final String CREATE_AIRPORT_TABLE = "CREATE TABLE tbl_airport (" +
            "airportId_PK INTEGER PRIMARY KEY AUTOINCREMENT," +
            "airportName TEXT);";

    private static final String CREATE_TICKET_TABLE = "CREATE TABLE tbl_ticket (" +
            "ticketId_PK INTEGER PRIMARY KEY AUTOINCREMENT," +
            "flightId_FK INTEGER," +
            "itineraryId_FK INTEGER);";

    private static final String CREATE_ITINERARY_TABLE = "CREATE TABLE tbl_itinerary (" +
            "itineraryId_PK INTEGER PRIMARY KEY AUTOINCREMENT," +
            "itineraryName TEXT," +
            "clientId_FK INTEGER);";

    private static final String CREATE_CLIENT_TABLE = "CREATE TABLE tbl_client (" +
            "clientId_PK INTEGER PRIMARY KEY AUTOINCREMENT," +
            "firstName TEXT," +
            "lastName TEXT," +
            "email TEXT UNIQUE," +
            "password TEXT" +
            "creditCardNo TEXT);";

    public FlightAppDatabaseHelper(Context context){super(context, DB_NAME, null, DB_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_FLIGHT_TABLE);
        db.execSQL(CREATE_AIRLINE_TABLE);
        db.execSQL(CREATE_AIRPORT_TABLE);
        db.execSQL(CREATE_TICKET_TABLE);
        db.execSQL(CREATE_ITINERARY_TABLE);
        db.execSQL(CREATE_CLIENT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Insert Airports into the database
    public static void insertAirport(SQLiteDatabase db, Airport airport){
        ContentValues airportValues = new ContentValues();
        airportValues.put("airportName", airport.getAirportName());
        db.insert("tbl_airport", null, airportValues);
    }

    //Insert Airlines into the database
    public static void insertAirline(SQLiteDatabase db, Airline airline){
        ContentValues airlineValues = new ContentValues();
        airlineValues.put("airlineName", airline.getAirlineName());
        db.insert("tbl_airline", null, airlineValues);
    }

    //Insert Flight into the database
    public static void insertFlight(SQLiteDatabase db, Flight flight){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        ContentValues flightValues = new ContentValues();
        flightValues.put("flightId_PK", flight.getFlightId());
        flightValues.put("flightNumber", flight.getFlightNumber());
        flightValues.put("departureDateTime", dateFormat.format(flight.getDepartureDateTime()));
        flightValues.put("arrivalDateTime", dateFormat.format(flight.getArrivalDateTime()));
        flightValues.put("cost", flight.getCost());
        flightValues.put("travelTime", flight.getTravelTime());
        flightValues.put("airlineId_FK", flight.getAirlineId_FK());
        flightValues.put("originAirportId_FK", flight.getOriginAirportId_FK());
        flightValues.put("destAirportId_FK", flight.getDestAirportId_FK());
        db.insert("tbl_flight", null, flightValues);
    }

    //Insert Itinerary into the database
    public static void insertItinerary(SQLiteDatabase db, Itinerary itinerary){
        ContentValues itineraryValues = new ContentValues();
        itineraryValues.put("itineraryId_PK", itinerary.getItineraryId());
        itineraryValues.put("itineraryName", itinerary.getItineraryName());
        itineraryValues.put("clientId_FK", itinerary.getClientId_FK());
        db.insert("tbl_itinerary", null, itineraryValues);
    }

    //Overloaded method
    public void insertItinerary(Itinerary itinerary){
        insertItinerary(this.getWritableDatabase(), itinerary);
    }

    //Insert Ticket into the database
    public static void insertTicket(SQLiteDatabase db, Ticket ticket){
        ContentValues ticketValues = new ContentValues();
        ticketValues.put("ticketId_PK", ticket.getTicketId());
        ticketValues.put("flightId_FK", ticket.getFlightId_FK());
        ticketValues.put("itineraryId_FK", ticket.getItinerary_FK());
        db.insert("tbl_ticket", null, ticketValues);
    }

    //Overloaded method
    public void insertTicket(Ticket ticket){
        insertTicket(this.getWritableDatabase(), ticket);
    }

    //Insert Client into the database
    public static void insertClient(SQLiteDatabase db, Client client){
        ContentValues clientValues = new ContentValues();
        clientValues.put("clientId_PK", client.getClientId());
        clientValues.put("firstName", client.getFirstName());
        clientValues.put("lastName", client.getLastName());
        clientValues.put("email", client.getEmail());
        clientValues.put("password", client.getPassword());
        clientValues.put("creditCardNo", client.getCreditCardNo());
        db.insert("tbl_client", null, clientValues);
    }

    //Overloaded method
    public void insertClient(Client client){
        insertClient(this.getWritableDatabase(), client);
    }

}
