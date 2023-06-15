package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.CourseTeacher;
import il.cshaifasweng.OCSFMediatorExample.entities.SubjectTeacher;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

import java.util.LinkedList;
import java.util.List;


import il.cshaifasweng.OCSFMediatorExample.client.EventBus.*;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
public class choosesubjectController {

    @FXML
    private ComboBox<String> Combo;
    @FXML
    private Button Next;
    int lastIndex= SimpleClient.getParams().size()-1;
    CourseTeacher courseTeacher = (CourseTeacher) SimpleClient.getParams().get(lastIndex);

   List<SubjectTeacher> list = (List<SubjectTeacher>) courseTeacher.getSubjectTeacher();

    public choosesubjectController() {
    }

    public void initialize(){
       for(int i=0;i<list.size();i++){
          Combo.getItems().addAll(list.get(i).getSb_name());}
    }

    @FXML
    void Next(ActionEvent event) {
        try{
            String choose = Combo.getSelectionModel().getSelectedItem();
             LinkedList<Object> message = new LinkedList<Object>();
                message.add("#MakeExam2");
                message.add(choose);

                System.out.println("Selected item: " + choose);
                SimpleClient.getClient().sendToServer(message);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
