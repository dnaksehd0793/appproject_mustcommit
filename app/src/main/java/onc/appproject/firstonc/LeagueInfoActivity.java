package onc.appproject.firstonc;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LeagueInfoActivity extends AppCompatActivity {
    TextView lname;
    TextView lsponsor;
    TextView llocation;
    TextView ldate;
    TextView lcost;
    TextView lteamnum;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    ArrayList<Team> userArraylist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_info);

        mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view4);

        mRecyclerView.setHasFixedSize(true);//옵션
        //Linear layout manager 사용
        mLayoutManager = new LinearLayoutManager(getApplication());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter4(userArraylist); //스트링 배열 데이터 인자로
        mRecyclerView.setAdapter(mAdapter);

        Intent intent = getIntent();
        String name = intent.getStringExtra("l name");
        String sponsor = intent.getStringExtra("l sponsor");
        String location = intent.getStringExtra("l location");
        String date = intent.getStringExtra("l date");
        String cost = intent.getStringExtra("l cost");
        String teamnum = intent.getStringExtra("l teamnum");

        lname = (TextView)findViewById(R.id.Lname);
        lsponsor = (TextView)findViewById(R.id.Lsponsor);
        llocation = (TextView)findViewById(R.id.Llocation);
        ldate = (TextView)findViewById(R.id.Ldate);
        lcost = (TextView)findViewById(R.id.Lcost);
        lteamnum = (TextView)findViewById(R.id.Lteamnum);
        lname.setText(name);
        lsponsor.setText(sponsor);
        llocation.setText(location);
        ldate.setText(date);
        lcost.setText(cost);
        lteamnum.setText(teamnum);

        String leaguekey = DatabaseManager.getLeague(name);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String leaguekey = DatabaseManager.getLeague(name);
                //Toast.makeText(getApplication(),name+"의 팀키는 "+leaguekey,Toast.LENGTH_LONG).show();
                DatabaseReference databaseRef = firebaseDatabase.getReference("league").child(leaguekey).child("leaguemember");
                Team team = new Team();
                databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {
                            Team inputteam = (Team)fileSnapshot.getValue(Team.class);
                            userArraylist.add(inputteam);
                        }
                        mAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        },200);



    }
}
