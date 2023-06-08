package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.server.DataControl.Data;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SimpleServer extends AbstractServer {

	public SimpleServer(int port) {
		super(port);
		try {
			//Data.LogOutSt(3);
			//Data.LogOutSt(4);
			//Data.generateStusent();
			//Data.generateSubject();
			//Data.generateEnglishQusetions();
			//Data.main(null);
			//System.out.println("why there is exeption");
			//Data.updatePrice(500,1);

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
				System.out.println( studentList.getStudents().get(0).getFirstName());
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

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if (message.get(0).equals("#UpdateGrade")){
				/*try{
				Data.updateGrade((int)message.get(2),(int)message.get(1),(int)message.get(3));
					Warning updated = new Warning("Grade Updated Successfully :) \n go back to the students list");
					client.sendToClient(updated);
				}
				catch (IOException e) {
					e.printStackTrace();
				}*/

			}
			else if (message.get(0).equals("#Login")){
				System.out.println("im in login ");
				try{
					if(message.get(3).equals("Teacher")) {
						System.out.println("the user is a teacher ");
						Teacher teacherlog = Data.TeacherLog((String) message.get(1), (String) message.get(2));
						//Data.TeacherLog((String) message.get(1),(String) message.get(2) );
						System.out.println("the username is " + (String) message.get(1));
						System.out.println(teacherlog.getFirstName());
						if(teacherlog.getFirstName()==null){
							System.out.println("the user is not in the database ");
							Warning warning = new Warning("incorrect information, try again!!");
							client.sendToClient(warning);
						}
						else if(teacherlog.getActive()==true){
							Warning warning = new Warning("you are already in");
							client.sendToClient(warning);
						}
						else{
							client.sendToClient(teacherlog);}
					}
					else if(message.get(3).equals("Student")){
						System.out.println("the user is a student ");
						Student studentlog = Data.StudentLog((String) message.get(1), (String) message.get(2));
						System.out.println("the username is " + (String) message.get(1));
						System.out.println(studentlog.getFirstName());
						if(studentlog.getFirstName()==null){
							System.out.println("the user is not in the database ");
							Warning warning = new Warning("incorrect information, try again!!");
							client.sendToClient(warning);
						}
						else if(studentlog.getActive()==true){
							Warning warning = new Warning("you are already in");
							client.sendToClient(warning);
						}
						else {
							Data.activateSt(studentlog.getId());
							client.sendToClient(studentlog);
						}
					}


				}
				catch (IOException e) {
					e.printStackTrace();
				}

			}

			else if(message.get(0).equals("#LogOut")){
				System.out.println("are you in the log out?");
				LogOut logOut=new LogOut("success");
				try {
					System.out.println("the id of the user is: "+ (int)message.get(1));
					Data.LogOutSt((int)message.get(1));
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					client.sendToClient(logOut);
					System.out.format("Sent logout to client %s\n", client.getInetAddress().getHostAddress());
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

	}

}