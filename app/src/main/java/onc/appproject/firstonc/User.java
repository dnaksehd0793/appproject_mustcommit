package onc.appproject.firstonc;

/**
 * Created by jamleekim on 2017-11-13.
 */

public class User {
    public String useremail;
    public String username;
    public User(){

    }
    public User(String useremail){
        this.useremail = useremail;
    }
    public User(String useremail,String username){
        this.useremail = useremail;
        this.username = username;
    }
    public String getUseremail(){
        return useremail;
    }
    public String getUsername(){
        return username;
    }
}
