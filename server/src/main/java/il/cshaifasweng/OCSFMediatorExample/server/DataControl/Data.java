package il.cshaifasweng.OCSFMediatorExample.server.DataControl;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Data {
    private static Session session;

    private static SessionFactory getSessionFactory() throws HibernateException {
        try{
        Configuration configuration = new Configuration();
        // Add ALL of your entities here. You can also try adding a whole package.
        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(Question.class);
        configuration.addAnnotatedClass(Subject.class);
        configuration.addAnnotatedClass(Teacher.class);
        configuration.addAnnotatedClass(SubjectTeacher.class);
        configuration.addAnnotatedClass(SubjectStudent.class);
        configuration.addAnnotatedClass(CourseTeacher.class);
        configuration.addAnnotatedClass(CourseStudent.class);
        configuration.addAnnotatedClass(Exam.class);
        configuration.addAnnotatedClass(ManagerMessage.class);
        configuration.addAnnotatedClass(ExamTeacher.class);
        configuration.addAnnotatedClass(ExamStudent.class);
        configuration.addAnnotatedClass(DetailedQuestion.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        return configuration.buildSessionFactory(serviceRegistry);}
        catch (HibernateException e) {

            e.printStackTrace();

            return null;
        }
    }
    public static void generateStusent() throws Exception {
        SubjectStudent ST11 = new SubjectStudent("Grammar");
        SubjectStudent ST2 = new SubjectStudent("Geometry");
        SubjectStudent ST3 = new SubjectStudent("Algebra");
        SubjectStudent ST1 = new SubjectStudent("comprehension");
        LinkedList<SubjectStudent> subjectst=new LinkedList<>();
        subjectst.add(ST1);
        subjectst.add(ST11);
        CourseStudent English = new CourseStudent("English",subjectst);
        LinkedList<SubjectStudent> subjectst2=new LinkedList<>();
        subjectst2.add(ST2);
        subjectst2.add(ST3);
        CourseStudent Math = new CourseStudent("Math",subjectst2);
        LinkedList<CourseStudent> course = new LinkedList<>();
        course.add(Math);
        Student julanar = new Student("Julanar", "Hammoud","jula123","0101",course);
        course.add(English);
        Student rozaleen = new Student("Rozaleen", "Hassanin", "roza99","1999",course);

        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            System.err.println("Generated starts ...");
            session.saveOrUpdate(ST1);
            session.saveOrUpdate(ST11);
            session.saveOrUpdate(ST2);
            session.saveOrUpdate(English);
            session.saveOrUpdate(Math);
            session.saveOrUpdate(julanar);
            session.saveOrUpdate(rozaleen);
            System.err.println("Generated ends ...");
            session.flush();
            session.getTransaction().commit(); // Save everything.
        } catch (Exception exception) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            exception.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return;
    }

    public static CourseStudent getCourByName(String name){
        System.out.println("server searching course by its name");
        List<CourseStudent> list = null;
        try {
            list = getDataList(CourseStudent.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(CourseStudent cour :list){
            if(cour.getName().equals(name)){
                System.out.println(cour.getName());
                return cour;
            }
        }
        return null;
    }
    public static List<Student> getAllStudents() throws Exception {
        try{
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Student> query = builder.createQuery(Student.class);
        query.from(Student.class);
        List<Student> result = session.createQuery(query).getResultList();
        session.close();
        System.out.println(result.size());
        return result;}
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    public static List<Teacher> getAllTeachers() throws Exception {
        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Teacher> query = builder.createQuery(Teacher.class);
            query.from(Teacher.class);
            List<Teacher> result = session.createQuery(query).getResultList();
            session.close();
            System.out.println(result.size());
            return result;
        }
        catch (Exception e){
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
    public static Exam BringExamBasedOnCode(int Id) throws Exception {
        System.out.println("in BringExamBasedOnCode "+Id);
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println("mmmmmmmmm");
        Exam exam = session.get(Exam.class,Id); ////?????
        System.out.println("lllll");
        System.out.println("lklklklklkl");
        session.close();
        System.out.println("gggg");
        System.out.println(exam.getIdCode());
        System.out.println(exam.getId()+"kl");
        //  System.out.println(student.getSt_name());
        return exam;
    }

    public static Student getStudent(int id) throws Exception {

        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            Student student = session.get(Student.class, id);
            session.close();
            //  System.out.println(student.getSt_name());
            return student;
        }
        catch (Exception e){
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return null;

    }

    public static void codeStudentId(int id) throws Exception {
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        Student change =session.get(Student.class,id);
        //change.setActive(false);
        session.saveOrUpdate(change);
        session.flush();
        session.getTransaction().commit();
        session.close();

    }

    public static void LogOutSt(int id) throws Exception{
        try{
        // Student student = getStudent(id);
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        Student change =session.get(Student.class,id);
        change.setActive(false);
        session.saveOrUpdate(change);
        session.flush();
        session.getTransaction().commit();
        session.close();}
        catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static void LogOutTeacher(int id) throws Exception {
        try{
        // Student student = getStudent(id);
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        Teacher change =session.get(Teacher.class,id);
        change.setActive(false);
        session.saveOrUpdate(change);
        session.flush();
        session.getTransaction().commit();
        session.close();
        System.out.println(change.getActive()+"change active status");}
        catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static void BackSt(int id) throws Exception {
        // Student student = getStudent(id);
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        Student change =session.get(Student.class,id);
        change.setActive(false);
        session.saveOrUpdate(change);
        session.flush();
        session.getTransaction().commit();
        session.close();

    }

    public static void activateSt(int id) throws Exception {
        // Student student = getStudent(id);
        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            Student change = session.get(Student.class, id);
            change.setActive(true);
            session.saveOrUpdate(change);
            session.flush();
            session.getTransaction().commit();
            session.close();
        }catch (Exception e){
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }

    }

    public static void activateTeacher(int id) throws Exception {
        try {
            // Student student = getStudent(id);
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            Teacher change = session.get(Teacher.class, id);
            change.setActive(true);
            session.saveOrUpdate(change);
            session.flush();
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e){
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }

    }
    public static String updateExamId(String IdCourse , int currentid, String IdSubject){
        try {
            System.out.println("I am updating: " + IdCourse + " " + currentid + " " + IdSubject);
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            Exam change = session.get(Exam.class, currentid);
            String s = IdCourse + IdSubject;
            DecimalFormat formatter = new DecimalFormat("00");
            String aFormatted = formatter.format(currentid);
            s = s + aFormatted;
            System.out.println("" + s);
            change.setIdCode(s);
            session.saveOrUpdate(change);
            session.flush();
            session.getTransaction().commit();
            session.close();
            return s;
        }catch (Exception e){
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
    public static void updateQuestion(int IdQuestion , String q,String ans1 ,String ans2,String ans3 ,String ans4, String note ,String right ){
       System.out.println("I am updating: " + IdQuestion + " " + q + " "+ans1);
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        Question question = session.get(Question.class, IdQuestion);
        question.setQuestion(q);
        question.setAns1(ans1);
        question.setAns2(ans2);
        question.setAns3(ans3);
        question.setAns4(ans4);
        question.setNote(note);
        question.setThe_right_ans(right);
        session.saveOrUpdate(question);
        session.flush();
        session.getTransaction().commit();
        session.close();
        System.out.println(question.getQuestion());
        // return exam; Exam
    }

    public static int returnid(String question) throws Exception {
        List<Question> all=getAllQuestions();
        System.out.println(all);
        for (Question q1 : all) {
            if (q1.getQuestion().equals(question)) {
                return q1.getId();
            }
        }
        return -1;
    }

    public static Teacher TeacherLog(String username, String password,Teacher teacher) throws Exception {
            if (teacher.getUserName().equals(username)) {
                if (password.equals(teacher.getPassWord())) {
                    return teacher;
                } else {
                    teacher.setFirstName("wrongteacherpassword");
                    return teacher;
                }
            }

        Teacher teachernotfound = new Teacher(null, null, null, null, null);
        return teachernotfound;
    }

    public static Student StudentLog(String username, String password,Student student) throws Exception {
            if (student.getUserName().equals(username)) {
                if (password.equals(student.getPassWord())) {
                    return student;
                } else {
                    student.setFirstName("wrongstudentpassword");
                    return student;
                }
            }

        Student studentnotfound = new Student(null, null, null, null, null);
        return studentnotfound;
    }

    public static void printAllStudents() throws Exception {
        List<Student> students = getAllStudents();
        for (Student student : students) {
            System.out.print("Id: ");
            System.out.print(student.getId());
            System.out.print(", student name: ");
            System.out.print(student.getActive());
            System.out.print(", Grade1: ");
            System.out.print(student.getFirstName());
            System.out.print(", Grade2: ");
            System.out.print(student.getFirstName());
            System.out.print('\n');
        }
    }

    public static void generateSubject() throws Exception {
        SubjectStudent ST11 = new SubjectStudent("Grammar");
        SubjectStudent ST2 = new SubjectStudent("Geometry");
        SubjectStudent ST3 = new SubjectStudent("Algebra");
        SubjectStudent ST1 = new SubjectStudent("comprehension");
        LinkedList<SubjectStudent> subjectst=new LinkedList<>();
        subjectst.add(ST1);
        subjectst.add(ST11);
        CourseStudent Eng = new CourseStudent("English",subjectst);
        LinkedList<SubjectStudent> subjectst2=new LinkedList<>();
        subjectst2.add(ST2);
        subjectst2.add(ST3);
        CourseStudent Mat = new CourseStudent("Math",subjectst2);
        LinkedList<CourseStudent> course = new LinkedList<>();
        course.add(Mat);
        Student julanar = new Student("Julanar", "Hammoud","1jula","0101",course);
        course.add(Eng);
        Student rozaleen = new Student("Rozaleen", "Hassanin", "2roza","1999",course);
        Student Lana = new Student("Lana","Abbas","3lana" ,"159",course);
        Question Num1 = new Question("I'm very happy _____ in India. I really miss being there.", "to live", "to have lived", "to be lived", "to be living","" ,"to live");
        Question Num2 = new Question("They didn't reach an agreement ______ their differences.", "on account of", "due", "because", "owing","bay extra attention" ,"owing");
        Question Num3 = new Question("I wish I _____ those words. But now it's too late.", "not having said", "have never said", "never said", "had never said","good luck" ,"have never said");
        Question Num4 = new Question("Each term in the sequence below is five times the previous term. What is the eighth term in the sequence? 4, 20, 100, 500,....", "500 * 8", "4 * 5^7", "4 * 5^8", "4^8", "mentioned in lecture 10","4 * 5^7");
        Question Num5 = new Question("The inequality –4(x – 1) ≤ 2(x + 1) is equivalent to", "x => -1/3", "x=>1/3", "x <= 1/3", "x <= -1/3","" ,"x=> 1/3");
        Question Num6 = new Question("For what values of x is the expression : 3x2 – 3x – 18 equal to 0?", "x = 3, x = –6", "x = –3, x = 2", "x = 3, x = –2", "x = -3, x = –6", "","x = 3, x = –2");
        Question Num7 = new Question("A circle has an area of 64π ft.2. What is the circumference of the circle?", "8π ft", "32π ft", "18π ft", "16π ft","Good Luck" ,"8π ft");
        Question Num8 = new Question("A circle has an area of 64π ft.2. What is the circumference of the circle?", "8π ft", "32π ft", "18π ft", "16π ft","" ,"8π ft");
        Question Num9 = new Question("Which of the following statements is true?", "All squares are rectangles and rhombuses.", "All rectangles are rhombuses, but not allrhombuses are rectangles", "All rhombuses are parallelograms and all parallelograms are rhombuses.", "All rhombuses are squares, but not all squaresare rhombuses.","","All rhombuses are squares, but not all squares are rhombuses.");
        Question Num10 = new Question("When winding an old clock, it is important not to overwind it. ", "clocks have changed over the years. ", "old-fashioned clocks become fragile with age. ", "old-fashioned clocks were operated by an internal spring. ", "time flies when you’re having fun ", "","old-fashioned clocks were operated by an internal spring.");
        LinkedList<Question> questions1 = new LinkedList<>();
        LinkedList<Question> questions2 = new LinkedList<>();
        LinkedList<Question> questions3 = new LinkedList<>();
        LinkedList<Question> questions4 = new LinkedList<>();
        questions1.add(Num1);
        questions1.add(Num2);
        questions1.add(Num3);
        questions2.add(Num4);
        questions2.add(Num5);
        questions2.add(Num6);
        questions3.add(Num7);
        questions3.add(Num8);
        questions3.add(Num9);
        questions4.add(Num10);
        SubjectTeacher Grammar = new SubjectTeacher("Grammar", questions1);
        SubjectTeacher Geometry = new SubjectTeacher("Geometry", questions3);
        SubjectTeacher algebra = new SubjectTeacher("Algebra", questions2);
        SubjectTeacher comprehension = new SubjectTeacher("comprehension", questions4);
        LinkedList<SubjectTeacher> subject1 = new LinkedList<>();
        subject1.add(Grammar);
        subject1.add(comprehension);
        CourseTeacher English =new CourseTeacher("English",subject1);
        LinkedList<SubjectTeacher> subject2=new LinkedList<>();
        subject2.add(algebra);
        subject2.add(Geometry);
        CourseTeacher Math =new CourseTeacher("Math",subject2);
        LinkedList<CourseTeacher> courses = new LinkedList<>();
        courses.add(English);
        Teacher mona = new Teacher("Mona","Amara","1mona","1234",courses);
        courses.add(Math);
        Teacher noran = new Teacher("Noran","morad","2noran","1235",courses);

        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            System.err.println("Generated starts ...");

            session.saveOrUpdate(ST1);
            session.saveOrUpdate(ST11);
            session.saveOrUpdate(ST2);
            session.saveOrUpdate(ST3);
            session.saveOrUpdate(Eng);
            session.saveOrUpdate(Mat);
            session.saveOrUpdate(julanar);
            session.saveOrUpdate(rozaleen);
            session.saveOrUpdate(Lana);
            session.saveOrUpdate(Num1);
            session.saveOrUpdate(Num2);
            session.saveOrUpdate(Num3);
            session.saveOrUpdate(Num4);
            session.saveOrUpdate(Num5);
            session.saveOrUpdate(Num6);
            session.saveOrUpdate(Num7);
            session.saveOrUpdate(Num8);
            session.saveOrUpdate(Num9);
            session.saveOrUpdate(Num10);
            session.saveOrUpdate(Grammar);
            session.saveOrUpdate(Geometry);
            session.saveOrUpdate(algebra);
            session.saveOrUpdate(comprehension);
            session.saveOrUpdate(English);
            session.saveOrUpdate(Math);
            session.saveOrUpdate(mona);
            session.saveOrUpdate(noran);
            System.err.println("Generated ends ...");

            session.flush();
            session.getTransaction().commit(); // Save everything.


        } catch (Exception exception) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            exception.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return;
    }

    public static void MakeQes(int id, String question, String ans1, String ans2, String ans3, String ans4, String the_right_ans) {
        System.out.println("in make Data33 ");
        Question q1 = new Question("israa *** crying?", "is", "are", "an", "none of the answers", "","is");
        Question q2 = new Question("reem *** happy?", "is", "are", "an", "none of the answers", "Focus","is");

        try {
            System.out.println("in make Data3 ");
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            System.err.println("Generated starts ...");

            session.saveOrUpdate(q1);
            System.err.println("Generated ends ...");

            session.saveOrUpdate(q2);
            System.err.println("Generated ends ...");

            session.flush();
            session.getTransaction().commit(); // Save everything.


        } catch (Exception exception) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            exception.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }

    public static int MakeExam(int NumQ, String TNotes, String timm, String SNotes, String course, SubjectTeacher sub, String teacher ,int teacherid) {
        Exam ex = new Exam(NumQ, TNotes, timm, SNotes, course, sub.getSb_name(), teacher,teacherid);
        try {
            System.out.println("generating an exam");
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            System.err.println("Generated starts ...");

            session.saveOrUpdate(ex);
            System.err.println("Generated ends ...");
            SubjectTeacher change = session.get(SubjectTeacher.class,sub.getId());
            change.getExams().add(ex);
            session.saveOrUpdate(change);
            session.flush();
            session.getTransaction().commit(); // Save everything.
            System.out.println("ex.id"+ex.getId());
            return ex.getId();

        } catch (Exception exception) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            exception.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return 0;
    }

    public static SubjectTeacher MakeQuestion(String Q, String an1, String an2, String an3, String an4, String note, String right, SubjectTeacher sub,LinkedList<SubjectTeacher> subjects) {
        System.out.println("in make Question ");
        // Exam ex=new Exam(0,NumQ,chose,"T",TNotes,SNotes,cc);
        Question newquestion =new Question(Q,an1,an2,an3,an4, note,right);
        SubjectTeacher change1=new SubjectTeacher();
        try {
            //    System.out.println("dataaaaaaaaaaaaaaaa"+subjects.get(0).getSb_name());
            for(SubjectTeacher s :subjects){
                System.out.println(s.getSb_name());
                SessionFactory sessionFactory = getSessionFactory();
                session = sessionFactory.openSession();
                session.beginTransaction();
                System.err.println("Generated starts ...");
                session.saveOrUpdate(newquestion);
                System.err.println("Generated ends ...");
                SubjectTeacher change = session.get(SubjectTeacher.class,s.getId());
                change.getQuestions().add(newquestion);
                session.saveOrUpdate(change);
                session.flush();
                session.getTransaction().commit(); // Save everything.
                session.close();
                if(sub.getId()==change.getId()){ change1=change; System.out.println("change1:" + change.getSb_name());}
            }
        } catch (Exception exception) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            exception.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
            return change1;

        }
    }

    public static List<CourseTeacher> getAllCourses() throws Exception {
        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CourseTeacher> query = builder.createQuery(CourseTeacher.class);
            query.from(CourseTeacher.class);
            List<CourseTeacher> result = session.createQuery(query).getResultList();
            session.close();
            System.out.println(result.size());
            return result;
        }
        catch (Exception e){
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    public static List<Question> getAllQuestions() throws Exception {
        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Question> query = builder.createQuery(Question.class);
            query.from(Question.class);
            List<Question> result = session.createQuery(query).getResultList();
            session.close();
            System.out.println(result.size());
            return result;
        }
        catch (Exception e){
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    public static CourseTeacher FindCourse(String name) throws Exception {
        System.out.println(name);
        List<CourseTeacher> CourseTeacher = getAllCourses();
        //System.out.println(teachers);

        for (CourseTeacher coursee : CourseTeacher) {
            System.out.println(name.equals(coursee.getName()));
            if (name.equals(coursee.getName())) {
                return coursee;
            }
        }
        return null;
    }

    public static List<SubjectTeacher> getAllSubjects() {
        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<SubjectTeacher> query = builder.createQuery(SubjectTeacher.class);
            query.from(SubjectTeacher.class);
            List<SubjectTeacher> result = session.createQuery(query).getResultList();
            session.close();
            System.out.println(result.size());
            return result;
        } catch (Exception e){
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    public static void updateExam(int examId, String TeacherNote, String StudentNote, int ExamTime) {
        try {
            System.out.println("I am updating: " + TeacherNote + " " + StudentNote + " " + ExamTime);
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            Exam exam = session.get(Exam.class, examId);
            exam.setTimerr(ExamTime);
            exam.setTeacherNotes(TeacherNote);
            exam.setStudentNotes(StudentNote);
            session.saveOrUpdate(exam);
            session.flush();
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e){
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static List<CourseTeacher> getAllCoutsrss() {
        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CourseTeacher> query = builder.createQuery(CourseTeacher.class);
            query.from(CourseTeacher.class);
            List<CourseTeacher> result = session.createQuery(query).getResultList();
            session.close();
            System.out.println(result.size());
            return result;
        } catch (Exception e){
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return null;

    }

    public static CourseTeacher findcourse(String choose) {
        try {
            System.out.println("I'm in findcourse method");
            System.out.println(choose);
            List<CourseTeacher> list = Data.getAllCoutsrss();
            System.out.println(list.get(1).getName());
            System.out.println(list.get(0).getName());
            for (CourseTeacher course : list) {
                if (course.getName().equals(choose)) {
                    System.out.println(course.getName());
                    return course;
                }
            }
            CourseTeacher notfound = new CourseTeacher(null, null);
            return notfound;
        } catch (Exception e){

            e.printStackTrace();
        }
        return null;
    }

    public static SubjectTeacher findsubject(String choose) {
        System.out.println("I'm in findsubject method");
        System.out.println(choose);
        List<SubjectTeacher> list = Data.getAllSubjects();
        for (SubjectTeacher subjectTeacher : list) {
            if (subjectTeacher.getSb_name().equals(choose)) {
                System.out.println(subjectTeacher.getSb_name());
                return subjectTeacher;
            }
        }
        SubjectTeacher notfound = new SubjectTeacher(null, null);
        return notfound;
    }

    public static Exam findExam(int id) {
        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            Exam exam = session.get(Exam.class, id);
            session.flush();
            session.getTransaction().commit();
            session.close();
            return exam;
        }
        catch (Exception e){
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return null;

    }

    public static Exam setQuestions(int exId, LinkedList<Question> questions) {
        try {
            System.out.println("the server is sitting the questions");
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();

            Exam exam = session.get(Exam.class, exId);
            exam.setQuestions(questions);

            session.saveOrUpdate(exam);
            session.flush();
            session.getTransaction().commit();
            session.close();
            return exam;
        }
        catch (Exception e){
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return null;

    }

    public static SubjectTeacher GetSubjectById(int id) {
        try {
            System.out.println("the server is getting the subject");
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            SubjectTeacher sub = session.get(SubjectTeacher.class, id);

            session.flush();
            session.getTransaction().commit();
            session.close();
            return sub;
        }
        catch (Exception e){
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    public static void deleteExamSub(int id, SubjectTeacher sub) {
        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            Exam exam = session.get(Exam.class, id);
            SubjectTeacher change = session.get(SubjectTeacher.class, sub.getId());
            change.getExams().remove(exam);
            session.saveOrUpdate(change);
            session.flush();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static void deleteExam(int id) {
        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            Exam delete = session.get(Exam.class, id);
            session.delete(delete);
            session.flush();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }
    public static List<SubjectStudent> getAllSubjectStd() throws Exception {
        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<SubjectStudent> query = builder.createQuery(SubjectStudent.class);
            query.from(SubjectStudent.class);
            List<SubjectStudent> result = session.createQuery(query).getResultList();
            session.close();
            System.out.println(result.size());
            return result;
        }
        catch (Exception e){
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
    public static int generateExamTeacher(ExamTeacher exam){
        int id = -1;
        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            System.err.println("Generated starts ...");
            id = (int) session.save(exam);
            System.err.println("Generated ends ...");
            session.flush();
            session.getTransaction().commit(); // Save everything.
        } catch (Exception exception) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            exception.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return id ;

    }

    public static int generateExamStudent(ExamStudent exam){
        int id = -1;
        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            System.err.println("Generated starts ...");
            id = (int) session.save(exam);
            System.err.println("Generated ends ...");
            session.flush();
            session.getTransaction().commit(); // Save everything.
        } catch (Exception exception) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            exception.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return id ;

    }
    public static SubjectStudent findSubjectStd (String choose)  {
        System.out.println("I'm in findsubject method");
        System.out.println(choose);
        List<SubjectStudent> list = null;
        try {
            list = Data.getAllSubjectStd();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // System.out.println(list.get(1).getName());
        // System.out.println(list.get(0).getName());
        for(SubjectStudent subjectStudent :list){
            if(subjectStudent.getSb_name().equals(choose)){
                System.out.println(subjectStudent.getSb_name());
                return subjectStudent;
            }
        }
        return null;
    }

    public static <T> int generateData(T data){
        int id = -1;
        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            System.err.println("Generating " + data.getClass().getName() + " starts ...");
            id = (int) session.save(data);
            System.err.println("Generated ends ...");
            session.flush();
            session.getTransaction().commit(); // Save everything.
        } catch (Exception exception) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            exception.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return id ;

    }


    public static Teacher publishExam(String subject,ExamStudent exam1,ExamTeacher exam2,Teacher teacher) throws Exception {
        for(DetailedQuestion q : exam1.getQuestions()){
            generateData(q);
        }
        int ExStdId =generateExamStudent(exam1);
        exam2.setExamStdId(ExStdId);
        int ExTId = generateExamTeacher(exam2);
        SubjectStudent sub = findSubjectStd(subject);
        CourseStudent cour = getCourByName(exam1.getExam().getCourse());

        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            ExamTeacher exam = session.get(ExamTeacher.class, ExTId);
            ExamStudent examStudent = session.get(ExamStudent.class, ExStdId);
            Teacher changeTeacher = session.get(Teacher.class, teacher.getId());
            SubjectStudent changeSub = session.get(SubjectStudent.class, sub.getId());
            CourseStudent course = session.get(CourseStudent.class, cour.getId());
            List<ExamStudent> examsSt = changeSub.getExams();
            examStudent.setExamTId(ExTId);
            session.saveOrUpdate(examStudent);
            examsSt.add(examStudent);
            changeSub.setExams(examsSt);
            course.setLastExam(examStudent);
            session.saveOrUpdate(changeSub); /// adding the exam to the sbjectStudent
            session.saveOrUpdate(course);
            List<ExamTeacher> exams = changeTeacher.getPublishedExams();
            exams.add(exam);
            changeTeacher.setPublishedExams(exams); /// adding the exam to the publisshed exams list for teacher
            session.flush();
            session.getTransaction().commit();
            session.close();
            return changeTeacher;
        }
        catch (Exception e){
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return null;

    }
    public static Student SubmitExam(Student student,ExamStudent exam){
        // we have to add the exam to the examTeacher
        // and generate new exam for the student and adding it to the list
      try{
          System.out.println(student.getId() + " " + student.getFirstName());
          ExamStudent e = new ExamStudent(exam.getTime(),exam.getDate(), exam.isComputed(),exam.getExam(),exam.getCode(),exam.getTeacherPubId(),exam.getTeacherid());
          e.setGrade(exam.getGrade());
         // e.setQuestions(exam.getQuestions());
          List<DetailedQuestion> ff=new ArrayList<>();

          e.setExecuted(true);
          e.setOnTime(exam.isOnTime());
          e.setExamTId(exam.getExamTId());
          for(DetailedQuestion q : exam.getQuestions()){
              DetailedQuestion question = new DetailedQuestion(q.getPoints(),q.getStdAnswer() ,q.getQuestion());
             int Qid= generateData(question);
              question=getDataById(DetailedQuestion.class,Qid);
              ff.add(question);
          }
          e.setQuestions(ff);
          int id =  generateData(e);
          SessionFactory sessionFactory = getSessionFactory();
          session = sessionFactory.openSession();
          session.beginTransaction();
          ExamStudent change =session.get(ExamStudent.class,id);
          change.setStdName(student.getFirstName() + " " + student.getLastName());
          session.saveOrUpdate(change);
          Student updateStd = session.get(Student.class,student.getId());
          ExamTeacher ex =session.get(ExamTeacher.class,exam.getExamTId());
          if( updateStd.getStudentExams() == null){
              List<ExamStudent> list = new ArrayList<>();
              updateStd.setStudentExams(list);
          }
          updateStd.getStudentExams().add(change);
          session.saveOrUpdate(updateStd);
          if( ex.getExamsOfStudents() == null){
              List<ExamStudent> list = new ArrayList<>();
              ex.setExamsOfStudents(list);
          }
          ex.getExamsOfStudents().add(change);
          session.saveOrUpdate(ex);
          session.flush();
          session.getTransaction().commit();
          session.close();
          return updateStd;
      } catch (Exception e){
          if (session != null) {
              session.getTransaction().rollback();
          }
          System.err.println("An error occured, changes have been rolled back.");
          e.printStackTrace();
      }
      finally {
          if (session != null) {
              session.close();
          }
      }
      return null;

    }

    public static void GenerateMessage(int time, String m, int t,int e) {
        ManagerMessage messages = new ManagerMessage(time, m, t,e);
        try {
            System.out.println("in make Data3 ");
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            System.err.println("Generated starts ...");

            session.saveOrUpdate(messages);
            System.err.println("Generated ends ...");

            session.flush();
            session.getTransaction().commit(); // Save everything.


        } catch (Exception exception) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            exception.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static List<ManagerMessage> getAllMessages() throws Exception {
        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<ManagerMessage> query = builder.createQuery(ManagerMessage.class);
            query.from(ManagerMessage.class);
            List<ManagerMessage> result = session.createQuery(query).getResultList();
            session.close();
            System.out.println(result.size());
            return result;
        }
        catch (Exception e){
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
    public static void DeleteMessage(int id) {
        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            ManagerMessage delete = session.get(ManagerMessage.class, id);
            session.delete(delete);
            session.flush();
            session.getTransaction().commit();
            session.close();
            System.out.println("Deleted Successfully");
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }
    public static <T> T getDataById(Class<T> entityType, int id) {
        try {
            System.out.println("the server is getting the Data");
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            T data =session.get(entityType,id);
            session.flush();
            session.getTransaction().commit();
            session.close();
            return data;
        }
        catch (Exception e){
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
       return null;
    }
    public static <T> List<T> getDataList(Class<T> entityType) throws Exception {
        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> query = builder.createQuery(entityType);
            query.from(entityType);
            List<T> result = session.createQuery(query).getResultList();
            session.close();
            System.out.println(result.size());
            return result;
        } catch (Exception e){
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
    public static void setNumberOfQuestions(int numberofquestions ,int examId) {
        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            Exam exam = session.get(Exam.class, examId);
            exam.setNumOfQuestions(numberofquestions);
            session.saveOrUpdate(exam);
            session.flush();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e){
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static void updateGrade(int grade,boolean approve , int id){
        try {
            System.out.println("I am updating: " + grade + " " + approve + " " + id);
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            ExamStudent change = session.get(ExamStudent.class, id);
            change.setApprove(approve);
            change.setGrade(grade);
            session.saveOrUpdate(change);
            session.flush();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e){
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }
    public static void updateExamstartandfinish(int finish,int start , int id){
        try {
            System.out.println("I am updating: " + start + " " + finish + " " + id);
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            ExamTeacher change = session.get(ExamTeacher.class, id);
            change.setFinish(finish);
            change.setStart(start);
            session.saveOrUpdate(change);
            session.flush();
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e){
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }
    public static void updateTime(String Time , int id){
        try {
            System.out.println("I am updating: " + Time + " " + id);
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            ExamTeacher change = session.get(ExamTeacher.class, id);
            change.setFinishTime(Time);
            session.saveOrUpdate(change);
            session.flush();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e){
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }
    public static void updateExecuted(int id){
//        System.out.println("I am updating: "+Time+" "+id);
        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            ExamStudent change = session.get(ExamStudent.class, id);
            change.setExecuted(true);
            session.saveOrUpdate(change);
            session.flush();
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e){
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }
    public static void updateTimer(int timer , int idStd, int idT){
        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            ExamTeacher change1 = session.get(ExamTeacher.class, idT);
            ExamStudent change2 = session.get(ExamStudent.class, idStd);
            change1.setNewTimer(timer);
            change2.setNewTimer(timer);
            session.saveOrUpdate(change1);
            session.saveOrUpdate(change2);
            session.flush();
            session.getTransaction().commit();
            session.close();
        }catch (Exception e){
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }
    public static Student SubmitManual(Student student,ExamStudent exam){
        try{
            System.out.println(student.getId() + " " + student.getFirstName());
            ExamStudent e = new ExamStudent(exam.getTime(),exam.getDate(), exam.isComputed(),exam.getExam(),exam.getCode(),exam.getTeacherPubId(),exam.getTeacherid());
            e.setManualPath(exam.getManualPath());
            e.setComputed(false);
            int id =  generateData(e);
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            ExamStudent change =session.get(ExamStudent.class,id);
            change.setStdName(student.getFirstName() + " " + student.getLastName());
            session.saveOrUpdate(change);
            Student updateStd = session.get(Student.class,student.getId());
            ExamTeacher ex =session.get(ExamTeacher.class,exam.getExamTId());
            if( updateStd.getStudentExams() == null){
                List<ExamStudent> list = new ArrayList<>();
                updateStd.setStudentExams(list);
            }
            updateStd.getStudentExams().add(change);
            session.saveOrUpdate(updateStd);
            if( ex.getExamsOfStudents() == null){
                List<ExamStudent> list = new ArrayList<>();
                ex.setExamsOfStudents(list);
            }
            ex.getExamsOfStudents().add(change);
            session.saveOrUpdate(ex);
            session.flush();
            session.getTransaction().commit();
            session.close();
            return updateStd;
        } catch (Exception e){
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return null;

    }
    public static List<ExamTeacher> getAllExamteacher() {
        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<ExamTeacher> query = builder.createQuery(ExamTeacher.class);
            query.from(ExamTeacher.class);
            List<ExamTeacher> result = session.createQuery(query).getResultList();
            session.close();
            System.out.println(result.size());
            return result;
        }
        catch (Exception e){
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
}