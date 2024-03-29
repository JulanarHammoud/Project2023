
package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.CourseStudent;
import il.cshaifasweng.OCSFMediatorExample.entities.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class StudentController {
    int lastIndex= SimpleClient.getParams().size()-1;
    Student student = (Student) SimpleClient.getParams().get(lastIndex);
    List<CourseStudent> list =student.getCourses();

    @FXML
    private Label StName;

    @FXML
    private Button exams;

    @FXML
    private Button grades;

    @FXML
    private Button logout;

    @FXML
    void initialize() {
//        examButton.setDisable(true);
//        combo.setDisable(true);
//        examCode.setDisable(true);
        StName.setText(String.valueOf(student.getFirstName() + " " + student.getLastName()));
//        combo.getItems().add("computerized");
//        combo.getItems().add("manual");
        //  for(int i=0;i<list.size();i++){
        //      // Create TitledPane
        //     TitledPane titledPane = new TitledPane();
        //     titledPane.setText(list.get(i).getName());
        //     // Add content to TitledPane 1
        //     VBox content1 = new VBox();
        //     Button button = new Button("test1");
        //     content1.getChildren().add(button);
        //     titledPane.setContent(content1);
        //     // Add TitledPanes to the Accordion
        //     accordian.getPanes().add(titledPane);}
        //}
    }

    @FXML
    void examsGold(ActionEvent event) throws IOException {
        try {
            LinkedList<Object> message = new LinkedList<Object>();
            message.add("#GoToExStudentA");
            message.add(student);
//        message.add(list);
            System.out.println("" + student.getFirstName() + list.size());
            SimpleClient.getClient().sendToServer(message);
//        String id_ex=examCode.getText();
//        String choose=combo.getSelectionModel().getSelectedItem();
//        if(choose=="manual")
//        {System.out.println(id_ex+choose);
//            message.add(id_ex);
//            message.add(choose);
//            String t= String.valueOf(student.getId());
//            message.add(t);
//            System.out.println(id_ex+choose+t);
//            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void gradesAct(ActionEvent event) {
        try {
            LinkedList<Object> message = new LinkedList<Object>();
            message.add("#GradesStudent");
            message.add(String.valueOf(student.getId()));
            System.out.println("" + student.getFirstName() + list.size());
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void LogOut(ActionEvent event) throws IOException {
        LinkedList<Object> message = new LinkedList<Object>();
        message.add("#LogOut");
        message.add(student.getId());
        message.add("student");
        SimpleClient.getClient().sendToServer(message);
    }

}





