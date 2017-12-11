package team11.comp3074_project11.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import team11.comp3074_project11.dataModel.Airline;
import team11.comp3074_project11.dataModel.Airport;
import team11.comp3074_project11.dataModel.Client;
import team11.comp3074_project11.dataModel.Flight;
import team11.comp3074_project11.dataModel.Itinerary;
import team11.comp3074_project11.helper.SearchUtility;

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
            "originAirportId_FK INTEGER," +
            "destAirportId_FK INTEGER);";

    private static final String CREATE_AIRLINE_TABLE = "CREATE TABLE tbl_airline (" +
            "airlineId_PK INTEGER PRIMARY KEY AUTOINCREMENT," +
            "airlineName TEXT," +
            "airlineInitials TEXT);";

    private static final String CREATE_AIRPORT_TABLE = "CREATE TABLE tbl_airport (" +
            "airportId_PK INTEGER PRIMARY KEY AUTOINCREMENT," +
            "airportName TEXT);";

    private static final String CREATE_ITINERARY_TABLE = "CREATE TABLE tbl_itinerary (" +
            "itineraryId_PK INTEGER PRIMARY KEY AUTOINCREMENT," +
            "flightId_FK INTEGER," +
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
        db.execSQL(CREATE_ITINERARY_TABLE);
        db.execSQL(CREATE_CLIENT_TABLE);

        //Insert airports
        insertAirport(db, new Airport("Toronto - YYZ"));
        insertAirport(db, new Airport("Calgary - YYC"));
        insertAirport(db, new Airport("Vancouver - YVR"));
        insertAirport(db, new Airport("Montreal - YUL"));
        insertAirport(db, new Airport("Edmonton - YEG"));
        insertAirport(db, new Airport("Halifax - YHZ"));
        insertAirport(db, new Airport("Québec City - YQB"));
        insertAirport(db, new Airport("Regina - YQR"));

        //Insert airlines
        insertAirline(db, new Airline("Air Canada", "AC"));
        insertAirline(db, new Airline("WestJet", "WJ"));
        insertAirline(db, new Airline("Delta", "DT"));
        insertAirline(db, new Airline("American Airlines", "AA"));

        //Insert flights
        try {
            List<Flight> flights = generateFlights(db);
            for (Flight flight : flights){
                insertFlight(db, flight);
                System.out.println(flight.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


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
        //Convert to format suitable to store in the database
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

        ContentValues flightValues = new ContentValues();
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
        itineraryValues.put("flightId_FK", itinerary.getFlightId_FK());
        itineraryValues.put("clientId_FK", itinerary.getClientId_FK());
        db.insert("tbl_itinerary", null, itineraryValues);
    }

    //Overloaded method
    public void insertItinerary(Itinerary itinerary){
        insertItinerary(this.getWritableDatabase(), itinerary);
    }

    //Insert Client into the database
    public static void insertClient(SQLiteDatabase db, Client client){
        ContentValues clientValues = new ContentValues();
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

    /**
     * Authenticate client based on email and password.
     *
     * @param email     String that represents the client's email stored in the database.
     * @param password  String that represents the client's password stored in the database.
     * @return  Client  If a record that matches the email/password combination criteria is not found,
     *                  the object returned is null.
     */
    public Client authenticateClient(String email, String password){
        Client client = null;
        //Select query
        String selectClient = "SELECT * FROM tbl_client WHERE email IS '" + email + "' AND " +
                "password IS '" + password + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectClient, null);

        //Returns true if a record is found, false otherwise.
        if (cursor.moveToFirst())
            client = new Client(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5));

        return client;
    }

    /**
     * Update client information.
     *
     * @param db            SQLiteDatabase object
     * @param client        Client object with the updated information
     * @param clientId      Integer that represents the client ID of the client whose
     *                      information will be updated.
     */
    public void updateClient(SQLiteDatabase db, Client client, int clientId){
        ContentValues clientValues = new ContentValues();
        clientValues.put("firstName", client.getFirstName());
        clientValues.put("lastName", client.getLastName());
        clientValues.put("email", client.getEmail());
        clientValues.put("password", client.getPassword());
        clientValues.put("creditCardNo", client.getCreditCardNo());
        db.update("tbl_client", clientValues, "clientId_PK = " + clientId, null);
    }

    public List<Flight> generateFlights(SQLiteDatabase db) throws ParseException {
        List<Flight> randomFlights = new ArrayList<Flight>();

        //Get all airports
        List<Airport> airports = SearchUtility.getAirports(this, db);
        List<Airline> airlines = SearchUtility.getAirlines(this);

        int numOfAirlines = airlines.size();

        //Tools
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        Date curDate = new Date();


        List<Date> randomDates = new ArrayList<Date>();
        randomDates.add(dateFormat.parse("12-30-2017 13:15:00"));
        randomDates.add(dateFormat.parse("03-01-2018 14:24:00"));
        randomDates.add(dateFormat.parse("02-26-2018 21:45:00"));

        int numOfDates = randomDates.size();

        //Randomly generate flights
        for(int i = 0; i < airports.size(); i++){ //Origin
            for(int j = 0; j < airports.size(); j++){ //Destination
                //If the origin equals the destination, continue
                if(airports.get(i).getAirportId() == airports.get(j).getAirportId())
                    continue;
                //Get random airline
                Airline anAirline = airlines.get((int) Math.random()*(numOfAirlines-1));
                String randomFlightNumber = anAirline.getAirlineInitials() + Integer.toString((int) Math.random()*(999-100) + 100);

                //Get random departure and arrival dates, and travel time
                int travelTime = (int) Math.random()*(10-1) + 1;
                Calendar cal = Calendar.getInstance();
                Date departureDate = randomDates.get(2);
                cal.add(Calendar.HOUR, travelTime);
                Date arrivalDate = cal.getTime(); //5 hours long flight

                //Get random cost
                double cost = travelTime * 100;

                Flight aFlight = new Flight(airports.get(i).getAirportId(), airports.get(j).getAirportId(),
                       anAirline.getAirlineId(), randomFlightNumber, departureDate, arrivalDate, cost, travelTime);

                randomFlights.add(aFlight);

            }
        }

        return randomFlights;
    }

}
