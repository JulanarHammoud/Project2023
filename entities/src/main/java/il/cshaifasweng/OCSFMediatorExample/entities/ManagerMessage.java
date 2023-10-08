package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Messagesformanager")
public class ManagerMessage implements Serializable {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    int time;
    String message;
    int TID;
    int examTeacherid;

    public int getId() {
        return id;
    }

    public ManagerMessage(int time, String message, int TID, int examTeacherid) {
        this.time = time;
        this.message = message;
        this.TID = TID;
        this.examTeacherid = examTeacherid;
    }

    public int getExamTeacherid() {
        return examTeacherid;
    }

    public void setExamTeacherid(int examTeacherid) {
        this.examTeacherid = examTeacherid;
    }

    public ManagerMessage() {
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTID() {
        return TID;
    }

    public void setTID(int TID) {
        this.TID = TID;
    }
}
