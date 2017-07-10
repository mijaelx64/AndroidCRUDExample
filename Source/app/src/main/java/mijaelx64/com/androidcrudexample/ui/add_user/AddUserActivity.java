package mijaelx64.com.androidcrudexample.ui.add_user;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import butterknife.BindView;
import mijaelx64.com.androidcrudexample.R;
import mijaelx64.com.androidcrudexample.data.local.UsersDataSource;

public class AddUserActivity extends Activity {

    private AddUserPresenter mAddUserPresenter;
    private AddUserFragment mFragmentContent;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        Intent mIntent = getIntent();
        int userId = mIntent.getIntExtra("USERID", -1);

        mFragmentContent = (AddUserFragment) getFragmentManager().findFragmentById(R.id.add_user_fragment);
        mAddUserPresenter = new AddUserPresenter(userId, UsersDataSource.getInstance(this), mFragmentContent);
    }
}
