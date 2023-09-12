package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.EventBus.*;
import il.cshaifasweng.OCSFMediatorExample.client.controller.ChooseQesController;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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
    		Alert alert = new Alert(AlertType.WARNING,
        			String.format("Message: %s\nTimestamp: %s\n",
        					event.getWarning().getMessage(),
        					event.getWarning().getTime().toString())
        	);
        	alert.show();

    	});

    	
    }

    @Subscribe
    public void onstdlEvent(stdlEvent event) {
        Platform.runLater(() -> {
            try {

                System.out.println("im in stdlEvent");
                SimpleClient.getParams().add(event.getStudent());
               // System.out.println(event.getStudent().getStudents().get(0).getSt_name());
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
                setRoot("ChooseQes");
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
                setRoot("Grades");
            } catch (IOException e) {
                e.printStackTrace();
            }

        });}

    @Subscribe
    public void onTeacherEvent(TeacherLogEvent event) {
        Platform.runLater(() -> {
            try {
                System.out.println("im in teacherlog event");
                SimpleClient.getParams().add(event.getTeacher());
               // System.out.println(event.getStudent().getStudent().getSt_name());
                setRoot("teacherpage");
            } catch (IOException e) {
                e.printStackTrace();
            }

        });}


    @Subscribe
    public void onStudentLogEvent(StudentLogEvent event) {
        Platform.runLater(() -> {
            try {
                System.out.println("im in teacherlog event");
                SimpleClient.getParams().add(event.getStudent());
                // System.out.println(event.getStudent().getStudent().getSt_name());
                setRoot("PrimaryStudent");
            } catch (IOException e) {
                e.printStackTrace();
            }

        });}


    @Subscribe
    public void onLogOutEvent(LogOutEvent event) throws IOException {
        Platform.runLater(() -> {
            try {
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
                setRoot("subject");
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
                setRoot("ChooseQes");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });}

    @Subscribe
    public void onSubIdEvent(SubIdEvent event) {

        Platform.runLater(() -> {
            try {
                System.out.println("saving SubId");
                SimpleClient.getParams().add(event.getSubId());
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
                //System.out.println("im in teacherlog event");
                SimpleClient.getParams().add(event.getExam());
                // System.out.println(event.getStudent().getStudent().getSt_name());
                setRoot("ShowExam");
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }



	public static void main(String[] args) {
        launch();
    }

}