package il.cshaifasweng.OCSFMediatorExample.server.DataControl;
import java.sql.PreparedStatement;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import il.cshaifasweng.OCSFMediatorExample.entities.Student;
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
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        return configuration.buildSessionFactory(serviceRegistry); }
    public static void generateStusent() throws Exception {
        Student julnar = new Student("julnar", 100, 100);
        Student rozaleen = new Student("rozaleen", 100, 100);
        Student lana = new Student("lana", 100, 100);
        Student reem = new Student("reem", 100, 100);
        Student israa = new Student("israa", 100, 100);


        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            System.err.println("Generated starts ...");


            session.saveOrUpdate(julnar);
            session.saveOrUpdate(rozaleen);
            session.saveOrUpdate(lana);
            session.saveOrUpdate(reem);
            session.saveOrUpdate(israa);
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
    public static void printAllStudents() throws Exception {
        // System.out.print("11111 whyyyyyyyyyyyyyy!!!!!!");
        List<Student> students = getAllStudents();
        //System.out.print("22222 whyyyyyyyyyyyyyy!!!!!!");
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
}
