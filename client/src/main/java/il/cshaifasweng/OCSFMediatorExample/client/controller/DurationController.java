package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.ExamTeacher;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;
import il.cshaifasweng.OCSFMediatorExample.entities.ToDuration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;

public class DurationController {
    int lastIndex = SimpleClient.getParams().size() - 1;
    ToDuration message = (ToDuration) SimpleClient.getParams().get(lastIndex);
    Teacher teacher = message.getTeacher();
    ExamTeacher examTeacher = message.getExamTeacher();
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
    Timeline timeline=new Timeline();

    @FXML
    void initialize() throws IOException {
        SimpleClient.setPosition("Duration");
        int last = SimpleClient.getMesFromClient().size() - 1;
        System.out.println(" the list size is: " + last) ;
        if(last != -1)
        {
            if(SimpleClient.getMesFromClient().get(last).getClass().equals(ToDuration.class)) {
                ToDuration toDuration = (ToDuration) SimpleClient.getMesFromClient().get(last);
                teacher = toDuration.getTeacher();
                examTeacher = toDuration.getExamTeacher();
            }
        }
        date.setText(examTeacher.getDate());
        if(examTeacher.isComputed()){
            type.setText("Computed Exam");
        } else{
            type.setText("Manual Exam");
        }
        starttime.setText(examTeacher.getTime());
        startstudent.setText(String.valueOf(examTeacher.getStart()));
        completestudent.setText(String.valueOf(examTeacher.getFinish()));
        t=0; d=0;
        TitledPane timemessage = new TitledPane();
        timemessage.setText("Request For Extra Time");
        extratime.getPanes().add(timemessage);

        finish.setText(examTeacher.getFinishTime());

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
        javafx.util.Duration sec = javafx.util.Duration.seconds(1);
        timeline = new Timeline(new KeyFrame(sec, event -> {
           LocalTime currentTime = LocalTime.now();
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
           LocalTime parsedTime = LocalTime.parse(examTeacher.getFinishTime(),formatter);
           int comparison = currentTime.compareTo(parsedTime);
           if(comparison>0){
                submit.setDisable(true);
           }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        submit.setOnAction(this::extratime);
        timemessage.setContent(timeVbox);
    }
    @FXML
    public void extratime(ActionEvent event){
        LinkedList<Object> message = new LinkedList<Object>();
        try{
            timeline.stop();
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
            timeline.stop();
            SimpleClient.setPosition("");
            SimpleClient.getParams().add(teacher);
            setRoot("teacherpage");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void LogOut (ActionEvent event) throws IOException {
        timeline.stop();
        LinkedList<Object> message = new LinkedList<Object>();
        SimpleClient.setPosition("");
        message.add("#LogOut");
        message.add(teacher.getId());
        message.add("teacher");
        SimpleClient.getClient().sendToServer(message);
    }
}
