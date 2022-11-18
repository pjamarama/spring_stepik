package hibernate_one2one;

import hibernate_one2one.entity.Employee;
import hibernate_one2one.entity.Detail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test1 {
    public static void main(String[] args) {
        try (
                SessionFactory factory = new Configuration()
                        .configure("hib.conf.xml")
                        .addAnnotatedClass(Employee.class)
                        .addAnnotatedClass(Detail.class)
                        .buildSessionFactory();
                Session session = factory.getCurrentSession();
        ) {


//            Employee employee = new Employee("Alexey", "Grokhotov", "IT", 300);
//            Detail detail = new Detail("Birmingham", "88005553535", "superadmin@yandex.ru");
//            employee.setEmpDetail(detail);

//            get Employee and its details
            session.beginTransaction();
            Employee employee = session.get(Employee.class, 2);
            session.delete(employee);
            session.getTransaction().commit();
            System.out.println("Done!");
        }
    }
}
