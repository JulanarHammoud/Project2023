package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.server.DataControl.Data;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;


public class SimpleServer extends AbstractServer {
	ConnectionToClient mngr = null;
	private Map<Integer, ConnectionToClient> onlineTeachers = new HashMap<>();
	private Map<Integer, List<ConnectionToClient>> studentsInExam = new HashMap<>();


	public SimpleServer(int port) {
		super(port);
		try {
			Data.LogOutSt(1);
			List<Student> s = Data.getAllStudents();
			int i = 0;
			int j = 0;
			while (i < s.size()) {
				j = s.get(i).getId();
				Data.LogOutSt(j);
				i++;
			}
			List<Teacher> t = Data.getAllTeachers();
			int i2 = 0;
			int j2 = 0;
			while (i2 < t.size()) {
				j2 = t.get(i2).getId();
				Data.LogOutTeacher(j2);
				i2++;
			}
//			SubjectTeacher grammar=  Data.findsubject("Grammar");
//			Data.MakeQuestion("aaa","bbb","ccc","ddd","ddd","ttt",grammar);
			//Data.LogOutSt(1);
			//Data.LogOutSt(4);
//			Data.generateSubject();
			//////////Data.generateStusent();
			//Data.generateEnglishQusetions();

			//Data.main(null);
			//System.out.println("why there is exeption");
			//Data.updatePrice(500,1);

		} catch (Exception e) {
			System.out.print("there is an error");
			e.printStackTrace();
		}
		System.out.println("Server Is Ready...");
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
			System.out.println("No Question is choosed");
			try {
				client.sendToClient(warning);
				System.out.format("Sent warning to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#ListStudents")) {
			try {
				System.out.print("Sent list of all students to the client");
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
					System.out.println("Sent info of the student to the client");
					int id = (int) message.get(1);
					StudentInfo student = new StudentInfo(Data.getStudent(id));
					client.sendToClient(student);

				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (message.get(0).equals("#UpdateGrade")) {
			}
			///////////////////////////////////////////////////////
			else if (message.get(0).equals("#Login")) {
				System.out.println("im in login ");
				try {
					System.out.println("in try login");
					if (message.get(2).equals("")  && message.get(1).equals("")){
						System.out.println("nothing is filled");
						Warning warning = new Warning("please fill the informations!!");
						client.sendToClient(warning);
					} else if (message.get(1).equals("")) {
						if (message.get(2).equals("")) {
							System.out.println("there is no username or password ");
							Warning warning = new Warning("please fill the username and password!!");
							client.sendToClient(warning);
						} else {
							System.out.println("the user did not fill the username");
							Warning warning = new Warning("please fill the username!!");
							client.sendToClient(warning);
						}
					}
					else if(!message.get(1).equals("") && message.get(2).equals("")){
						Warning warning = new Warning("please fill the password!!");
						client.sendToClient(warning);
					}
					/*else if (message.get(2).equals("")) {
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
							onlineTeachers.put(teacherlog.getId(),client);
							Data.activateTeacher(teacherlog.getId());
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
						} else if (studentlog.getFirstName().equals("wrongstudentpassword")) {
							System.out.println("wrong password to this teacher's name ");
							Warning warning = new Warning("wrong password, please try again!!");
							client.sendToClient(warning);
						} else if (studentlog.getActive() == true) {
							Warning warning = new Warning("you are already in");
							client.sendToClient(warning);
						} else {
							Data.activateSt(studentlog.getId());
							client.sendToClient(studentlog);
						}
					}*/
					System.out.println("checkkkkkkk");
					String username = (String) message.get(1);
					int  id = (int )username.charAt(0);
					id = id-48;
					System.out.println(id);
					Student student = Data.getDataById(Student.class,id);
					//if(student==null){System.out.println(" The user is student "); }
					Teacher teacher = Data.getDataById(Teacher.class,id);
					//if(teacher==null){System.out.println(" The user is teacher "); }
					if(student!=null && student.getUserName().equals(username)){
						System.out.println("the user is a student ");
						Student studentlog = Data.StudentLog((String) message.get(1), (String) message.get(2),student);
						System.out.println("the username is " + (String) message.get(1));
						System.out.println(studentlog.getFirstName());
						if (studentlog.getFirstName() == null) {
							System.out.println("the user is not in the database ");
							Warning warning = new Warning("there is no student with this name, please try again!!");
							client.sendToClient(warning);
						} else if (studentlog.getFirstName().equals("wrongstudentpassword")) {
							System.out.println("wrong password to this teacher's name ");
							Warning warning = new Warning("wrong password, please try again!!");
							client.sendToClient(warning);
						} else if (studentlog.getActive() == true) {
							Warning warning = new Warning("you are already in");
							client.sendToClient(warning);
						} else {
							Data.activateSt(studentlog.getId());
							client.sendToClient(studentlog);
						}
					}
					else if (teacher!=null && teacher.getUserName().equals(username)){
						System.out.println("the user is a teacher ");
						Teacher teacherlog = Data.TeacherLog((String) message.get(1), (String) message.get(2),teacher);
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
							onlineTeachers.put(teacherlog.getId(),client);
							Data.activateTeacher(teacherlog.getId());
							client.sendToClient(teacherlog);
						}
					}
					else if (teacher!=null && !(teacher.getUserName().equals(username))){
						Warning warning = new Warning("There is no user with this name, please try again!!");
						client.sendToClient(warning);
					}
					else if (student!=null && !(student.getUserName().equals(username))){
						Warning warning = new Warning("There is no user with this name, please try again!!");
						client.sendToClient(warning);
					}
					else if (teacher==null && student==null){
						Warning warning = new Warning("There is no user with this name, please try again!!");
						client.sendToClient(warning);
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}///////////////////////////////////////////

			else if (message.get(0).equals("#LogOut")) {
				System.out.println("are you in the log out?");
				LogOut logOut = new LogOut("success");
				String n = (String) message.get(2);
				try {
					if (n.equals("mangerlogout")) {
						mngr.setInfo("Maill", 0);//we are not in the mail manager
						System.out.println("the id of the user is: " + (int) message.get(1));
						mngr = null;
					} else if ("teacher".equals(n)) {
						onlineTeachers.remove((int) message.get(1));
						Data.LogOutTeacher((int) message.get(1));
					} else {
						Data.LogOutSt((int) message.get(1));
						System.out.println(".");
					}

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
			}//////////////////////////////////////////////////////////
			else if (message.get(0).equals("ShowQuestionn")) {
				try {
					int origin = (Integer) message.get(1);
					if (origin == 0) {
						System.out.println("No Question is choosed");
						Warning warning = new Warning("you didn't choose any question!!");
						client.sendToClient(warning);
					} else {
						Warning warning = new Warning("you didn't choose any question!!");
						client.sendToClient(warning);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (message.get(0).equals("#MakeExam")) {
				try {
					System.out.println("Make Exam Process ");
					String t_N = (String) message.get(1);
					String timm = (String) message.get(2);
					String S_N = (String) message.get(3);
					CourseTeacher course = (CourseTeacher) message.get(4);
					SubjectTeacher sub = (SubjectTeacher) message.get(5);
					String teacherr = (String) message.get(6);
					Teacher teacher = (Teacher) message.get(7);
					if (timm.isEmpty()) {
						System.out.println("the user did not fill the time");
						Warning warning = new Warning("please fill the time!!");
						client.sendToClient(warning);
					} else {
						boolean result1 = timm.matches("[0-9]+");
						if (result1 == false) {
							System.out.println("ellegal time");
							Warning warning = new Warning("please fill a legal time!!");
							client.sendToClient(warning);
						} else {System.out.println("1");
							System.out.println(t_N+"" +timm+ ""+S_N+""+ course.getName()+""+ sub.getSb_name()+""+ teacherr);
							int id = Data.MakeExam(0, t_N, timm, S_N, course.getName(), sub, teacherr);System.out.println("2");
							DecimalFormat formatter = new DecimalFormat("00");System.out.println("3");
							String cor_id = formatter.format(course.getId());System.out.println("4");
							String sub_id = formatter.format(sub.getId());System.out.println("5");
							Data.updateExamId(cor_id, id, sub_id); System.out.println("6");
							SubjectAndId subId = new SubjectAndId(sub, id, teacher, course);System.out.println("7");
							client.sendToClient(subId);
						}
					}
				} catch (IOException e) {

				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			} else if (message.get(0).equals("#GoToExStudentA")) {            //israaa
				try {

					Student std= (Student) message.get(1);
					//Student studentFull=Data.getStudent(Integer.parseInt(stt));
					Student student = Data.getDataById(Student.class , std.getId());
					StudentWillDoEx studentWillDo =new StudentWillDoEx(student);
					System.out.println(""+studentWillDo.getStudent().getCourses().size()+";;");

					client.sendToClient(studentWillDo);
				}catch (Exception e) {
					e.printStackTrace();
				}

			} else if (message.get(0).equals("#GoToExStudentBUTTON")){
				try {
					String code= (String) message.get(1);
					String stt= (String) message.get(2);
					ExamStudent exam = (ExamStudent) message.get(3);
					Student studentFull=Data.getStudent(Integer.parseInt(stt));
					StudentWillMakeEx StEx=new StudentWillMakeEx();
					StEx.setSs(studentFull);
					StEx.setEx(exam);
					//List<ExamStudent> t=studentFull.getStudentExams();

//						ExamStudent x=new ExamStudent();
//						System.out.println("1");
//						int i=0;
//						while(i<t.size()){
//							System.out.println("2"+i+t.size());
//							x=t.get(i);
//							System.out.println("3"+x.getCodeGivenByTeacher()+"3"+code);
//							if(code.equals(x.getCodeGivenByTeacher())) {
//								System.out.println("4");
//								i=t.size();
//								System.out.println("5"+i+t.size());
//							} else{
//								System.out.println("6");
//								i++;
//							}
//						}
//						StEx.setEx(x);
					client.sendToClient(StEx);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (message.get(0).equals("#GoToExStudentAnswers")) {
				try {
					System.out.println("I'm in server ");
					ExamStudent ex= (ExamStudent) message.get(1);
					GradeSt gradeSt=(GradeSt) message.get(2);
					//System.out.println("I'm in server "+ex.getGrade()+ex.getQuestions().get(0).getThe_student_ans());
					System.out.println("Sent exam's student to the client ");
					ExamStudentsandGrade exgr=new ExamStudentsandGrade(ex,gradeSt);
					client.sendToClient(exgr);

				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (message.get(0).equals("#GradesStudent")) {
				try {
					String st= (String) message.get(1);
					Student studentFul=Data.getStudent(Integer.parseInt(st));
					GradeSt h=new GradeSt(studentFul);
					System.out.println("Sent grade's student to the client ");
					client.sendToClient(h);

				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			} else if (message.get(0).equals("#CoursetTeacher")) {
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
			} else if (message.get(0).equals("editquestion")) {
				try {
					Question question = (Question) message.get(1);
					Question oldquestion = (Question) message.get(5);
					int flag = (Integer) message.get(6);
					System.out.println("in edit question ");
					System.out.println(question.getQuestion());
					String ques1 = question.getQuestion();
					String ans1 = question.getAns1();
					String ans2 = question.getAns2();
					String ans3 = question.getAns3();
					String ans4 = question.getAns4();
					String right = question.getThe_right_ans();
					String note = question.getNote();
					int id = Data.returnid(oldquestion.getQuestion());
					SubjectTeacher subjectteacher = (SubjectTeacher) message.get(3);
					System.out.println("before data function");
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
					} else {
						Question newq= new Question(ques1, ans1, ans2, ans3, ans4, note, right);
						LinkedList nulllinked = new LinkedList();
						nulllinked = null;
						Data.MakeQuestion(ques1,ans1,ans2,ans3,ans4,note,right,subjectteacher,nulllinked);
						//Data.updateQuestion(id, ques1, ans1, ans2, ans3, ans4, note, right);
						System.out.println("after data function");
						Warning warning = new Warning("The Question updated Successfully!!");
						SubjectTeacher subject = (SubjectTeacher) message.get(3);
						SubjectTeacher newsubject = Data.GetSubjectById(subject.getId());
						SubjectAndId subid;
						Teacher teacher = (Teacher) message.get(4);
						if (flag == 0) {
							subid = new SubjectAndId(newsubject, -1, teacher);
						} else {
							subid = new SubjectAndId(newsubject, (Integer) message.get(2), teacher);
						}
						client.sendToClient(warning);
						client.sendToClient(subid);
					}
				} catch (IOException e) {

				} catch (Exception e) {
					throw new RuntimeException(e);
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
					String note = (String) message.get(8);
					int id = (int) message.get(9);
					Teacher teacher = (Teacher) message.get(10);
					//int x=0;
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
						LinkedList<SubjectTeacher> subjects = (LinkedList<SubjectTeacher>) message.get(13);
						SubjectTeacher subjectTeacher = (SubjectTeacher) message.get(1);
						SubjectTeacher subjectTeacher1 = Data.MakeQuestion(ques1, ans1, ans2, ans3, ans4, right, note, subjectTeacher,subjects);
						LinkedList<Question> questions = (LinkedList<Question>) message.get(11);
						SubjectAndId subid;
						teacher=Data.getDataById(Teacher.class,teacher.getId());
						if (questions == null) { // make new question coming from question table
							subid = new SubjectAndId(subjectTeacher1, id, teacher);
						} else { // make new question coming from make exam
							subid = new SubjectAndId(subjectTeacher1, id, teacher, questions);
							subid.setQuestions(questions);
						}
						Warning warning = new Warning("The Question added Successfully!!");
						if ((Integer) message.get(12) == 1) { //we are in edit exam page
							int flag = (Integer) message.get(14);
							subid.setQuestions(questions);
							id = subid.getId();
							Exam exam = Data.findExam(id);
							CourseTeacher course = Data.FindCourse(exam.getCourse());
							SubjectTeacher subject1 = Data.GetSubjectById(subjectTeacher.getId());
							ExamSubjectTeacherEdit examSubjectTeacherEdit = new ExamSubjectTeacherEdit(teacher, subject1, exam, flag, course);
							client.sendToClient(warning);
							client.sendToClient(examSubjectTeacherEdit);
						} else { //we are in make new question page
							client.sendToClient(warning);
							client.sendToClient(subid);
						}
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
			} else if (message.get(0).equals("#GetSubject")) {
				int sub_id = (int) message.get(1);
				Teacher teacher = (Teacher) message.get(2);
				CourseTeacher courseTeacher = (CourseTeacher) message.get(3);
				SubjectTeacher subjectTeacher = Data.GetSubjectById(sub_id);
				GetSubject sub = new GetSubject(subjectTeacher, teacher, courseTeacher);
				System.out.println("Sent student's subject to the client ");
				try {
					client.sendToClient(sub);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}else if (message.get(0).equals("#BuildExam")) {
				try {
					System.out.println("I'm in server BuildExam");
					Teacher teacher = (Teacher) message.get(1);
					SubjectTeacher subject = (SubjectTeacher) message.get(2);
					CourseTeacher courseTeacher = (CourseTeacher) message.get(4);
					int questionNumber = (Integer) message.get(5);
					Exam exam = Data.setQuestions((int) message.get(3), (LinkedList<Question>) message.get(6));
					for (Question question : exam.getQuestions()) {
						System.out.println(question.getQuestion());
					}
					Data.setNumberOfQuestions(questionNumber, exam.getId());
					ExamSubjectTeacher examsubjectteacher = new ExamSubjectTeacher(teacher, subject, exam, courseTeacher);
					System.out.println("we made the class:");
					System.out.println(examsubjectteacher.getExam().getSubject());
					System.out.println("Sent exam's subject to the client ");
					client.sendToClient(examsubjectteacher);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (message.get(0).equals("#ShowExam")) {
				try {
					if(message.get(1)==null){
						System.out.println("Not selecting any the exam");
						Warning warning = new Warning("please select a exam!!");
						client.sendToClient(warning);
					} else{
						System.out.println("client ask to show exam");
						ExamSubjectTeacher examsubjectteacher = (ExamSubjectTeacher) message.get(1);
						client.sendToClient(examsubjectteacher);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (message.get(0).equals("DeleteExam")) {
				try {
					if (message.get(1) == null) {
						System.out.println("Not selecting any exam");
						Warning warning = new Warning("please select a exam!!");
						client.sendToClient(warning);
					} else {
						SubjectTeacher subjectTeacher = (SubjectTeacher) message.get(1);
						Teacher teacher = (Teacher) message.get(2);
						CourseTeacher courseTeacher = (CourseTeacher) message.get(3);
						int id = (Integer) message.get(4);
						Data.deleteExamSub(id, subjectTeacher);
						Data.deleteExam(id);
						SubjectTeacher updatedSub = Data.GetSubjectById(subjectTeacher.getId());
						GetSubject getSubject = new GetSubject(updatedSub, teacher, courseTeacher);
						System.out.println("Sent subject to the client ");
						client.sendToClient(getSubject);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (message.get(0).equals("#EditExam")) {
				try {
					if (message.get(1) == null) {
						System.out.println("Not selecting any the exam");
						Warning warning = new Warning("please select a exam!!");
						client.sendToClient(warning);
					} else {
						ExamSubjectTeacherEdit examSubjectTeacherEdit = (ExamSubjectTeacherEdit) message.get(1);
						String name=examSubjectTeacherEdit.getTeacher().getFirstName() + " " +examSubjectTeacherEdit.getTeacher().getLastName();
						System.out.println(name);
						System.out.println(examSubjectTeacherEdit.getExam().getTeacher());
						if(!examSubjectTeacherEdit.getExam().getTeacher().equals(name)){
							examSubjectTeacherEdit.setFlag(10);
						}
						System.out.println("Im in EditExam in simpleserver");
						System.out.println("Sent the exam to the client to edit it");
						client.sendToClient(examSubjectTeacherEdit);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (message.get(0).equals("#Edit_Q_Exam")) {
				try {
					System.out.println("I'm in server Edit_Q_Exam");
					int flag = (Integer) message.get(2);
					Exam exFromClient = (Exam) message.get(3);
					CourseTeacher course = Data.findcourse(exFromClient.getCourse());
					Teacher teacher = (Teacher) message.get(5);
					SubjectTeacher subject = (SubjectTeacher) message.get(6);
					int id = (Integer) message.get(7);
					ExamSubjectTeacherEdit examSubjectTeacherEdit;
					int good = 1;
					if (flag == 3) {
						System.out.println("Not selecting any the exam copy");
						Warning warning = new Warning("please select the exam copy!!");
						client.sendToClient(warning);
						good = 0;
					} else if ((Integer) message.get(1) == 0) { // Add questions Button or delete questions Button errors
						good = 1;
						if ((Integer) message.get(9) == 0) {
							good = 0;
							if (flag == 1 || flag == 2) { //save the exam copy
								flag = 2;
							} else {
								flag = 4;
							}
							int wrong = (Integer) message.get(10);
							if (wrong == 1) {
								System.out.println("deleting all questions from exam");
								Warning warning = new Warning("you can't delete all the questions from the exam!!" + "\n" +
										"you can try these solutions:" + "\n" +
										"1. keep at least one question in the exam" + "\n" +
										"2. delete the exam" + "\n" +
										"3. make a new exam");
								examSubjectTeacherEdit = new ExamSubjectTeacherEdit(teacher, subject, exFromClient, flag, course);
								client.sendToClient(warning);
								client.sendToClient(examSubjectTeacherEdit);
							} else if (wrong == 2) {
								System.out.println("Not selecting any question to delete");
								Warning warning = new Warning("you didn't select any question to delete");
								client.sendToClient(warning);
							} else if (wrong == 3) {
								System.out.println("Not selecting any question to add");
								Warning warning = new Warning("you didn't select any question to add");
								client.sendToClient(warning);
							}
						}
					} else if ((Integer) message.get(1) == 1) { //we are in saveall button error
						good = 1;
						if ((Integer) message.get(13) == 0) { // no changes have been done
							if (flag == 1 || flag == 2) { //save the exam copy
								flag = 2;
							} else {
								flag = 4;
							}
							good = 0;
							System.out.println("there is no changes");
							Warning warning = new Warning("you didn't change anything");
							client.sendToClient(warning);
						} else if ((Integer) message.get(14) == 0) { // deleting all questions without adding any
							if (flag == 1 || flag == 2) { //save the exam copy
								flag = 2;
							} else {
								flag = 4;
							}
							good = 0;
							System.out.println("deleting all questions from exam without adding any question");
							Warning warning = new Warning("you can't delete all the questions from the exam!!" + "\n" +
									"you can try these solutions:" + "\n" +
									"1. keep at least one question in the exam" + "\n" +
									"2. delete the exam" + "\n" +
									"3. make a new exam");
							client.sendToClient(warning);
						}
					}
					if (good == 1) { // No problems
						Exam exam;
						String TeacherNote = (String) message.get(10);
						String StudentNote = (String) message.get(11);
						String Time = (String) message.get(12);
						boolean result1 = Time.matches("[0-9]+");
						if (result1 == false) {
							System.out.println("ellegal time");
							Warning warning = new Warning("please fill a legal time!!");
							client.sendToClient(warning);
						} else {
							if (flag == 1) { //New Exam Copy
								String name = teacher.getFirstName() + " " + teacher.getLastName();
								id = Data.MakeExam(exFromClient.getNumOfQuestions(), TeacherNote,
										Time, StudentNote, exFromClient.getCourse(),
										subject, name);
								exam = Data.setQuestions(id, (LinkedList<Question>) message.get(8));

								DecimalFormat formatter = new DecimalFormat("00");
								String cor_id = formatter.format(course.getId());//course
								String sub_id = formatter.format(subject.getId());
								Data.updateExamId(cor_id, id, sub_id);
							} else { // same exam
								Data.setNumberOfQuestions(exFromClient.getNumOfQuestions(), id);
								Data.updateExam(id, TeacherNote, StudentNote, Integer.valueOf(Time));
								exam = Data.setQuestions(id, (LinkedList<Question>) message.get(8));
							}
							if (flag == 1 || flag == 2) { //save the exam copy
								flag = 2;
							} else {
								flag = 4;
							}
							examSubjectTeacherEdit = new ExamSubjectTeacherEdit(teacher, subject, exam, flag, course);
							client.sendToClient(examSubjectTeacherEdit);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (message.get(0).equals("SaveEditExam")) {
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
						client.sendToClient(warning);
					} else if ((Integer) message.get(10) == 0) {
						System.out.println("Not editing anything");
						Warning warning = new Warning("you didn't edit any of the time or notes!");
						client.sendToClient(warning);
					} else { // no problems
						String TeacherNote = (String) message.get(7);
						String StudentNote = (String) message.get(8);
						String Time = (String) message.get(9);
						boolean result1 = Time.matches("[0-9]+");
						if (result1 == false) {
							System.out.println("ellegal time");
							Warning warning = new Warning("please fill a legal time!!");
							client.sendToClient(warning);
						} else {
							if (flag == 1) { //New Exam Copy
								id = Data.MakeExam(ex.getNumOfQuestions(), TeacherNote,
										Time, StudentNote, ex.getCourse(),
										subject, ex.getTeacher());
								ex = Data.setQuestions(id, (LinkedList<Question>) message.get(11));
								DecimalFormat formatter = new DecimalFormat("00");
								String cor_id = formatter.format(course.getId());//course
								String sub_id = formatter.format(subject.getId());
								Data.updateExamId(cor_id, id, sub_id);
							} else {
								Data.updateExam(ex.getId(), TeacherNote, StudentNote, Integer.valueOf(Time));
								ex = Data.findExam(id);
							}
							SubjectTeacher subject1 = Data.GetSubjectById(subject.getId());
							course = Data.findcourse(ex.getCourse());
							if (flag == 1 || flag == 2) { //save the exam copy
								flag = 2;
							} else {
								flag = 4;
							}
							examSubjectTeacherEdit = new ExamSubjectTeacherEdit(teacher, subject1, ex, flag, course);
							client.sendToClient(examSubjectTeacherEdit);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (message.get(0).equals("#GetAllSubjectsSimpleServer")) {
				try {
					mngr = client;
					mngr.setInfo("Maill",0);//we are not in the mail manager
					List<Teacher> teachers = (List<Teacher>) Data.getAllTeachers();
					List<Student> students = Data.getAllStudents();
					List<CourseTeacher> courses = Data.getAllCourses();
					GetForManager getForManager = new GetForManager(teachers, students, courses);
					client.sendToClient(getForManager);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			} else if (message.get(0).equals("#ShowExamManager")) {
				try {
					mngr.setInfo("Maill",0);//we are not in the mail manager
					System.out.println("Not selecting any the exam");
					Warning warning = new Warning("please select a exam!!");
					client.sendToClient(warning);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (message.get(0).equals("SendMassage")) {
				Teacher teacher = (Teacher) message.get(1);
				ExamTeacher examteacher = (ExamTeacher) message.get(2);
				int time = (Integer) message.get(3);
				String exp = (String) message.get(4);
				Data.GenerateMessage(time, exp, teacher.getId(), examteacher.getId());
				Warning warning = new Warning("Message Added Successfully");
				try {
					if (mngr != null) {
						if(mngr.getInfo("Maill").toString().equals("1")) // if the manager is in the maill page, refresh it
						{
							List<Teacher> teachers = (List<Teacher>) Data.getAllTeachers();
							List<Student> students = Data.getAllStudents();
							List<CourseTeacher> courses = Data.getAllCourses();
							GetForManager getForManager = new GetForManager(teachers, students, courses);
							List<ManagerMessage> M = Data.getAllMessages();
							MailManagerEntity mailManagerEntity = new MailManagerEntity(M, getForManager);
							System.out.println("Sent mail manger to the client ");
							mngr.sendToClient(mailManagerEntity);
						}
						client.sendToClient(warning);
					} else {
						Warning warning1 = new Warning("The message have been sent, but the manager is not available");
						client.sendToClient(warning1);
					}
				} catch (IOException e) {
					throw new RuntimeException(e);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (message.get(0).equals("MaillManager")) {
				try {
					mngr.setInfo("Maill",1);
					GetForManager getForManager = (GetForManager) message.get(1);
					List<ManagerMessage> M = Data.getAllMessages();
					MailManagerEntity mailManagerEntity = new MailManagerEntity(M, getForManager);
					System.out.println("Sent manger's massege to the client ");
					client.sendToClient(mailManagerEntity);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			} else if (message.get(0).equals("AnswerMessage")) {
				try {
					mngr.setInfo("Maill",1);
					ManagerMessage Message = (ManagerMessage) message.get(1);
					MailManagerEntity mailManagerEntity = (MailManagerEntity) message.get(2);
					int id = Message.getId();
					int AdditionalTime = (Integer) message.get(3);
					int examteacherid = (Integer) message.get(4);
					Data.DeleteMessage(id);
					if (AdditionalTime != 0) {
						ExamTeacher examTeacher = Data.getDataById(ExamTeacher.class, examteacherid);
						LocalTime currentTime = LocalTime.parse(examTeacher.getFinishTime());
						Duration timeToAdd = Duration.ofMinutes(AdditionalTime);
						LocalTime newTime1 = currentTime.plus(timeToAdd);
						//String finishTime = String.valueOf(newTime1);
						examTeacher.setFinishTime(newTime1.toString());
						int newTimer =examTeacher.getExam().getTimerr() + AdditionalTime;
						Data.updateTime(newTime1.toString(),examTeacher.getId());
						Data.updateTimer(newTimer,examTeacher.getExamStdId(),examTeacher.getId());
						List<ConnectionToClient> clients = studentsInExam.get(examteacherid);
						UpdateTimer updateTimer = new UpdateTimer(newTimer);
						Warning wr = new Warning("the theacher gave you additional time");
						for(ConnectionToClient std : clients ){
							std.sendToClient(updateTimer);
							std.sendToClient(wr);
						}
						ConnectionToClient teacher = onlineTeachers.get(Message.getTID());
						if(teacher != null){
						Teacher teacher1 = Data.getDataById(Teacher.class,Message.getTID());
						ToDuration toDuration= new ToDuration(teacher1,examTeacher,true);
						teacher.sendToClient(toDuration);}
					}
					GetForManager getForManager = mailManagerEntity.getGFM();
					List<ManagerMessage> newmanagerm = Data.getAllMessages();
					MailManagerEntity mailManagerEntity1 = new MailManagerEntity(newmanagerm, getForManager);
					client.sendToClient(mailManagerEntity1);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			} else if (message.get(0).equals("noselection")) {
				mngr.setInfo("Maill",0);//we are not in the mail manager
				Warning warning = new Warning("You didn't select anything!!");
				try {
					client.sendToClient(warning);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (message.get(0).equals("ManagerLogin")) {
				String user = (String) message.get(1);
				String pass = (String) message.get(2);
				if (user.equals("")) {
					Warning warning = new Warning("please fill the username!");
					try {
						client.sendToClient(warning);
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				} else if (!user.equals("malki")) {
					Warning warning = new Warning("wrong manager name, please try again!!");
					try {
						client.sendToClient(warning);
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}else if (pass.equals("")) {
					Warning warning = new Warning("please fill the password!");
					try {
						client.sendToClient(warning);
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				} else if (!pass.equals("123")) {
					Warning warning1 = new Warning("wrong manager password, please try again!!");
					try {
						client.sendToClient(warning1);
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
			} else if (message.get(0).equals("ExitMessages")){
				mngr.setInfo("Maill",0);//we are not in the mail manager
			} else if (message.get(0).equals("#PublishExam")){
				ExamTeacher examTeacher = (ExamTeacher) message.get(1);
				Teacher teacher = (Teacher) message.get(2);
				ExamStudent examStudent = (ExamStudent) message.get(3);
				try {
					System.out.println(examStudent.getExam().getSubject());
					Teacher teacher1 = Data.publishExam(examStudent.getExam().getSubject(),examStudent,examTeacher,teacher);
					client.sendToClient(teacher1);

				} catch (Exception e) {
					e.printStackTrace();
				}
				///// l7d hon mn7ot el exam 3nd teacher and students
			} else if (message.get(0).equals("#StdFinishExam")){

				System.out.println("StdFinishExam");
				Student student = (Student) message.get(1);
				ExamStudent exam = (ExamStudent) message.get(2);
				ExamTeacher examTeacher = Data.getDataById(ExamTeacher.class, exam.getExamTId());
				int x;
				x=examTeacher.getFinish()+1;
				examTeacher.setFinish(x);
				Data.updateExamstartandfinish(x, examTeacher.getStart(), exam.getExamTId());
				System.out.println(x);
				Student std = Data.SubmitExam(student, exam);
				examTeacher = Data.getDataById(ExamTeacher.class, exam.getExamTId());
				Teacher teacher = Data.getDataById(Teacher.class, exam.getTeacherPubId());
				try {
					ConnectionToClient teacherClient = onlineTeachers.get(exam.getTeacherPubId());
					if (teacherClient != null) {
						List<ExamStudent> examStudents = examTeacher.getExamsOfStudents();
						UpdatedExams updatedExams = new UpdatedExams(examStudents);
						teacherClient.sendToClient(updatedExams);
						ToDuration toDuration = new ToDuration(teacher,examTeacher,true);
						teacherClient.sendToClient(toDuration);
					}
					System.out.println("sending " + std.getFirstName() + "'s detailes to client");
					client.sendToClient(std);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if(message.get(0).equals("dateexception")){
				int x=(Integer)message.get(1);
				int y=(Integer)message.get(2);
				if((Integer)message.get(4)==0){
					try {
						Warning warning = new Warning("Please enter a code consisting of exactly 4 digits or characters!!");
						client.sendToClient(warning);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
				if((Integer)message.get(3)==0){
					try {
						Warning warning = new Warning("please select the exam type!!");
						client.sendToClient(warning);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
				if(x==0){
					Warning warning = new Warning("wrong date formatte, please try again!!");
					try {
						client.sendToClient(warning);
						//client.sendToClient(examSubjectTeacher);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				} else if(y==0){
					Warning warning1 = new Warning("the date is in the past, please try again!!");
					try {
						client.sendToClient(warning1);
						//	client.sendToClient(examSubjectTeacher);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				} else if((Integer) message.get(5)==0){
					Warning warning1 = new Warning("please fill all the exam points");
					try {
						client.sendToClient(warning1);
						//	client.sendToClient(examSubjectTeacher);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				} else if ((Integer) message.get(6) == 0) {
					Warning warning = new Warning("wrong time formatte, please try again!!");
					try {
						client.sendToClient(warning);
						//client.sendToClient(examSubjectTeacher);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				} else if ((Integer) message.get(7) == 0) {
					Warning warning1 = new Warning("the time is in the past, please try again!!");
					try {
						client.sendToClient(warning1);
						//	client.sendToClient(examSubjectTeacher);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			} else if(message.get(0).equals("#StudentsExams")){
				// teacher wants to watch the students exam
				int teacherId = (int) message.get(1);
				ExamTeacher examTeacher = (ExamTeacher) message.get(2);
				Teacher teacher = Data.getDataById(Teacher.class,teacherId);
				ExamTeacher exam = Data.getDataById(ExamTeacher.class, examTeacher.getId());
				StudentsExams event = new StudentsExams(teacher,exam);
				try {
					client.sendToClient(event);
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else if(message.get(0).equals("#ApprovingGrade")){
				ExamTeacher exam = (ExamTeacher) message.get(1);
				Teacher t=(Teacher) message.get(2);
				String s=(String) message.get(3);
				boolean approved = (boolean) message.get(4);
				String grade = (String) message.get(5);
				ExamStudent examm = (ExamStudent) message.get(6);
				int idd=exam.getId();
				if(!approved)
				{
					Data.updateGrade(Integer.parseInt(grade),true,examm.getId());
				}else{
					Data.updateGrade(examm.getGrade(),true,examm.getId());
				}
				ExamTeacher ex=Data.getDataById(ExamTeacher.class,idd);
//				StudentsExams ee=new StudentsExams();

				List<Student> st=null;
				try{
					st=Data.getAllStudents();} catch(IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				int i=0;
				int f=1;
				while((i<st.size())&&(f==1)) {
					String hh=st.get(i).getFirstName();
					hh=hh+" "+st.get(i).getLastName();
					System.out.println("kl"+hh+"ll"+s);
					if(hh.equals(s)) {
						f=0;
					} else {
						f=1;
						i++;
					}
				}
				System.out.println(exam.getSubject()+"RRR"+t.getFirstName()+exam.getCode()+exam.getExamsOfStudents().get(0).getGrade());
//				System.out.println(approved);
//				System.out.println(grade);
				Student studentt=st.get(i);
				List<ExamStudent> gg=studentt.getStudentExams();
				int l=0;
				int flagg=1;
				while((flagg==1)&&(l<gg.size())) {
					if((exam.getCode()).equals(gg.get(l).getCode())) {
						flagg=0;
						System.out.println("kkk"+exam.getCode());
					} else{
						l++;
					}
				}
				gg.get(l).setGrade(Integer.parseInt(grade));
				gg.get(l).setApprove(approved);
				studentt.setStudentExams(gg);
				System.out.println("pppppp"+i+studentt.getFirstName());
				System.out.println(""+studentt.getStudentExams().get(0).getCode());
				System.out.println(":"+studentt.getStudentExams().get(0).getGrade());

				StudentsExams ee = new StudentsExams(t, ex);
				try {
					client.sendToClient(ee);
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else if (message.get(0).equals("#UpdateExamsList")) {
				int id = (int) message.get(1);
				ExamTeacher examTeacher = Data.getDataById(ExamTeacher.class, id);
				List<ExamStudent> updatedExams = examTeacher.getExamsOfStudents();
				UpdatedExams updated = new UpdatedExams(updatedExams);
				try {
					client.sendToClient(updated);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

			else if (message.get(0).equals("#StartComputedExam")) {
				ExamStudent exam =(ExamStudent) message.get(1);
				//				Data.updateExecuted(exam.getId());
				List<ConnectionToClient> clients = studentsInExam.get(exam.getExamTId());
				if(clients == null){
					clients = new ArrayList<>();
				}
				clients.add(client);
				studentsInExam.put(exam.getExamTId(),clients);// saving the students clients that making exams
				int id = exam.getExamTId();
				int tId = exam.getTeacherPubId();
				ExamTeacher examTeacher = Data.getDataById(ExamTeacher.class, id);
				int x = examTeacher.getStart() + 1;
				examTeacher.setStart(x);
				Data.updateExamstartandfinish(examTeacher.getFinish(), x, id);
				Teacher teacher = Data.getDataById(Teacher.class,tId);
				try {
					System.out.println(" the sudents in the exam: " + x);
					ConnectionToClient teacherClient = onlineTeachers.get(tId);
					if(teacherClient != null){
						ToDuration toDuration = new ToDuration(teacher,examTeacher,true);
						teacherClient.sendToClient(toDuration);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			else if (message.get(0).equals("#toDuration")) {
				Teacher teacher = (Teacher) message.get(1);
				ExamTeacher examTeacher = (ExamTeacher) message.get(2);
				teacher = Data.getDataById(Teacher.class,teacher.getId());
				examTeacher = Data.getDataById(ExamTeacher.class,examTeacher.getId());
				ToDuration toDuration = new ToDuration(teacher,examTeacher,false);
				try {
					client.sendToClient(toDuration);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

			else if (message.get(0).equals("#SubmitManual")) {
				StudentWillMakeEx makeEx = (StudentWillMakeEx) message.get(1);
				String targetPath = (String) message.get(2);
				Student std = makeEx.getSs();
				ExamStudent exam = makeEx.getEx();
				exam.setComputed(false);
				exam.setManualPath(targetPath);
				Data.SubmitManual(std,exam);


			}
		}
	}
}
