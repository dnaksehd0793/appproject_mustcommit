package onc.appproject.firstonc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
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
    TextView textleaguesponsor;
    TextView textleagueregion;
    Spinner spinner_cost;
    String cost;
    Spinner spinner_teamnum;
    String teamnum;
    DatePicker league_date;
    String date;
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
        textleaguesponsor = (TextView)findViewById(R.id.textleaguesponsor);
        textleagueregion = (TextView)findViewById(R.id.textleagueregion);
        leaguemakebutton = (Button)findViewById(R.id.leaguemakebutton);

        spinner_cost = (Spinner)findViewById(R.id.spinner_cost);
        cost = (String)spinner_cost.getSelectedItem();
        spinner_cost.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View arg1,
                                       int position, long id) {
                cost = (String)spinner_cost.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_teamnum = (Spinner)findViewById(R.id.spinner_teamnumbering);
        teamnum = (String)spinner_teamnum.getSelectedItem();
        spinner_teamnum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View arg1,
                                       int position, long id) {
                teamnum = (String)spinner_teamnum.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        league_date = (DatePicker)findViewById(R.id. league_date);
        league_date.init(league_date.getYear(), league_date.getMonth(), league_date.getDayOfMonth(),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear,
                                              int dayOfMonth) {
                        date = String.valueOf(year)+"년 " + String.valueOf(monthOfYear+1)+"월 " + String.valueOf(dayOfMonth)+"일";
                        //(String)year + monthOfYear+1 + dayOfMonth;
                    }
                });

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

            League league = new League(textleaguename.getText().toString(),textleaguesponsor.getText().toString(),
                    textleagueregion.getText().toString(),cost,teamnum,date );
            databaseReference.child("league").push().setValue(league);
            Toast.makeText(this, "대회 생성이 완료되었습니다.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        });

    }
}