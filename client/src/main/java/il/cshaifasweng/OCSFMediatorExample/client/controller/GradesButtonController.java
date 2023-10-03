package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.ExamStudent;
import il.cshaifasweng.OCSFMediatorExample.entities.GradeSt;
import il.cshaifasweng.OCSFMediatorExample.entities.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;

public class GradesButtonController {

    @FXML
    private Pane Arabic;

    @FXML
    private Button Button1;

    @FXML
    private Button Button2;

    @FXML
    private Button Button3;

    @FXML
    private Button Button4;

    @FXML
    private Pane Eng;

    @FXML
    private Pane Hebrow;

    @FXML
    private Pane Math;

    @FXML
    private Text Sub1;

    @FXML
    private Text Sub2;

    @FXML
    private Text Sub3;

    @FXML
    private Text Sub4;
    @FXML
    private Text EngFinalGrade1;

    @FXML
    private Text EngFirstGrade;

    @FXML
    private Text EngsecondGrade;

    @FXML
    private Text MathFinalGrade;

    @FXML
    private Text MathFirstGrade;

    @FXML
    private Text MathsecondGrade1;
    @FXML
    private Button paperEngFinal;

    @FXML
    private Button paperEngFirst;

    @FXML
    private Button paperEngSecond1;

    @FXML
    private Button paperMathFinal;

    @FXML
    private Button paperMathFirst;

    @FXML
    private Button paperMathSecond;
    @FXML
    private Text ArabicFinalGrade;

    @FXML
    private Text ArabicFirstGrade;

    @FXML
    private Text ArabicsecondGrade;
    @FXML
    private Button paperArabicFinal;

    @FXML
    private Button paperArabicFirst;

    @FXML
    private Button paperArabicSecond;
    @FXML
    private Text HebrowFinalGrade;

    @FXML
    private Text HebrowFirstGrade;

    @FXML
    private Text HebrowsecondGrade;
    @FXML
    private Button paperHebrowFinal;

    @FXML
    private Button paperHebrowFirst;

    @FXML
    private Button paperHebrowSecond;

    int lastIndex= SimpleClient.getParams().size()-1;
    GradeSt StEx = (GradeSt) SimpleClient.getParams().get(lastIndex);
    ExamStudent FirstMath=null;
    ExamStudent SecondMath=null;
    ExamStudent FinalMath=null;
    ExamStudent FirstEnglish=null;
    ExamStudent SecondEnglish=null;
    ExamStudent FinalEnglish=null;
    ExamStudent FirstArabic=null;
    ExamStudent SecondArabic=null;
    ExamStudent FinalArabic=null;
    ExamStudent FirstHebrow=null;
    ExamStudent SecondHebrow=null;
    ExamStudent FinalHebrow=null;
    Student student = StEx.getSs();
    public void initialize(){
//        ExamStudent mm=new ExamStudent();
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

        Math.setVisible(false);
        Arabic.setVisible(false);
       Hebrow.setVisible(false);
        Eng.setVisible(false);

        paperEngFinal.setDisable(true);
        paperEngFirst.setDisable(true);
        paperEngSecond1.setDisable(true);

        paperArabicFinal.setDisable(true);
        paperArabicFirst.setDisable(true);
        paperArabicSecond.setDisable(true);

        paperHebrowFinal.setDisable(true);
        paperHebrowFirst.setDisable(true);
        paperHebrowSecond.setDisable(true);

        paperMathFinal.setDisable(true);
        paperMathSecond.setDisable(true);
        paperMathFirst.setDisable(true);
    }
    @FXML
    void Button1Act(ActionEvent event) {
        Arabic.setVisible(false);
        Hebrow.setVisible(false);
        Eng.setVisible(false);
        Math.setVisible(!Math.isVisible());

        Button2.setText("+");
        Button3.setText("+");
        Button4.setText("+");
        if(Button1.getText().equals("+"))
        {
            Button1.setText("-");
        }
        else if(Button1.getText().equals("-"))
        {
            Button1.setText("+");
        }

        List<ExamStudent> t=StEx.getSs().getStudentExams();

        int i=0;
        int j=0;
        int k1=0;
        int k2=0;
        int k3=0;

        while((i<t.size())&&((k1==0)||(k2==0)||(k3==0)))
        {
            if("Math".equals(t.get(i).getSubject()))
            {
                if(j==0)
                {System.out.println("llll"+i+j+t.size());
                    FirstMath=t.get(i);
                    j++;
                    i++;
                    k1=1;
                }
                else if(j==1)
                {System.out.println("llll"+i+j+t.size());
                    SecondMath=t.get(i);
                    j++;
                    i++;
                    k2=1;
                }
                else if(j==2)
                {System.out.println("llll"+i+j+t.size());
                    FinalMath=t.get(i);
                    j++;
                    i++;
                    k3=1;
                }
            }
            else{
                System.out.println("llll"+i+j+t.size());
                i++;
            }
        }

        if(FirstMath!=null)
        {
            String s= String.valueOf(FirstMath.getGrade());
            MathFirstGrade.setText(s);
            paperMathFirst.setDisable(false);
        }
        if(SecondMath!=null)
        {
            String s= String.valueOf(SecondMath.getGrade());
            MathsecondGrade1.setText(s);
            paperMathSecond.setDisable(false);
        }
        if(FinalMath!=null)
        {
            String s= String.valueOf(FinalMath.getGrade());
            MathFinalGrade.setText(s);
            paperMathFinal.setDisable(false);
        }
    }

