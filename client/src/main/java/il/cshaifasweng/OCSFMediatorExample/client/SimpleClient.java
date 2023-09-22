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

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		System.out.println(msg.getClass());
		if (msg.getClass().equals(Warning.class)) {
			EventBus.getDefault().post(new WarningEvent((Warning) msg));
			System.out.println("im in the warning");
		}
			else if(msg.getClass().equals(stlist.class)){
				EventBus.getDefault().post(new stdlEvent((stlist) msg));}
		else if(msg.getClass().equals(StudentInfo.class)){
			EventBus.getDefault().post(new StudentEvent((StudentInfo) msg));}
		else if(msg.getClass().equals(Teacher.class)){
			EventBus.getDefault().post(new TeacherLogEvent((Teacher) msg));
			System.out.println("im in logIn teacher");
		}
		else if(msg.getClass().equals(Student.class)){
			EventBus.getDefault().post(new StudentLogEvent((Student) msg));
		}
		else if(msg.getClass().equals(LogOut.class)){
			EventBus.getDefault().post(new LogOutEvent((LogOut) msg));
		}
		else if(msg.getClass().equals(CourseTeacher.class)){
			System.out.println("else if");
			EventBus.getDefault().post(new CourseTeacherEvent((CourseTeacher) msg));
		}


		else if(msg.getClass().equals(ExamCourse.class)){
			System.out.println("else if");
			EventBus.getDefault().post(new ExamCourseEvent((ExamCourse) msg));
		}
		else if (msg.getClass().equals(SubWITHid.class)) {
			System.out.println("else if");
			EventBus.getDefault().post(new SubWITHidEvent((SubWITHid) msg));
		}

		else if(msg.getClass().equals(SubjectTeacher.class)){
			System.out.println("SubjectTeacher Client");
			//EventBus.getDefault().post(new SubjectTeacherEvent((SubjectTeacher) msg));
			EventBus.getDefault().post(new SubjectTeacherEvent((SubjectTeacher) msg));
		     }
		else if(msg.getClass().equals(SubjectAndId.class)){
			System.out.println("SubjectAndId Client");
			//EventBus.getDefault().post(new SubjectTeacherEvent((SubjectTeacher) msg));
			EventBus.getDefault().post(new SubIdEvent((SubjectAndId) msg));
		}
		else if(msg.getClass().equals(Question.class)){
			EventBus.getDefault().post(new QuestionEvent((Question) msg));
		}
		else if(msg.getClass().equals(Exam.class)){
			EventBus.getDefault().post(new ExamShowEvent((Exam) msg));
		}
		else if (msg.getClass().equals(StudentWillMakeEx.class)) {
			EventBus.getDefault().post(new StudentWillMakeExEvent((StudentWillMakeEx) msg));
		}

	}
	
	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3000);
		}
		return client;
	}
	public static List<Object> getParams() {
		return params;
	}

}
