package team11.comp3074_project11.helper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import team11.comp3074_project11.dataModel.Airport;
import team11.comp3074_project11.dataModel.Client;
import team11.comp3074_project11.dataModel.Flight;
import team11.comp3074_project11.dataModel.Itinerary;
import team11.comp3074_project11.dataModel.Ticket;
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
    public static List<Airport>  getAirports(FlightAppDatabaseHelper flightDb){
        List<Airport> airports = new ArrayList<Airport>();

        //Select query
        String selectAirports = "SELECT * FROM tbl_airport";
        SQLiteDatabase db = flightDb.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectAirports, null);

        if(cursor.moveToFirst()){
            do {
                Airport airport = new Airport(cursor.getInt(0), cursor.getString(1));
                airports.add(airport);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return airports;
    }

    /**
     * Get all itineraries for a certain client.
     *
     * @param   flightDb            A FlightAppDatabaseHelper object.
     * @param   client              A Client object.
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
                Itinerary itinerary = new Itinerary(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
                itineraries.add(itinerary);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return itineraries;
    }

    /**
     * Get all flights based on origin,  destination and departure date.
     * @param flightDb          A FlightAppDatabaseHelper object.
     * @param origin            Airport object that represents the flight origin.
     * @param destination       Airport object that represents the flight destination.
     * @param departureDate     Date object that represents the flight's departure date.
     * @return List<Flight>     List of Flight objects that match the search criteria
     * @throws ParseException   Can be thrown during the conversion from the database (it's going to be read as a String)
     *                          to the Date object format. Exception is escalated to the next level.
     */
    public static List<Flight> getFlights(FlightAppDatabaseHelper flightDb, Airport origin, Airport destination, Date departureDate)
            throws ParseException {
        List<Flight> flights = new ArrayList<Flight>();

        //Select query
        String selectFlights = "SELECT * FROM tbl_flight WHERE originAirportId_FK IS '" + origin.getAirportId() +
                "' AND destAirportId_FK IS '" + destination.getAirportId() + "' AND departureDateTime IS '" + departureDate + "'";
        SQLiteDatabase db = flightDb.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectFlights, null);

        if(cursor.moveToFirst()){
            //The data from the database can only be read as a string.
            // Convert it to Date format so it can be stored in the Flight object.
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

            do {
                Flight flight = new Flight(cursor.getInt(0), cursor.getString(1), cursor.getInt(2),
                        cursor.getInt(3), cursor.getInt(4), df.parse(cursor.getString(5)), df.parse(cursor.getString(6)),
                        cursor.getDouble(7), cursor.getDouble(8));
                flights.add(flight);
            } while (cursor.moveToNext());
        }

        return flights;
    }

    /**
     * Get all tickets that belong in a certain itinerary.
     *
     * @param flightDb          A FlightAppDatabaseHelper object.
     * @param itinerary         An Itinerary object.
     * @return List<Ticket>     List of Ticket objects that match the search criteria.
     */
    public static List<Ticket> getTickets(FlightAppDatabaseHelper flightDb, Itinerary itinerary){
        List<Ticket> tickets = new ArrayList<Ticket>();

        //Select query
        String selectTickets = "SELECT * FROM tbl_ticket WHERE itineraryId_FK IS '" + itinerary.getItineraryId() + "'";
        SQLiteDatabase db = flightDb.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectTickets, null);

        if(cursor.moveToFirst()){
            do {
                Ticket ticket = new Ticket(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2));
                tickets.add(ticket);
            } while (cursor.moveToNext());
        }

        return tickets;
    }
}
