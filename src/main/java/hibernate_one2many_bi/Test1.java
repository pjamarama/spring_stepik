package hibernate_one2many_bi;


import hibernate_one2many_uni.enitiy.Department;
import hibernate_one2many_uni.enitiy.Employee;
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
            Department department = new Department("HR", 500, 1000);
            Employee employee1 = new Employee("Andrey", "Ivanov", 800);
            Employee employee2 = new Employee("Elena", "Sidorova", 750);

            department.addEmployeeToDepartment(employee1);
            department.addEmployeeToDepartment(employee2);

            session.beginTransaction();
//            Department department = session.get(Department.class, 2);
//            session.delete(department);
            session.save(department);
            session.getTransaction().commit();
            System.out.println("Done!");
        }
    }
}
