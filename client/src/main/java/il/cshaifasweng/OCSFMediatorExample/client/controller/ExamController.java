package il.cshaifasweng.OCSFMediatorExample.client.controller;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;

import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;


import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.control.ComboBox;

public class ExamController {

    @FXML
    private ComboBox<String> Combobox;
                @FXML
                private Button Next;

                @FXML
                private TextField NumQ;

                @FXML
                private TextField PutSNotes;

                @FXML
                private TextField PutTNotes;

                @FXML
                private TextField PutTimer;

                @FXML
                private TextField SNotes;

                @FXML
                private TextField TNotes;


                @FXML
                private TextField putNumQ;

                @FXML
                private TextField timer;

        int lastIndex= SimpleClient.getParams().size()-1;
         Teacher teacher = (Teacher) SimpleClient.getParams().get(lastIndex);
    List<CourseTeacher> list=teacher.getCourses();


    public void initialize(){
            for(int i=0;i<list.size();i++){
                Combobox.getItems().addAll(list.get(i).getName());
                TitledPane titledPane = new TitledPane();
                titledPane.setText(list.get(i).getName());
                List<SubjectTeacher> sub = list.get(i).getSubjectTeacher();
                VBox content1 = new VBox();
                // Add content to TitledPane
                for(int j=0; j< sub.size();j++){
                Button button = new Button(sub.get(j).getSb_name());
                content1.getChildren().add(button);
                    titledPane.setContent(content1);
                }
                // Add TitledPanes to the Accordion
                }
    }


        @FXML
        void Next(ActionEvent event) {
        try{

               LinkedList<Object> message = new LinkedList<Object>();
                message.add("#MakeExam");
            String choose = Combobox.getSelectionModel().getSelectedItem();
                message.add(putNumQ.getText());
                message.add(PutTNotes.getText());
                message.add(PutTimer.getText());
                message.add(PutSNotes.getText());
                message.add(choose);
                String kl=teacher.getFirstName();
                kl=kl+" ";
                kl=kl+teacher.getLastName();
                message.add(kl);

                System.out.println("Selected item: " + putNumQ.getText());
                System.out.println("Selected item: " + PutTNotes.getText());
                System.out.println("Selected item: " + PutTimer.getText());
                System.out.println("Selected item: " + PutSNotes.getText());
                SimpleClient.getClient().sendToServer(message);

         /*   LinkedList<Object> list1=new LinkedList<>();
            list1.add("#SubjectTeacher");
            list1.add(choose);
            SimpleClient.getClient().sendToServer(list1);*/

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }


}





//    private int IdCode;
//   // private Timer timer;   v
//    private int NumOfQuestions;  v
//    private String subject;   v
//    private String teacher;
//    private String TeacherNotes;   v
//    private String StudentNotes;   v
//    private String code;
