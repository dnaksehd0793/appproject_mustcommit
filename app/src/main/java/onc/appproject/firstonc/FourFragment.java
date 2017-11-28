package onc.appproject.firstonc;

import android.os.Bundle;
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

public class FourFragment extends Fragment {
    public TextView tname;
    public TextView tregion;
    public TextView tleader;
    User getUser  = new User();
    Team getTeam = new Team();
    static FirebaseUser mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private static ArrayList<User> userArraylist = new ArrayList<>();
    private static ArrayList<Team> teamArraylist = new ArrayList<>();
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
       /* tname = (TextView)view.findViewById(R.id.team_name);
        tname.setText(mFirebaseUser.getEmail());

        getUser = DatabaseManager.getUser(mFirebaseUser.getEmail());
        User sojunghaluser = new User();
        if(getUser.getUsername()!=null){
            getTeam = DatabaseManager.findteambyuser(getUser);
        }
        if(getTeam!=null)
        {
            Toast.makeText(view.getContext(), getTeam.getTeamName()+"팀이름!", Toast.LENGTH_SHORT).show();
        }
        Button button3 = (Button)view.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), getTeam.getTeamName()+"팀이름!", Toast.LENGTH_LONG).show();
            }
        });*/
       return view;
    }

}
