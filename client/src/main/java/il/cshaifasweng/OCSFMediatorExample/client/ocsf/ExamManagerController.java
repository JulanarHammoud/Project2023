package il.cshaifasweng.OCSFMediatorExample.client.ocsf;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;

public class ExamManagerController{
    int lastIndex= SimpleClient.getParams().size()-1;
    LinkedList<Object> msg = (LinkedList<Object>) SimpleClient.getParams().get(lastIndex);
    CourseTeacher course = (CourseTeacher) msg.get(0);
    GetForManager getForManager = (GetForManager) msg.get(1);
    ObservableList<Exam> data;
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
    void initialize() throws IOException {
        LinkedList<Exam> e = new LinkedList<>();
        for (SubjectTeacher subjectTeacher : course.getSubjectTeacher()) {
            List<Exam> listexams = subjectTeacher.getExams();
            for (Exam E:listexams){
                e.add(E);
            }
            data = FXCollections.observableArrayList(e);
            Etable.setItems(data);
            Etable.setEditable(true);
            IdCode.setCellValueFactory(new PropertyValueFactory<Exam, String>("IdCode"));
            subjectExam.setCellValueFactory(new PropertyValueFactory<Exam, String>("subject"));
            NumOfQuestions.setCellValueFactory(new PropertyValueFactory<Exam, String>("NumOfQuestions"));
            teacherExam.setCellValueFactory(new PropertyValueFactory<Exam, String>("teacher"));
            timerr.setCellValueFactory(new PropertyValueFactory<Exam, String>("timerr"));
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
