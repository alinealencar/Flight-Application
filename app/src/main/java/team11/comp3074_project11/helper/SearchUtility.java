package team11.comp3074_project11.helper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import team11.comp3074_project11.dataModel.Airline;
import team11.comp3074_project11.dataModel.Airport;
import team11.comp3074_project11.dataModel.Client;
import team11.comp3074_project11.dataModel.Flight;
import team11.comp3074_project11.dataModel.Itinerary;
import team11.comp3074_project11.database.FlightAppDatabaseHelper;

/**
 * Created by aline on 2017-12-08.
 */

public class SearchUtility {
    /**
     * Get all airports from the database.
     *
     * @param   flightDb        A FlightAppDatabaseHelper object.
     * @return  List<Airport>   A list with Airport objects.
     */
    public static List<Airport>  getAirports(FlightAppDatabaseHelper flightDb, SQLiteDatabase db){
        List<Airport> airports = new ArrayList<Airport>();

        //Select query
        String selectAirports = "SELECT * FROM tbl_airport";
        //SQLiteDatabase db = flightDb.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectAirports, null);

        if(cursor.moveToFirst()){
            do {
                Airport airport = new Airport(cursor.getInt(0), cursor.getString(1));
                airports.add(airport);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return airports;
    }

    /**
     * Get all airlines stored in the database.
     *
     * @param flightDb
     * @return
     */
    public static List<Airline> getAirlines(FlightAppDatabaseHelper flightDb, SQLiteDatabase db){
        List<Airline> airlines = new ArrayList<Airline>();

        //Select query
        String selectAirlines = "SELECT * FROM tbl_airline";
        //SQLiteDatabase db = flightDb.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectAirlines, null);

        if(cursor.moveToFirst()){
            do {
                Airline airline = new Airline(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                airlines.add(airline);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return airlines;
    }

    /**
     * Get all flights stored in the database.
     *
     * @param flightDb
     * @return
     * @throws ParseException
     */
    public static List<Flight> getAllFlights(FlightAppDatabaseHelper flightDb, SQLiteDatabase db) {
        List<Flight> flights = new ArrayList<Flight>();

        //Select query
        String selectAirlines = "SELECT * FROM tbl_flight";
        //SQLiteDatabase db = flightDb.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectAirlines, null);

        if(cursor.moveToFirst()){
            do {
                Flight flight = new Flight();
                flight.setFlightId(cursor.getInt(0));
                flight.setFlightNumber(cursor.getString(1));
                flight.setDepartureDate(cursor.getString(2));
                flight.setArrivalDate(cursor.getString(3));
                flight.setCost(cursor.getDouble(4));
                flight.setTravelTime(cursor.getDouble(5));
                flight.setDepartureTime(cursor.getDouble(6));
                flight.setAirlineId_FK(cursor.getInt(7));
                flight.setOriginAirportId_FK(cursor.getInt(8));
                flight.setDestAirportId_FK(cursor.getInt(9));
                flights.add(flight);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return flights;
    }

    /**
     * Get the airline of a specific flight.
     *
     * @param flightDb
     * @param flight
     * @return
     */
    public static Airline getAirlineByFlight(FlightAppDatabaseHelper flightDb, Flight flight){
        Airline airline = null;

        //Select query
        String selectAirline = "SELECT * FROM tbl_airline WHERE airlineId_PK IS '" +
                flight.getAirlineId_FK() + "'";
        SQLiteDatabase db = flightDb.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectAirline, null);

        if(cursor.moveToFirst())
            airline = new Airline(cursor.getInt(0), cursor.getString(1), cursor.getString(2));

        return airline;
    }

    /**
     * Get the Primary Key of an Airport by its name
     *
     * @param flightDb
     * @param airportName
     * @return
     */
    public static Airport getAirportPKByName(FlightAppDatabaseHelper flightDb, String airportName){
        Airport airport = null;

        //Select query
        String selectAirline = "SELECT * FROM tbl_airport WHERE airportName IS '" +
                airportName + "'";
        SQLiteDatabase db = flightDb.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectAirline, null);

        if(cursor.moveToFirst())
            airport = new Airport(cursor.getInt(0), cursor.getString(1));

        return airport;
    }

    public static Airport getAirportNameByPK(FlightAppDatabaseHelper flightDb, int airportId){
        Airport airport = new Airport();

        //Select query
        SQLiteDatabase db = flightDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_airport WHERE airportId_PK = " + airportId, null);

        if(cursor.moveToFirst()){
            airport.setAirportId(cursor.getInt(0));
            airport.setAirportName(cursor.getString(1));
        }

        return airport;
    }

    public static Client getClientByPK(FlightAppDatabaseHelper flightDb, int clientId){
        Client client = new Client();

        //Select query
        //Select query
        SQLiteDatabase db = flightDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_client WHERE clientId_PK = " + clientId, null);

        if(cursor.moveToFirst()){
            client.setClientId(cursor.getInt(0));
            client.setFirstName(cursor.getString(1));
            client.setLastName(cursor.getString(2));
            client.setEmail(cursor.getString(3));
            //Do not store the password in the object, therefore, skip 4
            client.setCreditCardNo(cursor.getString(5));
        }

        return client;
    }

    /**
     * Get all flights based on origin,  destination and departure date.
     *
     * @param flightDb          A FlightAppDatabaseHelper object.
     * @param origin            Airport object that represents the flight origin.
     * @param destination       Airport object that represents the flight destination.
     * @param departureDate     Date object that represents the flight's departure date.
     * @return List<Flight>     List of Flight objects that match the search criteria
     * @throws ParseException   Can be thrown during the conversion from the database (it's going to be read as a String)
     *                          to the Date object format. Exception is escalated to the next level.
     */
    public static List<Flight> getFlights(FlightAppDatabaseHelper flightDb, Airport origin, Airport destination, String departureDate)
            throws ParseException {
        List<Flight> flights = new ArrayList<Flight>();

        //Select query
        String selectFlights = "SELECT * FROM tbl_flight WHERE originAirportId_FK IS '" + origin.getAirportId() +
                "' AND destAirportId_FK IS '" + destination.getAirportId() + "' AND departureDate IS '" + departureDate + "'";
        SQLiteDatabase db = flightDb.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectFlights, null);

        if(cursor.moveToFirst()){
            do {
                Flight flight = new Flight();
                flight.setFlightId(cursor.getInt(0));
                flight.setFlightNumber(cursor.getString(1));
                flight.setDepartureDate(cursor.getString(2));
                flight.setArrivalDate(cursor.getString(3));
                flight.setCost(cursor.getDouble(4));
                flight.setTravelTime(cursor.getDouble(5));
                flight.setDepartureTime(cursor.getDouble(6));
                flight.setAirlineId_FK(cursor.getInt(7));
                flight.setOriginAirportId_FK(cursor.getInt(8));
                flight.setDestAirportId_FK(cursor.getInt(9));
                flights.add(flight);
            } while (cursor.moveToNext());
        }

        return flights;
    }

    public static Flight getFlightByPK(FlightAppDatabaseHelper flightDb, int flightId){
        Flight flight = new Flight();

        //Select query
        SQLiteDatabase db = flightDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_flight WHERE flightId_PK = " + flightId, null);

        if(cursor.moveToFirst()){
            do {
                flight.setFlightId(cursor.getInt(0));
                flight.setFlightNumber(cursor.getString(1));
                flight.setDepartureDate(cursor.getString(2));
                flight.setArrivalDate(cursor.getString(3));
                flight.setCost(cursor.getDouble(4));
                flight.setTravelTime(cursor.getDouble(5));
                flight.setDepartureTime(cursor.getDouble(6));
                flight.setAirlineId_FK(cursor.getInt(7));
                flight.setOriginAirportId_FK(cursor.getInt(8));
                flight.setDestAirportId_FK(cursor.getInt(9));
            } while (cursor.moveToNext());
        }

        cursor.close();

        return flight;
    }

    /**
     * Get all flights that are in itineraries of a certain client
     *
     * @param flightDb
     * @param clientId
     * @return
     */
    public static List<Flight> getFlightByClient(FlightAppDatabaseHelper flightDb, int clientId) {
        List<Flight> flights = new ArrayList<Flight>();

        //Select query
        SQLiteDatabase db = flightDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_flight INNER JOIN tbl_itinerary " +
                "ON tbl_flight.flightId_PK = tbl_itinerary.flightId_FK WHERE tbl_itinerary.clientId_FK = " + clientId, null);

        if(cursor.moveToFirst()){
            do {
                Flight flight = new Flight();
                flight.setFlightId(cursor.getInt(0));
                flight.setFlightNumber(cursor.getString(1));
                flight.setDepartureDate(cursor.getString(2));
                flight.setArrivalDate(cursor.getString(3));
                flight.setCost(cursor.getDouble(4));
                flight.setTravelTime(cursor.getDouble(5));
                flight.setDepartureTime(cursor.getDouble(6));
                flight.setAirlineId_FK(cursor.getInt(7));
                flight.setOriginAirportId_FK(cursor.getInt(8));
                flight.setDestAirportId_FK(cursor.getInt(9));
                flights.add(flight);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return flights;
    }


    /**
     * Get all itineraries that belong to a certain client.
     *
     * @param   flightDb         A FlightAppDatabaseHelper object.
     * @param   client           A Client object.
     * @return  List<Itinerary>     A list of Itinerary objects.
     */
    public static List<Itinerary> getItineraries(FlightAppDatabaseHelper flightDb, Client client){
        List<Itinerary> itineraries = new ArrayList<Itinerary>();

        //Select query
        String selectItineraries = "SELECT * FROM tbl_itinerary WHERE clientId_FK IS '" + client.getClientId() + "'";
        SQLiteDatabase db = flightDb.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectItineraries, null);

        if(cursor.moveToFirst()){
            do {
                Itinerary itinerary = new Itinerary(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2));
            } while (cursor.moveToNext());
        }

        cursor.close();

        return itineraries;
    }
}