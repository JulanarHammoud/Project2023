package il.cshaifasweng.OCSFMediatorExample.client.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.UpdateTimer;

public class UpdateTimerEvevnt {
    UpdateTimer updateTimer;

    public UpdateTimerEvevnt(UpdateTimer updateTimer) {
        this.updateTimer = updateTimer;
    }

    public UpdateTimer getUpdateTimer() {
        return updateTimer;
    }

    public void setUpdateTimer(UpdateTimer updateTimer) {
        this.updateTimer = updateTimer;
    }
}
