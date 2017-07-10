package mijaelx64.com.androidcrudexample.ui.add_user;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import mijaelx64.com.androidcrudexample.data.local.UsersDataSource;
import mijaelx64.com.androidcrudexample.data.model.User;

/**
 * Created by Mijael Vargas on 7/10/2017.
 */

public class AddUserPresenter implements IAddUserContract.Presenter, UsersDataSource.GetUserCallback {

    @NonNull
    private final UsersDataSource mUsersDataSource;
    private int userId;

    @NonNull
    private final IAddUserContract.View mAddUserView;

    public AddUserPresenter(int userId, @NonNull UsersDataSource mUsersDataSource, @NonNull IAddUserContract.View mAddUserView) {
        this.mUsersDataSource = mUsersDataSource;
        this.mAddUserView = mAddUserView;
        this.userId = userId;
        mAddUserView.setPresenter(this);
    }

    @Override
    public void start() {
        if (userId != -1)
            populateTask();
    }

    @Override
    public void saveUser(String name, String address, String birthDate, String phoneNumber, String email) {

        if (userId == -1){
            User user = new User(name,address,birthDate,phoneNumber,email);
            mUsersDataSource.createUser(user);
        }
        else{
            User user = new User(userId,name,address,birthDate,phoneNumber,email);
            mUsersDataSource.updateUser(user);
        }
        mAddUserView.showUserList();
    }

    @Override
    public void populateTask() {
        mUsersDataSource.getUser(userId, this);
    }

    @Override
    public void deleteUser() {
        mUsersDataSource.deleteUser(userId);
        mAddUserView.showUserList();
    }

    @Override
    public void onUserLoaded(User user) {
        mAddUserView.showUser(user);
    }

    @Override
    public void onDataNotAvailable() {

    }
}
