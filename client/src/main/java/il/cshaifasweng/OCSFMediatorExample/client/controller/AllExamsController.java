package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;

public class AllExamsController {

    int lastIndex = SimpleClient.getParams().size() - 1;
    GetSubject message = (GetSubject) SimpleClient.getParams().get(lastIndex);
    Teacher teacher =  message.getTeacher();
    SubjectTeacher subject =  message.getSubjectTeacher();
    List<Exam> exams = subject.getExams();

    @FXML
    private TableView<Exam> Etable;
    @FXML
    private TableColumn<Exam, String> IdCode;
    @FXML
    private TableColumn<Exam, String> subjectExam;
    @FXML
    private TableColumn<Exam, String> NumOfQuestions;
    @FXML
    private TableColumn<Exam, String> teacherExam;
    @FXML
    private TableColumn<Exam, String> timerr;
    @FXML
    private AnchorPane pane;
    List<Exam> listexams = subject.getExams();
    ObservableList<Exam> data ;



    public void initialize()  {
        data= FXCollections.observableArrayList(exams);
        for(Exam e :listexams){
            e.setExist(false);
        }
        Etable.setEditable(true);
        IdCode.setCellValueFactory(new PropertyValueFactory<Exam, String>("IdCode"));
        //course.setCellValueFactory(new PropertyValueFactory<Question, String>("ans1"));
        subjectExam.setCellValueFactory(new PropertyValueFactory<Exam, String>("subject"));
        NumOfQuestions.setCellValueFactory(new PropertyValueFactory<Exam, String>("NumOfQuestions"));
        teacherExam.setCellValueFactory(new PropertyValueFactory<Exam, String>("teacher"));
        timerr.setCellValueFactory(new PropertyValueFactory<Exam, String>("timerr"));
        Etable.setItems(data);
    }

    @FXML
    public void show (ActionEvent event) throws IOException {
        try{
            Exam exam = Etable.getSelectionModel().getSelectedItem();
            ExamSubjectTeacher EST=new ExamSubjectTeacher(teacher,subject,exam);
            LinkedList<Object> message = new LinkedList<Object>();
            message.add("#ShowExamm");
            message.add(EST);
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void edit (ActionEvent event) throws IOException {
        try{
            Exam exam = Etable.getSelectionModel().getSelectedItem();
            ExamSubjectTeacherEdit ESTE = new ExamSubjectTeacherEdit(teacher,subject,exam);
            LinkedList<Object> message = new LinkedList<Object>();
            message.add("#EditExam");
            message.add(ESTE);
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void makeExam(ActionEvent event) {
//        try {
////            Course course = message.getCourse();
////            Subject subject1 = message.getSubject();
////            LinkedList<Object> message = new LinkedList<Object>();
////            message.add(teacher);
////            message.add(course);
////            message.add(subject1);
////            SimpleClient.getParams().add(message);
////            setRoot("MakeExam");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @FXML
    void deleteExam(ActionEvent event){

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
        //message.add(teacher.getId());
        SimpleClient.getClient().sendToServer(message);
    }
}


