package il.cshaifasweng.OCSFMediatorExample.client.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.QuestionManager1;

public class QuestionManagerEvent {
    QuestionManager1 questionManager;

    public QuestionManagerEvent(QuestionManager1 questionManager) {
        this.questionManager = questionManager;
    }

    public QuestionManager1 getQuestionManager() {
        return questionManager;
    }

    public void setQuestionManager(QuestionManager1 questionManager) {
        this.questionManager = questionManager;
    }
}
