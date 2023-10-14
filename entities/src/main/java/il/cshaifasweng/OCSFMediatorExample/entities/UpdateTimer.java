package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class UpdateTimer implements Serializable {
    int timer;

    public UpdateTimer(int timer) {
        this.timer = timer;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }
}
