package mijaelx64.com.androidcrudexample.ui.user;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mijaelx64.com.androidcrudexample.R;
import mijaelx64.com.androidcrudexample.data.model.User;
import mijaelx64.com.androidcrudexample.ui.user.UserFragment.OnUserListListener;

import java.util.ArrayList;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<User> mValues;
    private final OnUserListListener mListener;

    public UserRecyclerViewAdapter(ArrayList<User> items, OnUserListListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mName.setText(mValues.get(position).getName());
        holder.mAddress.setText(mValues.get(position).getAddress());
        holder.mBirthdate.setText(mValues.get(position).getBirthDay());
        holder.mPhoneNumber.setText(mValues.get(position).getPhoneNumber());
        holder.mEmail.setText(mValues.get(position).getEmail());


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mName;
        public final TextView mAddress;
        public final TextView mBirthdate;
        public final TextView mPhoneNumber;
        public final TextView mEmail;

        public User mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mName = (TextView) view.findViewById(R.id.name);
            mAddress = (TextView) view.findViewById(R.id.address);
            mBirthdate = (TextView) view.findViewById(R.id.birthdate);
            mPhoneNumber = (TextView) view.findViewById(R.id.phone);
            mEmail = (TextView) view.findViewById(R.id.email);

        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
