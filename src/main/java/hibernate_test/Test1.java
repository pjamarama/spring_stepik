package hibernate_test;

import hibernate_test.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test1 {
    public static void main(String[] args) {

        try (
                SessionFactory factory = new Configuration()
                .configure("hib.conf.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory()
        ) {
            Session session = factory.getCurrentSession();
            Employee employee = new Employee("Robert", "Foster", "Management", 240);

            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
            System.out.println(employee);
        }
    }
}
