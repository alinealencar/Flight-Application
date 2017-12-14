package team11.comp3074_project11.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import team11.comp3074_project11.dataModel.Airline;
import team11.comp3074_project11.dataModel.Airport;
import team11.comp3074_project11.dataModel.Client;
import team11.comp3074_project11.dataModel.Flight;
import team11.comp3074_project11.dataModel.Itinerary;
import team11.comp3074_project11.helper.SearchUtility;

import static team11.comp3074_project11.database.FlightAppContract.ClientEntry.COLUMN_CLIENT_CREDITCARDNO;
import static team11.comp3074_project11.database.FlightAppContract.ClientEntry.COLUMN_CLIENT_EMAIL;
import static team11.comp3074_project11.database.FlightAppContract.ClientEntry.COLUMN_CLIENT_FIRSTNAME;
import static team11.comp3074_project11.database.FlightAppContract.ClientEntry.COLUMN_CLIENT_LASTNAME;
import static team11.comp3074_project11.database.FlightAppContract.ClientEntry.COLUMN_CLIENT_PASSWORD;
import static team11.comp3074_project11.database.FlightAppContract.ClientEntry.TABLE_NAME;

/**
 * Created by aline on 2017-12-07.
 */

public class FlightAppDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "flightAppDB.db";
    private static final int DB_VERSION = 1;

     //Tables
    private static final String CREATE_FLIGHT_TABLE = "CREATE TABLE tbl_flight (" +
            "flightId_PK INTEGER PRIMARY KEY AUTOINCREMENT," +
            "flightNumber TEXT," +
            "departureDateTime TEXT," +
            "arrivalDateTime TEXT," +
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
            "password TEXT," +
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

//        Flight example = new Flight();
//        example.setCost(1.0);
//        example.setTravelTime(300);
//        example.setOriginAirportId_FK(1);
//        example.setDestAirportId_FK(2);
//        example.setDepartureDateTime("02-26-2018");
//        insertFlight(db, new Flight());

        List<Flight> flights = generateFlights(db);
        for (Flight flight : flights){
            insertFlight(db, flight);
        }

        //Insert clients
        insertClient(db, new Client("Jon", "Snow", "jonsnow@gmail.com", "1234", "5191000000000000"));

        //Insert itineraries
        insertItinerary(db, new Itinerary(1,1));
        insertItinerary(db, new Itinerary(2,1));

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
        airlineValues.put("airlineInitials", airline.getAirlineInitials());
        db.insert("tbl_airline", null, airlineValues);
    }

    //Insert Flight into the database
    public static void insertFlight(SQLiteDatabase db, Flight flight){
        ContentValues flightValues = new ContentValues();
        flightValues.put("flightNumber", flight.getFlightNumber());
        flightValues.put("departureDateTime", flight.getDepartureDateTime());
        flightValues.put("arrivalDateTime", flight.getArrivalDateTime());
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

    public static long insertClient(SQLiteDatabase db, Client client){
        //create a ContentValue object column
        ContentValues clientValues = new ContentValues();
        clientValues.put("firstName", client.getFirstName());
        clientValues.put("lastName", client.getLastName());
        clientValues.put("email", client.getEmail());
        clientValues.put("password", client.getPassword());
        clientValues.put("creditCardNo", client.getCreditCardNo());
        //insert a new row for client in the database
        //returning the ID for that new row
        long newRowId = db.insert("tbl_client", null, clientValues);
        return newRowId;
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
        SQLiteDatabase db = this.getReadableDatabase();
        //Select query
        String selectClient = "SELECT * FROM tbl_client WHERE email =  '" + email + "' AND " +
                "password = '" + password + "'";

        Cursor cursor = db.rawQuery(selectClient, null);

        //Returns true if a record is found, false otherwise.
        try {
            if (cursor.moveToFirst()) {
                client = new Client(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5));
            }
        }
        catch(SQLiteException e){
                client = null;
        }finally{
            cursor.close();
        }
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

    public List<Flight> generateFlights(SQLiteDatabase db) {
        List<Flight> randomFlights = new ArrayList<Flight>();

        //Get all airports
        List<Airport> airports = SearchUtility.getAirports(this, db);
        List<Airline> airlines = SearchUtility.getAirlines(this, db);

        int numOfAirlines = airlines.size();
        int numOfAirports = airports.size();

        //Tools

        List<String> randomDates = new ArrayList<>();
        randomDates.add("02-26-2018");
        randomDates.add("12-19-2018");
        randomDates.add("05-23-2019");
        randomDates.add("03-08-2019");

        int numOfDates = randomDates.size();

        //Randomly generate flights
        for(int i = 0; i < numOfAirports; i++){ //Origin
            for(int j = 0; j < numOfAirports; j++){ //Destination
                //Origin airport must be different from destination airport
                if(i != j) {
                    //Loop twice for each origin/destination pair
                    for (int k = 0; k < 2; k++) {
                        //Get random airline
                        Airline anAirline = airlines.get(new Random().nextInt(numOfAirlines));
                        String randomFlightNumber = anAirline.getAirlineInitials() + (new Random().nextInt(999 - 100) + 100);

                        //Get random travel time
                        double travelTime = 2.0 + ((15.0 - 2.0) * new Random().nextDouble());

                        //Get random cost
                        double cost = 200 + ((900 - 200) * new Random().nextDouble());

                        //Get random date
                        int d = new Random().nextInt(numOfDates);

                        Flight aFlight = new Flight();
                        aFlight.setOriginAirportId_FK(airports.get(i).getAirportId());
                        aFlight.setDestAirportId_FK(airports.get(j).getAirportId());
                        aFlight.setAirlineId_FK(anAirline.getAirlineId());
                        aFlight.setFlightNumber(randomFlightNumber);
                        aFlight.setDepartureDateTime(randomDates.get(0));
                        aFlight.setArrivalDateTime(randomDates.get(0));
                        aFlight.setCost(cost);
                        aFlight.setTravelTime(travelTime);

                        randomFlights.add(aFlight);
                    }
                }
            }
        }

        return randomFlights;
    }

}
