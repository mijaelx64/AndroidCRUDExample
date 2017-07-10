package mijaelx64.com.androidcrudexample.data.local;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;

import mijaelx64.com.androidcrudexample.data.local.UsersPersistenceContract.UserEntry;

/**
 * Created by Mijael Vargas on 7/9/2017.
 *
 * Db helper for Users DataTable that contains:
 * ID, NAME, ADDRESS, BIRTH DATE, PHONE NUMBER and EMAIL
 *
 */

public class UsersDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Users.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UserEntry.TABLE_NAME + " (" +
                    UserEntry._ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT," +
                    UserEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    UserEntry.COLUMN_NAME_ADDRESS + TEXT_TYPE + COMMA_SEP +
                    UserEntry.COLUMN_NAME_BIRTH_DATE + TEXT_TYPE + COMMA_SEP +
                    UserEntry.COLUMN_NAME_PHONE_NUMBER + TEXT_TYPE + COMMA_SEP +
                    UserEntry.COLUMN_NAME_EMAIL + TEXT_TYPE +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UsersPersistenceContract.UserEntry.TABLE_NAME;

    public UsersDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creating Db from defined templated on SQL_CREATE_ENTRIES
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    /**
     * Use only when db schema is changed.
     * TODO: Implement additional functions or logic when necessary for versions above 1.
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
