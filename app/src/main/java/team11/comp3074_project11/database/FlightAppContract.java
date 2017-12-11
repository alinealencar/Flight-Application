package team11.comp3074_project11.database;

import android.provider.BaseColumns;

/**
 * Created by Owner on 12/10/2017.
 */

public class FlightAppContract {
    private FlightAppContract(){}

    public static abstract class ClientEntry implements BaseColumns{
        public static final String TABLE_NAME = "tbl_client";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_CLIENT_FIRSTNAME = "firstName";
        public final static String COLUMN_CLIENT_LASTNAME = "lastName";
        public final static String COLUMN_CLIENT_EMAIL = "email";
        public final static String COLUMN_CLIENT_PASSWORD = "password";
        public final static String COLUMN_CLIENT_CREDITCARDNO = "creditCardNo";

    }
}
