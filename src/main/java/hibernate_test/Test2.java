package hibernate_test;

import hibernate_test.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test2 {
    public static void main(String[] args) {
        try (
                SessionFactory factory = new Configuration()
                        .configure("hib.conf.xml")
                        .addAnnotatedClass(Employee.class)
                        .buildSessionFactory();
                Session session = factory.getCurrentSession()
        ) {


            session.beginTransaction();
            System.out.println(session.get(Employee.class, 14));
            session.getTransaction().commit();
        }

    }
}
