package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.server.DataControl.Data;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;


public class SimpleServer extends AbstractServer {

	public SimpleServer(int port) {
		super(port);
		try {

//			SubjectTeacher grammar=  Data.findsubject("Grammar");
//			Data.MakeQuestion("aaa","bbb","ccc","ddd","ddd","ttt",grammar);
			//Data.LogOutSt(1);
			//Data.LogOutSt(4);
			//Data.generateSubject();
			//Data.generateStusent();
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
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		String msgString = msg.toString();

		System.out.println("Message = " + msgString + ", reached server");
		System.out.println(msgString.startsWith("#warningNoQes"));
		if (msgString.startsWith("#warning")) {
			Warning warning = new Warning("Warning from server!");
			try {
				client.sendToClient(warning);
				System.out.format("Sent warning to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (msgString.equals("[#warningNoQes]")) {
			Warning warning = new Warning("please choose a question !!");
			System.out.println("im in the if");
			try {
				client.sendToClient(warning);
				System.out.format("Sent warning to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#ListStudents")) {
			try {
				System.out.print("im in");
				System.out.flush();
				System.out.println("Id: ");
				List<Student> students = Data.getAllStudents();
				stlist studentList = new stlist(students);
				client.sendToClient(studentList);
				System.out.print("the first student is: ");
				System.out.println(studentList.getStudents().get(0).getFirstName());
				System.out.format("Sent list to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
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
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (message.get(0).equals("#UpdateGrade")) {

			} else if (message.get(0).equals("#Login")) {
				System.out.println("im in login ");
				try {
					if (message.get(1).equals("")) {
						if (message.get(2).equals("")) {
							System.out.println("there is no username or password ");
							Warning warning = new Warning("please fill the informations!!");
							client.sendToClient(warning);
						} else {
							System.out.println("the user did not fill the username");
							Warning warning = new Warning("please fill the username!!");
							client.sendToClient(warning);
						}
					} else if (message.get(2).equals("")) {
						if (message.get(3) == null) {
							System.out.println("no role and password yet");
							Warning warning = new Warning("please fill the password, and pick your role!!");
							client.sendToClient(warning);
						} else {
							System.out.println("the user did not fill the password");
							Warning warning = new Warning("please fill the password!!");
							client.sendToClient(warning);
						}
					} else if (message.get(3) == null) {
						System.out.println("no role yet");
						Warning warning = new Warning("please pick your role!!");
						client.sendToClient(warning);
					} else if (message.get(3).equals("Teacher")) {
						System.out.println("the user is a teacher ");
						Teacher teacherlog = Data.TeacherLog((String) message.get(1), (String) message.get(2));
						System.out.println("the username is " + (String) message.get(1));
						System.out.println(teacherlog.getFirstName());
						if (teacherlog.getFirstName() == null) {
							System.out.println("the user is not in the database ");
							Warning warning = new Warning("there is no teacher with this name, please try again!!");
							client.sendToClient(warning);
						} else if (teacherlog.getFirstName().equals("wrongteacherpassword")) {
							System.out.println("wrong password to this teacher's name ");
							Warning warning = new Warning("wrong password, please try again!!");
							client.sendToClient(warning);
						} else if (teacherlog.getActive() == true) {
							Warning warning = new Warning("you are already in");
							client.sendToClient(warning);
						} else {
							client.sendToClient(teacherlog);
						}
					} else if (message.get(3).equals("Student")) {
						System.out.println("the user is a student ");
						Student studentlog = Data.StudentLog((String) message.get(1), (String) message.get(2));
						System.out.println("the username is " + (String) message.get(1));
						System.out.println(studentlog.getFirstName());
						if (studentlog.getFirstName() == null) {
							System.out.println("the user is not in the database ");
							Warning warning = new Warning("there is no student with this name, please try again!!");
							client.sendToClient(warning);
						}
						if (studentlog.getFirstName().equals("wrongstudentpassword")) {
							System.out.println("wrong password to this teacher's name ");
							Warning warning = new Warning("wrong password, please try again!!");
							client.sendToClient(warning);
						} //else if (studentlog.getActive() == true) {
						//	Warning warning = new Warning("you are already in");
						//	client.sendToClient(warning);
						//}
						else {
							Data.activateSt(studentlog.getId());
							client.sendToClient(studentlog);
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (message.get(0).equals("#LogOut")) {
				System.out.println("are you in the log out?");
				LogOut logOut = new LogOut("success");
				try {
					System.out.println("the id of the user is: " + (int) message.get(1));
					Data.LogOutSt((int) message.get(1));
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					client.sendToClient(logOut);
					System.out.format("Sent logout to client %s\n", client.getInetAddress().getHostAddress());
					Warning warning = new Warning("you loged out successfully");
					client.sendToClient(warning);
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else if (message.get(0).equals("#MakeExam")) {
				try {
					System.out.println("in make exam ");
					//System.out.println("the username is " + (String) message.get(1));
					String num = (String) message.get(1);
					String t_N = (String) message.get(2);
					String timm = (String) message.get(3);
					String S_N = (String) message.get(4);
					//System.out.println(S_N);
					CourseTeacher course = (CourseTeacher) message.get(5);
					SubjectTeacher sub = (SubjectTeacher) message.get(6);
					String teacherr = (String) message.get(7);
					Teacher teacher = (Teacher) message.get(8);

					if (timm.isEmpty()) {
						if (num.isEmpty()) {
							System.out.println("there is no time or number of questions filled yet");
							Warning warning = new Warning("please fill the informations!!");
							client.sendToClient(warning);
						} else {
							System.out.println("the user did not fill the time");
							Warning warning = new Warning("please fill the time!!");
							client.sendToClient(warning);
						}
					} else if (num.isEmpty()) {
						System.out.println("the user did not fill the number of questions");
						Warning warning = new Warning("please fill the number of questions!!");
						client.sendToClient(warning);
					} else {
						boolean result1 = timm.matches("[0-9]+");
						boolean result2 = num.matches("[0-9]+");
						if (result1 == false && result2 == false) {
							System.out.println("ellegal time and number of questions");
							Warning warning = new Warning("please fill a legal time and a legal number of questions!!");
							client.sendToClient(warning);
						} else if (result1 == false) {
							System.out.println("ellegal time");
							Warning warning = new Warning("please fill a legal time!!");
							client.sendToClient(warning);

						} else if (result2 == false) {
							System.out.println("ellegal num of questions");
							Warning warning = new Warning("please fill a legal number of questions!!");
							client.sendToClient(warning);
						} else {

							int num_q = Integer.valueOf(num);
							int id = Data.MakeExam(num_q, t_N, timm, S_N, course.getName(), sub, teacherr);
							System.out.println("after data find");
							System.out.println(course.getName());            //ex. english esraa
							DecimalFormat formatter = new DecimalFormat("00");
							String cor_id = formatter.format(course.getId());
							String sub_id = formatter.format(sub.getId());
							Data.updateExamId(cor_id, id, sub_id);
							SubjectAndId subId = new SubjectAndId(sub, id, teacher,course);
							client.sendToClient(subId);
						}
					}
				} catch (IOException e) {

				} catch (Exception e) {
					throw new RuntimeException(e);
				}

			}


//			} else if (message.get(0).equals("#MakeExam2")) {
//				try {
//					System.out.println("in make exam2 ");
//					//System.out.println("the username is " + (String) message.get(1));
//					String sub = (String) message.get(1);   //ex. Grammar
//					if(sub==null){
//						System.out.println("no subject is picked yet");
//						Warning warning = new Warning("Please pick a subject!!");
//						client.sendToClient(warning);
//					}
//					int ex_id = (int) message.get(2);
//					String cor_id = (String) message.get(3);
//					SubjectTeacher sub2 = Data.findsubject(sub);
//					System.out.println("" + (String) message.get(1));
//					//Data.MakeExam2((int)message.get(1));
//					//CourseTeacher course= Data.FindCourse((String)message.get(5));
//					DecimalFormat formatter = new DecimalFormat("00");
//					String aFormatted = formatter.format(sub2.getId());
//					System.out.println(aFormatted);
//					Data.updateExamId(cor_id, ex_id, aFormatted);
//					//System.out.println(message.getClass());
//					SubjectAndId subId= new SubjectAndId(sub2,ex_id);
//					client.sendToClient(subId);
//				} catch (IOException e) {
//
//				} catch (Exception e) {
//					throw new RuntimeException(e);
//				}
//			}
//			else if (message.get(0).equals("#GoToExStudentA")) {			//israaa
//				try {
//					int idd = Data.MakeExam(12,"","130","","English","mona","esra","25.09.2023","manual");
//					String i=updateExamId("01",idd,"04");
//System.out.println("sssssss");
////					String code = (String) message.get(1);
////					String type = (String) message.get(2);
////					String studentID = (String) message.get(3);
//					String stt= (String) message.get(1);
//					Student st=Data.getStudent(Integer.parseInt(stt));
//					System.out.println("s"+st.getFirstName());
//					System.out.println("jjjjjjjj");
////					Student student=Data.getStudent(Integer.parseInt(studentID));
//
////					int FoundId=-1;
////					List<CourseStudent> y=student.getCourses();
////					int size=y.size();
////					int j=0;
////					while(size!=0)
////					{
////						List<Exam> g=y.get(j).getEx();
////						int hg=g.size();
////						int m=0;
////						while(hg!=0)
////						{
////							Exam singleEx=g.get(m);
////							String name=singleEx.getCodeGivenByTeacher();
////							if(name==code)
////							{
////								FoundId=singleEx.getId();
////								size=0;
////								hg=0;
////							}
////							else{
////								hg--;
////								m++;
////							}
////						}
////						size--;
////						j++;
////					}
//
////					System.out.println(""+code+type+student.getFirstName());
//					System.out.println(""+st.getFirstName());
//					Exam n=Data.BringExamBasedOnCode(idd);
//					System.out.println(";;;;"+n.getIdCode());
//					StudentWillDoEx studentWillDo=new StudentWillDoEx(st,n);
//					//StudentWillMakeEx StEx=new StudentWillMakeEx(n);
//					//System.out.println("gkl"+StEx.getEx().getCodeGivenByTeacher()+"date"+StEx.getEx().getDate());
//					////	PROBLEM	//StEx.getStudentsMakeThisEx().add(StEx.getStudentsMakeThisEx().size(),dd);
//					//System.out.println("JJ"+StEx.getStudentsMakeThisEx().size());
////					System.out.println("pp"+student.getUserName());
////					StEx.setTypee(type);
//					//StEx.setIdCodeForExam(idd);
//					//StEx.setTheLastStJoined(st);
//					//System.out.println("kl"+StEx.getTypee()+StEx.getIdCodeForExam());
//
//				System.out.println(""+studentWillDo.getStudent().getFirstName()+studentWillDo.getExamWillBeDone().getType());
//					System.out.println("Received message class: " + msg.getClass().getName());
//					client.sendToClient(studentWillDo);
//				}catch (Exception e) {
//					e.printStackTrace();
//				}
//
//			} //-----> Yaman And Lana turned it off
//			else if (message.get(0).equals("#GoToExStudentBUTTON")){
//				try {
//					int idd = Data.MakeExam(12,"","130","","English","mona","esra","25.09.2023","manual");
//					String i=updateExamId("01",idd,"04");
//
//					String code = (String) message.get(1);
//					String type = (String) message.get(2);
//					String studentID = (String) message.get(3);
//
//					Student st=Data.getStudent(Integer.parseInt(studentID));
//					System.out.println("s"+st.getFirstName());
//
////					int FoundId=-1;
////					List<CourseStudent> y=student.getCourses();
////					int size=y.size();
////					int j=0;
////					while(size!=0)
////					{
////						List<Exam> g=y.get(j).getEx();
////						int hg=g.size();
////						int m=0;
////						while(hg!=0)
////						{
////							Exam singleEx=g.get(m);
////							String name=singleEx.getCodeGivenByTeacher();
////							if(name==code)
////							{
////								FoundId=singleEx.getId();
////								size=0;
////								hg=0;
////							}
////							else{
////								hg--;
////								m++;
////							}
////						}
////						size--;
////						j++;
////					}
//
////					System.out.println(""+code+type+student.getFirstName());
//					System.out.println(""+st.getFirstName());
//					Exam n=Data.BringExamBasedOnCode(idd);
//					System.out.println(";;;;"+n.getIdCode());
//
//					StudentWillMakeEx StEx=new StudentWillMakeEx(n);
//					System.out.println("gkl"+StEx.getEx().getCodeGivenByTeacher()+"date"+StEx.getEx().getDate());
//					////	PROBLEM	//StEx.getStudentsMakeThisEx().add(StEx.getStudentsMakeThisEx().size(),dd);
//					//System.out.println("JJ"+StEx.getStudentsMakeThisEx().size());
////					System.out.println("pp"+student.getUserName());
//					StEx.setTypee(type);
//					StEx.setIdCodeForExam(idd);
//					StEx.setTheLastStJoined(st);
//					//System.out.println("kl"+StEx.getTypee()+StEx.getIdCodeForExam());
//					client.sendToClient(StEx);
//
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			} --------------> Yaman And Lana turned it off
			/*else if (message.get(0).equals("#SubjectTeacher")) {
				try {
					System.out.println("I'm in server subjectteacher");
					String choose = (String) message.get(1);
					System.out.println(choose);
					CourseTeacher course = Data.FindCourse(choose);
					System.out.println("after data find");
					System.out.println(course.getName());            //ex. english esraa
					DecimalFormat formatter = new DecimalFormat("00");
					String aFormatted = formatter.format(course.getId());
					System.out.println(aFormatted);
					course.setId_String(aFormatted);
					//System.out.println(message.getClass());
					client.sendToClient(course);

				} catch (Exception e) {
					e.printStackTrace();
				}

			} */
//			else if (message.get(0).equals("#SubjectTeacher")) {
//				try {
//					System.out.println("I'm in server subjectteacher");
//					String choose= (String) message.get(1);
//					System.out.println(choose);
//					SubjectTeacher subject =Data.findsubject(choose);
//					System.out.println("after data find");
//					System.out.println(subject.getSb_name());
//					SubjectAndId subId= new SubjectAndId(subject,-1);
//					client.sendToClient(subId);
//
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
			else if (message.get(0).equals("#CoursetTeacher")) {
				try {
					System.out.println("I'm in server courseteacher");
					String choose = (String) message.get(2);
					System.out.println(choose);
					if (choose == null) {
						System.out.println("no course is picked yet");
						Warning warning = new Warning("Please pick a course!!");
						client.sendToClient(warning);
					}
					CourseTeacher course = Data.findcourse(choose);
					System.out.println("after data find");
					System.out.println(course.getName());
					client.sendToClient(course);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (message.get(0).equals("MakenewQuestion")) {
				try {
					System.out.println("in make question ");
					String ques1 = (String) message.get(2);
					String ans1 = (String) message.get(3);
					String ans2 = (String) message.get(4);
					String ans3 = (String) message.get(5);
					String ans4 = (String) message.get(6);
					String right = (String) message.get(7);
					int id = (int) message.get(8);
					Teacher teacher = (Teacher) message.get(9);
					if (ques1.isEmpty()) {
						if (ans1.isEmpty() || ans2.isEmpty() || ans3.isEmpty() || ans4.isEmpty()) {
							System.out.println("there is no question or 4 possible answers yet");
							Warning warning = new Warning("please write the question and 4 possible answers!!");
							client.sendToClient(warning);
						} else {
							System.out.println("there is no question yet");
							Warning warning = new Warning("please write the question!!");
							client.sendToClient(warning);
						}
					} else if (ans1.isEmpty() || ans2.isEmpty() || ans3.isEmpty() || ans4.isEmpty()) {
						System.out.println("there is no 4 possible answers yet");
						Warning warning = new Warning("please write 4 possible answers!!");
						client.sendToClient(warning);
					} else if (right.isEmpty()) {
						System.out.println("the right answer is not selected yet");
						Warning warning = new Warning("please write the right answer!!");
						client.sendToClient(warning);
					} else if (ans1.equals(ans2) || ans1.equals(ans3) || ans1.equals(ans4) || ans2.equals(ans3) || ans2.equals(ans4) || ans3.equals(ans4)) {
						System.out.println("there are duplicated answers");
						Warning warning = new Warning("there are duplicated answers, please write 4 different answers!!");
						client.sendToClient(warning);
					} else if (!right.equals(ans1) && !right.equals(ans2) && !right.equals(ans3) && !right.equals(ans4)) {
						System.out.println("the right answer is not from the 4 answers");
						Warning warning = new Warning("please pick the right answer from the 4 possible answers!!");
						client.sendToClient(warning);
					} else if (ques1.equals(ans1) || ques1.equals(ans2) || ques1.equals(ans3) || ques1.equals(ans4)) {
						System.out.println("the question is in the answers too");
						Warning warning = new Warning("please don't write the question the same as the answer!!");
						client.sendToClient(warning);
					} else { //the input is good
						SubjectTeacher subjectTeacher = (SubjectTeacher) message.get(1);
						System.out.println("before question make data");
						SubjectTeacher subjectTeacher1 = Data.MakeQuestion(ques1, ans1, ans2, ans3, ans4, right, subjectTeacher);
						System.out.println("After question make data");
						//SubjectAndId subid =new SubjectAndId(subjectTeacher1,id,teacher);
						LinkedList<Question> questions = (LinkedList<Question>) message.get(10);
						SubjectAndId subid;
						if (questions == null) {
							subid = new SubjectAndId(subjectTeacher1, id, teacher);
						} else {
							subid = new SubjectAndId(subjectTeacher1, id, teacher, questions);
							subid.setQuestions(questions);
						}
						Warning warning = new Warning("The Question added Successfully!!");
						client.sendToClient(warning);
						client.sendToClient(subid);
					}
				} catch (IOException e) {
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			} else if (message.get(0).equals("#GetSubject")) {
				int sub_id = (int) message.get(1);
				Teacher teacher = (Teacher) message.get(2);
				CourseTeacher courseTeacher = (CourseTeacher) message.get(3);
				SubjectTeacher subjectTeacher = Data.GetSubjectById(sub_id);
				GetSubject sub = new GetSubject(subjectTeacher, teacher,courseTeacher);
				try {
					client.sendToClient(sub);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}

			//BuildExam
			else if (message.get(0).equals("#BuildExam")) {
				try {
					System.out.println("I'm in server BuildExam");
					Teacher teacher = (Teacher) message.get(1);
					SubjectTeacher subject = (SubjectTeacher) message.get(2);
					CourseTeacher courseTeacher=(CourseTeacher) message.get(4);
					Exam exam = Data.setQuestions((int) message.get(3), (LinkedList<Question>) message.get(5));
					for (Question question : exam.getQuestions()) {
						//System.out.println(" im in the loop");
						System.out.println(question.getQuestion());
					}
					ExamSubjectTeacher examsubjectteacher = new ExamSubjectTeacher(teacher, subject, exam,courseTeacher);
					System.out.println("we made the class:");
					System.out.println(examsubjectteacher.getExam().getSubject());
					client.sendToClient(examsubjectteacher);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			//ShowExam
			else if (message.get(0).equals("#ShowExamm")) {
				try {
					ExamSubjectTeacher examsubjectteacher = (ExamSubjectTeacher) message.get(1);
					System.out.println(examsubjectteacher.getExam().getSubject());
					client.sendToClient(examsubjectteacher);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//EditExam
			else if (message.get(0).equals("#EditExam")) {
				try {
					ExamSubjectTeacherEdit examSubjectTeacherEdit = (ExamSubjectTeacherEdit) message.get(1);
					System.out.println("Im in EditExam in simpleserver");
					client.sendToClient(examSubjectTeacherEdit);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}


			//Edit_Q_Exam
			else if (message.get(0).equals("#Edit_Q_Exam")) {
				try {
					System.out.println("I'm in server Edit_Q_Exam");
					int flag = (Integer) message.get(2);
					Exam exFromClient = (Exam) message.get(3);
					CourseTeacher course = Data.findcourse(exFromClient.getCourse());
					Teacher teacher = (Teacher) message.get(5);
					SubjectTeacher subject = (SubjectTeacher) message.get(6);
					int id = (Integer) message.get(7);
					ExamSubjectTeacherEdit examSubjectTeacherEdit;
					int good=1;

					if(flag==3){
						System.out.println("Not selecting any the exam copy");
						Warning warning = new Warning("please select the exam copy!!");
						examSubjectTeacherEdit = new ExamSubjectTeacherEdit(teacher, subject, exFromClient,flag,course);
						client.sendToClient(warning);
						client.sendToClient(examSubjectTeacherEdit);
						good=0;
					}
					else if((Integer)message.get(1)==0){ // Add questions Button or delete questions Button errors
						good=0;
						if((Integer)message.get(9)==0){
							if (flag == 1 || flag == 2) { //save the exam copy
								flag = 2;
							} else{
								flag = 4;
							}
							int wrong =(Integer) message.get(10);
							if(wrong==1){
								System.out.println("deleting all questions from exam");
								Warning warning = new Warning("you can't delete all the questions from the exam!!" + "\n" +
										"you can try these solutions:" + "\n" +
										"1. keep at least one question in the exam" + "\n" +
										"2. delete the exam" + "\n" +
										"3. make a new exam");
								examSubjectTeacherEdit = new ExamSubjectTeacherEdit(teacher, subject, exFromClient,flag,course);
								client.sendToClient(warning);
								client.sendToClient(examSubjectTeacherEdit);
							}
							else if(wrong==2){
								System.out.println("Not selecting any question to delete");
								Warning warning = new Warning("you didn't select any question to delete");
								examSubjectTeacherEdit = new ExamSubjectTeacherEdit(teacher, subject, exFromClient,flag,course);
								client.sendToClient(warning);
								client.sendToClient(examSubjectTeacherEdit);
							}
							else if(wrong==3){
								System.out.println("Not selecting any question to add");
								Warning warning = new Warning("you didn't select any question to add");
								examSubjectTeacherEdit = new ExamSubjectTeacherEdit(teacher, subject, exFromClient,flag,course);
								client.sendToClient(warning);
								client.sendToClient(examSubjectTeacherEdit);
							}
						}
					}
					else if((Integer)message.get(1)==0){ //we are in saveall button error
						if (flag == 1 || flag == 2) { //save the exam copy
							flag = 2;
						} else {
							flag = 4;
						}
						if((Integer)message.get(13)==0){
							good=0;
							System.out.println("there is no changes");
							Warning warning = new Warning("you didn't change anything");
							examSubjectTeacherEdit = new ExamSubjectTeacherEdit(teacher, subject, exFromClient,flag,course);
							client.sendToClient(warning);
							client.sendToClient(examSubjectTeacherEdit);
						}
					}
					if(good==1){ // No problems
						Exam exam;
						if (flag == 1 || flag == 2) { //save the exam copy
							flag = 2;
						} else {
							flag = 4;
						}
						String TeacherNote = (String) message.get(10);
						String StudentNote = (String) message.get(11);
						int Time = (Integer) message.get(12);
						if(flag == 1) { //New Exam Copy
							id = Data.MakeExam(exFromClient.getNumOfQuestions(), TeacherNote,
									String.valueOf(Time), StudentNote, exFromClient.getCourse(),
									subject, exFromClient.getTeacher());
							exam = Data.setQuestions(id, (LinkedList<Question>) message.get(8));
						}
						else{ // same exam
							Data.updateExam(id,TeacherNote,StudentNote,Time);
							exam = Data.setQuestions(id, (LinkedList<Question>) message.get(8));
						}
						DecimalFormat formatter = new DecimalFormat("00");
						String cor_id = formatter.format(course.getId());//course
						String sub_id = formatter.format(subject.getId());
						Data.updateExamId(cor_id, id, sub_id);
						examSubjectTeacherEdit = new ExamSubjectTeacherEdit(teacher, subject, exam,flag,course);
						client.sendToClient(examSubjectTeacherEdit);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			else if (message.get(0).equals("SaveEditExam")){
				try {
					System.out.println("I'm in simpleserver Save Edits Exam");
					int flag = (Integer) message.get(1);
					Exam ex = (Exam) message.get(2);
					CourseTeacher course = Data.findcourse(ex.getCourse());
					Teacher teacher = (Teacher) message.get(4);
					SubjectTeacher subject = (SubjectTeacher) message.get(5);
					int id = (Integer) message.get(6);
					ExamSubjectTeacherEdit examSubjectTeacherEdit;
					if (flag == 3) {
						System.out.println("Not selecting any the exam copy");
						Warning warning = new Warning("please select the exam copy!!");
						examSubjectTeacherEdit = new ExamSubjectTeacherEdit(teacher, subject, ex, 3, course);
						client.sendToClient(warning);
						client.sendToClient(examSubjectTeacherEdit);
					}
					else if((Integer)message.get(10)==0){
						System.out.println("Not editing anything");
						Warning warning = new Warning("you didn't edit any of the time or notes!");
						examSubjectTeacherEdit = new ExamSubjectTeacherEdit(teacher, subject, ex, flag, course);
						client.sendToClient(warning);
						client.sendToClient(examSubjectTeacherEdit);
					} else {
						if (flag == 1 || flag == 2) { //save the exam copy
							flag = 2;
						} else {
							flag = 4;
						}
						String TeacherNote = (String) message.get(7);
						String StudentNote = (String) message.get(8);
						int Time = (Integer) message.get(9);
						Data.updateExam(ex.getId(),TeacherNote,StudentNote,Time);
						ex = Data.setQuestions(id, (LinkedList<Question>) message.get(11));
						course = Data.findcourse(ex.getCourse());
						examSubjectTeacherEdit = new ExamSubjectTeacherEdit(teacher, subject, ex, flag, course);
						client.sendToClient(examSubjectTeacherEdit);
					}
				} catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
}

//delete questions from Exam
//			else if (message.get(0).equals("#Edit_Q_Exam")) {
//				try {
//					System.out.println("I'm in server Edit_Q_Exam");
//					if(message.get(1)==null){
//						ExamSubjectTeacherEdit examSubjectTeacherEdit = (ExamSubjectTeacherEdit) message.get(3);
//						if(message.get(2)==null){
//							System.out.println("deleting all questions from exam");
//							Warning warning = new Warning("you can't delete all the questions from the exam!!" + "\n" +
//									"you can try these solutions:" + "\n" +
//									"1. keep at least one question in the exam" + "\n" +
//									"2. delete the exam" + "\n" +
//									"3. make a new exam");
//							client.sendToClient(warning);
//							client.sendToClient(examSubjectTeacherEdit);
//						}else{
//							System.out.println("Not selecting any question to add");
//							Warning warning = new Warning("you didn't select any question");
//							client.sendToClient(warning);
//							client.sendToClient(examSubjectTeacherEdit);
//						}
//					}
//					else {
//						Teacher teacher = (Teacher) message.get(1);
//						SubjectTeacher subject = (SubjectTeacher) message.get(2);
////						int id = (Integer) message.get(3);
//						Exam exFromClient;
////						if (message.get(4).getClass().equals(Exam.class)) {
//						exFromClient = (Exam) message.get(4);
////						} else {
////							exFromClient = Data.BringExamBasedOnCode(id);
////						}
//						CourseTeacher course = Data.findcourse(exFromClient.getCourse());
//						int id = Data.MakeExam(exFromClient.getNumOfQuestions(), exFromClient.getTeacherNotes(),
//								Integer.toString(exFromClient.getTimerr()), exFromClient.getStudentNotes(), exFromClient.getCourse(),
//								subject, exFromClient.getTeacher());
//						Exam exam = Data.setQuestions(id, (LinkedList<Question>) message.get(5));
//						DecimalFormat formatter = new DecimalFormat("00");
//						String cor_id = formatter.format(course.getId());
//						String sub_id = formatter.format(subject.getId());
//						Data.updateExamId(cor_id, id, sub_id);
////						for (Question question : exam.getQuestions()) {
////							System.out.println(question.getQuestion());
////						}
//						ExamSubjectTeacherEdit examSubjectTeacherEdit = new ExamSubjectTeacherEdit(teacher,subject,exam);
//								//ExamSubjectTeacher examsubjectteacher = new ExamSubjectTeacher(teacher, subject, exam);
//						System.out.println("we made the class:");
//					}
//
//							System.out.println(examsubjectteacher.getExam().getSubject());
//							client.sendToClient(examsubjectteacher);
//							client.sendToClient(examSubjectTeacherEdit);
//						}
//					}} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//					System.out.println("I'm in server Edit_Q_Exam");
//					System.out.println("I'm in server Edit_Q_Exam");
//					if(message.get(1)==null){
//						System.out.println("deleting all questions from exam");
//						Warning warning = new Warning("you can't delete all the questions from the exam!!" + "\n" +
//								"you can try these solutions:" + "\n" +
//								"1. keep at least one question in the exam" + "\n" +
//								"2. delete the exam" + "\n" +
//								"3. make a new exam");
//						client.sendToClient(warning);
//					}
//					else {
//						Teacher teacher = (Teacher) message.get(1);
//						SubjectTeacher subject = (SubjectTeacher) message.get(2);
//						int id = (Integer) message.get(3);
//
//						Exam exFromClient;
//						if (message.get(4).getClass().equals(Exam.class)) {
//							exFromClient = (Exam) message.get(4);
//						} else {
//							exFromClient = Data.BringExamBasedOnCode(id);
//						}
//						CourseTeacher course = Data.findcourse(exFromClient.getCourse());
//						id = Data.MakeExam(exFromClient.getNumOfQuestions(), exFromClient.getTeacherNotes(),
//								Integer.toString(exFromClient.getTimerr()), exFromClient.getStudentNotes(), exFromClient.getCourse(),
//								subject, exFromClient.getTeacher());
//						Exam exam = Data.setQuestions(id, (LinkedList<Question>) message.get(5));
//						DecimalFormat formatter = new DecimalFormat("00");
//						String cor_id = formatter.format(course.getId());
//						String sub_id = formatter.format(subject.getId());
//						Data.updateExamId(cor_id, id, sub_id);
//						ExamSubjectTeacher examsubjectteacher = new ExamSubjectTeacher(teacher, subject, exam);
//						System.out.println("we made the class:");
//						System.out.println(examsubjectteacher.getExam().getSubject());
//						client.sendToClient(examsubjectteacher);
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//			}
//			else if (message.get(0).equals("#Edit_Q_Exam_New")) {
//				try {
//					if(message.add(3)==null){
//						String ques1 = (String) message.get(2);
//						String ans1 = (String) message.get(4);
//						String ans2 = (String) message.get(5);
//						String ans3 = (String) message.get(6);
//						String ans4 = (String) message.get(7);
//						String right = (String) message.get(8);
//						int id = (int)  message.get(9);
//						Teacher teacher = (Teacher) message.get(10);
//						ExamSubjectTeacherEdit examSubjectTeacherEdit = (ExamSubjectTeacherEdit) message.get(11);
//						if(ques1.isEmpty()){
//							if(ans1.isEmpty() || ans2.isEmpty() || ans3.isEmpty() || ans4.isEmpty()){
//								System.out.println("there is no question or 4 possible answers yet");
//								Warning warning = new Warning("please write the question and 4 possible answers!!");
//								client.sendToClient(warning);
//							}else{
//								System.out.println("there is no question yet");
//								Warning warning = new Warning("please write the question!!");
//								client.sendToClient(warning);
//							}
//						} else if(ans1.isEmpty() || ans2.isEmpty() || ans3.isEmpty() || ans4.isEmpty()){
//							System.out.println("there is no 4 possible answers yet");
//							Warning warning = new Warning("please write 4 possible answers!!");
//							client.sendToClient(warning);
//						} else if(right.isEmpty()){
//							System.out.println("the right answer is not selected yet");
//							Warning warning = new Warning("please write the right answer!!");
//							client.sendToClient(warning);
//						} else if (ans1.equals(ans2) || ans1.equals(ans3) || ans1.equals(ans4) || ans2.equals(ans3) || ans2.equals(ans4) || ans3.equals(ans4)) {
//							System.out.println("there are duplicated answers");
//							Warning warning = new Warning("there are duplicated answers, please write 4 different answers!!");
//							client.sendToClient(warning);
//						} else if (!right.equals(ans1) && !right.equals(ans2) && !right.equals(ans3) && !right.equals(ans4) ) {
//							System.out.println("the right answer is not from the 4 answers");
//							Warning warning = new Warning("please pick the right answer from the 4 possible answers!!");
//							client.sendToClient(warning);
//						} else if (ques1.equals(ans1) || ques1.equals(ans2) || ques1.equals(ans3) || ques1.equals(ans4) ) {
//							System.out.println("the question is in the answers too");
//							Warning warning = new Warning("please don't write the question the same as the answer!!");
//							client.sendToClient(warning);
//						} else { //the input is good
//							SubjectTeacher subjectTeacher = (SubjectTeacher) message.get(1);
//							System.out.println("before question make data");
//							SubjectTeacher subjectTeacher1 = Data.MakeQuestion(ques1, ans1, ans2, ans3, ans4, right ,subjectTeacher);
//							System.out.println("Afte question make data");
//							//SubjectAndId subid =new SubjectAndId(subjectTeacher1,id,teacher);
//							LinkedList<Question> questions = (LinkedList<Question>) message.get(10);
//							ExamSubjectTeacherEdit examSubjectTeacherEdit=(ExamSubjectTeacherEdit)message.get(11);
//							SubjectAndId subid = new SubjectAndId(subjectTeacher1,id,teacher);;
//							Warning warning = new Warning("The Question added Successfully!!");
//						}
//						else{
//							System.out.println("I'm in server Edit_Q_Exam");
//							System.out.println("I'm in server Edit_Q_Exam");
//							if(message.get(1)==null){
//								ExamSubjectTeacherEdit examSubjectTeacherEdit = (ExamSubjectTeacherEdit) message.get(3);
//								if(message.get(2)==null){
//									System.out.println("deleting all questions from exam");
//									Warning warning = new Warning("you can't delete all the questions from the exam!!" + "\n" +
//											"you can try these solutions:" + "\n" +
//											"1. keep at least one question in the exam" + "\n" +
//											"2. delete the exam" + "\n" +
//											"3. make a new exam");
//									client.sendToClient(warning);
//									client.sendToClient(examSubjectTeacherEdit);
//								}else{
//									System.out.println("Not selecting any question to add");
//									Warning warning = new Warning("you didn't select any question");
//									client.sendToClient(warning);
//									client.sendToClient(examSubjectTeacherEdit);
//								}
//							}
//							else {
//								Teacher teacher = (Teacher) message.get(1);
//								SubjectTeacher subject = (SubjectTeacher) message.get(2);
////						int id = (Integer) message.get(3);
//								Exam exFromClient;
////						if (message.get(4).getClass().equals(Exam.class)) {
//								exFromClient = (Exam) message.get(4);
////						} else {
////							exFromClient = Data.BringExamBasedOnCode(id);
////						}
//								CourseTeacher course = Data.findcourse(exFromClient.getCourse());
//								int id = Data.MakeExam(exFromClient.getNumOfQuestions(), exFromClient.getTeacherNotes(),
//										Integer.toString(exFromClient.getTimerr()), exFromClient.getStudentNotes(), exFromClient.getCourse(),
//										subject, exFromClient.getTeacher());
//								Exam exam = Data.setQuestions(id, (LinkedList<Question>) message.get(5));
//								DecimalFormat formatter = new DecimalFormat("00");
//								String cor_id = formatter.format(course.getId());
//								String sub_id = formatter.format(subject.getId());
//								Data.updateExamId(cor_id, id, sub_id);
////						for (Question question : exam.getQuestions()) {
////							System.out.println(question.getQuestion());
////						}
//								ExamSubjectTeacherEdit examSubjectTeacherEdit = new ExamSubjectTeacherEdit(teacher,subject,exam);
//								//ExamSubjectTeacher examsubjectteacher = new ExamSubjectTeacher(teacher, subject, exam);
//								System.out.println("we made the class:");
//							}
//
//							//System.out.println(examsubjectteacher.getExam().getSubject());
//							//client.sendToClient(examsubjectteacher);
//							client.sendToClient(examSubjectTeacherEdit);
//						}
//					}} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}

            /*
									//BuildExam
			else if (message.get(0).equals("#BuildExam")) {
				try {
					System.out.println("I'm in server BuildExam");
					Teacher teacher = (Teacher) message.get(1);//
					SubjectTeacher subject = (SubjectTeacher) message.get(2);//
					Exam exam = Data.setQuestions((int)message.get(3),(LinkedList<Question>) message.get(4));
					for(Question question: exam.getQuestions()){
						//System.out.println(" im in the loop");
						System.out.println(question.getQuestion());
					}
					//SimpleClient.getParams().add(message);
					client.sendToClient(teacher);
					client.sendToClient(subject);
					client.sendToClient(exam);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			 */
            /*else if (message.get(0).equals("#MakeExam")) {
				try {
					System.out.println("in make exam ");
					String num = (String) message.get(1);
					String t_N = (String) message.get(2);
					String timm = (String) message.get(3);
					String S_N = (String) message.get(4);
					CourseTeacher course = (CourseTeacher) message.get(5);
					SubjectTeacher sub = (SubjectTeacher) message.get(6);
					String teachername = (String) message.get(7);
					Teacher teacher = (Teacher) message.get(8);

					if (timm.isEmpty()) {
						if (num.isEmpty()) {
							System.out.println("there is no time or number of questions filled yet");
							Warning warning = new Warning("please fill the informations!!");
							client.sendToClient(warning);
						} else {
							System.out.println("the user did not fill the time");
							Warning warning = new Warning("please fill the time!!");
							client.sendToClient(warning);
						}
					} else if (num.isEmpty()) {
						System.out.println("the user did not fill the number of questions");
						Warning warning = new Warning("please fill the number of questions!!");
						client.sendToClient(warning);
					} else {
						boolean result1 = timm.matches("[0-9]+");
						boolean result2 = num.matches("[0-9]+");
						if (result1 == false && result2 == false) {
							System.out.println("ellegal time and number of questions");
							Warning warning = new Warning("please fill a legal time and a legal number of questions!!");
							client.sendToClient(warning);
						} else if (result1 == false) {
							System.out.println("ellegal time");
							Warning warning = new Warning("please fill a legal time!!");
							client.sendToClient(warning);

						} else if (result2 == false) {
							System.out.println("ellegal num of questions");
							Warning warning = new Warning("please fill a legal number of questions!!");
							client.sendToClient(warning);
						} else { //the input is valid
							int num_q = Integer.valueOf(num);
							int exam_id = Data.MakeExam(num_q, t_N, timm, S_N, course.getName(), sub, teachername); //this will return the id of the new exam
							System.out.println("after data find");
							System.out.println(course.getName());            //ex. english esraa
							DecimalFormat formatter = new DecimalFormat("00");
							String cor_id = formatter.format(course.getId());
							String sub_id = formatter.format(sub.getId());
							Data.updateExamId(cor_id, exam_id, sub_id);
							SubjectAndId subId = new SubjectAndId(sub, exam_id,teacher);
							client.sendToClient(subId);
						}
					}
				} catch (IOException e) {

				} catch (Exception e) {
					throw new RuntimeException(e);
			}*/

