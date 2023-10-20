package il.cshaifasweng.OCSFMediatorExample.client.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.GetForManager1;

public class GetForManagerEvent1 {
    GetForManager1 getForManager1;

    public GetForManagerEvent1(GetForManager1 getForManager1) {
        this.getForManager1 = getForManager1;
    }

    public GetForManager1 getGetForManager1() {
        return getForManager1;
    }

    public void setGetForManager1(GetForManager1 getForManager1) {
        this.getForManager1 = getForManager1;
    }
}
