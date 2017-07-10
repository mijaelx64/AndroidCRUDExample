package mijaelx64.com.androidcrudexample.ui.user;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import mijaelx64.com.androidcrudexample.data.model.User;
import mijaelx64.com.androidcrudexample.ui.base.BasePresenter;
import mijaelx64.com.androidcrudexample.ui.base.BaseView;

/**
 * Created by Mijael Vargas on 7/9/2017.
 */

public interface IUsersContract {

    interface View extends BaseView<Presenter> {

        void showUsers(ArrayList<User> Users);

    }

    interface Presenter extends BasePresenter {

        void loadUsers(boolean forceUpdate);
    }
}
