
package puzzlegame;

import java.io.Serializable;
import javafx.scene.control.TextField;

public class User implements Serializable {
    private String id;
    private String name;
    private int age;
    private String gender;
    private String password;
    private long score1;
    private long score2;
    private long score3;
    User(){}
    User(String id,String name,int age,String gender,String password){
        this.id=id;
        this.name=name;
        this.age=age;
        this.gender=gender;
        this.password=password;
    }
    public String toString(){
        return "Id: "+ this.id+"    Name: "+this.name+"    Age: "+this.age+"    Gender: "+this.gender;
    }
    public String getId(){
        return this.id;
    }
    public String getPassword(){
         return this.password;
    }
    public String getName(){
        return this.name;
    }
    public void setScore1(long score){
        this.score1=score;
    }
    public long getScore1(){
        return this.score1;
    }
    public void setScore2(long score){
        this.score2=score;
    }
    public long getScore2(){
        return this.score2;
    }
    public void setScore3(long score){
        this.score3=score;
    }
    public long getScore3(){
        return this.score3;
    }
}
