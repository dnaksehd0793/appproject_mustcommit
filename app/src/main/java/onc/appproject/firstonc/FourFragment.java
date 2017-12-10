package onc.appproject.firstonc;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static onc.appproject.firstonc.MyAdapter.mFirebaseUser;

public class FourFragment extends Fragment {
    public TextView myname;
    public TextView myregion;
    public TextView myheight;

    public TextView myteamname;
    public TextView myteamregion;
    public TextView myteamleader;
    Team myteam = null;
    public FourFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceUtil.overrideFont(getActivity(), "SERIF", "fonts/Roboto-Regular.ttf");

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_four, container, false);
        myname = (TextView)view.findViewById(R.id.profile_name);
        myregion = (TextView)view.findViewById(R.id.profile_region);
        myheight = (TextView)view.findViewById(R.id.profile_height);

        myteamname = (TextView)view.findViewById(R.id.myteamname);
        myteamregion = (TextView)view.findViewById(R.id.myteamregion);
        myteamleader = (TextView)view.findViewById(R.id.myteamleader);

        User myuser = DatabaseManager.getUser(mFirebaseUser.getEmail());
        //Team myteam = DatabaseManager.findteambyuser(myuser);
        myname.setText(myuser.getUsername());
        myregion.setText(myuser.getUserregion());
        myheight.setText(myuser.getUserheight());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                User myuser = DatabaseManager.getUser(mFirebaseUser.getEmail());
                //Team myteam = DatabaseManager.findteambyuser(myuser);
                myname.setText(myuser.getUsername());
                myregion.setText(myuser.getUserregion());
                myheight.setText(myuser.getUserheight());
                myteam = DatabaseManager.showmyteam(myuser.getUsername());
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        myteam = DatabaseManager.showmyteam(myuser.getUsername());
                        myteamname.setText(myteam.getTeamName());
                        myteamregion.setText(myteam.getTeamregion());
                        myteamleader.setText(myteam.getTeamleader().getUsername());
                    }
                },200);
            }
        },200);
       return view;
    }
}
