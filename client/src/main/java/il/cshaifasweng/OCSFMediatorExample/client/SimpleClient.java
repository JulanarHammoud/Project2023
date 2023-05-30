package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.StudentInfo;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;
import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Warning;

import java.util.LinkedList;
import java.util.List;
import il.cshaifasweng.OCSFMediatorExample.entities.stlist;


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
