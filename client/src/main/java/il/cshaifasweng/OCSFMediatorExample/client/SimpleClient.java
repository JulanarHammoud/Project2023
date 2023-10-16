package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.EventBus.*;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;

import java.util.LinkedList;
import java.util.List;


public class SimpleClient extends AbstractClient {

	private static SimpleClient client = null;
	private  static List<Object> params=new LinkedList<>();
	private  static List<ExamStudent> exams=new LinkedList<>();
	private static String position="";
	private  static List<Object> mesFromClient=new LinkedList<>();

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		System.out.println("Received message class: " + msg.getClass().getName());
		System.out.println(msg.getClass());
		if (msg.getClass().equals(Warning.class)) {
			EventBus.getDefault().post(new WarningEvent((Warning) msg));
			System.out.println("im in the warning");
		}
		else if(msg.getClass().equals(StudentWillDoEx.class)){
			System.out.println("Received message class: " + msg.getClass().getName() +"from server");
			EventBus.getDefault().post(new StudentWillDoExEvent((StudentWillDoEx) msg));
		}
		else if(msg.getClass().equals(ExamStudentsandGrade.class)){
			System.out.println("Received message class: " + msg.getClass().getName() +"from server");
			EventBus.getDefault().post(new ExamandGrageEvent((ExamStudentsandGrade) msg));
		}
		else if(msg.getClass().equals(stlist.class)){
			System.out.println("Received message class: " + msg.getClass().getName() +"from server");
			EventBus.getDefault().post(new stdlEvent((stlist) msg));
		}
		else if(msg.getClass().equals(StudentInfo.class)){
			System.out.println("Received message class: " + msg.getClass().getName() +"from server");
			EventBus.getDefault().post(new StudentEvent((StudentInfo) msg));
		}
		else if(msg.getClass().equals(Teacher.class)){
			System.out.println("Received message class: " + msg.getClass().getName() +"from server");
			EventBus.getDefault().post(new TeacherLogEvent((Teacher) msg));
		}
		else if(msg.getClass().equals(Student.class)){
			System.out.println("Received message class: " + msg.getClass().getName() +"from server");
			EventBus.getDefault().post(new StudentLogEvent((Student) msg));
		}
		else if(msg.getClass().equals(GradeandExam.class)){
			System.out.println("Received message class: " + msg.getClass().getName() +"from server");
			EventBus.getDefault().post(new GradeExamEvent((GradeandExam) msg));
		}
		else if(msg.getClass().equals(ExamStudent.class)){
			System.out.println("Received message class: " + msg.getClass().getName() +"from server");
			EventBus.getDefault().post(new ExamStudentEvent((ExamStudent) msg));
		}
		else if(msg.getClass().equals(GradeSt.class)){
			System.out.println("Received message class: " + msg.getClass().getName() +"from server");
			EventBus.getDefault().post(new GradeStEvent((GradeSt) msg));
		}
		else if(msg.getClass().equals(LogOut.class)){
			System.out.println("Received message class: " + msg.getClass().getName() +"from server");
			EventBus.getDefault().post(new LogOutEvent((LogOut) msg));
		}
		else if(msg.getClass().equals(CourseTeacher.class)){
			System.out.println("Received message class: " + msg.getClass().getName() +"from server");
			System.out.println("else if");
			EventBus.getDefault().post(new CourseTeacherEvent((CourseTeacher) msg));
		}

		else if(msg.getClass().equals(ExamCourse.class)){
			System.out.println("Received message class: " + msg.getClass().getName() +"from server");
			System.out.println("else if");
			EventBus.getDefault().post(new ExamCourseEvent((ExamCourse) msg));
		}
		else if (msg.getClass().equals(SubWITHid.class)) {
			System.out.println("Received message class: " + msg.getClass().getName() +"from server");
			System.out.println("else if");
			EventBus.getDefault().post(new SubWITHidEvent((SubWITHid) msg));
		}
		else if(msg.getClass().equals(GetSubject.class)){
			System.out.println("Received message class: " + msg.getClass().getName() +"from server");
			System.out.println("GetSubject Client");
			EventBus.getDefault().post(new GetSubjectEvent((GetSubject) msg));
		}
		else if(msg.getClass().equals(SubjectTeacher.class)){
			System.out.println("Received message class: " + msg.getClass().getName() +"from server");
			System.out.println("SubjectTeacher Client");
			EventBus.getDefault().post(new SubjectTeacherEvent((SubjectTeacher) msg));
		}
		else if(msg.getClass().equals(SubjectAndId.class)){
			System.out.println("Received message class: " + msg.getClass().getName() +"from server");
			System.out.println("SubjectAndId Client");
			EventBus.getDefault().post(new SubIdEvent((SubjectAndId) msg));
		}
		else if(msg.getClass().equals(Question.class)){
			System.out.println("Received message class: " + msg.getClass().getName() +"from server");
			EventBus.getDefault().post(new QuestionEvent((Question) msg));
		}
		else if(msg.getClass().equals(ExamSubjectTeacher.class)){
			System.out.println("Received message class: " + msg.getClass().getName() +"from server");
			System.out.println("Exam Subject teacher client");
			EventBus.getDefault().post(new ExamShowEvent((ExamSubjectTeacher) msg));

		}
		else if(msg.getClass().equals(ExamSubjectTeacherEdit.class)){
			System.out.println("Received message class: " + msg.getClass().getName() +"from server");
			System.out.println("Exam Subject teacher edit simpleclient");
			EventBus.getDefault().post(new EditExamEvent((ExamSubjectTeacherEdit) msg));
		}
		else if (msg.getClass().equals(StudentWillMakeEx.class)) {
			System.out.println("Received message class: " + msg.getClass().getName() +"from server");
			EventBus.getDefault().post(new StudentWillMakeExEvent((StudentWillMakeEx) msg));
		}
		else if (msg.getClass().equals(GetForManager.class)) {
			System.out.println("Received message class: " + msg.getClass().getName() +"from server");
			EventBus.getDefault().post(new GetForManagerEvent((GetForManager) msg));
		}
		else if (msg.getClass().equals(MailManagerEntity.class)) {
			System.out.println("Received message class: " + msg.getClass().getName() +"from server");
			EventBus.getDefault().post(new MailManagerEvent((MailManagerEntity) msg));
		}
		else if (msg.getClass().equals(StudentsExams.class)) {
			EventBus.getDefault().post(new StudentsExamsEvent((StudentsExams) msg));
		}
		else if (msg.getClass().equals(UpdatedExams.class)) {
			EventBus.getDefault().post(new UpdatedExamsEvent((UpdatedExams) msg));
		}
		else if (msg.getClass().equals(ToDuration.class)) {
			EventBus.getDefault().post(new ToDurationEvent((ToDuration) msg));
		}
		else if (msg.getClass().equals(UpdateTimer.class)) {
			EventBus.getDefault().post(new UpdateTimerEvevnt((UpdateTimer) msg));
		}

	}

	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3000);}
		return client;
	}
	public static List<Object> getParams() {
		return params;
	}

	public static List<ExamStudent> getExams() {
		return exams;
	}

	public static void setExams(List<ExamStudent> exams) {
		SimpleClient.exams = exams;
	}

	public static String getPosition() {
		return position;
	}

	public static void setPosition(String position) {
		SimpleClient.position = position;
	}

	public static List<Object> getMesFromClient() {
		return mesFromClient;
	}
}
