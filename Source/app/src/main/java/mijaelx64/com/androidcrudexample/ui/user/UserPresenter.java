package mijaelx64.com.androidcrudexample.ui.user;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import mijaelx64.com.androidcrudexample.data.local.UsersDataSource;
import mijaelx64.com.androidcrudexample.data.model.IUsersDataSource;
import mijaelx64.com.androidcrudexample.data.model.User;

/**
 * Created by Mijael Vargas on 7/10/2017.
 */

public class UserPresenter implements IUsersContract.Presenter {

    @NonNull
    private final UsersDataSource mUsersDataSource;

    @NonNull
    private final IUsersContract.View mUserView;

    public UserPresenter(@NonNull UsersDataSource mUsersDataSource, @NonNull IUsersContract.View mUserView) {
        this.mUsersDataSource = mUsersDataSource;
        this.mUserView = mUserView;

        this.mUserView.setPresenter(this);
    }

    @Override
    public void start() {
        loadUsers(false);
    }

    @Override
    public void loadUsers(boolean forceUpdate) {

        mUsersDataSource.getUsers(new IUsersDataSource.LoadUsersCallback() {
            @Override
            public void onUsersLoaded(ArrayList<User> users) {
                mUserView.showUsers(users);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

}
