package mijaelx64.com.androidcrudexample.ui.user;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mijaelx64.com.androidcrudexample.R;
import mijaelx64.com.androidcrudexample.data.model.User;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnUserListListener}
 * interface.
 */
public class UserFragment extends Fragment implements IUsersContract.View {


    private IUsersContract.Presenter mPresenter;
    private ArrayList<User> mUserList;
    private UserRecyclerViewAdapter mUserAdapter;
    private RecyclerView recyclerView;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnUserListListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public UserFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static UserFragment newInstance(int columnCount) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        mUserList = new ArrayList<>();
        mListener = (UserActivity)this.getActivity();
        mUserAdapter = new UserRecyclerViewAdapter(mUserList, mListener);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(mUserAdapter);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnUserListListener) {
            mListener = (OnUserListListener) context;
        } else {
            throw new RuntimeException(context.toString()
                   + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    @Override
    public void setPresenter(IUsersContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showUsers(ArrayList<User> Users) {
        mUserList.clear();

        for (User obj: Users ) {
            mUserList.add(obj);
        }

        recyclerView.getAdapter().notifyDataSetChanged();
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnUserListListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(User item);
    }
}
