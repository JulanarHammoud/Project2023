package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;

public class ManualExStController {

    @FXML
    private Button downloaded;

    @FXML
    private Text hourN;

    @FXML
    private Text minuteN;

    @FXML
    private Text secondN;

    @FXML
    private Button submitt;
    // int timeInMinutes = 0;
    private File selectedDocument;
    int hoursParams = 0;
    int lastIndex = SimpleClient.getParams().size() - 1;
    StudentWillMakeEx ExSt = (StudentWillMakeEx) SimpleClient.getParams().get(lastIndex);
    List<Question> WordQ=ExSt.getEx().getExam().getQuestions();
    int timeInMinutes = ExSt.getEx().getTimerr();
    //int timeInMinutes=130;
    boolean shouldStopSec = false;
    private SimpleIntegerProperty hours;
    private SimpleIntegerProperty minutes;
    private SimpleIntegerProperty seconds;
    Timeline timelineSeconds;
    Timeline timelineMinutes;
    Timeline timelineHours;
    @FXML
    private Button finish;
    Exam ex = new Exam();
    Student student = ExSt.getSs();
    Timeline timeline;

    public void initialize() {
       SimpleClient.setPosition("ManualExam");
    }
    @FXML
    void finishACt(ActionEvent event) {
        timelineSeconds.stop();
        timelineMinutes.stop();
        timelineHours.stop();
        SimpleClient.getParams().add(student);
        try {
            setRoot("PrimaryStudent");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void downloadedAct() {
        createWord();

        downloaded.setDisable(true);

        hours = new SimpleIntegerProperty(hoursParams);
        minutes = new SimpleIntegerProperty(timeInMinutes);
        seconds = new SimpleIntegerProperty(60);

        javafx.util.Duration sec = javafx.util.Duration.seconds(1);
        timeline = new Timeline(new KeyFrame(sec, event -> {
            updateCountdown();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void createWord() {
//        Question Q1=new Question("what is computer Q2","food","drink","game","none","none");
//        WordQ.add(Q1);

        ex.setCode("test");
        //Question q = new Question("q", "a", "b", "c", "d", "a");
        //LinkedList<Question> questionList = new LinkedList<>();
        //questionList.add(q);
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
                for (Question question : WordQ) {
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
            }
        }
        //startTimer();
    }


    public void sec() {

    }
    @FXML
    void submittAct(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Word Document");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Word Documents", "*.docx"));

        // Show the dialog and get the selected file
        File selectedDocument = fileChooser.showOpenDialog(new Stage());

        if (selectedDocument != null) {
            try {
                // Define the target directory within your IntelliJ project
                String targetDirectory = "src/main/java/il/cshaifasweng/OCSFMediatorExample/client/manualExams"; // Relative path in your project

                // Define the target path for the copied document
                String targetPath = targetDirectory + "/" + selectedDocument.getName();

                // Copy the selected Word document to the target directory
                Files.copy(selectedDocument.toPath(), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);

                // Now you can access the copied document from your IntelliJ project
                System.out.println("Copied Word Document to: " + targetPath);
                try {
                    SimpleClient.getClient().sendToServer("#WarningSubmit");
                    LinkedList<Object> message = new LinkedList<>();
                    message.add("#SubmitManual");
                    message.add(ExSt);
                    message.add(targetPath);
                    SimpleClient.getClient().sendToServer(message);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error copying the Word document.");
            }
        } else {
            System.out.println("No Word document selected.");
        }


    }

    public void updateCountdown(){
        ExamStudent exam =ExSt.getEx();
        int timer = (exam.getNewTimer() == -1) ? exam.getTimerr() : exam.getNewTimer();
        int last = SimpleClient.getMesFromClient().size() - 1;
        if(last != -1) {
            //System.out.println(SimpleClient.getMesFromClient().get(last).getClass());
            if (SimpleClient.getMesFromClient().get(last).getClass().equals(UpdateTimer.class)) {
                UpdateTimer updateTimer = (UpdateTimer) SimpleClient.getMesFromClient().get(last);
                int newTimer = updateTimer.getTimer();
                if (exam.getExam().getTimerr() != newTimer) {
                    System.out.println(newTimer);
                    exam.getExam().setTimerr(newTimer);
                    timer = newTimer;
                }
                System.out.println("the timer is: " + exam.getTimerr());
            }
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime parsedTime = LocalTime.parse(exam.getTime(),formatter);
        LocalTime currentTime = LocalTime.now();
        LocalTime endTime = parsedTime.plusMinutes(timer);
        java.time.Duration remainingTime = java.time.Duration.between(currentTime, endTime);
        String formattedTime = String.format("%02d:%02d:%02d",
                remainingTime.toHours(), (remainingTime.toMinutes() % 60), (remainingTime.getSeconds() % 60));
        minuteN.setText(formattedTime);
        if( remainingTime.toHours()<=0 && (remainingTime.toMinutes() % 60)<=0 && (remainingTime.getSeconds() % 60)<=0){
            System.out.println("time finished before submitting");
          submitt.setDisable(true);
        }

    }
}

