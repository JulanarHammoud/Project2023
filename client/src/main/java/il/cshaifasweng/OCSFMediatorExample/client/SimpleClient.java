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
		if (msg.getClass().equals(Warning.class)) {
			EventBus.getDefault().post(new WarningEvent((Warning) msg));
			System.out.println("im in the warning");
		}
			else if(msg.getClass().equals(stlist.class)){
				EventBus.getDefault().post(new stdlEvent((stlist) msg));}
		else if(msg.getClass().equals(StudentInfo.class)){
			EventBus.getDefault().post(new StudentEvent((StudentInfo) msg));}
		else if(msg.getClass().equals(Teacher.class)){
			EventBus.getDefault().post(new TeacherLogEvent((Teacher) msg));}
		else if(msg.getClass().equals(Student.class)){
			EventBus.getDefault().post(new StudentLogEvent((Student) msg));
		}
		else if(msg.getClass().equals(LogOut.class)){
			EventBus.getDefault().post(new LogOutEvent((LogOut) msg));
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
