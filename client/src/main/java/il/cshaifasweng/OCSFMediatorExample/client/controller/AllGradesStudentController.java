package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.ExamStudent;
import il.cshaifasweng.OCSFMediatorExample.entities.GradeSt;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;

public class AllGradesStudentController {
    @FXML
    private Button back1;
    @FXML
    private Button log;
    @FXML
    private TableColumn<ExamStudent, Integer> gradeColumn;

    @FXML
    private TableColumn<ExamStudent, Integer> idExamColumn;

    @FXML
    private TableColumn<ExamStudent, String> subjectColumn;


    @FXML
    private TableView<ExamStudent> tableView;
    int lastIndex= SimpleClient.getParams().size()-1;
    GradeSt StEx = (GradeSt) SimpleClient.getParams().get(lastIndex);
    List<ExamStudent> t=StEx.getSs().getStudentExams();
    LinkedList<ExamStudent> examStudents = new LinkedList<>();
    ObservableList<ExamStudent> data = FXCollections.observableArrayList();
    public void initialize() {
        for(ExamStudent s : t){
            if(s.isApprove()==true){
                examStudents.add(s);
            }
        }
        ObservableList<ExamStudent> data = FXCollections.observableArrayList(examStudents);
        data = FXCollections.observableArrayList(examStudents);
        gradeColumn.setCellValueFactory(dataValueFactory ->
                Bindings.createObjectBinding(() -> dataValueFactory.getValue().getGrade()));
        idExamColumn.setCellValueFactory(dataValueFactory ->
                Bindings.createObjectBinding(() -> dataValueFactory.getValue().getExamTId()));
        subjectColumn.setCellValueFactory(dataValueFactory ->
                new SimpleStringProperty(String.valueOf(dataValueFactory.getValue().getExam().getSubject())));
        tableView.setItems(data);
    }

    @FXML
    void backaction(ActionEvent event) {
        SimpleClient.getParams().add(StEx);
        try {
            setRoot("GradesButton");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void logoutaction(ActionEvent event) {
        LinkedList<Object> message = new LinkedList<Object>();
        message.add("#LogOut");
        message.add(StEx.getSs().getId());
        message.add("student");
        try {
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }


