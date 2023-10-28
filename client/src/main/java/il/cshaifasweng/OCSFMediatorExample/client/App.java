package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.EventBus.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.LinkedList;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private SimpleClient client;
    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        EventBus.getDefault().register(this);
        client = SimpleClient.getClient();
        client.openConnection();
        scene = new Scene(loadFXML("primary"), 640, 480);
        this.stage=stage;
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static void set(Parent root) {
        scene = new Scene(root, 640, 480);
        App.stage.setScene(scene);
        App.stage.show();
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    

    @Override
    public void stop() throws Exception {
        // TODO Auto-generated method stub
        EventBus.getDefault().unregister(this);
        super.stop();
    }
    
    @Subscribe
    public void onWarningEvent(WarningEvent event) {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText(null);
            alert.setContentText(event.getWarning().getMessage());
            alert.showAndWait();
        });


    }
    @Subscribe
    public void onMessageEvent(MassegeEvent event) {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            ImageView imageView = new ImageView(new Image("il/cshaifasweng/OCSFMediatorExample/client/34950617-3d-illustration-of-right-sign-in-green-color.jpg"));
            imageView.setFitWidth(70);  // Set the desired width
            imageView.setFitHeight(70); // Set the desired height
            Label messageLabel = new Label(event.getMessage().getMsg());

            VBox content = new VBox(10); // 10 is the spacing between the image and the message
            content.getChildren().addAll(imageView, messageLabel);

            DialogPane dialogPane = new DialogPane();
            Button closeButton = new Button("Close");
            closeButton.setOnAction(e -> {
                alert.setResult(ButtonType.CLOSE);
                alert.hide();
            });
            closeButton.setStyle("-fx-background-color: #f0f0f0;"); // Customize the close button style
            dialogPane.getButtonTypes().clear(); // Remove default button types
            dialogPane.getButtonTypes().add(ButtonType.CLOSE); // Add a custom button type
            dialogPane.setContent(content);
            dialogPane.setGraphic(imageView);
            dialogPane.setPrefWidth(500); // Set the desired width
            dialogPane.setPrefHeight(50);
            alert.setDialogPane(dialogPane);
            alert.showAndWait();
        });


    }

    @Subscribe
    public void onstdlEvent(stdlEvent event) {
        Platform.runLater(() -> {
            try {

                System.out.println("im in stdlEvent");
                SimpleClient.getParams().add(event.getStudent());
               // System.out.println(event.getStudent().getStudents().get(0).getSt_name());
                System.out.println("Move to secondary page");
                setRoot("secondary");
            } catch (IOException e) {
                e.printStackTrace();
            }

        });


    }
    @Subscribe
    public void onQuestionlEvent(QuestionEvent event) {
        Platform.runLater(() -> {
            try {

                System.out.println("im in Question");
                SimpleClient.getParams().add(event.getQuestion());
                // System.out.println(event.getStudent().getStudents().get(0).getSt_name());
                System.out.println("Move to ChooseQes page");
                setRoot("ChooseQes");
            } catch (IOException e) {
                e.printStackTrace();
            }

        });



    }
    @Subscribe
    public void onFDlEvent(CourseFormangerEvent event) {
        Platform.runLater(() -> {
            try {

                System.out.println("im in FD");
                LinkedList<Object> msg =new LinkedList<>();
                SimpleClient.getParams().add(event.getCF());
                // System.out.println(event.getStudent().getStudents().get(0).getSt_name());
                //System.out.println("Move to ChooseQes page");
                setRoot("ExamManager");
            } catch (IOException e) {
                e.printStackTrace();
            }

        });



    }
    @Subscribe
    public void onStudentEvent(StudentEvent event) {
        Platform.runLater(() -> {
            try {
                System.out.println("im in StudentEvent");
                SimpleClient.getParams().add(event.getStudent());
               // System.out.println(event.getStudent().getStudent().getSt_name());
                System.out.println("Move to Grades page");
                setRoot("Grades");
            } catch (IOException e) {
                e.printStackTrace();
            }

        });}

    @Subscribe
    public void onTeacherEvent(TeacherLogEvent event) {
        Platform.runLater(() -> {
            try {
                System.out.println("im in teacher log event");
                SimpleClient.getParams().add(event.getTeacher());
               // System.out.println(event.getStudent().getStudent().getSt_name());
                System.out.println("Move to teacher page");
                setRoot("teacherpage");
            } catch (IOException e) {
                e.printStackTrace();
            }

        });}


    @Subscribe
    public void onStudentLogEvent(StudentLogEvent event) {
        Platform.runLater(() -> {
            try {
                System.out.println("im in student log event");
                SimpleClient.getParams().add(event.getStudent());
                 System.out.println(event.getStudent().getActive());
                System.out.println("Move to PrimaryStudent page");
                setRoot("PrimaryStudent");
            } catch (IOException e) {
                e.printStackTrace();
            }

        });}


    @Subscribe
    public void onLogOutEvent(LogOutEvent event) throws IOException {
        Platform.runLater(() -> {
            try {
                System.out.println("Move to primary page");
                setRoot("primary");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    @Subscribe
    public void onExamCourseEvent(ExamCourseEvent event) {

        Platform.runLater(() -> {
            try {
                System.out.println("in handle");
                SimpleClient.getParams().add(event.getExx());
                System.out.println("Move to subject page");
                setRoot("subject");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Subscribe
    public void onExamEvent(ExamEvent event) {

        Platform.runLater(() -> {
            try {
                System.out.println("in handle onExamEvent");
                SimpleClient.getParams().add(event.getExx());
                System.out.println("Move to exam page");
                setRoot("TheExPage");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    @Subscribe
    public void onStudentWillDoExEvent(StudentWillDoExEvent event) {

        Platform.runLater(() -> {
            try {
                System.out.println("in handle StudentDOMakeEx");
                SimpleClient.getParams().add(event.getSs());
                System.out.println("");
                System.out.println("after"+event.getSs().getStudent().getCourses().size()+event.getSs().getStudent().getFirstName());
                System.out.println("Move to exam button page");
                setRoot("StExamButton");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    @Subscribe
    public void onGradeStEvent(GradeStEvent event) {

        Platform.runLater(() -> {
            try {
                System.out.println("in handle StudentDMakeEx");
                SimpleClient.getParams().add(event.getDd());
                System.out.println("");
                System.out.println("after"+event.getDd().getSs().getFirstName());
                System.out.println("Move to gradesbutton page");
                setRoot("GradesButton");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    @Subscribe
    public void onExamStudentEvent(ExamStudentEvent event) {

        Platform.runLater(() -> {
            try {
                System.out.println("in handle ExamStudent");
                SimpleClient.getParams().add(event.getEx());
                System.out.println("");
                System.out.println("after"+event.getEx().getGrade());
                setRoot("TestPaper");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    @Subscribe
    public void onExamGradeStudentEvent(GradeExamEvent event) {

        Platform.runLater(() -> {
            try {
                System.out.println("in handle ExamStudent");
                SimpleClient.getParams().add(event.getGradeexam().getExam());
                SimpleClient.getParams().add(event.getGradeexam().getGrade());
                System.out.println(event.getGradeexam().getExam());
                System.out.println(event.getGradeexam().getGrade());
                int lastIndex= SimpleClient.getParams().size()-1;
                System.out.println(SimpleClient.getParams().get(lastIndex));
                System.out.println("");
                setRoot("TestPaper");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Subscribe
    public void onStudentWillMakeExEvent( StudentWillMakeExEvent event) {

        Platform.runLater(() -> {
            try {
                System.out.println("in handle StudentWillMakeEx");
                SimpleClient.getParams().add(event.getStEx());
                System.out.println("");
                System.out.println("after");
                System.out.println("Move to Manual exam student page");
                setRoot("ManualExSt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    @Subscribe
    public void onSubjectTeacherEvent(SubjectTeacherEvent event) {

        Platform.runLater(() -> {
            try {
                System.out.println("in app aa");
                SimpleClient.getParams().add(event.getSubjectTeacher());
                System.out.println("Move to choose question page");
                setRoot("ChooseQes");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });}

    @Subscribe
    public void onGetSubjectEvent(GetSubjectEvent event) {

        Platform.runLater(() -> {
                System.out.println("in app heading to all exams");
                SimpleClient.getParams().add(event.getGetSubject());
            try {
                System.out.println("Move to all exams page");
                setRoot("allExams");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });}

    @Subscribe
    public void onSubIdEvent(SubIdEvent event) {

        Platform.runLater(() -> {
            try {
                System.out.println("saving SubId");
                SimpleClient.getParams().add(event.getSubId());
                System.out.println(event.getSubId().getSubject().getSb_name());
                //System.out.println(
                        //event.getSubId().getSubject().getQuestions().get(0).getQuestion());
                System.out.println("Move to choose question page");
                setRoot("ChooseQes");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });}

        @Subscribe
        public void onCourseTeacherEvent (CourseTeacherEvent event){
            Platform.runLater(() -> {
                try {
                    //System.out.println("im in teacherlog event");
                    SimpleClient.getParams().add(event.getCt());
                    // System.out.println(event.getStudent().getStudent().getSt_name());
                    System.out.println("Move to choose subject page");
                    setRoot("choose_subject");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
        }

    @Subscribe
    public void onExamShowEvent (ExamShowEvent event){
        Platform.runLater(() -> {
            try {
                System.out.println("Exam Show App-client");
                SimpleClient.getParams().add(event.getExamSubjectTeacher());
                System.out.println("Move to show exam page");
                setRoot("ShowExam");
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }

    @Subscribe
    public void onEditExamEvent(EditExamEvent event) {
        Platform.runLater(() -> {
            System.out.println("ExamEdit App-client");
            SimpleClient.getParams().add(event.getExamSubjectTeacherEdit());
            try {
                System.out.println("Move to exam edit page");
                setRoot("ExamEdit");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });}

    @Subscribe
    public void onGetForManagerEvent(GetForManagerEvent event) {
        Platform.runLater(() -> {
            System.out.println("GetForManager App-client");
            SimpleClient.getParams().add(event.getGetForManager());
            try {
                System.out.println("Move to manager page");

                setRoot("Manager");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });}
    @Subscribe
    public void onMailManagerEvent(MailManagerEvent event) {
        Platform.runLater(() -> {
            System.out.println("MailManagerEvent App-client");
            SimpleClient.getParams().add(event.getMM());
            try {
                System.out.println("Move to manager mail page");
                setRoot("ManagerMail");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });}


    @Subscribe
    public void onStudentsExamsEvent(StudentsExamsEvent event) {
        Platform.runLater(() -> {
            System.out.println("StudentExamsEvent App-client");
            SimpleClient.getParams().add(event.getStudentsExams());
            //System.out.println(event.getStudentsExams().getExam().getExamsOfStudents().get(0).getQuestions().get(0).getStdAnswer());
            try {
                setRoot("StudentsExams");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });}
    @Subscribe
    public void onUpdatedExamsEvent(UpdatedExamsEvent event) {
        Platform.runLater(() -> {
            System.out.println("StudentExamsEvent App-client");
            if(SimpleClient.getPosition().equals("StudentsExams")) {
               // System.out.println(" the client updating the duration to: " + event.getToDuration().getExamTeacher().getStart());
                SimpleClient.getMesFromClient().add(event.getExams());
                try {
                    setRoot("StudentsExams");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });}
    @Subscribe
    public void onToDurationEvent(ToDurationEvent event) {
        Platform.runLater(() -> {
            if(event.getToDuration().isUpdate()) {
                if (SimpleClient.getPosition().equals("Duration")) {
                    System.out.println(" the client updating the duration to: " + event.getToDuration().getExamTeacher().getStart());
                    SimpleClient.getMesFromClient().add(event.getToDuration());
                    try {
                        setRoot("Duration");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
            else {
                SimpleClient.getParams().add(event.getToDuration());
                try {
                    System.out.println("Move to Duration page");
                    setRoot("Duration");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }

        });}
    @Subscribe
    public void onexamandgradeEvent(ExamandGrageEvent event) {
        Platform.runLater(() -> {
            System.out.println("MailManagerEvent App-client");
            SimpleClient.getParams().add(event.getExgr());
            try {
                System.out.println("Move to manager mail page");
                setRoot("TestPaper");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });}

    @Subscribe
    public void UpdateTimerEvevnt(UpdateTimerEvevnt event) {
        Platform.runLater(() -> {
            if(SimpleClient.getPosition().equals("ComputedExam" ) || SimpleClient.getPosition().equals("ManualExam")){
               // System.out.println(" the client updating the duration to: " + event.getToDuration().getExamTeacher().getStart());
                SimpleClient.getMesFromClient().add(event.getUpdateTimer());

            }

        });}

    @Subscribe
    public void onGradeTeacherEvent(GradeTeacherEvent event) {
        Platform.runLater(() -> {
            System.out.println("GradeTeacherEvent App-client");
            SimpleClient.getParams().add(event.getGradeTeacher());
            try {
                System.out.println("Move to Gradesteacher page");
                setRoot("Gradesteacher");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });}
    @Subscribe
    public void onQuestionManagerEvent(QuestionManagerEvent event) {
        Platform.runLater(() -> {
            System.out.println("Question manager App-client");
            SimpleClient.getParams().add(event.getQuestionManager());
            try {
                System.out.println("Move to QuestionManager page");
                setRoot("QuestionManager");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });}
    @Subscribe
    public void onGetForManagerEvent1(GetForManagerEvent1 event) {
        Platform.runLater(() -> {
            System.out.println("GetForManager1 App-client");
            SimpleClient.getParams().add(event.getGetForManager1());
            try {
                setRoot("StudentResults");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });}
    @Subscribe
    public void onGetForManagerEvent2(GetForManagerEvent2 event) {
        Platform.runLater(() -> {
            System.out.println("GetForManager2 App-client");
            SimpleClient.getParams().add(event.getGetForManager2());
            try {
                setRoot("TeacherResults");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });}
    @Subscribe
    public void onGetForManagerEvent3(GetForManagerEvent3 event) {
        Platform.runLater(() -> {
            System.out.println("GetForManager3 App-client");
            SimpleClient.getParams().add(event.getGetForManager3());
            try {
                setRoot("CourseResults");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });}


        public static void main(String[] args) {
        launch();
    }

}