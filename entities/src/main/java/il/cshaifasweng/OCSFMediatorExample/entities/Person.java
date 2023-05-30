package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;


@MappedSuperclass
public abstract class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_id")
    protected int id;
    protected String FirstName;
    protected String LastName;
    protected String UserName;
    @Column(name = "PassWord")
    protected String PassWord;
    protected boolean Active;

    public Person(String firstName, String lastName, String userName, String passWord) {
        FirstName = firstName;
        LastName = lastName;
        UserName = userName;
        PassWord = passWord;
        Active= false;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }


    public String getUserName() {
        return UserName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public boolean getActive() {
        return Active;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }


    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public void setActive(boolean active) {
        Active = active;
    }
}
