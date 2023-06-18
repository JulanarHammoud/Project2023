package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.CourseTeacher;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ChooseCourseController {
    @FXML
    private Button submitbutton;
    @FXML
    private ComboBox<String> role;
    int lastIndex= SimpleClient.getParams().size()-1;
    Teacher teacher = (Teacher) SimpleClient.getParams().get(lastIndex);
    List<CourseTeacher> list =teacher.getCourses();
    public void initialize() throws IOException {
        for(int i=0;i<list.size();i++){
            //System.out.println(list.get(i));
        role.getItems().add(String.valueOf(list.get(i).getName()));}
       // String choose = role.getSelectionModel().getSelectedItem();
    }


    @FXML
    void submitaction(ActionEvent event)  {
        try {
        LinkedList list =new LinkedList();
        System.out.println(role.getSelectionModel().getSelectedItem());
        String choose = role.getSelectionModel().getSelectedItem();
        list.add("#CoursetTeacher");
        list.add(teacher);
        list.add(choose);
        System.out.println("QuestionPage");

            SimpleClient.getClient().sendToServer(list);}
         catch (IOException e) {
            e.printStackTrace();
        }

    }
}
