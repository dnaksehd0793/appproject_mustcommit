package onc.appproject.firstonc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
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



public class TeamInfoActivity extends AppCompatActivity {
    TextView teamname;
    TextView teamregion;
    TextView teamleader;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    ArrayList<User> userArraylist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_info);

        mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view3);

        mRecyclerView.setHasFixedSize(true);//옵션
        //Linear layout manager 사용
        mLayoutManager = new LinearLayoutManager(getApplication());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter3(userArraylist); //스트링 배열 데이터 인자로
        mRecyclerView.setAdapter(mAdapter);

        Intent intent = getIntent();
        String name = intent.getStringExtra("team name");
        String region = intent.getStringExtra("team region");
        String teamleadername = intent.getStringExtra("team leader");
        //newsView = (TextView)rootView.findViewById(R.id.newsView);
        teamname = (TextView)findViewById(R.id.getteamname);
        teamregion = (TextView)findViewById(R.id.getteamregion);
        teamleader = (TextView)findViewById(R.id.getteamleadername);
        teamname.setText(name);
        teamregion.setText(region);
        teamleader.setText(teamleadername);
        String teamkey = DatabaseManager.getTeam(name);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String teamkey = DatabaseManager.getTeam(name);
                Toast.makeText(getApplication(),name+"의 팀키는 "+teamkey,Toast.LENGTH_LONG).show();
                DatabaseReference databaseRef = firebaseDatabase.getReference("team").child(teamkey).child("teammember");
                User user = new User();
                databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {
                            User inputteam = (User)fileSnapshot.getValue(User.class);
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


        /*if(teamkey!=null)
        {

        }*/

    }
}
