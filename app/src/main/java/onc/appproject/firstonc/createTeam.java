package onc.appproject.firstonc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class createTeam extends AppCompatActivity {
    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;
    TextView textteamname;
    TextView textteamregion;
    TextView textteamleader;
    CheckBox officialcheckBox;
    Button teammakebutton;
    User teamleader = null;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private static ArrayList<User> userArraylist = new ArrayList<>();
    boolean checker;
    Spinner spinner_location;
    String region;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);
        TypefaceUtil.overrideFont(this, "SERIF", "fonts/Roboto-Regular.ttf");
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser =  mFirebaseAuth.getCurrentUser();
        textteamname = (TextView) findViewById(R.id.textteamname);
        //    textteamregion = (TextView) findViewById(R.id.textteamregion);
        teammakebutton = (Button) findViewById(R.id.teammakebutton);
        checker = false;

        spinner_location = (Spinner)findViewById(R.id.spinner_location);
        region = (String)spinner_location.getSelectedItem();
        spinner_location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View arg1,
                                       int position, long id) {
                region = (String)spinner_location.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        findViewById(R.id.officialcheckBox).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Checked(v); // 체크되었을 때 동작코드
            }
        });

        DatabaseReference databaseRef = firebaseDatabase.getReference("users");

        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {
                    User inputuser = (User)fileSnapshot.getValue(User.class);
                    if(inputuser.useremail.equals(mFirebaseUser.getEmail())){
                        teamleader = inputuser;
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        teammakebutton.setOnClickListener((view -> {
            //Team team = new Team(textteamname.getText().toString(),textteamregion.getText().toString(), checker,teamleader);
            Team team = new Team(textteamname.getText().toString(),region, checker,teamleader);
            databaseReference.child("team").push().setValue(team);
            //FirebaseDatabase.getInstance().getReference().child("users").child(mFirebaseAuth.)
            //DatabaseManager.setteaminfoinuser(team);
            Toast.makeText(this, "팀 생성이 완료되었습니다.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }));
    }
    public boolean Checked(View v) {
        CheckBox opt1 = (CheckBox)findViewById(R.id.officialcheckBox);
        if(opt1.isChecked())
            checker  = true;
        else
            checker = false;
        return checker;
    }
}