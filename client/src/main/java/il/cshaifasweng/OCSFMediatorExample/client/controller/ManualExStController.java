package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import il.cshaifasweng.OCSFMediatorExample.entities.StudentWillMakeEx;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import static javafx.application.Application.launch;


public class ManualExStController{

        @FXML
        private Button FinishBut;

        @FXML
        private Text Minutee;

//        @FXML
//        private Text Secondd;

        @FXML
        private Button downloadBut;

        @FXML
        private Text hourr;

        @FXML
        private TextArea text;
        @FXML
        private Text SSecond;

//        int del = 0;
        int i1 = 0;
        int i2 = 0;
        int i3 = 0;
        int lastIndex = SimpleClient.getParams().size() - 1;
        StudentWillMakeEx ExSt = (StudentWillMakeEx) SimpleClient.getParams().get(lastIndex);
        int timeInMinutes = ExSt.getEx().getTimerr();
//        int timeInMinutes=70;
        int hoursParams = 0;
        int secParams = 0;

//        public static void main(String[] args) throws Exception {
//            new il.cshaifasweng.OCSFMediatorExample.client.controller.ManualExStController().start(new Stage());
//        }

        @FXML
        void FinishButAct(ActionEvent event) {


        }


        @FXML
        void downloadButAct(ActionEvent event){
            createWord();
            downloadBut.setDisable(true);
            int t = 1;
            while(t==1)

            {
                if (timeInMinutes > 60) {
                    hoursParams = timeInMinutes / 60;
                    timeInMinutes=timeInMinutes-60*hoursParams;
                } else {
                    t = 2;
                }
            }
//            System.out.println(""+timeInMinutes+hoursParams);
//            System.out.println(""+timeInMinutes+hoursParams);

//                int L1 = 60;
//                i1=60;
//
//                SSecond.setText(String.valueOf(i1));

//                if((timeInMinutes==0)&&(hoursParams!=0))
//                {
//                    hoursParams--;
//                    timeInMinutes++;
//                }
//            Minutee.setText(String.valueOf(timeInMinutes));
//            hourr.setText(String.valueOf(hoursParams));
//
//            Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
//                    SSecond.setText(String.valueOf(i1));
//                    i1--;
//               }));
//             timeline1.setCycleCount(61);
//             timeline1.play();
//            if((SSecond.getText()=="0")&&(timeInMinutes!=0))
//            {
//                timeInMinutes--;
//                i1=60;
//            }
//            Minutee.setText(String.valueOf(timeInMinutes));
//            SSecond.setText(String.valueOf(i1));

            i2=timeInMinutes;
            Minutee.setText(String.valueOf(i2));
            Timeline timeline2=new Timeline(new KeyFrame(Duration.minutes(1), e->{
                i2--;
                Minutee.setText(String.valueOf(i2));
            }));
            timeline2.setCycleCount(timeInMinutes+1);
            timeline2.play();

            i3=hoursParams;
            hourr.setText(String.valueOf(i3));
            Timeline timeline3=new Timeline(new KeyFrame(Duration.hours(1), e->{
                i3--;
                hourr.setText(String.valueOf(i3));
            }));
            timeline3.setCycleCount(hoursParams+1);
            timeline3.play();

        }
    public void createWord() {
        Exam ex = new Exam();
        ex.setCode("test1");
        Question q = new Question("q","a","b","c","d","a");
        LinkedList<Question> questionList = new LinkedList<>();
        questionList.add(q);
        XWPFDocument document = new XWPFDocument();

        // Create a FileChooser dialog
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Word Document");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Word Documents", "*.docx"));

        // Show the dialog and get the selected file
        File file = fileChooser.showSaveDialog(new Stage());

        if (file != null) {
            String fileName = file.getAbsolutePath();

            try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
                XWPFParagraph titleParagraph = document.createParagraph();
                titleParagraph.setAlignment(ParagraphAlignment.CENTER);
                XWPFRun titleRun = titleParagraph.createRun();

                titleRun.setText(ex.getCode());
                titleRun.addBreak();
                titleRun.addBreak();
                titleRun.addBreak();
                titleRun.addBreak();
                titleRun.setFontSize(24);

                int questionNumber = 1;
                for (Question question : questionList) {
                    XWPFParagraph questionParagraph = document.createParagraph();
                    questionParagraph.setAlignment(ParagraphAlignment.LEFT);
                    XWPFRun questionRun = questionParagraph.createRun();

                    questionRun.setText("Question " + questionNumber + ": " + question.getQuestion());
                    questionRun.addBreak();
                    questionRun.setText(1 + ". " + question.getAns1());
                    questionRun.addBreak();
                    questionRun.setText(2 + ". " + question.getAns2());
                    questionRun.addBreak();
                    questionRun.setText(3 + ". " + question.getAns3());
                    questionRun.addBreak();
                    questionRun.setText(4 + ". " + question.getAns4());
                    questionRun.addBreak();

                    questionRun.setText("Answer: ________________________");
                    questionRun.addBreak();
                    questionRun.addBreak();
                    questionNumber++;
                }
                document.write(fileOutputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }}
        //startTimer();
    }
}





