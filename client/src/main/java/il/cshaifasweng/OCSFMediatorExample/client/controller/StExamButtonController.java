package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StExamButtonController implements Serializable {

    @FXML
    private Button LogIn2;

    @FXML
    private Text Sub1;

    @FXML
    private Text date1;

    @FXML
    private Text date2;

    @FXML
    private Button logIn1;

    @FXML
    private Text sub2;

    @FXML
    private Button LogIn3;

    @FXML
    private Button LogIn4;
    @FXML
    private Text date3;

    @FXML
    private Text date4;
    @FXML
    private Text sub3;

    @FXML
    private Text sub4;

    int lastIndex= SimpleClient.getParams().size()-1;
    StudentWillDoEx StEx = (StudentWillDoEx) SimpleClient.getParams().get(lastIndex);
    List<CourseStudent> list =StEx.getStudent().getCourses();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
//   List<Exam> st=StEx.getTheLastStJoined().getStudentExams();
    List<Exam> st=new ArrayList<>();
    LocalDate currentDate = LocalDate.now();
    String formattedDate="";
    LocalDate formattedLocalDate;
    @FXML
    private TextField code1;

    @FXML
    private TextField code2;

    @FXML
    private TextField code3;

    @FXML
    private TextField code4;
    @FXML
    private Button next;
    private String codee;

    @FXML
    void initialize() {
        //System.out.println(""+StEx.getTheLastStJoined().getStudentExams().size());
        st.add(StEx.getExamWillBeDone());
       // System.out.println(""+"size"+st.size()+st.get(0).getSubject()+st.get(0).getDate());
//        try {
//            // Parse the date string into a LocalDate object
//            LocalDate date = LocalDate.parse(datee, formatter);
//
//            // You now have a LocalDate object representing the date
//            System.out.println("Parsed date: " + date);
//
//            // You can also format it back to a string if needed
//            formattedDate = date.format(formatter);
//            System.out.println("Formatted date: " + formattedDate);
//            formattedLocalDate = LocalDate.parse(formattedDate, formatter);
//            System.out.println("I"+formattedLocalDate);
//            System.out.println("I"+currentDate);
//
//        } catch (java.time.format.DateTimeParseException e) {
//            System.err.println("Error parsing date: " + e.getMessage());
//        }

        LogIn2.setDisable(true);
        logIn1.setDisable(true);
        LogIn3.setDisable(true);
        LogIn4.setDisable(true);
        code1.setDisable(true);
        code4.setDisable(true);
        code2.setDisable(true);
        code3.setDisable(true);

        int j=0;
        int s=st.size();
        while (j<s)
        {
//            if((Sub1.getText().equals(st.get(j).getSubject()))&&((Sub1.getText().equals(list.get(j).getName()))))
//            {
//                date1.setText(st.get(j).getDate());
//            }
//            if((sub2.getText().equals(st.get(j).getSubject()))&&((sub2.getText().equals(list.get(j).getName()))))
//            {
//                date2.setText(st.get(j).getDate());
//            }
//            if((sub3.getText().equals(st.get(j).getSubject()))&&((sub3.getText().equals(list.get(j).getName()))))
//            {
//                date3.setText(st.get(j).getDate());
//            }
//            if((sub4.getText().equals(st.get(j).getSubject()))&&((sub4.getText().equals(list.get(j).getName()))))
//            {
//                date4.setText(st.get(j).getDate());
//            } // ----------------> Yaman And Lana turned it off
            j++;
        }

        int i=0;
            while((i<list.size())&&(i<st.size()))
            {
               // String datee=st.get(i).getDate();
                //LocalDate date = LocalDate.parse(datee, formatter);
               // formattedDate = date.format(formatter);
                formattedLocalDate = LocalDate.parse(formattedDate, formatter);

                if((Sub1.getText().equals(list.get(i).getName()))&&(currentDate.isEqual(formattedLocalDate)))
                {
                    logIn1.setDisable(false);
                }
                if((sub2.getText().equals(list.get(i).getName()))&&(currentDate.isEqual(formattedLocalDate)))
                {
                    LogIn2.setDisable(false);
                }
                if(sub3.getText().equals(list.get(i).getName())&&(currentDate.isEqual(formattedLocalDate)))
                {
                    LogIn3.setDisable(false);
                }
                if(sub4.getText().equals(list.get(i).getName())&&(currentDate.isEqual(formattedLocalDate)))
                {
                    LogIn4.setDisable(false);
                }
               i++;
      }
    }

    @FXML
    void LogIn2Act(ActionEvent event) {
        code2.setDisable(false);
    }

    @FXML
    void logIn1Act(ActionEvent event) {
        code1.setDisable(false);
    }

    @FXML
    void LogIn3Act(ActionEvent event) {
        code3.setDisable(false);
    }

    @FXML
    void LogIn4Act(ActionEvent event) {
        code4.setDisable(false);
    }
    @FXML
    void code1Act(ActionEvent event) {
    codee=code1.getText();
    }

    @FXML
    void code2Act(ActionEvent event) {
        codee=code2.getText();
    }

    @FXML
    void code3Act(ActionEvent event) {
        codee=code3.getText();
    }

    @FXML
    void code4Act(ActionEvent event) {
        codee=code4.getText();
    }
    @FXML
    void nextAct(ActionEvent event) throws IOException {
        LinkedList<Object> message = new LinkedList<Object>();
        message.add("#GoToExStudentBUTTON");
//        if("manual".equals(StEx.getExamWillBeDone().getType())) {
//            message.add(codee);
//            message.add(StEx.getExamWillBeDone().getType());
//            message.add(String.valueOf(StEx.getStudent().getId()));
//            System.out.println("" + StEx.getExamWillBeDone().getTimerr()+StEx.getExamWillBeDone().getType());
//            SimpleClient.getClient().sendToServer(message);
//        }// -----------> Yaman And Lana turned it off
    }
}
