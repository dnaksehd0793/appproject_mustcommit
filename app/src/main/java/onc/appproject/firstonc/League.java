package onc.appproject.firstonc;

/**
 * Created by jamleekim on 2017-11-20.
 */

public class League {
    String name;
    String schedule;
    String sponsor;
    String joincost;
    String teamnumber ;
    public User leagueleader;
    public League(){

    }
    public League(String name, String schedule,String sponsor,String joincost,String teamnumber,User leagueleader){
        this.name = name;
        this.schedule = schedule;
        this.sponsor = sponsor;
        this.joincost = joincost;
        this.teamnumber = teamnumber;
        this.leagueleader = leagueleader;
    }
    public String getName(){return  name;}
    public String getSchedule(){return schedule;}
    public String getSponsor(){return sponsor;}
    public String getJoincost(){return joincost;}
    public String getTeamnumber(){return teamnumber;}
}
