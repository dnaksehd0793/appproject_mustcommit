package onc.appproject.firstonc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.ArrayList;

public class createLeague extends AppCompatActivity {
    TextView textleaguename;
    TextView textleagueschedule;
    TextView textleaguesponsor;
    TextView textleaguejoincost;
    TextView textleagueteamnumber;
    Button leaguemakebutton;
    User leagueleader = null;
    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;
    private static ArrayList<User> userArraylist = new ArrayList<>();
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_league);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser =  mFirebaseAuth.getCurrentUser();
        textleaguename = (TextView)findViewById(R.id.textleaguename);
        textleagueschedule = (TextView)findViewById(R.id.textleagueschedule);
        textleaguesponsor = (TextView)findViewById(R.id.textleaguesponsor);
        textleaguejoincost = (TextView)findViewById(R.id.textleaguejoincost);
        textleagueteamnumber = (TextView)findViewById(R.id.textleagueteamnumber);
        leaguemakebutton = (Button)findViewById(R.id.leaguemakebutton);

        DatabaseReference databaseRef = firebaseDatabase.getReference("users");

        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {
                    User inputuser = (User)fileSnapshot.getValue(User.class);
                    userArraylist.add(inputuser);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        for(User searchuser : userArraylist)
        {
            if(searchuser.getUseremail().equals(mFirebaseUser.getEmail()))
            {
                leagueleader = searchuser;
                break;
            }
        }



        leaguemakebutton.setOnClickListener(view -> {
            League league = new League( textleaguename.getText().toString(),textleagueschedule.getText().toString(),
                    textleaguesponsor.getText().toString(),textleaguejoincost.getText().toString(),
                    textleagueteamnumber.getText().toString(),leagueleader);
            databaseReference.child("league").push().setValue(league);
            Toast.makeText(this, "대회 생성이 완료되었습니다.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        });

    }
}
