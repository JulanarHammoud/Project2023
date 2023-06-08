package il.cshaifasweng.OCSFMediatorExample.client.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.LogOut;

public class LogOutEvent {
    LogOut logout;

    public LogOutEvent(LogOut logout) {
        this.logout = logout;
    }

    public LogOut getLogout() {
        return logout;
    }

    public void setLogout(LogOut logout) {
        this.logout = logout;
    }
}
