package hibernate_test;

import hibernate_test.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Test3 {
    public static void main(String[] args) {
        try (
                SessionFactory factory = new Configuration()
                        .configure("hib.conf.xml")
                        .addAnnotatedClass(Employee.class)
                        .buildSessionFactory();
                Session session = factory.getCurrentSession()
        ) {
            session.beginTransaction();
            List<Employee> employeeList = session
                    .createQuery("from Employee where name = 'Alexander' and salary > 100")
                    .getResultList();

            for (Employee employee : employeeList) {
                System.out.println(employee);
            }

            session.getTransaction().commit();
            System.out.println("Done!");
        }

    }
}
