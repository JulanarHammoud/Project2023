package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.CourseTeacher;
import il.cshaifasweng.OCSFMediatorExample.entities.SubjectTeacher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class choosesubjectController {
    @FXML
    private Button submitbutton;
    @FXML
    private ComboBox<String> role;
    int lastIndex= SimpleClient.getParams().size()-1;
    CourseTeacher courseteacher = (CourseTeacher) SimpleClient.getParams().get(lastIndex);
    List<SubjectTeacher> list =courseteacher.getSubjectTeacher();
    public void initialize()  {
        for(int i=0;i<list.size();i++){
            //System.out.println(list.get(i));
            role.getItems().add(String.valueOf(list.get(i).getSb_name()));}
        // String choose = role.getSelectionModel().getSelectedItem();
    }


    @FXML
    void submitaction(ActionEvent event)  {
        try{
            LinkedList list =new LinkedList();
            System.out.println(role.getSelectionModel().getSelectedItem());
            String choose = role.getSelectionModel().getSelectedItem();
            list.add("#SubjectTeacher");
            // list.add(teacher);
            list.add(choose);
            SimpleClient.getClient().sendToServer(list);}
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
