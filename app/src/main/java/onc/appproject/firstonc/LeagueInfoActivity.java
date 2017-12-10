package onc.appproject.firstonc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LeagueInfoActivity extends AppCompatActivity {
    TextView lname;
    TextView lsponsor;
    TextView llocation;
    TextView ldate;
    TextView lcost;
    TextView lteamnum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_info);
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
    }
}
