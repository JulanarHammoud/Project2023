package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
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
   // List<ExamStudent> st=StEx.getStudent().getStudentExams();
    ExamStudent exam;
    LocalDate currentDate = LocalDate.now();
    String formattedDate="";
    LocalDate formattedLocalDate;
    Student student = StEx.getStudent();
    LocalTime currentTime = LocalTime.now();

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
    //final TextField[] cod = {null};
    double layoutYC = 73.0;
    double layoutYB=73;
    double layoutYT = 90;
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
        double layoutXT = 100.0;
        double layoutX = 35.0;
        double layoutY=90;
        double layoutXD = 181.0;
        double layoutYD=90;
        double layoutXB = 323.0;

        double layoutXC = 444.0;

        for(CourseStudent cor : list) {
            Text sub = new Text(cor.getName());
            sub.setLayoutX(layoutX);
            sub.setLayoutY(layoutY);
            anchor.getChildren().add(sub);
            layoutY = layoutY + 64;

            int j = 0;
            // int s=st.size();

            int Flag = 1;
//            while ((j<s)&&(Flag==1)){
//                if(st.get(j).getSubject().equals(cor.getName()))
//                {
            if (cor.getLastExam() != null) {
                ExamStudent ex = cor.getLastExam();

                int f = 0;
                for (ExamStudent exx : StEx.getStudent().getStudentExams()) {
                    if (exx.getExamTId() == ex.getExamTId()) {
                        ex = exx;
                        break;
                    }
                }

                if (ex != null) {
                    Text datet = new Text(ex.getDate());
                    datet.setLayoutX(layoutXD);
                    datet.setLayoutY(layoutYD);
                    anchor.getChildren().add(datet);
                    layoutYD = layoutYD + 63;
                    Flag = 0;

                    String tim = ex.getTime();          ////////////////////
//                String tim="02:15";
                    Text Timee = new Text(tim);
                    Timee.setLayoutX(layoutXT);
                    Timee.setLayoutY(layoutYT);
                    anchor.getChildren().add(Timee);
                    layoutYT = layoutYT + 63;


                    String datee = ex.getDate();

                    System.out.println("l" + tim + currentTime);
                    if (tim != null) {
                        LocalTime specifiedTime = LocalTime.parse(tim, DateTimeFormatter.ofPattern("HH:mm"));

                        DateTimeFormatter formatter33 = DateTimeFormatter.ofPattern("HH:mm");
                        String formattedTime22 = currentTime.format(formatter33);
                        LocalTime specifiedTime22 = LocalTime.parse(formattedTime22, formatter33); // Uncomment this line
                        System.out.println("OOOOO" + specifiedTime22);



                        DateTimeFormatter formatter55 = DateTimeFormatter.ofPattern("HH:mm");

                        // Parse the time string into a LocalTime object
                        LocalTime localTime55 = LocalTime.parse(tim, formatter55);
                         System.out.println("l" + specifiedTime);
                        int comparisonResult = specifiedTime22.compareTo(localTime55);


                        LocalTime futureTime = specifiedTime.plusMinutes(ex.getTimerr());
                        System.out.println("futuretime"+futureTime.toString());
                        int comparisonResult2 = specifiedTime22.compareTo(futureTime);
                        System.out.println("RRRRRRRRRRRRR"+specifiedTime22+"''''''''''''"+futureTime);
                        System.out.println("comparosonresult"+comparisonResult2);




                        LocalDate date = LocalDate.parse(datee, formatter);
                        formattedDate = date.format(formatter);
                        formattedLocalDate = LocalDate.parse(formattedDate, formatter);
                        System.out.println("lllll" + futureTime + "''" + ex.getTime() + "''''" + ex.getTimerr());


                        int comparisonResult4 = specifiedTime22.compareTo(localTime55);

                        System.out.println("1."+comparisonResult+"  "+specifiedTime22.toString()+"  "+localTime55.toString());
                        System.out.println("2."+comparisonResult2+"  "+specifiedTime22.toString()+"  "+futureTime.toString());

                        if ((comparisonResult==0)||((comparisonResult2<0)&&(comparisonResult4>0))) {
                            Button bb = new Button("Log In");
                            bb.setLayoutX(layoutXB);
                            bb.setLayoutY(layoutYB);
//                        anchor.getChildren().add(bb);

                            ExamStudent finalEx = ex;
                            bb.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    exam = finalEx;
                                    TextField code = new TextField("");
                                    code.setLayoutX(layoutXC);
                                    code.setLayoutY(bb.getLayoutY());
                                    code.textProperty().addListener(new ChangeListener<String>() {
                                        @Override
                                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                                            codee = newValue;
                                        }
                                    });
                                    anchor.getChildren().add(code);
                                    layoutYC = layoutYC + 62;
                                    System.out.println("" + codee);
                                }
                            });
//                        TextField cod=new TextField("Enter The Code");
//                        cod.setLayoutX(layoutXC);
//                        cod.setLayoutY(layoutYC);
                            anchor.getChildren().add(bb);
                            //  layoutYC = layoutYC + 62;
                        }
                    }
                }
//                }
//                else{
//                    j++;
//                }
//            }
                layoutYB = layoutYB + 63;

            }
        }
    }


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
        LinkedList<Object> message = new LinkedList<Object>();
        System.out.println("exam code is: " + exam.getCode());
        System.out.println("the student wrote: " + codee);
        if(!codee.equals(exam.getCode())){ // the student wrote wrong code
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText(null);
            alert.setContentText("Wrong Code!!, please try again.");
            alert.showAndWait();
        }
        else {
            System.out.println("QQ"+exam.isExecuted());
            if(exam.isExecuted()==true)
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("WARNING");
                alert.setHeaderText(null);
                alert.setContentText("You Did it!");
                alert.showAndWait();
            }
            else if ((exam.isComputed())) {
                /// computed exam

                message.add("#StartComputedExam");
                message.add(exam);
                SimpleClient.getClient().sendToServer(message);
                StartCompExam startExam = new StartCompExam(exam,student);
                SimpleClient.getParams().add(startExam);
                setRoot("computedExam");


            } else { /// manual exam

                if (codee != "") {
                    // System.out.println(""+StEx.getStudent().getStudentExams().size());
                    message.add("#GoToExStudentBUTTON");
                    System.out.println("22" + codee);
                    message.add(codee);
                    message.add(String.valueOf(student.getId()));
                    message.add(exam);
                    SimpleClient.getClient().sendToServer(message);
                }

            }
        }
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
