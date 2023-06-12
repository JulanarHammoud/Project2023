package il.cshaifasweng.OCSFMediatorExample.server.DataControl;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Data {
    private static Session session;
    private static SessionFactory getSessionFactory() throws HibernateException {
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

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        return configuration.buildSessionFactory(serviceRegistry); }
    public static void generateStusent() throws Exception {
        SubjectStudent ST11 = new SubjectStudent("Grammar");
        SubjectStudent ST2 = new SubjectStudent("Geometry");
        SubjectStudent ST1 = new SubjectStudent("comprehension");
        LinkedList<SubjectStudent> subjectst=new LinkedList<>();
        subjectst.add(ST1);
        subjectst.add(ST11);
        CourseStudent English = new CourseStudent("English",subjectst);
        LinkedList<SubjectStudent> subjectst2=new LinkedList<>();
        subjectst2.add(ST2);
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

    public static List<Student> getAllStudents() throws Exception {
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Student> query = builder.createQuery(Student.class);
        query.from(Student.class);
        List<Student> result = session.createQuery(query).getResultList();
        session.close();
        System.out.println(result.size());
        return result;
    }

    public static List<Teacher> getAllTeachers() throws Exception {
        System.out.println("line 1");
        SessionFactory sessionFactory = getSessionFactory();
        System.out.println("line 2");
        session = sessionFactory.openSession();
        System.out.println("line 3");
        session.beginTransaction();
        System.out.println("line 4");
        CriteriaBuilder builder = session.getCriteriaBuilder();
        System.out.println("line 5");
        CriteriaQuery<Teacher> query = builder.createQuery(Teacher.class);
        System.out.println("line 6");
        query.from(Teacher.class);
        System.out.println("line 7");
        List<Teacher> result = session.createQuery(query).getResultList();
        System.out.println("line 8");
        session.close();
        System.out.println(result.size());
        return result;
    }

    public static Student getStudent(int id) throws Exception {

        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        Student student =session.get(Student.class,id);
        session.close();
      //  System.out.println(student.getSt_name());
        return student;
    }
    public static void LogOutSt(int id) throws Exception {
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
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        Student change =session.get(Student.class,id);
        change.setActive(true);
        session.saveOrUpdate(change);
        session.flush();
        session.getTransaction().commit();
        session.close();

    }

   /* public static void updateGrade(int newGrade , int currentid, int Grade){
        // System.out.println("I am updating");
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        Student change =session.get(Student.class,currentid);
        if(Grade==1){
        change.setGrade1(newGrade);}
        else if(Grade==2)
            change.setGrade2(newGrade);
        session.saveOrUpdate(change);
        session.flush();
        session.getTransaction().commit();
        session.close();

    }*/

    public static Teacher TeacherLog(String username, String password) throws Exception {
        List<Teacher> teachers = getAllTeachers();
        //System.out.println(teachers);
        for (Teacher teacher : teachers) {
            if (password.equals(teacher.getPassWord()) && teacher.getUserName().equals(username) ) {
                return teacher;
            }
        }

        Teacher notfound =new Teacher(null,null,null,null,null);
        return notfound;
    }

    public static Student StudentLog(String username, String password) throws Exception {
        List<Student> students = getAllStudents();
        //System.out.println(teachers);
        for (Student student : students) {
            if (password.equals(student.getPassWord()) && student.getUserName().equals(username) ) {
                return student;
            }
        }

        Student notfound =new Student(null,null,null,null,null);
        return notfound;
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
        Question Num1 = new Question("How??","","","","","");
        Question Num2 =new Question("What?","","","","","");
        LinkedList<Question> questions1 =new LinkedList<>();
        questions1.add(Num1);
        questions1.add(Num2);
        SubjectTeacher Grammar = new SubjectTeacher("Grammar",questions1);
        SubjectTeacher Geometry = new SubjectTeacher("Geometry",questions1);
        SubjectTeacher algebra = new SubjectTeacher("Algebra",questions1);
        SubjectTeacher comprehension = new SubjectTeacher("comprehension",questions1);
        LinkedList<SubjectTeacher> subject1=new LinkedList<>();
        subject1.add(Grammar);
        subject1.add(comprehension);
        CourseTeacher English =new CourseTeacher("English",subject1);
        LinkedList<SubjectTeacher> subject2=new LinkedList<>();
        subject2.add(algebra);
        subject2.add(Geometry);
        CourseTeacher Math =new CourseTeacher("Math",subject2);
        LinkedList<CourseTeacher> courses = new LinkedList<>();
        courses.add(English);
        Teacher mona = new Teacher("Mona","Amara","mona123","1234",courses);
        courses.add(Math);
        Teacher noran = new Teacher("Noran","morad","noran123","1235",courses);

        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            System.err.println("Generated starts ...");

            session.saveOrUpdate(Num1);
            session.saveOrUpdate(Num2);
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
    /*public static void generateEnglishQusetions() throws Exception {
        Question Num1 = new Question("How??");
        Question Num2 =new Question("What?");
        LinkedList<Question> questions1 =new LinkedList<>();
        questions1.add(Num1);
        questions1.add(Num2);
        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            System.err.println("Generated starts ...");
            session.saveOrUpdate(Num1);
            session.saveOrUpdate(Num2);
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
    }*/

}
