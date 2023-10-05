package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;

public class StExamButtonController implements Serializable {

//    @FXML
//    private Button LogIn2;
//
//    @FXML
//    private Text Sub1;
//
//    @FXML
//    private Text date1;
//
//    @FXML
//    private Text date2;
//
//    @FXML
//    private Button logIn1;
//
//    @FXML
//    private Text sub2;

//    @FXML
//    private Button LogIn3;
//
//    @FXML
//    private Button LogIn4;
//    @FXML
//    private Text date3;
//
//    @FXML
//    private Text date4;
//    @FXML
//    private Text sub3;
//
//    @FXML
//    private Text sub4;

    int lastIndex= SimpleClient.getParams().size()-1;
    StudentWillDoEx StEx = (StudentWillDoEx) SimpleClient.getParams().get(lastIndex);
    List<CourseStudent> list =StEx.getStudent().getCourses();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    List<ExamStudent> st=StEx.getStudent().getStudentExams();
    LocalDate currentDate = LocalDate.now();
    String formattedDate="";
    LocalDate formattedLocalDate;
    Student student = StEx.getStudent();
//    @FXML
//    private TextField code1;
//
//    @FXML
//    private TextField code2;
//
//    @FXML
//    private TextField code3;
//
//    @FXML
//    private TextField code4;
    @FXML
    private Button next;
    private String codee="";
    @FXML
    private AnchorPane anchor;
    final TextField[] cod = {null};
    @FXML
    void initialize() {
//                  ExamStudent mm=new ExamStudent();
//					mm.setDate("05.10.2023");
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
//        LogIn2.setDisable(true);
//        logIn1.setDisable(true);
//        LogIn3.setDisable(true);
//        LogIn4.setDisable(true);
//        code1.setDisable(true);
//        code4.setDisable(true);
//        code2.setDisable(true);
//        code3.setDisable(true);

        double layoutX = 35.0;
        double layoutY=90;
        double layoutXD = 181.0;
        double layoutYD=90;
        double layoutXB = 323.0;
        double layoutYB=73;
        double layoutXC = 444.0;
        final double[] layoutYC = {73};
        for(CourseStudent cor : list){
            Text sub = new Text(cor.getName());
            sub.setLayoutX(layoutX);
            sub.setLayoutY(layoutY);
            anchor.getChildren().add(sub);
            layoutY = layoutY + 64;

            int j=0;
            int s=st.size();
            ExamStudent ex=null;
            int Flag=1;
            while ((j<s)&&(Flag==1)){
                if(st.get(j).getSubject().equals(cor.getName()))
                {
                    Text datet = new Text(st.get(j).getDate());
                    datet.setLayoutX(layoutXD);
                    datet.setLayoutY(layoutYD);
                    anchor.getChildren().add(datet);
                    layoutYD = layoutYD + 63;
                    Flag=0;

                    String datee=st.get(j).getDate();
                    LocalDate date = LocalDate.parse(datee, formatter);
                    formattedDate = date.format(formatter);
                    formattedLocalDate = LocalDate.parse(formattedDate, formatter);
                    if(currentDate.isEqual(formattedLocalDate))
                    {
                        Button bb=new Button("Log In");
                        bb.setLayoutX(layoutXB);
                        bb.setLayoutY(layoutYB);
//                        anchor.getChildren().add(bb);
                        layoutYB = layoutYB + 63;

                        bb.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                cod[0] =new TextField("");
                                cod[0].setLayoutX(layoutXC);
                                cod[0].setLayoutY(layoutYC[0]);
                                anchor.getChildren().add(cod[0]);
                                layoutYC[0] = layoutYC[0] + 62;

                                System.out.println(""+codee);
                            }
                        });
//                        TextField cod=new TextField("Enter The Code");
//                        cod.setLayoutX(layoutXC);
//                        cod.setLayoutY(layoutYC);
                        anchor.getChildren().add(bb);
                      //  layoutYC = layoutYC + 62;
                    }
                }
                else{
                    j++;
                }
            }

        }
//        int j=0;
//        int s=st.size();
//        while (j<s)
//        {
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
//            }
//            j++;
//        }

//        int i=0;
//            while((i<list.size())&&(i<st.size()))
//            {
//                String datee=st.get(i).getDate();
//                LocalDate date = LocalDate.parse(datee, formatter);
//                formattedDate = date.format(formatter);
//                formattedLocalDate = LocalDate.parse(formattedDate, formatter);
//                System.out.println(""+formattedLocalDate);//
//                System.out.println(""+currentDate);//
//                if((Sub1.getText().equals(list.get(i).getName()))&&(currentDate.isEqual(formattedLocalDate)))
//                {
//                    logIn1.setDisable(false);
//                }
//                if((sub2.getText().equals(list.get(i).getName()))&&(currentDate.isEqual(formattedLocalDate)))
//                {
//                    LogIn2.setDisable(false);
//                }
//                if(sub3.getText().equals(list.get(i).getName())&&(currentDate.isEqual(formattedLocalDate)))
//                {
//                    LogIn3.setDisable(false);
//                }
//                if(sub4.getText().equals(list.get(i).getName())&&(currentDate.isEqual(formattedLocalDate)))
//                {
//                    LogIn4.setDisable(false);
//                }
//               i++;
//      }
    }

//    @FXML
//    void LogIn2Act(ActionEvent event) {
//        code2.setDisable(false);
//    }
//
//    @FXML
//    void logIn1Act(ActionEvent event) {
//        code1.setDisable(false);
//    }
//
//    @FXML
//    void LogIn3Act(ActionEvent event) {
//        code3.setDisable(false);
//    }
//
//    @FXML
//    void LogIn4Act(ActionEvent event) {
//        code4.setDisable(false);
//    }
//    @FXML
//    void code1Act(ActionEvent event) {
//    }
//
//    @FXML
//    void code2Act(ActionEvent event) {
//    }
//
//    @FXML
//    void code3Act(ActionEvent event) {
//
//    }
//
//    @FXML
//    void code4Act(ActionEvent event) {
//
//    }
    @FXML
    void nextAct(ActionEvent event) throws IOException {
//        if (!code1.getText().equals(""))
//        {codee=code1.getText();
//        System.out.println(""+code1.getText()+"ll"+codee);}
//        if (!code3.getText().equals(""))
//        {codee=code3.getText();}
//        if (!code2.getText().equals(""))
//        {codee=code2.getText();}
//        if (!code4.getText().equals(""))
//        {codee=code4.getText();}
        codee= cod[0].getText();
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
        message.add("student");
        SimpleClient.getClient().sendToServer(message);
    }


}
