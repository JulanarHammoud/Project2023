package il.cshaifasweng.OCSFMediatorExample.client.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.Question;

public class QuestionEvent {
    Question question;

    public Question getQuestion() {
        return question;
    }

    public QuestionEvent(Question question) {
        this.question = question;
    }
    public QuestionEvent(){}

    public void setQuestion(Question question) {
        this.question = question;
    }
}