    @FXML
    void Button2Act(ActionEvent event) {
        Arabic.setVisible(false);
        Hebrow.setVisible(false);
        Math.setVisible(false);
        Eng.setVisible(!Eng.isVisible());

        Button1.setText("+");
        Button3.setText("+");
        Button4.setText("+");
        if(Button2.getText().equals("+"))
        {
            Button2.setText("-");
        }
        else if(Button2.getText().equals("-"))
        {
            Button2.setText("+");
        }

        List<ExamStudent> t=StEx.getSs().getStudentExams();

        int i=0;
        int j=0;
        int k1=0;
        int k2=0;
        int k3=0;

        while((i<t.size())&&((k1==0)||(k2==0)||(k3==0)))
        {
            if("English".equals(t.get(i).getSubject()))
            {
                if(j==0)
                {System.out.println("llll"+i+j+t.size());
                    FirstEnglish=t.get(i);
                    j++;
                    i++;
                    k1=1;
                }
                else if(j==1)
                {System.out.println("llll"+i+j+t.size());
                    SecondEnglish=t.get(i);
                    j++;
                    i++;
                    k2=1;
                }
                else if(j==2)
                {System.out.println("llll"+i+j+t.size());
                    FinalEnglish=t.get(i);
                    j++;
                    i++;
                    k3=1;
                }
            }
            else{
                System.out.println("llll"+i+j+t.size());
                i++;
            }
        }

        if(FirstEnglish!=null)
        {
            String s= String.valueOf(FirstEnglish.getGrade());
            EngFirstGrade.setText(s);
            System.out.println("ppp");
            paperEngFirst.setDisable(false);
        }
        if(SecondEnglish!=null)
        {
            String s= String.valueOf(SecondEnglish.getGrade());
            EngsecondGrade.setText(s);
            paperEngSecond1.setDisable(false);
        }
        if(FinalEnglish!=null)
        {
            String s= String.valueOf(FinalEnglish.getGrade());
            EngFinalGrade1.setText(s);
            paperEngFinal.setDisable(false);
        }

    }

    @FXML
    void Button3Act(ActionEvent event) {
        Hebrow.setVisible(false);
        Math.setVisible(false);
        Eng.setVisible(false);
        Arabic.setVisible(!Arabic.isVisible());

        Button2.setText("+");
        Button1.setText("+");
        Button4.setText("+");
        if(Button3.getText().equals("+"))
        {
            Button3.setText("-");
        }
        else if(Button3.getText().equals("-"))
        {
            Button3.setText("+");
        }

        List<ExamStudent> t=StEx.getSs().getStudentExams();

        int i=0;
        int j=0;
        int k1=0;
        int k2=0;
        int k3=0;

        while((i<t.size())&&((k1==0)||(k2==0)||(k3==0)))
        {
            if("Arabic".equals(t.get(i).getSubject()))
            {
                if(j==0)
                {System.out.println("llll"+i+j+t.size());
                    FirstArabic=t.get(i);
                    j++;
                    i++;
                    k1=1;
                }
                else if(j==1)
                {System.out.println("llll"+i+j+t.size());
                    SecondArabic=t.get(i);
                    j++;
                    i++;
                    k2=1;
                }
                else if(j==2)
                {System.out.println("llll"+i+j+t.size());
                    FinalArabic=t.get(i);
                    j++;
                    i++;
                    k3=1;
                }
            }
            else{
                System.out.println("llll"+i+j+t.size());
                i++;
            }
        }

        if(FirstArabic!=null)
        {
            String s= String.valueOf(FirstArabic.getGrade());
            ArabicFirstGrade.setText(s);
            paperArabicFirst.setDisable(false);
        }
        if(SecondArabic!=null)
        {
            String s= String.valueOf(SecondArabic.getGrade());
            ArabicsecondGrade.setText(s);
            paperArabicSecond.setDisable(false);
        }
        if(FinalArabic!=null)
        {
            String s= String.valueOf(FinalArabic.getGrade());
            ArabicFinalGrade.setText(s);
            paperArabicFinal.setDisable(false);
        }
    }

