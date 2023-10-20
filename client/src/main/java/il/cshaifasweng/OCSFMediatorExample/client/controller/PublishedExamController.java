package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.ExamTeacher;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;
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

public class PublishedExamController {
    int lastIndex = SimpleClient.getParams().size() - 1;
    Teacher teacher = (Teacher) SimpleClient.getParams().get(lastIndex);
   // SubjectTeacher subject =  getSubject.getSubjectTeacher();
    List<ExamTeacher> exams = teacher.getPublishedExams();
    //CourseTeacher courseteacher = getSubject.getCourseTeacher();
    @FXML
    private Button back1;
    @FXML
    private Button logout2;
    @FXML
    private TableView<ExamTeacher> Etable;
    @FXML
    private TableColumn<ExamTeacher, Integer> id;
    @FXML
    private TableColumn<ExamTeacher, String> Subject;
    @FXML
    private TableColumn<ExamTeacher, String> course;
    @FXML
    private TableColumn<ExamTeacher, String> Teacher;
//    @FXML
//    private TableColumn<Exam, String> timerr;
    List<ExamTeacher> listexams =teacher.getPublishedExams();
    ObservableList<ExamTeacher> data ;
    public void initialize() {
        data = FXCollections.observableArrayList(exams);
        Subject.setCellValueFactory(dataValueFactory ->
                new SimpleStringProperty(dataValueFactory.getValue().getExam().getSubject()));
        Teacher.setCellValueFactory(dataValueFactory ->
                new SimpleStringProperty(dataValueFactory.getValue().getExam().getTeacher()));
        course.setCellValueFactory(dataValueFactory ->
                new SimpleStringProperty(dataValueFactory.getValue().getExam().getCourse()));
        course.setCellValueFactory(dataValueFactory ->
                new SimpleStringProperty(String.valueOf(dataValueFactory.getValue().getExam().getTimerr())));
        Etable.setItems(data);
    }

    @FXML
    void StudentsExams(){
        System.out.println("Asking server to send students Exams");
        LinkedList<Object> message = new LinkedList<>();
        message.add("#StudentsExams");
        message.add(teacher.getId());
        ExamTeacher exam = Etable.getSelectionModel().getSelectedItem();
        message.add(exam);
        try {
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void examDuration(ActionEvent event) throws IOException{
        try{
            LinkedList<Object> message = new LinkedList<>();
            message.add("#toDuration");
            message.add(teacher);
            ExamTeacher exam = Etable.getSelectionModel().getSelectedItem();
            message.add(exam);
            SimpleClient.getClient().sendToServer(message);
           // setRoot("Duration");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void back (ActionEvent event) throws IOException {
        try{
            SimpleClient.getParams().add(teacher);
            setRoot("teacherpage");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void LogOut (ActionEvent event) throws IOException {
        LinkedList<Object> message = new LinkedList<Object>();
        message.add("#LogOut");
        message.add(teacher.getId());
        message.add("teacher");
        SimpleClient.getClient().sendToServer(message);
    }
}


