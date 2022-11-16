package hibernate_test;

import hibernate_test.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test5 {
    public static void main(String[] args) {
        try (
                SessionFactory factory = new Configuration()
                        .configure("hib.conf.xml")
                        .addAnnotatedClass(Employee.class)
                        .buildSessionFactory();
                Session session = factory.getCurrentSession()
        ) {
            session.beginTransaction();
//            Employee employee = session.get(Employee.class, 18);
//            session.delete(employee);

            session.createQuery("delete Employee where name = 'Alexander'").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Done!");
        }

    }
}
