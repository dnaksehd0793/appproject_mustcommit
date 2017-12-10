package onc.appproject.firstonc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TeamInfoActivity extends AppCompatActivity {
    TextView teamname;
    TextView teamregion;
    TextView teamleader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_info);
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
    }
}
