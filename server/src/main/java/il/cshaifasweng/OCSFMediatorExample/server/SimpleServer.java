package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Student;
import il.cshaifasweng.OCSFMediatorExample.entities.StudentInfo;
import il.cshaifasweng.OCSFMediatorExample.entities.stlist;
import il.cshaifasweng.OCSFMediatorExample.server.DataControl.Data;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.Warning;

public class SimpleServer extends AbstractServer {

	public SimpleServer(int port) {
		super(port);
		try {
			//Data.generateStusent();
			Data.generateSubject();
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

		//System.out.println("Message = " + msgString + ", reached server");
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
			try {
				System.out.print("im in");
				System.out.flush();
				System.out.println("Id: ");
				List<Student> students = Data.getAllStudents();
				stlist studentList = new stlist(students);
				client.sendToClient(studentList);
				System.out.print("the first student is: " );
				System.out.println( studentList.getStudents().get(0).getSt_name());
				System.out.format("Sent list to client %s\n", client.getInetAddress().getHostAddress());
			}
			catch (IOException e) {
				e.printStackTrace();
			}

		}
		 else {
			LinkedList<Object> message = (LinkedList<Object>) (msg);
			System.out.println(message.get(0));
			if (message.get(0).equals("#ClickGrades")) {
				try {
					System.out.println("whyyyyyyyyyyyyyy");
					int id = (int) message.get(1);
					StudentInfo student = new StudentInfo(Data.getStudent(id));
					client.sendToClient(student);
					System.out.println(student.getStudent().getSt_name());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if (message.get(0).equals("#UpdateGrade")){
				try{
				Data.updateGrade((int)message.get(2),(int)message.get(1),(int)message.get(3));
					Warning updated = new Warning("Grade Updated Successfully :) \n go back to the students list");
					client.sendToClient(updated);
				}
				catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

	}

}