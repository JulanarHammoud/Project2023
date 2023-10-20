package il.cshaifasweng.OCSFMediatorExample.client.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.GetForManager2;

public class GetForManagerEvent2 {
    GetForManager2 getForManager2;

    public GetForManagerEvent2(GetForManager2 getForManager2) {
        this.getForManager2 = getForManager2;
    }

    public GetForManager2 getGetForManager2() {
        return getForManager2;
    }

    public void setGetForManager2(GetForManager2 getForManager2) {
        this.getForManager2 = getForManager2;
    }
}
