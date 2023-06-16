package il.cshaifasweng.OCSFMediatorExample.client.controller;
import il.cshaifasweng.OCSFMediatorExample.client.EventBus.SubjectTeacherEvent;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.CourseTeacher;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import il.cshaifasweng.OCSFMediatorExample.entities.SubjectTeacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class ChooseQesController {
            @FXML
            private TableView<Question> Qtable;

            @FXML
            private TableColumn<Question, String> RightAns;

            @FXML
            private TableColumn<Question, String> ans1;

            @FXML
            private TableColumn<Question, String> ans2;

            @FXML
            private TableColumn<Question, String> ans3;

            @FXML
            private TableColumn<Question, String> ans4;

            @FXML
            private TableColumn<Question, String> theQ;

    int lastIndex= SimpleClient.getParams().size()-1;
    SubjectTeacher teacher = (SubjectTeacher) SimpleClient.getParams().get(lastIndex);
    List<Question> q = teacher.getQuestions();

            ObservableList<Question> list= FXCollections.observableArrayList(
                    new Question("the student ** crying","is","are","they","none","is")
            );

            @FXML
            public void initialize(URL url, ResourceBundle rb){
                theQ.setCellValueFactory(new PropertyValueFactory<Question, String>("theQ"));
                ans1.setCellValueFactory(new PropertyValueFactory<Question, String>("ans1"));
                ans2.setCellValueFactory(new PropertyValueFactory<Question, String>("ans2"));
                ans3.setCellValueFactory(new PropertyValueFactory<Question, String>("ans3"));
                ans4.setCellValueFactory(new PropertyValueFactory<Question, String>("ans4"));
                RightAns.setCellValueFactory(new PropertyValueFactory<Question, String>("RightAns"));

                Qtable.setItems(list);
            }




        }



