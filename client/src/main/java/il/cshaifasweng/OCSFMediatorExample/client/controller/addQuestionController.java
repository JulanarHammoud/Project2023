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

public class addQuestionController {

    @FXML
    private TextField answ1;

    @FXML
    private TextField answ2;

    @FXML
    private TextField answ3;

    @FXML
    private TextField answ4;

    @FXML
    private TextField correct;

    @FXML
    private Button submit;

    @FXML
    private TextField theQ;
    int lastIndex= SimpleClient.getParams().size()-1;
    SubjectAndId subid = (SubjectAndId) SimpleClient.getParams().get(lastIndex);
    Teacher teacher = subid.getTeacher();
    SubjectTeacher subjectTeacher = subid.getSubject();
    int id = subid.getId();
    LinkedList<Question> questions = subid.getQuestions();

    //Teacher teacher = (Teacher) SimpleClient.getParams().get(lastIndex0);
    //int lastIndex= SimpleClient.getParams().size()-2;
    //SubjectTeacher subjectTeacher = (SubjectTeacher) SimpleClient.getParams().get(lastIndex);
    //int lastIndex2= SimpleClient.getParams().size()-3;
   // TableView<Question> Table = (TableView<Question>) SimpleClient.getParams().get(lastIndex2);
    //int lastIndex3= SimpleClient.getParams().size()-4;
    //int id = (int) SimpleClient.getParams().get(lastIndex3);
   // int lastIndex4= SimpleClient.getParams().size()-5;
   // LinkedList<Question> questions = (LinkedList<Question>) SimpleClient.getParams().get(lastIndex2);


    @FXML
    void submitaction(ActionEvent event) {
        try {
            System.out.println("Im in add question controller");
            LinkedList<Object> message = new LinkedList<Object>();
            String question = theQ.getText();
            String answer1 = answ1.getText();
            String answer2 = answ2.getText();
            String answer3 = answ3.getText();
            String answer4 = answ4.getText();
            String right = correct.getText();
            message.add("MakenewQuestion");
            message.add(subjectTeacher);
            //message.add(Table);
            message.add(question);
            message.add(answer1);
            message.add(answer2);
            message.add(answer3);
            message.add(answer4);
            message.add(right);
            message.add(id);
            message.add(teacher);
            message.add(questions);
            SimpleClient.getClient().sendToServer(message);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    //@FXML
    //void LogOutButton4(ActionEvent event) {
    // try{
    //      setRoot("primary");
    //  }
    // catch (IOException e) {
    //    e.printStackTrace();
    // }
    //}

    @FXML
    void returnbutton4(ActionEvent event) {
        try{
            LinkedList<Object> message = new LinkedList<Object>();
            if(subid.getQuestions()==null){
                SubjectAndId sub = new SubjectAndId(subjectTeacher,id,teacher);
                SimpleClient.getParams().add(sub);
            }
            else{
                SubjectAndId sub = new SubjectAndId(subjectTeacher,id,teacher,questions);
                SimpleClient.getParams().add(sub);
            }
            setRoot("ChooseQes");
        }
        catch (IOException e) {
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


