package mijaelx64.com.androidcrudexample.data.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.Serializable;

/**
 * Created by Mijael Vargas on 7/9/2017.
 */
public final class User implements Serializable {

    private final int id;

    @Nullable
    private final String name;

    @Nullable
    private final String address;

    @Nullable
    private final String birthDay;

    @Nullable
    private final String phoneNumber;

    @Nullable
    private final String email;

    /**
     * User Constructor
      * @param id Identifier at User DataTable.
     * @param name Name of the User
     * @param address Address
     * @param birthDay Birthday //TODO: No parsing definied for thsi field.
     * @param phoneNumber Phone Number
     * @param email Email //TODO: No email validation implemented for this field
     */
    public User(int id, String name, String address, String birthDay, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    /**
     * User Constructor
     * @param name Name of the User
     * @param address Address
     * @param birthDay Birthday //TODO: No parsing definied for thsi field.
     * @param phoneNumber Phone Number
     * @param email Email //TODO: No email validation implemented for this field
     */
    public User(String name, String address, String birthDay, String phoneNumber, String email) {
        this(-1,name,address,birthDay,phoneNumber,email);
    }

    public int getId() {
        return id;
    }

    @Nullable
    public String getName() {
        return name;
    }

    @Nullable
    public String getAddress() {
        return address;
    }

    @Nullable
    public String getBirthDay() {
        return birthDay;
    }

    @Nullable
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Nullable
    public String getEmail() {
        return email;
    }
}
