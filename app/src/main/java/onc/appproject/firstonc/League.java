package onc.appproject.firstonc;

/**
 * Created by jamleekim on 2017-11-20.
 */

public class League {
    String name;
    String schedule;
    String sponsor;
    String cost;
    String region;
    String teamnumber ;
    String date;
    public User leagueleader;
    public League(){

    }
    public League(String name, String schedule,String sponsor,String joincost,String teamnumber,User leagueleader){
        this.name = name;
        this.schedule = schedule;
        this.sponsor = sponsor;
        this.cost = joincost;
        this.teamnumber = teamnumber;
        this.leagueleader = leagueleader;
    }
    public League(String name , String sponsor, String region, String  cost,String teamnumber,String date){
        this.name = name;
        this.sponsor = sponsor;
        this.region = region;
        this.cost = cost;
        this.teamnumber = teamnumber;
        this.date = date;
    }

    /*TextView textleaguename;
    TextView textleaguesponsor;
    TextView textleagueregion;
    Spinner spinner_cost;
    String cost;
    Spinner spinner_teamnum;
    String teamnum;*/
    public String getName(){return  name;}
    public String getDate(){return date;}
    public String getRegion(){return region;}
    public String getSchedule(){return schedule;}
    public String getSponsor(){return sponsor;}
    //public String getJoincost(){return joincost;}
    public String getTeamnumber(){return teamnumber;}
}