package il.cshaifasweng.OCSFMediatorExample.client.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.GetForManager;

public class GetForManagerEvent {
    GetForManager getForManager;

    public GetForManagerEvent(GetForManager getForManager) {
        this.getForManager = getForManager;
    }

    public GetForManager getGetForManager() {
        return getForManager;
    }

    public void setGetForManager(GetForManager getForManager) {
        this.getForManager = getForManager;
    }
}
