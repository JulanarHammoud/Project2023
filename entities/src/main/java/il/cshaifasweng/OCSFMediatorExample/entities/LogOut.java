package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class LogOut implements Serializable {
    String msg;

    public LogOut(String msg) {
        this.msg = msg;
    }
}
