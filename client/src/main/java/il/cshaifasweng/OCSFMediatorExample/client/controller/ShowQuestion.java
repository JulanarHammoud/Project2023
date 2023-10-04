package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import il.cshaifasweng.OCSFMediatorExample.entities.SubjectAndId;
import il.cshaifasweng.OCSFMediatorExample.entities.SubjectTeacher;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.LinkedList;

import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;

public class ShowQuestion {
    @FXML
    Label theQ;
    @FXML
    Label answ1;
    @FXML
    Label answ2;
    @FXML
    Label answ3;
    @FXML
    Label answ4;
    @FXML
    Label right;
    int lastIndex= SimpleClient.getParams().size()-1;
    LinkedList<Object> msg = (LinkedList<Object>) SimpleClient.getParams().get(lastIndex);
    SubjectAndId subid = (SubjectAndId) msg.get(1);
    Teacher teacher = (Teacher) msg.get(2);
    SubjectTeacher subjectTeacher = (SubjectTeacher) msg.get(3);
    Question question = (Question) msg.get(4);
    int flag = (Integer) msg.get(5);
    public void initialize() {
        theQ.setText(question.getQuestion());
        answ1.setText("1. " + question.getAns1());
        answ2.setText("2. " + question.getAns2());
        answ3.setText("3. " + question.getAns3());
        answ4.setText("4. " + question.getAns4());
        right.setText("the right answer: " + question.getThe_right_ans());
    }
    @FXML
    void Back(ActionEvent event) {
        try {
            SubjectAndId newsubid;
            if(flag == 0){
                newsubid= new SubjectAndId(subjectTeacher , -1, teacher);
            } else {
                newsubid= new SubjectAndId(subjectTeacher , subid.getId() , teacher);
            }
            SimpleClient.getParams().add(newsubid);
            setRoot("ChooseQes");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void LogOut(ActionEvent event) throws IOException {

        LinkedList<Object> message = new LinkedList<Object>();
        message.add("#LogOut");
        //message.add(teacher.getId());
        SimpleClient.getClient().sendToServer(message);

    }
}
