package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import il.cshaifasweng.OCSFMediatorExample.entities.SubjectAndId;
import il.cshaifasweng.OCSFMediatorExample.entities.SubjectTeacher;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.LinkedList;

import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;


public class editQueController {
    @FXML
    private Button sumbit1;

    @FXML
    private TextField text;

    @FXML
    private TextField text1;

    @FXML
    private TextField text2;

    @FXML
    private TextField text3;

    @FXML
    private TextField text4;

    @FXML
    private TextField text5;
    @FXML
    private Button back;
    int lastIndex= SimpleClient.getParams().size()-1;
    LinkedList<Object> msg = (LinkedList<Object>) SimpleClient.getParams().get(lastIndex);
    Question question= (Question) msg.get(1);
    SubjectTeacher subject=(SubjectTeacher) msg.get(3);
    Teacher teacher=(Teacher) msg.get(4);
    public void initialize() {
        text.setText(question.getQuestion());
        text1.setText(question.getAns1());
        text2.setText(question.getAns2());
        text3.setText(question.getAns3());
        text4.setText(question.getAns4());
        text5.setText(question.getThe_right_ans());
    }

    @FXML
    public void submitaction(ActionEvent event) {
        Question updatequeston = new Question();
        updatequeston.setQuestion(text.getText());
        updatequeston.setAns1(text1.getText());
        updatequeston.setAns2(text2.getText());
        updatequeston.setAns3(text3.getText());
        updatequeston.setAns4(text4.getText());
        updatequeston.setThe_right_ans(text5.getText());
        System.out.println(updatequeston.getQuestion());
        LinkedList<Object> message = new LinkedList<Object>();
        message.add("editquestion");
        message.add(updatequeston);
        message.add(msg.get(2));
        message.add(msg.get(3));
        message.add(msg.get(4));
        message.add(question);
        message.add(msg.get(5));
        try {
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void bacaction(ActionEvent event) {
        try {
            SubjectAndId newsubid;
            if((Integer) msg.get(5) == 0){
                newsubid= new SubjectAndId(subject , -1, teacher);
            } else {
                newsubid= new SubjectAndId(subject , (Integer) msg.get(2) , teacher);
            }
            SimpleClient.getParams().add(newsubid);
            setRoot("ChooseQes");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void logOut(ActionEvent event) throws IOException {
        LinkedList<Object> message = new LinkedList<Object>();
        message.add("#LogOut");
        message.add(teacher.getId());
        message.add("teacher");
        SimpleClient.getClient().sendToServer(message);
    }
}
