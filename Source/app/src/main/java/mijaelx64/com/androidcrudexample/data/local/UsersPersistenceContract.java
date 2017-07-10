package mijaelx64.com.androidcrudexample.data.local;

import android.provider.BaseColumns;

/**
 * Created by Mijael Vargas on 7/9/2017.
 * Contract for Users DataTable
 */

public final class UsersPersistenceContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private UsersPersistenceContract() {}

    /* Inner class that defines the table contents */
    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_ADDRESS = "address";
        public static final String COLUMN_NAME_BIRTH_DATE  = "birth_date";
        public static final String COLUMN_NAME_PHONE_NUMBER = "phone_number";
        public static final String COLUMN_NAME_EMAIL = "email";
    }
}
