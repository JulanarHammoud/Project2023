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
			else if (message.get(0).equals("#GoToExStudentA")) {			//israaa
				try {

					String stt= (String) message.get(1);
					Student studentFull=Data.getStudent(Integer.parseInt(stt));
					System.out.println("s"+studentFull.getFirstName());
					System.out.println("jjjjjjjj");
					StudentWillDoEx studentWillDo =new StudentWillDoEx(studentFull);
					System.out.println(""+studentWillDo.getStudent().getCourses().size()+";;");

					client.sendToClient(studentWillDo);
				}catch (Exception e) {
					e.printStackTrace();
				}

			}
			else if (message.get(0).equals("#GoToExStudentBUTTON")){
				try {
					String code= (String) message.get(1);
					String stt= (String) message.get(2);
					Student studentFull=Data.getStudent(Integer.parseInt(stt));
					StudentWillMakeEx StEx=new StudentWillMakeEx();
					StEx.setSs(studentFull);
					List<ExamStudent> t=studentFull.getStudentExams();

					ExamStudent x=new ExamStudent();
					System.out.println("1");
					int i=0;
					while(i<t.size()){
						System.out.println("2"+i+t.size());
						x=t.get(i);
						System.out.println("3"+x.getCodeGivenByTeacher()+"3"+code);
						if(code.equals(x.getCodeGivenByTeacher()))
						{
							System.out.println("4");
							i=t.size();
							System.out.println("5"+i+t.size());
						}
						else{
							System.out.println("6");
							i++;
						}
					}
					StEx.setEx(x);
					client.sendToClient(StEx);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
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

			} else if (message.get(0).equals("#CoursetTeacher")) {
			} */
			else if (message.get(0).equals("#GoToExStudentAnswers")) {
				try {
					System.out.println("I'm in server ");
					ExamStudent ex= (ExamStudent) message.get(1);
					System.out.println("I'm in server "+ex.getGrade()+ex.getQuestions().get(0).getThe_student_ans());
					client.sendToClient(ex);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if (message.get(0).equals("#GradesStudent")) {
				try {
					String st= (String) message.get(1);
					Student studentFul=Data.getStudent(Integer.parseInt(st));
					GradeSt h=new GradeSt(studentFul);
					client.sendToClient(h);

				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}

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
			}
			else if (message.get(0).equals("editquestion")) {
				try {
					System.out.println("in edit question ");
					String ques1 = (String) message.get(2);
					String ans1 = (String) message.get(3);
					String ans2 = (String) message.get(4);
					String ans3 = (String) message.get(5);
					String ans4 = (String) message.get(6);
					String right = (String) message.get(7);
					int id = (int)  message.get(8);
					Teacher t=(Teacher)  message.get(9);
					SubjectTeacher subjectTeacher = (SubjectTeacher) message.get(1);
					SubjectTeacher subjectTeacher1 = Data.MakeQuestion(ques1, ans1, ans2, ans3, ans4, right ,subjectTeacher);
					SubjectAndId subid =new SubjectAndId(subjectTeacher1,id,t);
					Warning warning = new Warning("The Question added Successfully!!");

					client.sendToClient(warning);
					client.sendToClient(subid);
				} catch (IOException e) {

				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			else if (message.get(0).equals("MakenewQuestion")) {
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
					int x=0;
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
						SubjectTeacher subjectTeacher1 = Data.MakeQuestion(ques1, ans1, ans2, ans3, ans4, right, subjectTeacher);
						LinkedList<Question> questions = (LinkedList<Question>) message.get(10);
						SubjectAndId subid;
						if (questions == null) {
							subid = new SubjectAndId(subjectTeacher1, id, teacher);
						} else {
							subid = new SubjectAndId(subjectTeacher1, id, teacher, questions);
							subid.setQuestions(questions);
						}
						Warning warning = new Warning("The Question added Successfully!!");
						if((Integer) message.get(11)==1){ //we are in edit exam page
							int flag = (Integer) message.get(13);
							subid.setQuestions(questions);
							id=subid.getId();
							Exam exam = Data.findExam(id);
							CourseTeacher course =Data.FindCourse(exam.getCourse());
							SubjectTeacher subject1 = Data.GetSubjectById(subjectTeacher.getId());
							ExamSubjectTeacherEdit examSubjectTeacherEdit =new ExamSubjectTeacherEdit(teacher,subject1,exam,flag,course);
							client.sendToClient(warning);
							client.sendToClient(examSubjectTeacherEdit);
						} else{ //we are in make new question page
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
			} else if (message.get(0).equals("#BuildExam")) {
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
			} else if (message.get(0).equals("#ShowExamm")) {
				try {
					if(message.get(1)==null){
						System.out.println("Not selecting any the exam");
						Warning warning = new Warning("please select a exam!!");
						client.sendToClient(warning);
					} else{
						ExamSubjectTeacher examsubjectteacher = (ExamSubjectTeacher) message.get(1);
						client.sendToClient(examsubjectteacher);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if(message.get(0).equals("DeleteExam")){
				try {
					if(message.get(1)==null){
						System.out.println("Not selecting any the exam");
						Warning warning = new Warning("please select a exam!!");
						client.sendToClient(warning);
					} else{
						SubjectTeacher subjectTeacher = (SubjectTeacher) message.get(1);
						Teacher teacher = (Teacher) message.get(2);
						CourseTeacher courseTeacher = (CourseTeacher) message.get(3);
						int id=(Integer) message.get(4);
						System.out.println(id);
						Data.deleteExamSub(id, subjectTeacher);
						Data.deleteExam(id);
						SubjectTeacher updatedSub = Data.GetSubjectById(subjectTeacher.getId());
						GetSubject getSubject = new GetSubject(updatedSub, teacher, courseTeacher);
						client.sendToClient(getSubject);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (message.get(0).equals("#EditExam")) {
				try {
					if(message.get(1)==null){
						System.out.println("Not selecting any the exam");
						Warning warning = new Warning("please select a exam!!");
						client.sendToClient(warning);
					} else{
						ExamSubjectTeacherEdit examSubjectTeacherEdit = (ExamSubjectTeacherEdit) message.get(1);
						System.out.println("Im in EditExam in simpleserver");
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
					int good=1;
					if(flag==3){
						System.out.println("Not selecting any the exam copy");
						Warning warning = new Warning("please select the exam copy!!");
						client.sendToClient(warning);
						good=0;
					} else if((Integer)message.get(1)==0){ // Add questions Button or delete questions Button errors
						good=1;
						if((Integer)message.get(9)==0){
							good=0;
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
								client.sendToClient(warning);
							} else if(wrong==2){
								System.out.println("Not selecting any question to delete");
								Warning warning = new Warning("you didn't select any question to delete");
								client.sendToClient(warning);
							} else if(wrong==3){
								System.out.println("Not selecting any question to add");
								Warning warning = new Warning("you didn't select any question to add");
								client.sendToClient(warning);
							}
						}
					} else if((Integer)message.get(1)==1){ //we are in saveall button error
						good=1;
						if((Integer)message.get(13)==0){
							if (flag == 1 || flag == 2) { //save the exam copy
								flag = 2;
							} else {
								flag = 4;
							}
							good=0;
							System.out.println("there is no changes");
							Warning warning = new Warning("you didn't change anything");
							client.sendToClient(warning);
						}
					}
					if(good==1){ // No problems
						Exam exam;
						String TeacherNote = (String) message.get(10);
						String StudentNote = (String) message.get(11);
						String Time = (String) message.get(12);
						boolean result1 = Time.matches("[0-9]+");
						if (result1 == false) {
							System.out.println("ellegal time");
							Warning warning = new Warning("please fill a legal time!!");
							client.sendToClient(warning);
						} else{
							if(flag == 1) { //New Exam Copy
								id = Data.MakeExam(exFromClient.getNumOfQuestions(), TeacherNote,
										Time, StudentNote, exFromClient.getCourse(),
										subject, exFromClient.getTeacher());
								exam = Data.setQuestions(id, (LinkedList<Question>) message.get(8));

								DecimalFormat formatter = new DecimalFormat("00");
								String cor_id = formatter.format(course.getId());//course
								String sub_id = formatter.format(subject.getId());
								Data.updateExamId(cor_id, id, sub_id);
							} else{ // same exam
								Data.updateExam(id,TeacherNote,StudentNote,Integer.valueOf(Time));
								exam = Data.setQuestions(id, (LinkedList<Question>) message.get(8));
							}
							if (flag == 1 || flag == 2) { //save the exam copy
								flag = 2;
							} else {
								flag = 4;
							}
							examSubjectTeacherEdit = new ExamSubjectTeacherEdit(teacher, subject, exam,flag,course);
							client.sendToClient(examSubjectTeacherEdit);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (message.get(0).equals("SaveEditExam")){
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
					} else if((Integer)message.get(10)==0){
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
						} else{
							if(flag == 1) { //New Exam Copy
								id = Data.MakeExam(ex.getNumOfQuestions(), TeacherNote,
										Time, StudentNote, ex.getCourse(),
										subject, ex.getTeacher());
								ex = Data.setQuestions(id, (LinkedList<Question>) message.get(11));
								DecimalFormat formatter = new DecimalFormat("00");
								String cor_id = formatter.format(course.getId());//course
								String sub_id = formatter.format(subject.getId());
								Data.updateExamId(cor_id, id, sub_id);
							} else{
								Data.updateExam(ex.getId(),TeacherNote,StudentNote,Integer.valueOf(Time));
								ex=Data.findExam(id);
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
				} catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
}