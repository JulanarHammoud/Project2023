package il.cshaifasweng.OCSFMediatorExample.client.controller;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.LinkedList;

import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;

public class QuestionManagerController {
    int lastIndex= SimpleClient.getParams().size()-1;
    QuestionManager1 questionManager = (QuestionManager1) SimpleClient.getParams().get(lastIndex);

    CourseTeacher course = (CourseTeacher) questionManager.getCourseTeacher();
    GetForManager getForManager = (GetForManager) questionManager.getGetForManager();
    ObservableList<Question> data;

    @FXML
    TableView<Question> Qtable;
    @FXML
    TableColumn<Question, String> right;

    @FXML
    TableColumn<Question, String> answ1;

    @FXML
    TableColumn<Question, String> answ2;

    @FXML
    TableColumn<Question, String> answ3;

    @FXML
    TableColumn<Question, String> answ4;

    @FXML
    TableColumn<Question, String> question;
    @FXML
    TableColumn<Question, String> note;
    @FXML
    Label Course;

    @FXML
    void initialize() throws IOException {
        Course.setText(course.getName());
        LinkedList<Question> q = new LinkedList<>();
        for (SubjectTeacher subjectTeacher : course.getSubjectTeacher()) {
            for (Question Q:subjectTeacher.getQuestions()){
                q.add(Q);
        }
            data = FXCollections.observableArrayList(q);
            Qtable.setItems(data);
            Qtable.setEditable(true);

            question.setCellValueFactory(new PropertyValueFactory<Question, String>("question"));
            answ1.setCellValueFactory(new PropertyValueFactory<Question, String>("ans1"));
            answ2.setCellValueFactory(new PropertyValueFactory<Question, String>("ans2"));
            answ3.setCellValueFactory(new PropertyValueFactory<Question, String>("ans3"));
            answ4.setCellValueFactory(new PropertyValueFactory<Question, String>("ans4"));
            right.setCellValueFactory(new PropertyValueFactory<Question, String>("the_right_ans"));
            note.setCellValueFactory(new PropertyValueFactory<Question, String>("note"));
        }

    }

    @FXML
    void show(ActionEvent event){
        LinkedList<Object> message = new LinkedList<Object>();
        Question question1 = Qtable.getSelectionModel().getSelectedItem();
        message.add("ShowQuestionn");
        message.add(1); //comming from manager question page
        message.add(1);
        message.add(questionManager);
        message.add(1);
        message.add(question1);
        if(question1 == null){
            try {
                SimpleClient.getClient().sendToServer(message);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        } else{
            message.add(question1);
            SimpleClient.getParams().add(message);
            try{
                setRoot("ShowQuestion");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void back (ActionEvent event) throws IOException {
        SimpleClient.getParams().add(getForManager);
        try {
            setRoot("Manager");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void LogOut (ActionEvent event) throws IOException {
        LinkedList<Object> message = new LinkedList<Object>();
        message.add("#LogOut");
        SimpleClient.getClient().sendToServer(message);
        setRoot("primary");
    }
}
