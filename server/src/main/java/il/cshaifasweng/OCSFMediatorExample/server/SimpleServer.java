package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Student;
import il.cshaifasweng.OCSFMediatorExample.entities.stlist;
import il.cshaifasweng.OCSFMediatorExample.server.DataControl.Data;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

import java.io.IOException;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.Warning;

public class SimpleServer extends AbstractServer {

	public SimpleServer(int port) {
		super(port);
		try {
			//Data.generateStusent();
			//Data.main(null);
			//System.out.println("why there is exeption");
			//		Data.updatePrice(500,1);

		} catch (Exception e) {
			System.out.print("there is an error");
			e.printStackTrace();
		}

	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) throws Exception {
		String msgString = msg.toString();
		if (msgString.startsWith("#warning")) {
			Warning warning = new Warning("Warning from server!");
			try {
				client.sendToClient(warning);
				System.out.format("Sent warning to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(msgString.startsWith("#ListStudents")) {
			List<Student> students = Data.getAllStudents();
			stlist studentList=new stlist(students);
			client.sendToClient(studentList);
			System.out.format("Sent list to client %s\n", client.getInetAddress().getHostAddress());


		}

	}

}
