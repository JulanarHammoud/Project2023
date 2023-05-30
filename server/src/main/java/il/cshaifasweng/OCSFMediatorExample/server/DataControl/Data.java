package il.cshaifasweng.OCSFMediatorExample.server.DataControl;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import il.cshaifasweng.OCSFMediatorExample.entities.Student;
import il.cshaifasweng.OCSFMediatorExample.entities.Subject;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;
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
        configuration.addAnnotatedClass(Subject.class);
        configuration.addAnnotatedClass(Teacher.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        return configuration.buildSessionFactory(serviceRegistry); }
    public static void generateStusent() throws Exception {
        Student julanar = new Student("Julanar", 95, 98);
        Student rozaleen = new Student("Rozaleen", 99, 100);
        Student lana = new Student("Lana", 100, 100);
        Student reem = new Student("Reem", 100, 100);
        Student israa = new Student("Israa", 100, 100);
        Student yaman = new Student("Yaman", 80, 95);
        Student reman = new Student("Reman", 90, 95);
        Student marah = new Student("Marah", 100, 95);
        Student sarah = new Student("Sarah", 100, 95);
        Student nameer = new Student("Nameer", 100, 95);




        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            System.err.println("Generated starts ...");


            session.saveOrUpdate(julanar);
            session.saveOrUpdate(rozaleen);
            session.saveOrUpdate(lana);
            session.saveOrUpdate(reem);
            session.saveOrUpdate(israa);
            session.saveOrUpdate(yaman);
            session.saveOrUpdate(reman);
            session.saveOrUpdate(marah);
            session.saveOrUpdate(sarah);
            session.saveOrUpdate(nameer);
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

    public static Student getStudent(int id) throws Exception {
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        Student student =session.get(Student.class,id);
        session.close();
      //  System.out.println(student.getSt_name());
        return student;
    }

    public static void updateGrade(int newGrade , int currentid, int Grade){
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

    }

    public static void printAllStudents() throws Exception {
        List<Student> students = getAllStudents();
        for (Student student : students) {
            System.out.print("Id: ");
            System.out.print(student.getId());
            System.out.print(", student name: ");
            System.out.print(student.getSt_name());
            System.out.print(", Grade1: ");
            System.out.print(student.getGrade1());
            System.out.print(", Grade2: ");
            System.out.print(student.getGrade2());
            System.out.print('\n');
        }
    }

    public static void generateSubject() throws Exception {
        Subject English = new Subject("English");
        Subject History = new Subject("History");
        Subject Math = new Subject("Math");
        Subject Arabic = new Subject("Arabic");
        Subject Hebrew = new Subject("Hebrew");
        LinkedList<Subject> subject1=new LinkedList<>();
        subject1.add(English);
        subject1.add(Math);
        LinkedList<Subject> subject2=new LinkedList<>();
        subject2.add(History);
        Teacher mona = new Teacher("Mona","Amara","mona123","1234",subject1);
        Teacher noran = new Teacher("Noran","morad","noran123","1235",subject2);
        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            System.err.println("Generated starts ...");


            session.saveOrUpdate(English);
            session.saveOrUpdate(History);
            session.saveOrUpdate(Hebrew);
            session.saveOrUpdate(Math);
            session.saveOrUpdate(Arabic);
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
}
