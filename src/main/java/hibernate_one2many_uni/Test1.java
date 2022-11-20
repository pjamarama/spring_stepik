package hibernate_one2many_uni;

import hibernate_one2many_bi.entity.Department;
import hibernate_one2many_bi.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test1 {
    public static void main(String[] args) {
        try (
                SessionFactory factory = new Configuration()
                        .configure("hib.conf.xml")
                        .addAnnotatedClass(Employee.class)
                        .addAnnotatedClass(Department.class)
                        .buildSessionFactory();
                Session session = factory.getCurrentSession();
        ) {
            session.beginTransaction();
            Employee employee = session.get(Employee.class, 4);
            session.delete(employee);

            session.getTransaction().commit();
            System.out.println("Done!");
        }
    }
}
