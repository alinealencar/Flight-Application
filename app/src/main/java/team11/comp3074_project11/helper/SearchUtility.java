package team11.comp3074_project11.helper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
                "' AND destAirportId_FK IS '" + destination.getAirportId() + "' AND departureDateTime IS '" + departureDate + "'";
        SQLiteDatabase db = flightDb.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectFlights, null);

        if(cursor.moveToFirst()){
            do {
                Flight flight = new Flight();
                flight.setFlightId(cursor.getInt(0));
                flight.setFlightNumber(cursor.getString(1));
                flight.setDepartureDateTime(cursor.getString(2));
                flight.setArrivalDateTime(cursor.getString(3));
                flight.setCost(cursor.getDouble(4));
                flight.setTravelTime(cursor.getDouble(5));
                flight.setAirlineId_FK(cursor.getInt(6));
                flight.setOriginAirportId_FK(cursor.getInt(7));
                flight.setDestAirportId_FK(cursor.getInt(8));
                flights.add(flight);
            } while (cursor.moveToNext());
        }

        return flights;
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
                flight.setDepartureDateTime(cursor.getString(2));
                flight.setArrivalDateTime(cursor.getString(3));
                flight.setCost(cursor.getDouble(4));
                flight.setTravelTime(cursor.getDouble(5));
                flight.setAirlineId_FK(cursor.getInt(6));
                flight.setOriginAirportId_FK(cursor.getInt(7));
                flight.setDestAirportId_FK(cursor.getInt(8));
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

    public static ArrayList<Flight> getFlightByClient(FlightAppDatabaseHelper flightDb, SQLiteDatabase db, int clientId) {
        ArrayList<Flight> flights = new ArrayList<Flight>();

        //Select query
       // SQLiteDatabase db = flightDb.getReadableDatabase();
        //Cursor cursor = db.rawQuery("SELECT * FROM tbl_flight INNER JOIN tbl_itinerary ON tbl_flight.flightId_PK = tbl_itinerary.flightId_FK WHERE tbl_itinerary.clientId_FK = " + clientId, null);
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_flight", null);
        //SQLiteDatabase db = flightDb.getReadableDatabase();
        //Cursor cursor = db.rawQuery(selectFlights);

       /* if(cursor.moveToFirst()){
            do {
                Flight flight = new Flight();
                flight.setFlightId(cursor.getInt(0));
                flight.setFlightNumber(cursor.getString(1));
                flight.setDepartureDateTime(cursor.getString(2));
                flight.setArrivalDateTime(cursor.getString(3));
                flight.setCost(cursor.getDouble(4));
                flight.setTravelTime(cursor.getDouble(5));
                flight.setAirlineId_FK(cursor.getInt(6));
                flight.setOriginAirportId_FK(cursor.getInt(7));
                flight.setDestAirportId_FK(cursor.getInt(8));
                flights.add(flight);
            } while (cursor.moveToNext());
        }*/

<<<<<<< HEAD
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Flight flight = new Flight(cursor.getInt(0), cursor.getString(1), cursor.getInt(2),
                        cursor.getInt(3), cursor.getInt(4), cursor.getString(5), cursor.getString(6),
                        cursor.getDouble(7), cursor.getDouble(8));
                flights.add(flight);
            }
        }
=======
        cursor.close();
>>>>>>> 23b408abb7101537b6595158f29175296a68233f

            cursor.close();
            db.close();

            return flights;
        }
    }