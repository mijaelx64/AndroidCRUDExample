package mijaelx64.com.androidcrudexample.ui.add_user;

import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import mijaelx64.com.androidcrudexample.R;
import mijaelx64.com.androidcrudexample.data.model.User;

import static com.google.common.base.Preconditions.checkNotNull;


public class AddUserFragment extends Fragment implements IAddUserContract.View {

    private IAddUserContract.Presenter mPresenter;
    @BindView(R.id.name_edit_text) EditText mName;
    @BindView(R.id.address_edit_text) EditText mAddress;
    @BindView(R.id.birthdate_edit_text) EditText mBirthDay;
    @BindView(R.id.phone_edit_text) EditText mPhoneNumber;
    @BindView(R.id.email_edit_text) EditText mEmail;

    private Button saveButton;

    @BindView(R.id.deletebutton) Button deleteButton;

    public AddUserFragment() {
        // Required empty public constructor
    }

    public static AddUserFragment newInstance(String param1, String param2) {
        AddUserFragment fragment = new AddUserFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_add_user, container, false);
        ButterKnife.bind(this, view);

        saveButton = (Button) view.findViewById(R.id.savebutton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mPresenter.saveUser(
                                mName.getText().toString(),
                                mAddress.getText().toString(),
                                mBirthDay.getText().toString(),
                                mPhoneNumber.getText().toString(),
                                mEmail.getText().toString()
                );
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.deleteUser();
            }
        });

        return view;
    }

    @Override
    public void setPresenter(IAddUserContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showUserList() {
        getActivity().finish();
    }

    @Override
    public void showUser(User user) {
        mName.setText(user.getName());
        mAddress.setText(user.getAddress());
        mBirthDay.setText(user.getBirthDay());
        mPhoneNumber.setText(user.getPhoneNumber());
        mEmail.setText(user.getEmail());
    }
}
