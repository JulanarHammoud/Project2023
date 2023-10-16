package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Student;
import il.cshaifasweng.OCSFMediatorExample.entities.StudentWillMakeEx;
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
import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
    void downloadedAct(ActionEvent event) {
        createWord();

        downloaded.setDisable(true);
        int t = 1;
        while (t == 1) {
            if (timeInMinutes > 60) {
                hoursParams = timeInMinutes / 60;
                timeInMinutes = timeInMinutes - 60 * hoursParams;
            } else {
                t = 2;
            }
        }
        if((hoursParams%2==0)&&(timeInMinutes==0))
        {
            hoursParams--;
            timeInMinutes=60;
        }
        System.out.println(""+timeInMinutes+";;;"+hours+";;");
        hourN.setText(String.valueOf(hoursParams));
        minuteN.setText(String.valueOf(timeInMinutes - 1));
        secondN.setText("60");

        hours = new SimpleIntegerProperty(hoursParams);
        minutes = new SimpleIntegerProperty(timeInMinutes);
        seconds = new SimpleIntegerProperty(60);

        //hour//
        timelineHours = new Timeline(new KeyFrame(Duration.hours(1), e -> {
            hours.set(hours.get() - 1);
        }));
        timelineHours.setCycleCount(Timeline.INDEFINITE);
        timelineHours.play();

        // Create a timeline for minutes
        timelineMinutes = new Timeline(new KeyFrame(Duration.minutes(1), e -> {
            minutes.set(minutes.get() - 1);
        }));
        timelineMinutes.setCycleCount(Timeline.INDEFINITE);
        timelineMinutes.play();

        // Create a timeline for seconds
        timelineSeconds = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            seconds.set(seconds.get() - 1);

            // Handle rollover when seconds reach 0
            if ((seconds.get() == 0) || (seconds.get() < 0)) {
                if (("0".equals(hourN.getText())) && ("0".equals(minuteN.getText()))) {
                    shouldStopSec = true;
                    timelineSeconds.stop();
                    submitt.setDisable(true);
                } else {
                    seconds.set(59);
                    minutes.set(minutes.get() - 1);
                }

            }
            // Handle rollover when minutes reach 0
            if (minutes.get() < 0) {
                if ("0".equals(hourN.getText())) {
                    timelineMinutes.stop();
                } else {
                    minutes.set(59);
                    hours.set(hours.get() - 1);
                }

                // Handle rollover when hours reach 0 (or you can stop the timer)
                if (hours.get() < 0) {
                    hours.set(0);
                }
            }

        }));
        timelineSeconds.setCycleCount(Timeline.INDEFINITE);
        timelineSeconds.play();

        // Bind StringProperties to update UI
        StringProperty hourText = new SimpleStringProperty();
        StringProperty minuteText = new SimpleStringProperty();
        StringProperty secondText = new SimpleStringProperty();

        hourText.bind(hours.asString());
        minuteText.bind(minutes.asString());
        secondText.bind(seconds.asString());

        // Example: Print the values to the console
        hourText.addListener((obs, oldVal, newVal) -> hourN.setText(newVal));
        minuteText.addListener((obs, oldVal, newVal) -> minuteN.setText(newVal));
        secondText.addListener((obs, oldVal, newVal) -> secondN.setText(newVal));
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
}