    @FXML
    void Button4Act(ActionEvent event) {
        Math.setVisible(false);
        Eng.setVisible(false);
        Arabic.setVisible(false);
        Hebrow.setVisible(!Hebrow.isVisible());

        Button2.setText("+");
        Button3.setText("+");
        Button1.setText("+");
        if(Button4.getText().equals("+"))
        {
            Button4.setText("-");
        }
        else if(Button4.getText().equals("-"))
        {
            Button4.setText("+");
        }

        List<ExamStudent> t=StEx.getSs().getStudentExams();

        int i=0;
        int j=0;
        int k1=0;
        int k2=0;
        int k3=0;

        while((i<t.size())&&((k1==0)||(k2==0)||(k3==0)))
        {
            if("Hebrow".equals(t.get(i).getSubject()))
            {
                if(j==0)
                {System.out.println("llll"+i+j+t.size());
                    FirstHebrow=t.get(i);
                    j++;
                    i++;
                    k1=1;
                }
                else if(j==1)
                {System.out.println("llll"+i+j+t.size());
                    SecondHebrow=t.get(i);
                    j++;
                    i++;
                    k2=1;
                }
                else if(j==2)
                {System.out.println("llll"+i+j+t.size());
                    FinalHebrow=t.get(i);
                    j++;
                    i++;
                    k3=1;
                }
            }
            else{
                System.out.println("llll"+i+j+t.size());
                i++;
            }
        }

        if(FirstHebrow!=null)
        {
            String s= String.valueOf(FirstHebrow.getGrade());
            HebrowFirstGrade.setText(s);
            paperHebrowFirst.setDisable(false);
        }
        if(SecondHebrow!=null)
        {
            String s= String.valueOf(SecondHebrow.getGrade());
            HebrowsecondGrade.setText(s);
            paperHebrowSecond.setDisable(false);
        }
        if(FinalHebrow!=null)
        {
            String s= String.valueOf(FinalHebrow.getGrade());
            HebrowFinalGrade.setText(s);
            paperHebrowFinal.setDisable(false);
        }
    }
    @FXML
    void paperEngFinalAct(ActionEvent event) {
        try
        {LinkedList<Object> message = new LinkedList<Object>();
//            System.out.println(""+FirstEnglish.getGrade());
            message.add("#GoToExStudentAnswers");
            message.add(FinalEnglish);
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void paperEngFirstAct(ActionEvent event) {
        try
        {LinkedList<Object> message = new LinkedList<Object>();
            System.out.println(""+FirstEnglish.getGrade());
        message.add("#GoToExStudentAnswers");
        message.add(FirstEnglish);
        SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void paperEngSecondAct(ActionEvent event) {
        try
        {LinkedList<Object> message = new LinkedList<Object>();
//            System.out.println(""+FirstEnglish.getGrade());
            message.add("#GoToExStudentAnswers");
            message.add(SecondEnglish);
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void paperMathFinalAct(ActionEvent event) {
        try
        {LinkedList<Object> message = new LinkedList<Object>();
//            System.out.println(""+FirstEnglish.getGrade());
            message.add("#GoToExStudentAnswers");
            message.add(FinalMath);
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void paperMathFirstAct(ActionEvent event) {
        try
        {LinkedList<Object> message = new LinkedList<Object>();
//            System.out.println(""+FirstEnglish.getGrade());
            message.add("#GoToExStudentAnswers");
            message.add(FirstMath);
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void paperMathSecondAct(ActionEvent event) {
        try
        {LinkedList<Object> message = new LinkedList<Object>();
//            System.out.println(""+FirstEnglish.getGrade());
            message.add("#GoToExStudentAnswers");
            message.add(SecondMath);
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void paperArabicFinalAct(ActionEvent event) {
        try
        {LinkedList<Object> message = new LinkedList<Object>();
//            System.out.println(""+FirstEnglish.getGrade());
            message.add("#GoToExStudentAnswers");
            message.add(FinalArabic);
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void paperArabicFirstAct(ActionEvent event) {
        try
        {LinkedList<Object> message = new LinkedList<Object>();
//            System.out.println(""+FirstEnglish.getGrade());
            message.add("#GoToExStudentAnswers");
            message.add(FirstArabic);
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void paperArabicSecondAct(ActionEvent event) {
        try
        {LinkedList<Object> message = new LinkedList<Object>();
//            System.out.println(""+FirstEnglish.getGrade());
            message.add("#GoToExStudentAnswers");
            message.add(SecondArabic);
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void paperHebrowFinalAct(ActionEvent event) {
        try
        {LinkedList<Object> message = new LinkedList<Object>();
//            System.out.println(""+FirstEnglish.getGrade());
            message.add("#GoToExStudentAnswers");
            message.add(FinalHebrow);
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void paperHebrowFirstAct(ActionEvent event) {
        try
        {LinkedList<Object> message = new LinkedList<Object>();
//            System.out.println(""+FirstEnglish.getGrade());
            message.add("#GoToExStudentAnswers");
            message.add(FirstHebrow);
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void paperHebrowSecondAct(ActionEvent event) {
        try
        {LinkedList<Object> message = new LinkedList<Object>();
//            System.out.println(""+FirstEnglish.getGrade());
            message.add("#GoToExStudentAnswers");
            message.add(SecondHebrow);
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        SimpleClient.getClient().sendToServer(message);
    }

}
