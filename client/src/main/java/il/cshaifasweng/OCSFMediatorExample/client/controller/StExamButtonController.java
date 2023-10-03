package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.CourseStudent;
import il.cshaifasweng.OCSFMediatorExample.entities.ExamStudent;
import il.cshaifasweng.OCSFMediatorExample.entities.Student;
import il.cshaifasweng.OCSFMediatorExample.entities.StudentWillDoEx;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;

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
    List<ExamStudent> st=StEx.getStudent().getStudentExams();
    LocalDate currentDate = LocalDate.now();
    String formattedDate="";
    LocalDate formattedLocalDate;
    Student student = StEx.getStudent();
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
    private String codee="";

    @FXML
    void initialize() {
//                    ExamStudent mm=new ExamStudent();
//					mm.setDate("03.10.2023");
//					mm.setNumOfQuestions(12);
//					mm.setTeacherNotes("");
//					mm.setTimerr(130);
//					mm.setStudentNotes("");
//					mm.setSubject("English");
//					mm.setTeacher("mona");
//					mm.setCodeGivenByTeacher("esra");
//        Question Q1=new Question("what is computer Q2","food","drink","game","none","none");
//        mm.getQuestions().add(Q1);
//					st.add(mm);
//                    System.out.println(";;"+st.size()+"ll"+st.get(0).getDate());
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
            if((Sub1.getText().equals(st.get(j).getSubject()))&&((Sub1.getText().equals(list.get(j).getName()))))
            {
                date1.setText(st.get(j).getDate());
            }
            if((sub2.getText().equals(st.get(j).getSubject()))&&((sub2.getText().equals(list.get(j).getName()))))
            {
                date2.setText(st.get(j).getDate());
            }
            if((sub3.getText().equals(st.get(j).getSubject()))&&((sub3.getText().equals(list.get(j).getName()))))
            {
                date3.setText(st.get(j).getDate());
            }
            if((sub4.getText().equals(st.get(j).getSubject()))&&((sub4.getText().equals(list.get(j).getName()))))
            {
                date4.setText(st.get(j).getDate());
            }
            j++;
        }

        int i=0;
            while((i<list.size())&&(i<st.size()))
            {
                String datee=st.get(i).getDate();
                LocalDate date = LocalDate.parse(datee, formatter);
                formattedDate = date.format(formatter);
                formattedLocalDate = LocalDate.parse(formattedDate, formatter);
                System.out.println(""+formattedLocalDate);//
                System.out.println(""+currentDate);//
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
    }

    @FXML
    void code2Act(ActionEvent event) {
    }

    @FXML
    void code3Act(ActionEvent event) {

    }

    @FXML
    void code4Act(ActionEvent event) {

    }
    @FXML
    void nextAct(ActionEvent event) throws IOException {
        if (!code1.getText().equals(""))
        {codee=code1.getText();
        System.out.println(""+code1.getText()+"ll"+codee);}
        if (!code3.getText().equals(""))
        {codee=code3.getText();}
        if (!code2.getText().equals(""))
        {codee=code2.getText();}
        if (!code4.getText().equals(""))
        {codee=code4.getText();}
        if(codee!="")
        {LinkedList<Object> message = new LinkedList<Object>();
            System.out.println(""+StEx.getStudent().getStudentExams().size());
        message.add("#GoToExStudentBUTTON");
        System.out.println("22"+codee);
        message.add(codee);
        message.add(String.valueOf(StEx.getStudent().getId()));
          //  message.add(StEx);
        SimpleClient.getClient().sendToServer(message);}
        }
    @FXML
    void back (ActionEvent event) {
        try{
            SimpleClient.getParams().add(student);
            setRoot("PrimaryStudent");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void LogOut(ActionEvent event) throws IOException {
        LinkedList<Object> message = new LinkedList<Object>();
        message.add("#LogOut");
        message.add(student.getId());
        SimpleClient.getClient().sendToServer(message);
    }


}
