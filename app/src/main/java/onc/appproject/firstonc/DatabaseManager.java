package onc.appproject.firstonc;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static onc.appproject.firstonc.MyAdapter.mFirebaseUser;

public class DatabaseManager {

    private static FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    //private static DatabaseReference databaseReference = firebaseDatabase.getReference();
    private static ArrayList<User> userArraylist = new ArrayList<>();
    private static ArrayList<Team> teamArraylist = new ArrayList<>();
    static ArrayList<String> uidlist = new ArrayList<>();
    static ArrayList<String> uidlist2 = new ArrayList<>();
    public static String publlicteamkey = null;
    public static String publicuserkey = null;
    public static Team sosockteam = null;
    public static Team myteam = null;


    public static String findUserByKey(String key){
        String returnstring = null;
        DatabaseReference databaseRef = firebaseDatabase.getReference("users");

        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {
                    //User inputuser = (User)fileSnapshot.getValue(User.class);
                    //userArraylist.add(inputuser);
                    uidlist.add(fileSnapshot.getKey());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        for(String findkey : uidlist){
            if(findkey.equals(key))
            {
                returnstring = findkey;
                break;
            }
        }
        return returnstring;
    }

    public static User getUser(String authemail){
        User user = new User();
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
            if(searchuser.getUseremail().equals(authemail))
            {
                user = searchuser;
                break;
            }
        }
        return user;
    }

    public static String getTeam(String teamname)
    {
        Team team = new Team();
        DatabaseReference databaseRef = firebaseDatabase.getReference("team");
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {
                    Team inputteam = (Team)fileSnapshot.getValue(Team.class);
                    if(inputteam.getTeamName().equals(teamname))
                    {
                        publlicteamkey = fileSnapshot.getKey();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return publlicteamkey;
    }
    public static Team getTeambyname(String teamname)
    {
        DatabaseReference databaseRef = firebaseDatabase.getReference("team");
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {
                    Team inputteam = (Team)fileSnapshot.getValue(Team.class);
                    if(inputteam.getTeamName().equals(teamname))
                    {
                        sosockteam= inputteam;
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return sosockteam;
    }
    public static void setteaminfoinuser(Team team)
    {
        User user = new User();
        DatabaseReference databaseRef = firebaseDatabase.getReference("users");

        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {
                    User inputuser = (User)fileSnapshot.getValue(User.class);
                    if(inputuser.getUsername().equals(mFirebaseUser.getEmail())){
                        publicuserkey = fileSnapshot.getKey();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseRef.child(publicuserkey).child("teaminfo").push().setValue(team);
    }
    public static Team findteambyuser(User user)
    {
       Team team = new Team();
        DatabaseReference databaseRef = firebaseDatabase.getReference("team");

        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {
                    Team inputteam = (Team)fileSnapshot.getValue(Team.class);
                    teamArraylist.add(inputteam);
                    if(inputteam.getTeamleader().getUsername().equals(user.getUsername()))
                    {
                        sosockteam = inputteam;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return sosockteam;
    }
    public static Team showmyteam(String username)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseRef = database.getReference("team");

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {

                    Team readTeam= fileSnapshot.getValue(Team.class);
                    if(readTeam.getTeamleader().getUsername().equals(username))
                    {
                        myteam = readTeam;
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return myteam;
    }



}
