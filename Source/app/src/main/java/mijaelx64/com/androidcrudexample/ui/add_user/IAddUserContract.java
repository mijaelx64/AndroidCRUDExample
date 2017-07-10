package mijaelx64.com.androidcrudexample.ui.add_user;

import mijaelx64.com.androidcrudexample.data.model.User;
import mijaelx64.com.androidcrudexample.ui.base.*;

/**
 * Created by Mijael Vargas on 7/10/2017.
 */

public interface IAddUserContract {

    interface View extends BaseView<Presenter> {

        void showUserList();

        void showUser(User user);

    }

    interface Presenter extends BasePresenter {

        void saveUser(String name, String address, String birthDate, String phoneNumber, String email);

        void populateTask();

        void deleteUser();
    }
}
