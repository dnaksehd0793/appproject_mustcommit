package onc.appproject.firstonc;

/**
 * Created by jamleekim on 2017-11-13.
 */

public class User {
    public String useremail;
    public String username;
    public Team sosockteam;
    public boolean teampermission;
    public User(){

    }
    public User(String useremail){
        this.useremail = useremail;
    }
    public User(String useremail,String username,Team sosockteam){
        this.useremail = useremail;
        this.username = username;
        this.teampermission = false;
        this.sosockteam = null;
    }
    public String getUseremail(){
        return useremail;
    }
    public void setSosockteam(Team team){this.sosockteam = team;}
    public Team getSosockteam(){return this.sosockteam;}
    public String getUsername(){
        return username;
    }
    public Boolean getTeampermission(){return teampermission;}

}
