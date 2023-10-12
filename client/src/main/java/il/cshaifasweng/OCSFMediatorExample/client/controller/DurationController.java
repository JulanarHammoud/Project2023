package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.ExamTeacher;
import il.cshaifasweng.OCSFMediatorExample.entities.StudentsExams;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.LinkedList;

import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;

public class DurationController {
    int lastIndex = SimpleClient.getParams().size() - 1;
    LinkedList<Object> message = (LinkedList<Object>) SimpleClient.getParams().get(lastIndex);
    Teacher teacher = (Teacher) message.get(0);
    ExamTeacher examTeacher = (ExamTeacher) message.get(1);
    @FXML
    private Accordion extratime;
    @FXML Label starttime;
    @FXML Label startstudent;
    @FXML Label date;
    @FXML Label completestudent;
    @FXML Label finish;
    @FXML Label type;
    String tim;
    String des;
    int t,d;
    @FXML
    void initialize() throws IOException {
        date.setText(examTeacher.getDate());
        if(examTeacher.isComputed()){
            type.setText("Computed Exam");
        } else{
            type.setText("Manual Exam");
        }
        starttime.setText(examTeacher.getTime());
        startstudent.setText(String.valueOf(examTeacher.getExamsOfStudents().size()));
        completestudent.setText(String.valueOf(examTeacher.getFinish()));
        t=0; d=0;
        TitledPane timemessage = new TitledPane();
        timemessage.setText("Request For Extra Time");
        extratime.getPanes().add(timemessage);

        LocalTime currentTime = LocalTime.parse(examTeacher.getTime());
        Duration timeToAdd = Duration.ofMinutes(examTeacher.getExam().getTimerr());
        LocalTime newTime = currentTime.plus(timeToAdd);
        finish.setText(newTime.toString());

        Label T = new Label();
        Label D = new Label();
        T.setText("Requested Time: ");
        D.setText("Description: ");
        TextField time =new TextField();
        TextField description =new TextField();
        Button submit = new Button();
        submit.setText("Submit");
        HBox timeHbox = new HBox(2);
        HBox desHbox = new HBox(2);
        timeHbox.getChildren().addAll(T,time);
        desHbox.getChildren().addAll(D,description);
        VBox timeVbox = new VBox(3);
        timeVbox.getChildren().addAll(timeHbox,desHbox,submit);
        time.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String Tim) {
                tim=Tim; t=1;
            }
        });
        description.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String Des) {
                des=Des; d=1;
            }
        });
        submit.setOnAction(this::extratime);
        timemessage.setContent(timeVbox);
    }
    @FXML
    public void extratime(ActionEvent event){
        LinkedList<Object> message = new LinkedList<Object>();
        try{
            message.add("SendMassage");
            message.add(teacher);
            message.add(examTeacher);
            message.add(Integer.valueOf(tim));
            message.add(des);
            SimpleClient.getClient().sendToServer(message);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    public void Back (ActionEvent event) throws IOException {
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
