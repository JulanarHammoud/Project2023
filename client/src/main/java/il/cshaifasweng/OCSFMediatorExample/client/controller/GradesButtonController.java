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

    @FXML
    private Button allgrades;

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
        LinkedList<Pane> panes=new LinkedList<>();
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
        List<ExamStudent> t1=StEx.getSs().getStudentExams();
        LinkedList<ExamStudent> t = new LinkedList<>();
        for(ExamStudent s : t1){
            if(s.isApprove()==true){
                t.add(s);
            }
        }
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
        {   System.out.println("gggg");
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
                        message.add(StEx);
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
                        message.add(StEx);
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
                        message.add(StEx);
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

    @FXML
    void allgradesaction(ActionEvent event) {
        try {
            SimpleClient.getClient().sendToServer(StEx);
            setRoot("allgradesstudent");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
