package onc.appproject.firstonc;

import java.util.ArrayList;

/**
 * Created by jamleekim on 2017-11-10.
 */

public class Team {
    public String teamName;
    //public String teamLeader;
    public String teamregion;
    public boolean official; //등록유무
    public User teamleader;
    public User member;
    public Team(){

    }
    public Team(String teamName , String teamregion, boolean official){
        this.teamName = teamName;
        this.teamregion = teamregion;
        this.official = official;
    }
    public Team(String teamName , String teamregion, boolean official,User teamleader){
        this.teamName = teamName;
        this.teamregion = teamregion;
        this.official = official;
        this.teamleader = teamleader;
    }

    public String getTeamName() {
        return teamName;
    }
    public String getTeamregion(){
        return teamregion;
    }
    public User getTeamleader(){return teamleader;}

}
