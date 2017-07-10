package mijaelx64.com.androidcrudexample.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import mijaelx64.com.androidcrudexample.data.model.IUsersDataSource;
import mijaelx64.com.androidcrudexample.data.model.User;
import mijaelx64.com.androidcrudexample.data.local.UsersPersistenceContract.UserEntry;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Model for Database
 *
 * Created by Mijael Vargas on 7/10/2017.
 */

public class UsersDataSource implements IUsersDataSource {

    private static UsersDataSource INSTANCE;
    private UsersDbHelper mDbHelper;

    private UsersDataSource(@NonNull Context context) {
        checkNotNull(context);
        mDbHelper = new UsersDbHelper(context);
    }

    public static UsersDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new UsersDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getUsers(@NonNull LoadUsersCallback callback) {

        ArrayList<User> users = new ArrayList<User>();
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                UserEntry._ID,
                UserEntry.COLUMN_NAME_NAME,
                UserEntry.COLUMN_NAME_ADDRESS,
                UserEntry.COLUMN_NAME_BIRTH_DATE,
                UserEntry.COLUMN_NAME_PHONE_NUMBER,
                UserEntry.COLUMN_NAME_EMAIL,
        };

        Cursor c = db.query(
                UserEntry.TABLE_NAME, projection, null, null, null, null, null);

        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                int itemId = c.getInt(c.getColumnIndexOrThrow(UserEntry._ID));
                String itemName = c.getString(c.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_NAME));
                String itemAddress = c.getString(c.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_ADDRESS));
                String itemBirthDate = c.getString(c.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_BIRTH_DATE));
                String itemEmail = c.getString(c.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_EMAIL));
                String itemPhoneNumber = c.getString(c.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_PHONE_NUMBER));

                User user = new User(itemId, itemName, itemAddress, itemBirthDate, itemPhoneNumber, itemEmail);
                users.add(user);
            }
        }
        if (c != null) {
            c.close();
        }

        db.close();

        if (users.isEmpty()) {
            callback.onDataNotAvailable();
        } else {
            callback.onUsersLoaded(users);
        }
    }

    @Override
    public void getUser(@NonNull int userId, @NonNull GetUserCallback callback) {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                UserEntry._ID,
                UserEntry.COLUMN_NAME_NAME,
                UserEntry.COLUMN_NAME_ADDRESS,
                UserEntry.COLUMN_NAME_BIRTH_DATE,
                UserEntry.COLUMN_NAME_PHONE_NUMBER,
                UserEntry.COLUMN_NAME_EMAIL,
        };

        String selection = UserEntry._ID + " =?";
        String[] selectionArgs = { String.valueOf(userId) };

        Cursor c = db.query(
                UserEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        User user = null;

        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            int itemId = c.getInt(c.getColumnIndexOrThrow(UserEntry._ID));
            String itemName = c.getString(c.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_NAME));
            String itemAddress = c.getString(c.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_ADDRESS));
            String itemBirthDate = c.getString(c.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_BIRTH_DATE));
            String itemEmail = c.getString(c.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_EMAIL));
            String itemPhoneNumber = c.getString(c.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_PHONE_NUMBER));
            user = new User(itemId, itemName, itemAddress, itemBirthDate, itemPhoneNumber, itemEmail);
        }
        if (c != null) {
            c.close();
        }

        db.close();

        if (user != null) {
            callback.onUserLoaded(user);
        } else {
            callback.onDataNotAvailable();
        }

    }

    @Override
    public void createUser(@NonNull User user) {

        checkNotNull(user);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(UserEntry._ID, user.getId());
        values.put(UserEntry.COLUMN_NAME_NAME, user.getName());
        values.put(UserEntry.COLUMN_NAME_ADDRESS, user.getAddress());
        values.put(UserEntry.COLUMN_NAME_BIRTH_DATE, user.getBirthDay());
        values.put(UserEntry.COLUMN_NAME_PHONE_NUMBER, user.getPhoneNumber());
        values.put(UserEntry.COLUMN_NAME_EMAIL, user.getEmail());

        db.insert(UserEntry.TABLE_NAME, null, values);

        db.close();

    }

    @Override
    public void updateUser(@NonNull User user) {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(UserEntry.COLUMN_NAME_NAME, user.getName());
        values.put(UserEntry.COLUMN_NAME_ADDRESS, user.getAddress());
        values.put(UserEntry.COLUMN_NAME_BIRTH_DATE, user.getBirthDay());
        values.put(UserEntry.COLUMN_NAME_PHONE_NUMBER, user.getPhoneNumber());
        values.put(UserEntry.COLUMN_NAME_EMAIL, user.getEmail());

        String selection = UserEntry._ID + " = ?";
        String[] selectionArgs = {  String.valueOf(user.getId()) };

        db.update(UserEntry.TABLE_NAME, values, selection, selectionArgs);
        db.close();
    }

    @Override
    public void deleteUser(@NonNull int userId) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String selection = UserEntry._ID + " = ?";
        String[] selectionArgs = {  String.valueOf(userId) };

        db.delete(UserEntry.TABLE_NAME, selection, selectionArgs);

        db.close();
    }
}
