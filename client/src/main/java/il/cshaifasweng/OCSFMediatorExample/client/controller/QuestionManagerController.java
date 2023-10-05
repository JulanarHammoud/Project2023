package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.CourseTeacher;
import il.cshaifasweng.OCSFMediatorExample.entities.GetForManager;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import il.cshaifasweng.OCSFMediatorExample.entities.SubjectTeacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.LinkedList;

import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;

public class QuestionManagerController {
    int lastIndex= SimpleClient.getParams().size()-1;
    LinkedList<Object> msg = (LinkedList<Object>) SimpleClient.getParams().get(lastIndex);
    CourseTeacher course = (CourseTeacher) msg.get(0);
    GetForManager getForManager = (GetForManager) msg.get(1);
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
    void initialize() throws IOException {
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
    }
}
