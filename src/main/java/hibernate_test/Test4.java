package hibernate_test;

import hibernate_test.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Test4 {
    public static void main(String[] args) {
        try (
                SessionFactory factory = new Configuration()
                        .configure("hib.conf.xml")
                        .addAnnotatedClass(Employee.class)
                        .buildSessionFactory();
                Session session = factory.getCurrentSession()
        ) {
            session.beginTransaction();
//            Employee alexey = session.get(Employee.class, 0);
//            alexey.setSalary(500);

            session.createQuery("update Employee set salary = 1000 where name = 'Alexander'").executeUpdate();

            session.getTransaction().commit();
            System.out.println("Done!");
        }

    }
}
