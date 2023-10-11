package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;

public class GradesButtonController {

//    @FXML
//    private Pane Arabic;

//    @FXML
//    private Button Button1;
//
//    @FXML
//    private Button Button2;

//    @FXML
//    private Button Button3;
//
//    @FXML
//    private Button Button4;

//    @FXML
//    private Pane Eng;

//    @FXML
//    private Pane Hebrow;

//    @FXML
//    private Pane Math;

//    @FXML
//    private Text Sub1;
//
//    @FXML
//    private Text Sub2;

//    @FXML
//    private Text Sub3;
//
//    @FXML
//    private Text Sub4;
//    @FXML
//    private Text EngFinalGrade1;
//
//    @FXML
//    private Text EngFirstGrade;
//
//    @FXML
//    private Text EngsecondGrade;

//    @FXML
//    private Text MathFinalGrade;
//
//    @FXML
//    private Text MathFirstGrade;
//
//    @FXML
//    private Text MathsecondGrade1;
//    @FXML
//    private Button paperEngFinal;
//
//    @FXML
//    private Button paperEngFirst;
//
//    @FXML
//    private Button paperEngSecond1;

//    @FXML
//    private Button paperMathFinal;
//
//    @FXML
//    private Button paperMathFirst;
//
//    @FXML
//    private Button paperMathSecond;
//    @FXML
//    private Text ArabicFinalGrade;
//
//    @FXML
//    private Text ArabicFirstGrade;
//
//    @FXML
//    private Text ArabicsecondGrade;
//    @FXML
//    private Button paperArabicFinal;
//
//    @FXML
//    private Button paperArabicFirst;
//
//    @FXML
//    private Button paperArabicSecond;
//    @FXML
//    private Text HebrowFinalGrade;
//
//    @FXML
//    private Text HebrowFirstGrade;
//
//    @FXML
//    private Text HebrowsecondGrade;
//    @FXML
//    private Button paperHebrowFinal;
//
//    @FXML
//    private Button paperHebrowFirst;
//
//    @FXML
//    private Button paperHebrowSecond;

