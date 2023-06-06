package il.cshaifasweng.OCSFMediatorExample.client.controller;
import il.cshaifasweng.OCSFMediatorExample.client.EventBus.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.StudentInfo;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.LinkedList;

public class GradesController {

    private  static LinkedList<Object> Grademsg =new LinkedList<>();

    @FXML
    int lastIndex= SimpleClient.getParams().size()-1;
    StudentInfo student = (StudentInfo) SimpleClient.getParams().get(lastIndex);
    @FXML
    private TextField Grade1;

    @FXML
    private TextField Grade2;
    @FXML
    private Button update1;

    @FXML
    private Button update2;

    @FXML
    private Button back;

    @FXML
    void initialize() {
        Grademsg.clear();
       // Grade1.setText(Integer.toString(student.getStudent().getGrade1()));
      //  Grade2.setText(Integer.toString(student.getStudent().getGrade2()));

    }



    @FXML
    void updateGrade1(ActionEvent event) {
        try{

        int newGrade=Integer.valueOf(Grade1.getText());
        Grademsg.add("#UpdateGrade");
        Grademsg.add(student.getStudent().getId());
        Grademsg.add(newGrade);
        Grademsg.add(1);
        SimpleClient.getClient().sendToServer(Grademsg);}
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void updateGrade2(ActionEvent event) {
        try{
        int newGrade=Integer.valueOf(Grade2.getText());
        Grademsg.add("#UpdateGrade");
        Grademsg.add(student.getStudent().getId());
        Grademsg.add(newGrade);
        Grademsg.add(2);
        SimpleClient.getClient().sendToServer(Grademsg);}
        catch (IOException e) {
        e.printStackTrace();
    }

    }
    @FXML
    void backevent(ActionEvent event) {
        try{
            SimpleClient.getClient().sendToServer("#ListStudents");
            System.out.println("we sent to server");}
        catch (IOException e) {
            e.printStackTrace();
        }

    }

}