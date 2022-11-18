package hibernate_one2many_bi;

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
            Department department = new Department("IT", 300, 1200);
            Employee employee1 = new Employee("Alexey", "Grokhotov", 800);
            Employee employee2 = new Employee("Elena", "Smirnova", 750);

            department.addEmployeeToDepartment(employee1);
            department.addEmployeeToDepartment(employee2);

            session.beginTransaction();
            session.save(department);
            session.getTransaction().commit();
            System.out.println("Done!");
        }
    }
}
