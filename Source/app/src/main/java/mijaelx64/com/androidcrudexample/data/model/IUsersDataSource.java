package mijaelx64.com.androidcrudexample.data.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Contract for executing DataBase Operations
 *
 * Created by Mijael Vargas on 7/9/2017.
 */

public interface IUsersDataSource {

    /**
     * List of Users will be delivered once the callback finish its loading.
     */
    interface LoadUsersCallback {

        void onUsersLoaded(ArrayList<User> users);

        void onDataNotAvailable();
    }

    /**
     * Retrive a single user from DB
     */
    interface GetUserCallback {

        void onUserLoaded(User user);

        void onDataNotAvailable();
    }

    /**
     * Request List of user into the callback
     * @param callback
     */
    void getUsers(@NonNull LoadUsersCallback callback);

    /**
     * Request User
     * @param userId
     * @param callback
     */
    void getUser(@NonNull int userId, @NonNull GetUserCallback callback);

    /**
     * Create new User
     * @param user
     */
    void createUser(@NonNull User user);

    /**
     * Edit new User
     * @param user
     */
    void updateUser(@NonNull User user);

    /**
     * Delete Current User
     * @param UserId
     */
    void deleteUser(@NonNull int UserId);

}
