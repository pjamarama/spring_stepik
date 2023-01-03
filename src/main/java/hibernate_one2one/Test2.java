package hibernate_one2one;

import hibernate_one2one.entity.Detail;
import hibernate_one2one.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test2 {
    public static void main(String[] args) {
        try (
                SessionFactory factory = new Configuration()
                        .configure("hib.conf.xml")
                        .addAnnotatedClass(Employee.class)
                        .addAnnotatedClass(Detail.class)
                        .buildSessionFactory();
                Session session = factory.getCurrentSession()
        ) {
            session.beginTransaction();
            Detail detail = session.get(Detail.class, 3);
            detail.getEmployee().setEmpDetail(null);
            session.delete(detail);
            session.getTransaction().commit();
            System.out.println("Done!");
        }
    }
}