    int lastIndex= SimpleClient.getParams().size()-1;
    GradeSt StEx = (GradeSt) SimpleClient.getParams().get(lastIndex);
    ExamStudent Firstt=null;
    ExamStudent Secondd=null;
    ExamStudent Finall=null;
    Student student = StEx.getSs();
    @FXML
    private AnchorPane anchor;
    public void initialize(){
//        System.out.println("llL"+StEx.getSs().getStudentExams().size());
//        System.out.println(""+StEx.getSs().getStudentExams().get(0).getCode());
//        System.out.println(""+StEx.getSs().getStudentExams().get(0).getGrade());
        //   ExamStudent mm=new ExamStudent();
//					mm.setDate("03.10.2023");
//					mm.setNumOfQuestions(12);
//					mm.setTeacherNotes("");
//					mm.setTimerr(130);
//					mm.setStudentNotes("");
//					mm.setSubject("English");
//					mm.setTeacher("mona");
////					mm.setType("manual");
//					mm.setCodeGivenByTeacher("esra");
//                    Question Q=new Question("what is computer","food","drink","game","none","none");
//                    Q.setThe_student_ans("food");
//                    mm.getQuestions().add(Q);
//        Question Q1=new Question("what is computer Q2","food","drink","game","none","none");
//        Q1.setThe_student_ans("food");
//        mm.getQuestions().add(Q1);
//        StEx.getSs().getStudentExams().add(mm);
//        StEx.getSs().getStudentExams().get(0).setGrade(40);
//        System.out.println("llll"+StEx.getSs().getStudentExams().get(0).getGrade());

//        ExamStudent lm=new ExamStudent();
//        lm.setDate("02.10.2023");
//        lm.setNumOfQuestions(12);
//        lm.setTeacherNotes("");
//        lm.setTimerr(130);
//        lm.setStudentNotes("");
//        lm.setSubject("English");
//        lm.setTeacher("mona");
////        lm.setType("manual");
//        lm.setCodeGivenByTeacher("esra");
//        StEx.getSs().getStudentExams().add(lm);
//        StEx.getSs().getStudentExams().get(1).setGrade(90);
//        System.out.println("llll"+StEx.getSs().getStudentExams().get(1).getGrade());

//        ExamStudent km=new ExamStudent();
//        km.setDate("02.10.2023");
//        km.setNumOfQuestions(12);
//        km.setTeacherNotes("");
//        km.setTimerr(130);
//        km.setStudentNotes("");
//        km.setSubject("English");
//        km.setTeacher("mona");
//        km.setType("manual");
//        km.setCodeGivenByTeacher("esra");
//        StEx.getSs().getStudentExams().add(km);
//        StEx.getSs().getStudentExams().get(2).setGrade(100);
//        System.out.println("llll"+StEx.getSs().getStudentExams().get(2).getGrade()+StEx.getSs().getStudentExams().size());

//        Math.setVisible(false);
//        Arabic.setVisible(false);
//       Hebrow.setVisible(false);
//        Eng.setVisible(false);
//
//        paperEngFinal.setDisable(true);
//        paperEngFirst.setDisable(true);
//        paperEngSecond1.setDisable(true);

//        paperArabicFinal.setDisable(true);
//        paperArabicFirst.setDisable(true);
//        paperArabicSecond.setDisable(true);
//
//        paperHebrowFinal.setDisable(true);
//        paperHebrowFirst.setDisable(true);
//        paperHebrowSecond.setDisable(true);

//        paperMathFinal.setDisable(true);
//        paperMathSecond.setDisable(true);
//        paperMathFirst.setDisable(true);
        LinkedList<Pane> panes=new LinkedList<>();
//        List<ExamStudent> t=StEx.getSs().getStudentExams();
     //   System.out.println(";;;"+t.size()+t.get(0).getSubject());

        double layoutX = 30.0;
        double layoutY=111;
       // if(StEx.getSs().getCourses()!=null){
        for(CourseStudent cor : StEx.getSs().getCourses()){
            String color=getRandomColor();
            Text sub = new Text(cor.getName());
            sub.setWrappingWidth(128.13671875);
            sub.setStrokeWidth(0.0);
            sub.setLayoutX(layoutX);
            sub.setLayoutY(layoutY);
            anchor.getChildren().add(sub);
            Pane k=MakePane(layoutX+129,layoutY-38,310,110,color,cor);
            panes.add(k);
            Button btn = new Button("+") ;
            btn.setFont(Font.font(27.0));
            btn.setLayoutX(layoutX+48);
            btn.setLayoutY(layoutY-33);
            btn.setStyle("-fx-background-color: #ffffff;");
            btn.setTextFill(Paint.valueOf(color));
            btn.setMnemonicParsing(false);
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
//                    Arabic.setVisible(false);
//                    Hebrow.setVisible(false);
//                    Eng.setVisible(false);
                    for(Pane x: panes)
                    {
                        if(x!=k)
                        {
                            x.setVisible(false);
                        }
                    }

//
//                    Firstt=null;
//                    Secondd=null;
//                    Finall=null;
//                    int i = 0;
//                    int j = 0;
//                    int k1 = 0;
//                    int k2 = 0;
//                    int k3 = 0;
//       while((i <t.size())&&((k1 ==0)||(k2 ==0)||(k3 ==0)))
//        {
//            if(cor.getName().equals(t.get(i).getSubject()))
//            {
//                if(j ==0)
//                {System.out.println("llll"+ i + j +t.size()+cor.getName()+t.get(i).getSubject());
//                    Firstt=t.get(i);
//                    j++;
//                    i++;
//                    k1 =1;
//                }
//                else if(j ==1)
//                {System.out.println("llll"+ i + j +t.size());
//                    Secondd=t.get(i);
//                    j++;
//                    i++;
//                    k2 =1;
//                }
//                else if(j ==2)
//                {System.out.println("llll"+ i + j +t.size());
//                    Finall=t.get(i);
//                    j++;
//                    i++;
//                    k3 =1;
//                }
//            }
//            else{
//                System.out.println("llll"+ i + j +t.size());
//                i++;
//            }
//        }
//       if(Firstt!=null)
//        {System.out.println("gggg");
//            String s= String.valueOf(Firstt.getGrade());
//
//            Text FirstGrade = (Text) k.getChildren().get(7); // Assuming it's the 8th child in the Pane's children list
//            FirstGrade.setText(s);
//            Button first = (Button) k.getChildren().get(4);
//            first.setDisable(false);
//            first.setOnAction(new EventHandler<ActionEvent>() {
//                        @Override
//                        public void handle(ActionEvent event) {
//                            try
//                            {LinkedList<Object> message = new LinkedList<Object>();
//                                System.out.println(""+Firstt.getGrade());
//                                message.add("#GoToExStudentAnswers");
//                                message.add(Firstt);
//                                SimpleClient.getClient().sendToServer(message);
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//            });
//        }
//        if(Secondd!=null)
//        {
//            String s= String.valueOf(Secondd.getGrade());
//            Text secondGrade1 = (Text) k.getChildren().get(8); // Assuming it's the 8th child in the Pane's children list
//            secondGrade1.setText(s);
//            Button paperSecond = (Button) k.getChildren().get(5);
//            paperSecond.setDisable(false);
//            paperSecond.setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent event) {
//                    try
//                    {LinkedList<Object> message = new LinkedList<Object>();
//                        System.out.println(""+Secondd.getGrade());
//                        message.add("#GoToExStudentAnswers");
//                        message.add(Secondd);
//                        SimpleClient.getClient().sendToServer(message);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
//        if(Finall!=null)
//        {
//            String s= String.valueOf(Finall.getGrade());
//            Text FinalGrade = (Text) k.getChildren().get(9); // Assuming it's the 8th child in the Pane's children list
//            FinalGrade.setText(s);
//            Button paperFinal = (Button) k.getChildren().get(6);
//            paperFinal.setDisable(false);
//            paperFinal.setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent event) {
//                    try
//                    {LinkedList<Object> message = new LinkedList<Object>();
//                        System.out.println(""+Finall.getGrade());
//                        message.add("#GoToExStudentAnswers");
//                        message.add(Finall);
//                        SimpleClient.getClient().sendToServer(message);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
                    k.setVisible(!k.isVisible());

                    if(btn.getText().equals("+"))
                    {
                        btn.setText("-");
                    }
                    else if(btn.getText().equals("-"))
                    {
                        btn.setText("+");
                    }
                }
            });
            anchor.getChildren().addAll(k,btn);
            layoutY = layoutY + 85;
        }
       // }
    }

    private String getRandomColor() {
        int red = 128 + (int) (Math.random() * 64); // R: 128-192
         int green = 128 + (int) (Math.random() * 64); // G: 128-192
        int blue = 128 + (int) (Math.random() * 64); // B: 128-192
         return String.format("#%02X%02X%02X", red, green, blue);
    }

    //<Pane fx:id="Math" layoutX="159.0" layoutY="73.0" prefHeight="110.0" prefWidth="310.0" style="-fx-background-color: #95f8a3;">
         <children>
           // <Text layoutX="112.0" layoutY="18.0" strokeType="OUTSIDE" strokeWidth="0.0" text="Grade" wrappingWidth="55.0" />
           // <Text layoutX="14.0" layoutY="37.0" strokeType="OUTSIDE" strokeWidth="0.0" text="First exam" wrappingWidth="55.0" />
           // <Text layoutX="14.0" layoutY="65.0" strokeType="OUTSIDE" strokeWidth="0.0" text="second exam" wrappingWidth="71.0" />
           // <Text layoutX="14.0" layoutY="95.0" strokeType="OUTSIDE" strokeWidth="0.0" text="Final exam" wrappingWidth="71.0" />
            //<Button fx:id="paperMathFirst" layoutX="205.0" layoutY="20.0" mnemonicParsing="false" onAction="#paperMathFirstAct" prefHeight="25.0" prefWidth="91.0" text="Test paper" />
            //<Button fx:id="paperMathSecond" layoutX="205.0" layoutY="48.0" mnemonicParsing="false" onAction="#paperMathSecondAct" prefHeight="25.0" prefWidth="91.0" text="Test paper" />
            //<Button fx:id="paperMathFinal" layoutX="205.0" layoutY="78.0" mnemonicParsing="false" onAction="#paperMathFinalAct" prefHeight="25.0" prefWidth="91.0" text="Test paper" />
          //  <Text fx:id="MathFirstGrade" layoutX="106.0" layoutY="37.0" strokeType="OUTSIDE" strokeWidth="0.0" wrappingWidth="55.0" />
        //    <Text fx:id="MathsecondGrade1" layoutX="112.0" layoutY="65.0" strokeType="OUTSIDE" strokeWidth="0.0" wrappingWidth="55.0" />
          //  <Text fx:id="MathFinalGrade" layoutX="112.0" layoutY="95.0" strokeType="OUTSIDE" strokeWidth="0.0" wrappingWidth="55.0" />
     //    </children></Pane>

    Pane MakePane(double lX,double lY,double W,double H,String color,CourseStudent cor)
    {
       Pane cc =new Pane();
       cc.setLayoutX(lX);
       cc.setLayoutY(lY);
       cc.setPrefWidth(W);
       cc.setPrefHeight(H);
       cc.setStyle("-fx-background-color: "+color+";");

       Text G=new Text();
       G.setLayoutX(112.0);
       G.setLayoutY(18);
       G.setText("Grade");

        Text FirstEx=new Text();
        FirstEx.setLayoutX(14);
        FirstEx.setLayoutY(37);
        FirstEx.setText("First Exam");

        Text SecEx=new Text();
        SecEx.setLayoutX(14);
        SecEx.setLayoutY(65);
        SecEx.setText("Second Exam");

        Text FinalEx=new Text();
        FinalEx.setLayoutX(14);
        FinalEx.setLayoutY(95);
        FinalEx.setText("Final Exam");

        Button first= new Button();
        first.setText("Exam paper");
        first.setLayoutX(205);
        first.setLayoutY(20);
        first.setDisable(true);

        Button paperSecond= new Button();
        paperSecond.setText("Exam paper");
        paperSecond.setLayoutX(205);
        paperSecond.setLayoutY(48);
        paperSecond.setDisable(true);

        Button paperFinal= new Button();
        paperFinal.setText("Exam paper");
        paperFinal.setLayoutX(205);
        paperFinal.setLayoutY(78);
        paperFinal.setDisable(true);

        Text FirstGrade=new Text();
        FirstGrade.setLayoutX(106);
        FirstGrade.setLayoutY(37);
//        FirstGrade.setText("MathFirstGrade");

        Text secondGrade1=new Text();
        secondGrade1.setLayoutX(112);
        secondGrade1.setLayoutY(65);
//        secondGrade1.setText("MathsecondGrade1");

        Text FinalGrade=new Text();
        FinalGrade.setLayoutX(112);
        FinalGrade.setLayoutY(95);
//        FinalGrade.setText("MathFinalGrade");
        List<ExamStudent> t=StEx.getSs().getStudentExams();
//        System.out.println("t.size="+t.size()+t.get(0).getSubject()+cor.getName());
        int i = t.size()-1;
        int j = 0;
        int k1 = 0;
        int k2 = 0;
        int k3 = 0;
//        int m=0;
        Firstt=null;
        Secondd=null;
        Finall=null;
        if(t !=null) {
            while ((i >=0) && ((k1 == 0) || (k2 == 0) || (k3 == 0))) {
//            m=0;
                System.out.println("ok" + t.get(i).getCourse() + cor.getName());
//            while(m<cor.getSubjectstudent().size())
//            {

                if ((t.get(i).getCourse()).equals(cor.getName())) {
                    if (j == 0) {
                        System.out.println("llllpp" + i + j + t.size() + cor.getName() + t.get(i).getSubject());
                        Firstt = t.get(i);
                        System.out.println("//m"+Firstt.getGrade()+Firstt.getCode());
                        j++;
                        i--;
                        k1 = 1;
                    } else if (j == 1) {
                        System.out.println("llll1" + i + j + t.size());
                        Secondd = t.get(i);
                        j++;
                        i--;
                        k2 = 1;
                    } else if (j == 2) {
                        System.out.println("llll2" + i + j + t.size());
                        Finall = t.get(i);
                        j++;
                        i--;
                        k3 = 1;
                    }
                }
//                m++;
//            }
                else {
                    System.out.println("llll3" + i + j + t.size());
                    i--;
                }
            }
        }
        if(Firstt!=null)
        {System.out.println("gggg");
            String s= String.valueOf(Firstt.getGrade());
            System.out.println("llL"+Firstt.getGrade());
            //FirstGrade = (Text) k.getChildren().get(7); // Assuming it's the 8th child in the Pane's children list
            FirstGrade.setText(s);
            //Button first = (Button) k.getChildren().get(4);
            first.setDisable(false);
            ExamStudent dd=Firstt;
            first.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try
                    {LinkedList<Object> message = new LinkedList<Object>();
                        System.out.println(""+dd.getGrade());
                        message.add("#GoToExStudentAnswers");
                        message.add(dd);
                        SimpleClient.getClient().sendToServer(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        if(Secondd!=null)
        {System.out.println("gggg2");
            String s= String.valueOf(Secondd.getGrade());
            System.out.println("llL"+Secondd.getGrade());
           // Text secondGrade1 = (Text) k.getChildren().get(8); // Assuming it's the 8th child in the Pane's children list
            secondGrade1.setText(s);
           // Button paperSecond = (Button) k.getChildren().get(5);
            paperSecond.setDisable(false);
            ExamStudent dd2=Secondd;
            paperSecond.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try
                    {LinkedList<Object> message = new LinkedList<Object>();
                        System.out.println(""+dd2.getGrade());
                        message.add("#GoToExStudentAnswers");
                        message.add(dd2);
                        SimpleClient.getClient().sendToServer(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        if(Finall!=null)
        {System.out.println("gggg3");
            String s= String.valueOf(Finall.getGrade());
            System.out.println("llL"+Finall.getGrade());
           // Text FinalGrade = (Text) k.getChildren().get(9); // Assuming it's the 8th child in the Pane's children list
            FinalGrade.setText(s);
           // Button paperFinal = (Button) k.getChildren().get(6);
            paperFinal.setDisable(false);
            ExamStudent dd3=Finall;
            paperFinal.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try
                    {LinkedList<Object> message = new LinkedList<Object>();
                        System.out.println(""+dd3.getGrade());
                        message.add("#GoToExStudentAnswers");
                        message.add(dd3);
                        SimpleClient.getClient().sendToServer(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        cc.getChildren().addAll(G,FirstEx, SecEx,FinalEx,first,paperSecond,paperFinal,FirstGrade,secondGrade1,FinalGrade);
        cc.setVisible(false);

        return cc;
    }
//    @FXML
//    void Button1Act(ActionEvent event) {
//        Arabic.setVisible(false);
//        Hebrow.setVisible(false);
//        Eng.setVisible(false);
//        Math.setVisible(!Math.isVisible());
//
//        Button2.setText("+");
//        Button3.setText("+");
//        Button4.setText("+");
//        if(Button1.getText().equals("+"))
//        {
//            Button1.setText("-");
//        }
//        else if(Button1.getText().equals("-"))
//        {
//            Button1.setText("+");
//        }
//
//        List<ExamStudent> t=StEx.getSs().getStudentExams();
//
//        int i=0;
//        int j=0;
//        int k1=0;
//        int k2=0;
//        int k3=0;
//
//        while((i<t.size())&&((k1==0)||(k2==0)||(k3==0)))
//        {
//            if("Math".equals(t.get(i).getSubject()))
//            {
//                if(j==0)
//                {System.out.println("llll"+i+j+t.size());
//                    FirstMath=t.get(i);
//                    j++;
//                    i++;
//                    k1=1;
//                }
//                else if(j==1)
//                {System.out.println("llll"+i+j+t.size());
//                    SecondMath=t.get(i);
//                    j++;
//                    i++;
//                    k2=1;
//                }
//                else if(j==2)
//                {System.out.println("llll"+i+j+t.size());
//                    FinalMath=t.get(i);
//                    j++;
//                    i++;
//                    k3=1;
//                }
//            }
//            else{
//                System.out.println("llll"+i+j+t.size());
//                i++;
//            }
     //   }

//        if(FirstMath!=null)
//        {
//            String s= String.valueOf(FirstMath.getGrade());
//            MathFirstGrade.setText(s);
//            paperMathFirst.setDisable(false);
//        }
//        if(SecondMath!=null)
//        {
//            String s= String.valueOf(SecondMath.getGrade());
//            MathsecondGrade1.setText(s);
//            paperMathSecond.setDisable(false);
//        }
//        if(FinalMath!=null)
//        {
//            String s= String.valueOf(FinalMath.getGrade());
//            MathFinalGrade.setText(s);
//            paperMathFinal.setDisable(false);
//        }
//    }

//    @FXML
//    void Button2Act(ActionEvent event) {
//        Arabic.setVisible(false);
//        Hebrow.setVisible(false);
//        Math.setVisible(false);
//        Eng.setVisible(!Eng.isVisible());
//
//        Button1.setText("+");
//        Button3.setText("+");
//        Button4.setText("+");
//        if(Button2.getText().equals("+"))
//        {
//            Button2.setText("-");
//        }
//        else if(Button2.getText().equals("-"))
//        {
//            Button2.setText("+");
//        }
//
//        List<ExamStudent> t=StEx.getSs().getStudentExams();
//
//        int i=0;
//        int j=0;
//        int k1=0;
//        int k2=0;
//        int k3=0;
//
//        while((i<t.size())&&((k1==0)||(k2==0)||(k3==0)))
//        {
//            if("English".equals(t.get(i).getSubject()))
//            {
//                if(j==0)
//                {System.out.println("llll"+i+j+t.size());
//                    FirstEnglish=t.get(i);
//                    j++;
//                    i++;
//                    k1=1;
//                }
//                else if(j==1)
//                {System.out.println("llll"+i+j+t.size());
//                    SecondEnglish=t.get(i);
//                    j++;
//                    i++;
//                    k2=1;
//                }
//                else if(j==2)
//                {System.out.println("llll"+i+j+t.size());
//                    FinalEnglish=t.get(i);
//                    j++;
//                    i++;
//                    k3=1;
//                }
//            }
//            else{
//                System.out.println("llll"+i+j+t.size());
//                i++;
//            }
//        }
//
//        if(FirstEnglish!=null)
//        {
//            String s= String.valueOf(FirstEnglish.getGrade());
//            EngFirstGrade.setText(s);
//            System.out.println("ppp");
//            paperEngFirst.setDisable(false);
//        }
//        if(SecondEnglish!=null)
//        {
//            String s= String.valueOf(SecondEnglish.getGrade());
//            EngsecondGrade.setText(s);
//            paperEngSecond1.setDisable(false);
//        }
//        if(FinalEnglish!=null)
//        {
//            String s= String.valueOf(FinalEnglish.getGrade());
//            EngFinalGrade1.setText(s);
//            paperEngFinal.setDisable(false);
//        }
//
//   }

//    @FXML
//    void Button3Act(ActionEvent event) {
//        Hebrow.setVisible(false);
////        Math.setVisible(false);
////        Eng.setVisible(false);
//        Arabic.setVisible(!Arabic.isVisible());
//
////        Button2.setText("+");
////        Button1.setText("+");
//        Button4.setText("+");
//        if(Button3.getText().equals("+"))
//        {
//            Button3.setText("-");
//        }
//        else if(Button3.getText().equals("-"))
//        {
//            Button3.setText("+");
//        }
//
//        List<ExamStudent> t=StEx.getSs().getStudentExams();
//
//        int i=0;
//        int j=0;
//        int k1=0;
//        int k2=0;
//        int k3=0;
//
//        while((i<t.size())&&((k1==0)||(k2==0)||(k3==0)))
//        {
//            if("Arabic".equals(t.get(i).getSubject()))
//            {
//                if(j==0)
//                {System.out.println("llll"+i+j+t.size());
//                    FirstArabic=t.get(i);
//                    j++;
//                    i++;
//                    k1=1;
//                }
//                else if(j==1)
//                {System.out.println("llll"+i+j+t.size());
//                    SecondArabic=t.get(i);
//                    j++;
//                    i++;
//                    k2=1;
//                }
//                else if(j==2)
//                {System.out.println("llll"+i+j+t.size());
//                    FinalArabic=t.get(i);
//                    j++;
//                    i++;
//                    k3=1;
//                }
//            }
//            else{
//                System.out.println("llll"+i+j+t.size());
//                i++;
//            }
//        }
//
//        if(FirstArabic!=null)
//        {
//            String s= String.valueOf(FirstArabic.getGrade());
//            ArabicFirstGrade.setText(s);
//            paperArabicFirst.setDisable(false);
//        }
//        if(SecondArabic!=null)
//        {
//            String s= String.valueOf(SecondArabic.getGrade());
//            ArabicsecondGrade.setText(s);
//            paperArabicSecond.setDisable(false);
//        }
//        if(FinalArabic!=null)
//        {
//            String s= String.valueOf(FinalArabic.getGrade());
//            ArabicFinalGrade.setText(s);
//            paperArabicFinal.setDisable(false);
//        }
//    }
//
//    @FXML
//    void Button4Act(ActionEvent event) {
////        Math.setVisible(false);
////        Eng.setVisible(false);
//        Arabic.setVisible(false);
//        Hebrow.setVisible(!Hebrow.isVisible());
//
////        Button2.setText("+");
//        Button3.setText("+");
////        Button1.setText("+");
//        if(Button4.getText().equals("+"))
//        {
//            Button4.setText("-");
//        }
//        else if(Button4.getText().equals("-"))
//        {
//            Button4.setText("+");
//        }
//
//        List<ExamStudent> t=StEx.getSs().getStudentExams();
//
//        int i=0;
//        int j=0;
//        int k1=0;
//        int k2=0;
//        int k3=0;
//
//        while((i<t.size())&&((k1==0)||(k2==0)||(k3==0)))
//        {
//            if("Hebrow".equals(t.get(i).getSubject()))
//            {
//                if(j==0)
//                {System.out.println("llll"+i+j+t.size());
//                    FirstHebrow=t.get(i);
//                    j++;
//                    i++;
//                    k1=1;
//                }
//                else if(j==1)
//                {System.out.println("llll"+i+j+t.size());
//                    SecondHebrow=t.get(i);
//                    j++;
//                    i++;
//                    k2=1;
//                }
//                else if(j==2)
//                {System.out.println("llll"+i+j+t.size());
//                    FinalHebrow=t.get(i);
//                    j++;
//                    i++;
//                    k3=1;
//                }
//            }
//            else{
//                System.out.println("llll"+i+j+t.size());
//                i++;
//            }
//        }
//
//        if(FirstHebrow!=null)
//        {
//            String s= String.valueOf(FirstHebrow.getGrade());
//            HebrowFirstGrade.setText(s);
//            paperHebrowFirst.setDisable(false);
//        }
//        if(SecondHebrow!=null)
//        {
//            String s= String.valueOf(SecondHebrow.getGrade());
//            HebrowsecondGrade.setText(s);
//            paperHebrowSecond.setDisable(false);
//        }
//        if(FinalHebrow!=null)
//        {
//            String s= String.valueOf(FinalHebrow.getGrade());
//            HebrowFinalGrade.setText(s);
//            paperHebrowFinal.setDisable(false);
//        }
//    }
//    @FXML
//    void paperEngFinalAct(ActionEvent event) {
//        try
//        {LinkedList<Object> message = new LinkedList<Object>();
////            System.out.println(""+FirstEnglish.getGrade());
//            message.add("#GoToExStudentAnswers");
//            message.add(FinalEnglish);
//            SimpleClient.getClient().sendToServer(message);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    @FXML
//    void paperEngFirstAct(ActionEvent event) {
//        try
//        {LinkedList<Object> message = new LinkedList<Object>();
//            System.out.println(""+FirstEnglish.getGrade());
//        message.add("#GoToExStudentAnswers");
//        message.add(FirstEnglish);
//        SimpleClient.getClient().sendToServer(message);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    @FXML
//    void paperEngSecondAct(ActionEvent event) {
//        try
//        {LinkedList<Object> message = new LinkedList<Object>();
////            System.out.println(""+FirstEnglish.getGrade());
//            message.add("#GoToExStudentAnswers");
//            message.add(SecondEnglish);
//            SimpleClient.getClient().sendToServer(message);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    @FXML
//    void paperMathFinalAct(ActionEvent event) {
//        try
//        {LinkedList<Object> message = new LinkedList<Object>();
////            System.out.println(""+FirstEnglish.getGrade());
//            message.add("#GoToExStudentAnswers");
//            message.add(FinalMath);
//            SimpleClient.getClient().sendToServer(message);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @FXML
//    void paperMathFirstAct(ActionEvent event) {
//        try
//        {LinkedList<Object> message = new LinkedList<Object>();
////            System.out.println(""+FirstEnglish.getGrade());
//            message.add("#GoToExStudentAnswers");
//            message.add(FirstMath);
//            SimpleClient.getClient().sendToServer(message);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @FXML
//    void paperMathSecondAct(ActionEvent event) {
//        try
//        {LinkedList<Object> message = new LinkedList<Object>();
////            System.out.println(""+FirstEnglish.getGrade());
//            message.add("#GoToExStudentAnswers");
//            message.add(SecondMath);
//            SimpleClient.getClient().sendToServer(message);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    @FXML
//    void paperArabicFinalAct(ActionEvent event) {
//        try
//        {LinkedList<Object> message = new LinkedList<Object>();
////            System.out.println(""+FirstEnglish.getGrade());
//            message.add("#GoToExStudentAnswers");
//            message.add(FinalArabic);
//            SimpleClient.getClient().sendToServer(message);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @FXML
//    void paperArabicFirstAct(ActionEvent event) {
//        try
//        {LinkedList<Object> message = new LinkedList<Object>();
////            System.out.println(""+FirstEnglish.getGrade());
//            message.add("#GoToExStudentAnswers");
//            message.add(FirstArabic);
//            SimpleClient.getClient().sendToServer(message);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @FXML
//    void paperArabicSecondAct(ActionEvent event) {
//        try
//        {LinkedList<Object> message = new LinkedList<Object>();
////            System.out.println(""+FirstEnglish.getGrade());
//            message.add("#GoToExStudentAnswers");
//            message.add(SecondArabic);
//            SimpleClient.getClient().sendToServer(message);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    @FXML
//    void paperHebrowFinalAct(ActionEvent event) {
//        try
//        {LinkedList<Object> message = new LinkedList<Object>();
////            System.out.println(""+FirstEnglish.getGrade());
//            message.add("#GoToExStudentAnswers");
//            message.add(FinalHebrow);
//            SimpleClient.getClient().sendToServer(message);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @FXML
//    void paperHebrowFirstAct(ActionEvent event) {
//        try
//        {LinkedList<Object> message = new LinkedList<Object>();
////            System.out.println(""+FirstEnglish.getGrade());
//            message.add("#GoToExStudentAnswers");
//            message.add(FirstHebrow);
//            SimpleClient.getClient().sendToServer(message);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @FXML
//    void paperHebrowSecondAct(ActionEvent event) {
//        try
//        {LinkedList<Object> message = new LinkedList<Object>();
////            System.out.println(""+FirstEnglish.getGrade());
//            message.add("#GoToExStudentAnswers");
//            message.add(SecondHebrow);
//            SimpleClient.getClient().sendToServer(message);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @FXML
    void back(ActionEvent event) {
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
