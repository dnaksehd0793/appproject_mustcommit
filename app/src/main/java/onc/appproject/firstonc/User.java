package onc.appproject.firstonc;

/**
 * Created by jamleekim on 2017-11-13.
 */

public class User {
    public String useremail;
    public String username;
    public Team sosockteam;
    public boolean teampermission;

    public String sex;
    public String downtown;
    public String userheight;


    public User() {

    }

    public User(String useremail) {
        this.useremail = useremail;
    }

    public User(String useremail, String username, Team sosockteam) {
        this.useremail = useremail;
        this.username = username;
        this.teampermission = false;
        this.sosockteam = null;
    }
    public User(String useremail, String username, Team sosockteam,String sex, String downtown) {
        this.useremail = useremail;
        this.username = username;
        this.teampermission = false;
        this.sosockteam = null;
        this.sex = sex;
        this.downtown = downtown;
    }
    public User(String useremail, String username, Team sosockteam, String sex, String downtown, String userheight)
    {
        this.useremail = useremail;
        this.username = username;
        this.teampermission = false;
        this.sosockteam = null;
        this.sex = sex;
        this.downtown = downtown;
        this.userheight = userheight;
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
