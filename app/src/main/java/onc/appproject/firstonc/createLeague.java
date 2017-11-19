package onc.appproject.firstonc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class createLeague extends AppCompatActivity {
    TextView textleaguename;
    TextView textleagueschedule;
    TextView textleaguesponsor;
    TextView textleaguejoincost;
    TextView textleagueteamnumber;
    Button leaguemakebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_league);

        textleaguename = (TextView)findViewById(R.id.textleaguename);
        textleagueschedule = (TextView)findViewById(R.id.textleagueschedule);
        textleaguesponsor = (TextView)findViewById(R.id.textleaguesponsor);
        textleaguejoincost = (TextView)findViewById(R.id.textleaguejoincost);
        textleagueteamnumber = (TextView)findViewById(R.id.textleagueteamnumber);
        leaguemakebutton = (Button)findViewById(R.id.leaguemakebutton);
        leaguemakebutton.setOnClickListener(view -> {
            Toast.makeText(this, "대회 생성이 완료되었습니다.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        });

    }
}
