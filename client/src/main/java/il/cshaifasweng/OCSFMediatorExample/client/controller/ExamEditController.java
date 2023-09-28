package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;

public class ExamEditController {
    int lastIndex = SimpleClient.getParams().size() - 1;
    ExamSubjectTeacherEdit message = (ExamSubjectTeacherEdit) SimpleClient.getParams().get(lastIndex);
    Exam exam = message.getExam();
    List<Question> questions = exam.getQuestions();
    Teacher teacher = message.getTeacher();
    SubjectTeacher subject = message.getSubjectTeacher();
    //List<Question> listquestions = subject.getQuestions();
    ObservableList<Question> data = FXCollections.observableArrayList(questions);
    SubjectAndId subId = new SubjectAndId(subject, exam.getId(), teacher);
    @FXML
    private Label ETime;

    @FXML
    private Label Studentnote;

    @FXML
    private Label Teachernote;
    @FXML
    private AnchorPane test;
    @FXML
    void initialize() throws IOException {

        for(Question q :questions){
            q.setExist(false);
        }
        System.out.println("I reached Edit controller");
        ETime.setText("exam time is: " + exam.getTimerr());
        Studentnote.setText("notes for students: " + exam.getStudentNotes());
        Teachernote.setText("notes for teachesrs: " + exam.getTeacherNotes());
        double i = 100.0;
        int j=0;
        for (Question q : questions) {
            HBox hBox = new HBox(2);
            CheckBox checkbox = new CheckBox();
            Text text = new Text(q.getQuestion());
            hBox.getChildren().add(checkbox);
            hBox.getChildren().add(text);
            AnchorPane.setLeftAnchor(text, 20.0);AnchorPane.setTopAnchor(hBox, i);
            AnchorPane.setLeftAnchor(text, 20.0);
            test.getChildren().add(hBox);
            i = i + 20;
            j++;
            checkbox.setOnAction(e -> {
                if(checkbox.isSelected()){
                    q.setExist(true);
                }
            });
        }

    }

    @FXML
    public void deletequestions (ActionEvent event) {
        System.out.println("client is deleting questions");
        LinkedList<Question> selectedQuestions = new LinkedList<>() ;
        LinkedList<Object> message = new LinkedList<Object>();
        message.add("#Edit_Q_Exam");
        message.add(teacher);
        message.add(subject);
        message.add(subId.getId());
        System.out.println("client is sendeing these questions to delete them from the exam");

        for(Question question :questions){
            if (question.getExist()) {
                System.out.println(question.getQuestion());
            }
            else{
                selectedQuestions.add(question);
            }
        }
        System.out.println(selectedQuestions.isEmpty());
        message.add(exam);
        message.add(selectedQuestions);
        System.out.println( "the list is null:" + selectedQuestions == null);
        if(selectedQuestions.isEmpty()){
            message.clear();
        }
        else{
            try {
                SimpleClient.getClient().sendToServer(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    public void addquestions (ActionEvent event) {
        LinkedList<Question> selectedQuestions = new LinkedList<>(questions) ;
        System.out.println("client is adding questions");
        SubjectAndId prepareToChoose = new SubjectAndId(subject, exam.getId(), teacher,selectedQuestions);
        SimpleClient.getParams().add(prepareToChoose);
        try {
            setRoot("ChooseQes");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void Finish (ActionEvent event) {
        LinkedList<Object> message = new LinkedList<Object>();
        message.add("#GetSubject");
        message.add(subject.getId());
        message.add(teacher);
        try {
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
