package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
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
    @FXML
    Label note;
    int lastIndex= SimpleClient.getParams().size()-1;
    LinkedList<Object> msg = (LinkedList<Object>) SimpleClient.getParams().get(lastIndex);
    int origin = (Integer) msg.get(1);
    Question question = (Question) msg.get(5);
    public void initialize() {
        theQ.setText(question.getQuestion());
        note.setText("note: " + question.getNote() + "!");
        answ1.setText("1. " + question.getAns1());
        answ2.setText("2. " + question.getAns2());
        answ3.setText("3. " + question.getAns3());
        answ4.setText("4. " + question.getAns4());
        right.setText("the right answer: " + question.getThe_right_ans());
    }
    @FXML
    void Back(ActionEvent event) {
        try {
            if(origin ==0){ // coming from teacher's question table
                int flag = (Integer) msg.get(6);
                SubjectAndId subid = (SubjectAndId) msg.get(2);
                SubjectTeacher subjectTeacher = (SubjectTeacher) msg.get(4);;
                Teacher teacher = (Teacher) msg.get(3);;
                SubjectAndId newsubid;
                if(flag == 0){
                    subid= new SubjectAndId(subjectTeacher , -1, teacher);
                }
                SimpleClient.getParams().add(subid);
                setRoot("ChooseQes");
            }
            else{ // coming from manager's question table
                LinkedList<Object> msg1 = (LinkedList<Object>) msg.get(3);
                SimpleClient.getParams().add(msg1);
                setRoot("QuestionManager");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void LogOut(ActionEvent event) throws IOException {

        LinkedList<Object> message = new LinkedList<Object>();
        message.add("#LogOut");
        if(origin==0)
        {
            Teacher teacher = (Teacher) msg.get(3);
            message.add(teacher.getId());
            message.add("teacher");
        }
        SimpleClient.getClient().sendToServer(message);

    }
}
